package de.metaphoriker.pathetic.api.pathing.initializer;

import de.metaphoriker.pathetic.api.pathing.Pathfinder;
import de.metaphoriker.pathetic.api.pathing.PathfinderInitializer;
import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;

/**
 * A placeholder implementation of the {@link PathfinderInitializer} interface.
 * This class does nothing and is used when no specific initialization is required for the {@link Pathfinder}.
 */
public class StandardPathfinderInitializer implements PathfinderInitializer {

  /**
   * Initializes the given {@link Pathfinder} with the provided {@link PathfinderConfiguration}.
   * This implementation does nothing and serves as a placeholder.
   *
   * @param pathfinder the pathfinder to initialize
   * @param configuration the configuration to use for initialization
   */
  @Override
  public void initialize(Pathfinder pathfinder, PathfinderConfiguration configuration) {
    // Do nothing
  }
}
