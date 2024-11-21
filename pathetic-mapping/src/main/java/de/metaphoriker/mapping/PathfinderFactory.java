package de.metaphoriker.mapping;

import de.metaphoriker.Pathetic;
import de.metaphoriker.api.pathing.Pathfinder;
import de.metaphoriker.api.pathing.configuration.PathfinderConfiguration;
import de.metaphoriker.model.pathing.pathfinder.AStarPathfinder;
import de.metaphoriker.util.ErrorLogger;

/** A factory class for creating {@link Pathfinder} instances. */
public final class PathfinderFactory {

  private PathfinderFactory() {}

  /**
   * Creates a new {@link Pathfinder} instance.
   *
   * @param pathfinderConfiguration The configuration for the pathfinder.
   * @return A new {@link Pathfinder} instance.
   * @throws IllegalStateException If Pathetic is not initialized.
   */
  public static Pathfinder createPathfinder(PathfinderConfiguration pathfinderConfiguration) {
    if (!Pathetic.isInitialized()) {
      throw ErrorLogger.logFatalError("Pathetic is not initialized yet.");
    }
    return new AStarPathfinder(pathfinderConfiguration);
  }
}
