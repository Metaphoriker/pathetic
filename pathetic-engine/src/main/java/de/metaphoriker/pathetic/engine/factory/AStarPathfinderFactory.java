package de.metaphoriker.pathetic.engine.factory;

import de.metaphoriker.pathetic.api.factory.PathfinderFactory;
import de.metaphoriker.pathetic.api.pathing.Pathfinder;
import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;
import de.metaphoriker.pathetic.api.provider.NavigationPointProvider;
import de.metaphoriker.pathetic.engine.pathfinder.AStarPathfinder;

public class AStarPathfinderFactory implements PathfinderFactory {

  /**
   * Creates a new {@link AStarPathfinder} instance with the given configuration. The created
   * pathfinder is initialized using the initializer provided to this factory. The {@link
   * NavigationPointProvider} is obtained from the provided {@link PathfinderConfiguration}.
   *
   * @param configuration The configuration for the pathfinder, including the navigation point
   *     provider.
   * @return A new, initialized {@link AStarPathfinder} instance.
   */
  @Override
  public Pathfinder createPathfinder(PathfinderConfiguration configuration) {
    return new AStarPathfinder(configuration.getProvider(), configuration);
  }
}
