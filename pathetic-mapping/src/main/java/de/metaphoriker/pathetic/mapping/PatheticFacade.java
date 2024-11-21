package de.metaphoriker.pathetic.mapping;

import de.metaphoriker.pathetic.Pathetic;
import lombok.experimental.UtilityClass;
import org.bukkit.plugin.java.JavaPlugin;

/** PatheticMapper is a utility class that maps the Pathetic API to the Pathetic Implementation. */
@UtilityClass
public class PatheticFacade {

  /**
   * @apiNote If Pathetic is not initialized yet but is used anyways, this will cause many things to
   *     break.
   * @param javaPlugin the JavaPlugin which initializes the lib
   * @throws IllegalStateException If an attempt is made to initialize more than once
   */
  public void initialize(JavaPlugin javaPlugin) {
    Pathetic.initialize(javaPlugin);
  }

  /**
   * Signals Pathetic to initiate its shutdown process, releasing resources and finalizing
   * operations. This method should be called when Pathetic is no longer needed or the plugin is
   * being disabled.
   */
  public void shutdown() {
    Pathetic.shutdown();
  }
}
