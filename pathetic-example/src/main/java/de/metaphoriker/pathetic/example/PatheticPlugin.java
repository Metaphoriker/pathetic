package de.metaphoriker.pathetic.example;

import de.metaphoriker.pathetic.api.pathing.Pathfinder;
import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;
import de.metaphoriker.pathetic.example.command.PatheticCommand;
import de.metaphoriker.pathetic.mapping.PatheticFacade;
import de.metaphoriker.pathetic.mapping.PathfinderFactory;
import org.bukkit.plugin.java.JavaPlugin;

public final class PatheticPlugin extends JavaPlugin {

  // Called when the plugin is enabled
  @Override
  public void onEnable() {

    // Initialize the PatheticMapper with this plugin instance
    PatheticFacade.initialize(this);

    // Create a new Pathfinder instance with a custom configuration
    Pathfinder reusablePathfinder =
        PathfinderFactory.createPathfinder( // Use the factory to create a new pathfinder instance
            PathfinderConfiguration.createConfiguration()
                .withAllowingFallback(true) // Allow fallback strategies if the primary fails
                .withLoadingChunks(true) // Allow chunks to be loaded during pathfinding
            );

    // Register the command executor for the "pathetic" command
    getCommand("pathetic").setExecutor(new PatheticCommand(reusablePathfinder));
  }

  // Called when the plugin is disabled
  @Override
  public void onDisable() {
    // Shutdown the PatheticMapper to clear any resources it holds
    PatheticFacade.shutdown();
  }
}
