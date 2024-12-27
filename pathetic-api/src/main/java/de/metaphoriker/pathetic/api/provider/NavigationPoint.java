package de.metaphoriker.pathetic.api.provider;

/**
 * Represents information about a position in the pathfinding environment.
 */
public interface NavigationPoint {

  /**
   * Checks if the block is traversable.
   *
   * @return true if the block is traversable, false otherwise
   */
  boolean isTraversable();
}
