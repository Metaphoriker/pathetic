package org.patheloper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.patheloper.bukkit.listeners.ChunkInvalidateListener;
import org.patheloper.util.BukkitVersionUtil;
import org.patheloper.util.ErrorLogger;

@UtilityClass
public class Pathetic {

  private static final String PROPERTIES_FILE = "pathetic.properties";

  private static JavaPlugin instance;
  @Getter private static String modelVersion;

  /**
   * @throws IllegalStateException If an attempt is made to initialize more than 1 time
   */
  public static void initialize(JavaPlugin javaPlugin) {

    if (instance != null) throw ErrorLogger.logFatalError("Can't be initialized twice");

    instance = javaPlugin;
    Bukkit.getPluginManager().registerEvents(new ChunkInvalidateListener(), javaPlugin);

    BStatsHandler.init(javaPlugin);

    loadModelVersion();

    if (BukkitVersionUtil.getVersion().isUnder(13, 0))
      javaPlugin
          .getLogger()
          .warning(
              "pathetic is currently running in a version older than 1.13. "
                  + "Some functionalities might not be accessible, such as accessing the BlockState of certain blocks.");

    javaPlugin.getLogger().info("pathetic successfully initialized");
  }

  public static boolean isInitialized() {
    return instance != null;
  }

  public static JavaPlugin getPluginInstance() {
    return instance;
  }

  private static void loadModelVersion() {
    try (InputStream inputStream =
        Pathetic.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
      Properties properties = new Properties();
      properties.load(inputStream);

      modelVersion = properties.getProperty("model.version");
    } catch (IOException e) {
      throw ErrorLogger.logFatalError("Error loading model version", e);
    }
  }
}
