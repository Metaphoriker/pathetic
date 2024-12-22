package de.metaphoriker.pathetic.api.wrapper;

/**
 * Represents information about a block in the pathfinding environment.
 */
public interface BlockInformation {

  /**
   * Checks if the block is passable.
   *
   * @return true if the block is passable, false otherwise
   */
  boolean isPassable();
}
