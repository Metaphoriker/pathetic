package de.metaphoriker.pathetic.api.pathing;

import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;

/**
 * A factory class for creating {@link Pathfinder} instances.
 *
 * @param <I> The type of {@link PathfinderInitializer} used to initialize the pathfinder.
 */
public abstract class PathfinderFactory<I extends PathfinderInitializer> {

  protected final I initializer;

  /**
   * Creates a new {@link PathfinderFactory} with the given initializer.
   *
   * @param initializer The initializer to use for initializing the pathfinder.
   */
  protected PathfinderFactory(I initializer) {
    this.initializer = initializer;
  }

  /**
   * Creates a new {@link Pathfinder} instance with the given configuration.
   *
   * @param configuration The configuration for the pathfinder.
   * @return A new {@link Pathfinder} instance.
   */
  public abstract Pathfinder createPathfinder(PathfinderConfiguration configuration);
}
