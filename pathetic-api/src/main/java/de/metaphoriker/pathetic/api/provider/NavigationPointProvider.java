package de.metaphoriker.pathetic.api.provider;

import de.metaphoriker.pathetic.api.wrapper.NavigationPoint;
import de.metaphoriker.pathetic.api.wrapper.PathPosition;

/**
 * The NavigationPointProvider interface defines methods for retrieving navigation point data
 * at specific positions within a 3D environment.
 */
public interface NavigationPointProvider {

  /**
   * Gets the navigation point at the given position.
   *
   * @api.Note If the pathfinder is not permitted to load chunks, this method will return null if
   *     the chunk is not loaded.
   * @param position the position to get the navigation point for.
   * @return {@link NavigationPoint} the navigation point.
   */
  NavigationPoint getNavigationPoint(PathPosition position);
}
