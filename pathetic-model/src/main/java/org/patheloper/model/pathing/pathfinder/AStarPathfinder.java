package org.patheloper.model.pathing.pathfinder;

import java.util.*;
import org.jheaps.tree.FibonacciHeap;
import org.patheloper.api.pathing.configuration.PathfinderConfiguration;
import org.patheloper.api.pathing.filter.FilterAggregator;
import org.patheloper.api.pathing.filter.FilterOutcome;
import org.patheloper.api.pathing.filter.FilterResult;
import org.patheloper.api.pathing.filter.PathFilter;
import org.patheloper.api.pathing.filter.PathFilterStage;
import org.patheloper.api.pathing.filter.PathValidationContext;
import org.patheloper.api.wrapper.PathPosition;
import org.patheloper.api.wrapper.PathVector;
import org.patheloper.model.pathing.Node;
import org.patheloper.model.pathing.Offset;
import org.patheloper.util.ExpiringHashMap;
import org.patheloper.util.GridRegionData;
import org.patheloper.util.Tuple3;
import org.patheloper.util.WatchdogUtil;

public class AStarPathfinder extends AbstractPathfinder {

  private static final int DEFAULT_GRID_CELL_SIZE = 12;
  private static final int PRIORITY_BOOST_IN_PERCENTAGE = 80;

  /**
   * The grid map used to store the regional examined positions and Bloom filters for each grid
   * region.
   */
  private final Map<Tuple3<Integer>, ExpiringHashMap.Entry<GridRegionData>> gridMap =
      new ExpiringHashMap<>();

  public AStarPathfinder(PathfinderConfiguration pathfinderConfiguration) {
    super(pathfinderConfiguration);
  }

  @Override
  protected void tick(
      PathPosition start,
      PathPosition target,
      Node currentNode,
      Depth depth,
      FibonacciHeap<Double, Node> nodeQueue,
      Set<PathPosition> examinedPositions,
      List<PathFilter> filters,
      List<PathFilterStage> filterStages) {

    tickWatchdogIfNeeded(depth);

    evaluateNewNodes(
        nodeQueue,
        examinedPositions,
        currentNode,
        filters,
        filterStages,
        this.pathfinderConfiguration.isAllowingDiagonal());
    depth.increment();
  }

  private void tickWatchdogIfNeeded(Depth depth) {
    if (depth.getDepth() % 500 == 0) {
      WatchdogUtil.tickWatchdog();
    }
  }

  private void evaluateNewNodes(
      FibonacciHeap<Double, Node> nodeQueue,
      Set<PathPosition> examinedPositions,
      Node currentNode,
      List<PathFilter> filters,
      List<PathFilterStage> filterStages,
      boolean allowingDiagonal) {

    Collection<Node> newNodes =
        fetchValidNeighbours(
            examinedPositions, currentNode, filters, filterStages, allowingDiagonal, nodeQueue);

    for (Node newNode : newNodes) {
      double nodeCost = newNode.getFCostCache().get();
      if (pathfinderConfiguration.isPrioritizing()) {
        double priorityAdjustment = calculatePriorityAdjustment(newNode, filterStages);
        nodeCost -= priorityAdjustment;
      }
      nodeQueue.insert(nodeCost, newNode);
    }
  }

  private double calculatePriorityAdjustment(Node node, List<PathFilterStage> filterStages) {
    double priorityAdjustment = 0.0;

    PathValidationContext context =
        new PathValidationContext(
            node.getPosition(),
            node.getParent() != null ? node.getParent().getPosition() : null,
            snapshotManager);

    for (PathFilterStage filterStage : filterStages) {
      FilterAggregator aggregator = new FilterAggregator(filterStage.getFilters());
      FilterOutcome outcome = aggregator.aggregate(context);

      if (outcome.getResult() == FilterResult.PASS) {
        priorityAdjustment = node.getHeuristic().get() * (PRIORITY_BOOST_IN_PERCENTAGE / 100.0);
        break;
      }

      if (outcome.getResult() == FilterResult.WARNING) {
        priorityAdjustment =
            node.getHeuristic().get() * (((double) PRIORITY_BOOST_IN_PERCENTAGE / 2) / 100.0);
      }
    }

    return priorityAdjustment;
  }

  private boolean isNodeValid(
      Node currentNode,
      Node newNode,
      Set<PathPosition> examinedPositions,
      List<PathFilter> filters,
      List<PathFilterStage> filterStages,
      boolean allowingDiagonal,
      FibonacciHeap<Double, Node> nodeQueue) {

    if (isNodeInvalid(newNode, filters, filterStages, nodeQueue)) return false;

    if (!allowingDiagonal) return examinedPositions.add(newNode.getPosition());

    if (!isDiagonalMove(currentNode, newNode)) return examinedPositions.add(newNode.getPosition());

    return isReachable(currentNode, newNode, filters, filterStages)
        && examinedPositions.add(newNode.getPosition());
  }

  private boolean isDiagonalMove(Node from, Node to) {
    int xDifference = Math.abs(from.getPosition().getBlockX() - to.getPosition().getBlockX());
    int zDifference = Math.abs(from.getPosition().getBlockZ() - to.getPosition().getBlockZ());

    return xDifference != 0 && zDifference != 0;
  }

  /**
   * Returns whether the diagonal jump is possible by checking if the adjacent nodes are passable or
   * not. With adjacent nodes are the shared overlapping neighbours meant.
   */
  private boolean isReachable(
      Node from, Node to, List<PathFilter> filters, List<PathFilterStage> filterStages) {

    boolean hasYDifference = from.getPosition().getBlockY() != to.getPosition().getBlockY();
    PathVector[] offsets = Offset.VERTICAL_AND_HORIZONTAL.getVectors();

    for (PathVector vector1 : offsets) {
      if (vector1.getY() != 0) continue;

      Node neighbour1 = createNeighbourNode(from, vector1);
      for (PathVector vector2 : offsets) {
        if (vector2.getY() != 0) continue;

        Node neighbour2 = createNeighbourNode(to, vector2);
        if (neighbour1.getPosition().equals(neighbour2.getPosition())) {

          // Check if height difference is passable
          boolean heightDifferencePassable =
              isHeightDifferencePassable(from, to, vector1, hasYDifference);

          PathValidationContext context =
              new PathValidationContext(
                  neighbour1.getPosition(),
                  neighbour1.getParent() != null ? neighbour1.getParent().getPosition() : null,
                  snapshotManager);

          FilterAggregator aggregator = new FilterAggregator(filters);
          FilterOutcome outcome = aggregator.aggregate(context);

          if (outcome.getResult() == FilterResult.FAIL) {
            return false;
          }

          for (PathFilterStage stage : filterStages) {
            FilterAggregator stageAggregator = new FilterAggregator(stage.getFilters());
            FilterOutcome stageOutcome = stageAggregator.aggregate(context);

            if (stageOutcome.getResult() == FilterResult.FAIL) {
              return false;
            }

            if (stageOutcome.getUpdatedTarget() != null) {
              return true;
            }
          }

          if (heightDifferencePassable) {
            return true;
          }
        }
      }
    }

    return false;
  }

  private boolean isHeightDifferencePassable(
      Node from, Node to, PathVector vector1, boolean hasHeightDifference) {
    if (!hasHeightDifference) return true;

    int yDifference = from.getPosition().getBlockY() - to.getPosition().getBlockY();
    Node neighbour3 = createNeighbourNode(from, vector1.add(new PathVector(0, yDifference, 0)));

    return snapshotManager.getBlock(neighbour3.getPosition()).isPassable();
  }

  private Collection<Node> fetchValidNeighbours(
      Set<PathPosition> examinedPositions,
      Node currentNode,
      List<PathFilter> filters,
      List<PathFilterStage> filterStages,
      boolean allowingDiagonal,
      FibonacciHeap<Double, Node> nodeQueue) {
    Set<Node> newNodes = new HashSet<>(offset.getVectors().length);

    for (PathVector vector : offset.getVectors()) {
      Node newNode = createNeighbourNode(currentNode, vector);

      if (isNodeValid(
          currentNode,
          newNode,
          examinedPositions,
          filters,
          filterStages,
          allowingDiagonal,
          nodeQueue)) {
        newNodes.add(newNode);
      }
    }

    return newNodes;
  }

  private Node createNeighbourNode(Node currentNode, PathVector offset) {
    Node newNode =
        new Node(
            currentNode.getPosition().add(offset),
            currentNode.getStart(),
            currentNode.getTarget(),
            pathfinderConfiguration.getHeuristicWeights(),
            currentNode.getDepth() + 1);
    newNode.setParent(currentNode);
    return newNode;
  }

  /**
   * Checks if the node is invalid. A node is invalid if it is outside the world bounds or is not
   * valid according to the filters.
   */
  private boolean isNodeInvalid(
      Node node,
      List<PathFilter> filters,
      List<PathFilterStage> filterStages,
      FibonacciHeap<Double, Node> nodeQueue) {

    int gridX = node.getPosition().getBlockX() / DEFAULT_GRID_CELL_SIZE;
    int gridY = node.getPosition().getBlockY() / DEFAULT_GRID_CELL_SIZE;
    int gridZ = node.getPosition().getBlockZ() / DEFAULT_GRID_CELL_SIZE;

    // Check grid region data to see if the node has already been examined
    GridRegionData regionData =
        gridMap
            .computeIfAbsent(
                new Tuple3<>(gridX, gridY, gridZ),
                k -> new ExpiringHashMap.Entry<>(new GridRegionData()))
            .getValue();

    regionData.getRegionalExaminedPositions().add(node.getPosition());

    // If already examined, return true (invalid)
    if (regionData.getBloomFilter().mightContain(node.getPosition())) {
      if (regionData.getRegionalExaminedPositions().contains(node.getPosition())) {
        return true; // Node is invalid if already examined
      }
    }

    if (!isWithinWorldBounds(node.getPosition())) {
      return true; // Node is invalid if out of bounds
    }

    // Create validation context with initial target position
    PathValidationContext context =
        new PathValidationContext(
            node.getPosition(),
            node.getParent() != null ? node.getParent().getPosition() : null,
            snapshotManager);

    // Use FilterAggregator for shared filters
    FilterAggregator aggregator = new FilterAggregator(filters);
    FilterOutcome outcome = aggregator.aggregate(context);

    if (outcome.getResult() == FilterResult.FAIL) {
      return true; // Node is invalid if any shared filter fails
    }

    // Now use FilterAggregator for filter stages
    for (PathFilterStage stage : filterStages) {
      FilterAggregator stageAggregator = new FilterAggregator(stage.getFilters());
      FilterOutcome stageOutcome = stageAggregator.aggregate(context);

      if (stageOutcome.getResult() == FilterResult.FAIL) {
        return true; // Node is invalid if any stage filter fails
      }

      if (stageOutcome.getUpdatedTarget() != null
          && !stageOutcome.getUpdatedTarget().equals(node.getPosition())) {
        // Create a new node with the updated target position
        Node newNode =
            new Node(
                stageOutcome.getUpdatedTarget(),
                node.getStart(),
                node.getTarget(),
                node.getHeuristicWeights(),
                node.getDepth() + 1);
        newNode.setParent(node);
        double nodeCost = newNode.getFCostCache().get();
        nodeQueue.insert(nodeCost, newNode);
      }
    }

    return false; // Node is valid
  }

  private boolean isWithinWorldBounds(PathPosition position) {
    return position.getPathEnvironment().getMinHeight() < position.getBlockY()
        && position.getBlockY() < position.getPathEnvironment().getMaxHeight();
  }
}
