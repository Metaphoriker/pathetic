package de.metaphoriker.pathetic.api.pathing.filter.filters;

import lombok.NonNull;
import de.metaphoriker.pathetic.api.pathing.filter.PathValidationContext;
import de.metaphoriker.pathetic.api.pathing.filter.PathFilter;
import de.metaphoriker.pathetic.api.wrapper.PathBlock;

/**
 * A PathFilter implementation that determines if a path is passable.
 *
 * @see PathBlock#isPassable()
 */
public class PassablePathFilter implements PathFilter {

  @Override
  public boolean filter(@NonNull PathValidationContext pathValidationContext) {
    return pathValidationContext
        .getBlockProvider()
        .getBlock(pathValidationContext.getPosition())
        .isPassable();
  }
}
