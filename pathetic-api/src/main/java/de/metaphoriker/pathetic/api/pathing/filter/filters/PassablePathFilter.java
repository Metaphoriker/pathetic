package de.metaphoriker.pathetic.api.pathing.filter.filters;

import de.metaphoriker.pathetic.api.pathing.filter.PathFilter;
import de.metaphoriker.pathetic.api.pathing.filter.PathValidationContext;
import de.metaphoriker.pathetic.api.provider.NavigationPoint;

/** 
 * A PathFilter implementation that determines if a path is traversable.
 * {@link NavigationPoint#isTraversable()}
 */
public class PassablePathFilter implements PathFilter {

  @Override
  public boolean filter(PathValidationContext pathValidationContext) {
    return pathValidationContext
        .getNavigationPointProvider()
        .getNavigationPoint(pathValidationContext.getPosition())
        .isTraversable();
  }
}
