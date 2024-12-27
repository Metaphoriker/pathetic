package de.metaphoriker.pathetic.api.pathing.configuration;

/**
 * Represents a set of weights used to calculate a heuristic for the A* pathfinding algorithm. These
 * weights influence the prioritization of different path characteristics during the search.
 *
 * <p>This class defines weights for the following distance metrics:
 *
 * <ul>
 *   <li><b>Manhattan Distance:</b> Prioritizes direct movement along axes.
 *   <li><b>Octile Distance:</b> Allows for diagonal movement for finer-grained pathing.
 *   <li><b>Perpendicular Distance:</b> Penalizes deviation from the straight line to the target,
 *       aiding in smoother paths.
 *   <li><b>Height Difference:</b> Factors in elevation changes when calculating path costs.
 * </ul>
 */
public final class HeuristicWeights {

  /**
   * Provides a set of default heuristic weights that may be suitable for natural pathfinding. These
   * values can be adjusted for specific scenarios.
   */
  public static final HeuristicWeights NATURAL_PATH_WEIGHTS = create(0.3, 0.15, 0.6, 0.3);

  /**
   * Provides a set of weights strongly prioritizing the shortest direct path, even if diagonally.
   */
  public static final HeuristicWeights DIRECT_PATH_WEIGHTS = create(0.6, 0.3, 0.0, 0.1);

  /**
   * The weight applied to the Manhattan distance component of the heuristic. A higher weight
   * favours paths with a greater emphasis on direct, axis-aligned movement.
   */
  private final double manhattanWeight;

  /**
   * The weight applied to the Octile distance component of the heuristic. A higher weight allows
   * diagonal movement, enabling more flexible paths in 3D environments.
   */
  private final double octileWeight;

  /**
   * The weight applied to the perpendicular distance component of the heuristic. Increased weight
   * discourages deviations from the straight line between the start and target, resulting in
   * smoother paths.
   */
  private final double perpendicularWeight;

  /**
   * The weight applied to the height difference (elevation change) component of the heuristic. A
   * higher weight gives more consideration to vertical distance, important for terrains with
   * varying verticality.
   */
  private final double heightWeight;

  private HeuristicWeights(
      double manhattanWeight,
      double octileWeight,
      double perpendicularWeight,
      double heightWeight) {
    this.manhattanWeight = manhattanWeight;
    this.octileWeight = octileWeight;
    this.perpendicularWeight = perpendicularWeight;
    this.heightWeight = heightWeight;
  }

  public static HeuristicWeights create(
      double manhattanWeight,
      double octileWeight,
      double perpendicularWeight,
      double heightWeight) {
    return new HeuristicWeights(manhattanWeight, octileWeight, perpendicularWeight, heightWeight);
  }

  public double getManhattanWeight() {
    return this.manhattanWeight;
  }

  public double getOctileWeight() {
    return this.octileWeight;
  }

  public double getPerpendicularWeight() {
    return this.perpendicularWeight;
  }

  public double getHeightWeight() {
    return this.heightWeight;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof HeuristicWeights)) return false;
    final HeuristicWeights other = (HeuristicWeights) o;
    if (Double.compare(this.getManhattanWeight(), other.getManhattanWeight()) != 0) return false;
    if (Double.compare(this.getOctileWeight(), other.getOctileWeight()) != 0) return false;
    if (Double.compare(this.getPerpendicularWeight(), other.getPerpendicularWeight()) != 0)
      return false;
    if (Double.compare(this.getHeightWeight(), other.getHeightWeight()) != 0) return false;
    return true;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long $manhattanWeight = Double.doubleToLongBits(this.getManhattanWeight());
    result = result * PRIME + (int) ($manhattanWeight >>> 32 ^ $manhattanWeight);
    final long $octileWeight = Double.doubleToLongBits(this.getOctileWeight());
    result = result * PRIME + (int) ($octileWeight >>> 32 ^ $octileWeight);
    final long $perpendicularWeight = Double.doubleToLongBits(this.getPerpendicularWeight());
    result = result * PRIME + (int) ($perpendicularWeight >>> 32 ^ $perpendicularWeight);
    final long $heightWeight = Double.doubleToLongBits(this.getHeightWeight());
    result = result * PRIME + (int) ($heightWeight >>> 32 ^ $heightWeight);
    return result;
  }

  public String toString() {
    return "HeuristicWeights(manhattanWeight="
        + this.getManhattanWeight()
        + ", octileWeight="
        + this.getOctileWeight()
        + ", perpendicularWeight="
        + this.getPerpendicularWeight()
        + ", heightWeight="
        + this.getHeightWeight()
        + ")";
  }
}
