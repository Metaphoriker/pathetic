package de.metaphoriker.example;

import de.metaphoriker.api.pathing.Pathfinder;
import de.metaphoriker.api.pathing.configuration.PathfinderConfiguration;
import de.metaphoriker.example.command.PatheticCommand;
import de.metaphoriker.mapping.PatheticFacade;
import de.metaphoriker.mapping.PathfinderFactory;
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
                .withAllowingFailFast(true) // Allow pathfinding to fail fast if necessary
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
