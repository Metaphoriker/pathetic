package de.metaphoriker.pathetic.api.pathing.filter.filters;

import de.metaphoriker.pathetic.api.pathing.filter.PathValidationContext;
import de.metaphoriker.pathetic.api.pathing.filter.PathFilter;

/** A PathFilter implementation that determines if a path is passable. */
public class PassablePathFilter implements PathFilter {

  @Override
  public boolean filter(PathValidationContext pathValidationContext) {
    return pathValidationContext
        .getNavigationPointProvider()
        .getNavigationPoint(pathValidationContext.getPosition())
        .isTraversable();
  }
}
