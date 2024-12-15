package de.metaphoriker.pathetic.api.pathing.filter.filters;

import lombok.NonNull;
import de.metaphoriker.pathetic.api.pathing.filter.PathValidationContext;
import de.metaphoriker.pathetic.api.pathing.filter.PathFilter;
import de.metaphoriker.pathetic.api.snapshot.BlockProvider;
import de.metaphoriker.pathetic.api.wrapper.PathBlock;

/**
 * A PathFilter implementation that determines if a path is on solid ground.
 */
public class SolidGroundPathFilter implements PathFilter {

  @Override
  public boolean filter(@NonNull PathValidationContext pathValidationContext) {
    BlockProvider blockProvider = pathValidationContext.getBlockProvider();
    PathBlock block = blockProvider.getBlock(pathValidationContext.getPosition());
    return hasGround(block, blockProvider);
  }

  protected boolean hasGround(PathBlock block, BlockProvider blockProvider) {
    PathBlock below = blockProvider.getBlock(block.getPathPosition().add(0, -1, 0));
    return below.isSolid();
  }
}
