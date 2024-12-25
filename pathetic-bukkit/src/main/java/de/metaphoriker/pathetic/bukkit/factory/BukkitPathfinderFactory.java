package de.metaphoriker.pathetic.bukkit.factory;

import de.metaphoriker.pathetic.api.pathing.Pathfinder;
import de.metaphoriker.pathetic.api.pathing.PathfinderFactory;
import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;
import de.metaphoriker.pathetic.api.provider.NavigationPointProvider;
import de.metaphoriker.pathetic.bukkit.provider.FailingNavigationPointProvider;
import de.metaphoriker.pathetic.bukkit.provider.LoadingNavigationPointProvider;
import de.metaphoriker.pathetic.engine.pathfinder.AStarPathfinder;

public class BukkitPathfinderFactory extends PathfinderFactory<BukkitPathfinderInitializer> {

  /**
   * Creates a new {@link PathfinderFactory} with the given initializer.
   */
  public BukkitPathfinderFactory() {
    super(new BukkitPathfinderInitializer());
  }

  @Override
  public Pathfinder createPathfinder(PathfinderConfiguration configuration) {
    Pathfinder pathfinder = new AStarPathfinder(getBlockProvider(configuration), configuration);
    initializer.initialize(pathfinder, configuration);
    return pathfinder;
  }

  private NavigationPointProvider getBlockProvider(PathfinderConfiguration configuration) {
    return configuration.isLoadingChunks()
        ? new LoadingNavigationPointProvider()
        : new FailingNavigationPointProvider();
  }
}
