package de.metaphoriker.pathetic.example;

import de.metaphoriker.pathetic.api.pathing.Pathfinder;
import de.metaphoriker.pathetic.api.pathing.PathfinderFactory;
import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;
import de.metaphoriker.pathetic.bukkit.PatheticBukkit;
import de.metaphoriker.pathetic.bukkit.factory.BukkitPathfinderFactory;
import de.metaphoriker.pathetic.bukkit.factory.BukkitPathfinderInitializer;
import de.metaphoriker.pathetic.example.command.PatheticCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class PatheticPlugin extends JavaPlugin {

  // Called when the plugin is enabled
  @Override
  public void onEnable() {

    // Initialize Pathetic with this plugin instance
    PatheticBukkit.initialize(this);

    // Create the respective Pathfinder Factory with the respective initializer
    PathfinderFactory<BukkitPathfinderInitializer> factory = new BukkitPathfinderFactory();

    // Create a new Pathfinder instance with a custom configuration
    Pathfinder reusablePathfinder =
        factory.createPathfinder( // Use the factory to create a new pathfinder instance
            PathfinderConfiguration.builder()
                .fallback(true) // Allow fallback strategies if the primary fails
                .loading(true) // Allow chunks to be loaded during pathfinding
                .build() // Build the configuration()
            );

    // Register the command executor for the "pathetic" command
    getCommand("pathetic").setExecutor(new PatheticCommand(reusablePathfinder));
  }
}
