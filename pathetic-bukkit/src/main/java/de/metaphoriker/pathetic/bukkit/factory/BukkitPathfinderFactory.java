package de.metaphoriker.pathetic.bukkit.factory;

import de.metaphoriker.pathetic.api.pathing.Pathfinder;
import de.metaphoriker.pathetic.api.pathing.PathfinderFactory;
import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;
import de.metaphoriker.pathetic.api.snapshot.BlockProvider;
import de.metaphoriker.pathetic.bukkit.provider.FailingBlockProvider;
import de.metaphoriker.pathetic.engine.pathfinder.AStarPathfinder;

public class BukkitPathfinderFactory extends PathfinderFactory<BukkitPathfinderInitializer> {

  /**
   * Creates a new {@link PathfinderFactory} with the given initializer.
   *
   * @param initializer The initializer to use for initializing the pathfinder.
   */
  public BukkitPathfinderFactory(BukkitPathfinderInitializer initializer) {
    super(initializer);
  }

  @Override
  public Pathfinder createPathfinder(PathfinderConfiguration configuration) {
    Pathfinder pathfinder = new AStarPathfinder(getBlockProvider(configuration), configuration);
    initializer.initialize(pathfinder, configuration);
    return pathfinder;
  }

  private BlockProvider getBlockProvider(PathfinderConfiguration configuration) {
    return configuration.isLoadingChunks()
        ? new FailingBlockProvider.RequestingBlockProvider()
        : new FailingBlockProvider();
  }
}
