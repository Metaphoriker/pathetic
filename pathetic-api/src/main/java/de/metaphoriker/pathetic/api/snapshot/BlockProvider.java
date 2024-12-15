package de.metaphoriker.pathetic.api.snapshot;

import de.metaphoriker.pathetic.api.wrapper.PathBlock;
import de.metaphoriker.pathetic.api.wrapper.PathPosition;

/**
 * The BlockProvider interface defines methods for retrieving block data snapshots at specific
 * positions within a Minecraft world.
 */
public interface BlockProvider {

  /**
   * Gets the block at the given position
   *
   * @api.Note If the pathfinder is not permitted to load chunks, this method will return null if
   *     the chunk is not loaded.
   * @param position the position to get as block-form
   * @return {@link PathBlock} the block.
   */
  PathBlock getBlock(PathPosition position);

  /**
   * Gets the highest block at the given position
   *
   * @api.Note If the pathfinder is not permitted to load chunks, this method will return null if
   *     the chunk is not loaded.
   * @param position the position to get as block-form
   * @return {@link PathBlock} the block.
   */
  PathBlock getHighestBlockAt(PathPosition position);
}
