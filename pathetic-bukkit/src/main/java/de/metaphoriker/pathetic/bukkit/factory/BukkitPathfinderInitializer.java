package de.metaphoriker.pathetic.bukkit.factory;

import de.metaphoriker.pathetic.api.pathing.Pathfinder;
import de.metaphoriker.pathetic.api.pathing.PathfinderInitializer;
import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;
import de.metaphoriker.pathetic.bukkit.hook.SpigotPathfindingHook;

public class BukkitPathfinderInitializer implements PathfinderInitializer {

  @Override
  public void initialize(Pathfinder pathfinder, PathfinderConfiguration configuration) {
    pathfinder.registerPathfindingHook(new SpigotPathfindingHook());
  }
}
