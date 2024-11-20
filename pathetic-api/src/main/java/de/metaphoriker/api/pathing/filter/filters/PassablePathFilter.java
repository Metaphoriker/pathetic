package de.metaphoriker.api.pathing.filter.filters;

import lombok.NonNull;
import de.metaphoriker.api.pathing.filter.PathValidationContext;
import de.metaphoriker.api.pathing.filter.PathFilter;
import de.metaphoriker.api.wrapper.PathBlock;

/**
 * A PathFilter implementation that determines if a path is passable.
 *
 * @see PathBlock#isPassable()
 */
public class PassablePathFilter implements PathFilter {

  @Override
  public boolean filter(@NonNull PathValidationContext pathValidationContext) {
    return pathValidationContext
        .getSnapshotManager()
        .getBlock(pathValidationContext.getPosition())
        .isPassable();
  }
}
