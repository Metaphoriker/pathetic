package de.metaphoriker.pathetic.api.wrapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/** A Class to represent a block in the world, except exempt of Bukkit */
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public final class PathBlock {

  private final PathPosition pathPosition;
  private final BlockInformation blockInformation;

  /**
   * @return Whether the block is possible to walk through
   * @deprecated Use {@link BlockInformation#isPassable()} instead
   */
  @Deprecated
  public boolean isPassable() {
    return blockInformation.isPassable();
  }

  /**
   * Gets the X coordinate of the block
   *
   * @return The X coordinate of the block
   */
  public int getBlockX() {
    return this.pathPosition.getBlockX();
  }

  /**
   * Gets the Y coordinate of the block
   *
   * @return The Y coordinate of the block
   */
  public int getBlockY() {
    return this.pathPosition.getBlockY();
  }

  /**
   * Gets the Z coordinate of the block
   *
   * @return The Z coordinate of the block
   */
  public int getBlockZ() {
    return this.pathPosition.getBlockZ();
  }
}
