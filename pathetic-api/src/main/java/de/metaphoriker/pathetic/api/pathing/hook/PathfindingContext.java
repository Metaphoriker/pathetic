package de.metaphoriker.pathetic.api.pathing.hook;

import de.metaphoriker.pathetic.api.wrapper.Depth;
import lombok.Value;

/**
 * Context for the current step of the pathfinding process.
 */
@Value
public class PathfindingContext {

  /**
   * The depth of the current pathfinding step.
   */
  Depth depth;
}
