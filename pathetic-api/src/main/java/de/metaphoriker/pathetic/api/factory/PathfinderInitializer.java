package de.metaphoriker.pathetic.api.factory;

import de.metaphoriker.pathetic.api.pathing.Pathfinder;
import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;

/**
 * An interface for initializing {@link Pathfinder} instances.
 */
public interface PathfinderInitializer {

  /**
   * Initializes the given {@link Pathfinder} with the given {@link PathfinderConfiguration}.
   *
   * @param pathfinder The pathfinder to initialize.
   * @param configuration The configuration for the pathfinder.
   */
  void initialize(Pathfinder pathfinder, PathfinderConfiguration configuration);
}
