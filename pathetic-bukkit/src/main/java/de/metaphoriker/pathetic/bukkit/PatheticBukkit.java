package de.metaphoriker.pathetic.bukkit;

import com.google.common.eventbus.Subscribe;
import de.metaphoriker.pathetic.Pathetic;
import de.metaphoriker.pathetic.api.event.EventPublisher;
import de.metaphoriker.pathetic.api.event.PathingStartFindEvent;
import de.metaphoriker.pathetic.bukkit.listener.ChunkInvalidateListener;
import de.metaphoriker.pathetic.bukkit.util.BukkitVersionUtil;
import de.metaphoriker.pathetic.util.ErrorLogger;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Slf4j
@UtilityClass
public class PatheticBukkit {

  private static JavaPlugin instance;

  /**
   * @throws IllegalStateException If an attempt is made to initialize more than 1 time
   */
  public static void initialize(JavaPlugin javaPlugin) {

    if (instance != null) throw ErrorLogger.logFatalError("Can't be initialized twice");

    instance = javaPlugin;
    Bukkit.getPluginManager().registerEvents(new ChunkInvalidateListener(), javaPlugin);

    registerPathingStartListener();
    Pathetic.loadEngineVersion();

    if (BukkitVersionUtil.getVersion().isUnder(16, 0)
        || BukkitVersionUtil.getVersion().isEqual(BukkitVersionUtil.Version.of(16, 0))) {
      log.warn(
          "Pathetic is currently running in a version older than or equal to 1.16. "
              + "Some functionalities might not be accessible, such as accessing the BlockState of blocks.");
    }

    log.debug("Pathetic v{} initialized", Pathetic.getEngineVersion());
  }

  public static boolean isInitialized() {
    return instance != null;
  }

  public static JavaPlugin getPluginInstance() {
    return instance;
  }

  private static void registerPathingStartListener() {
    EventPublisher.registerListener(
        new Object() {
          @Subscribe
          public void onPathingStart(PathingStartFindEvent event) {
            pushToBStatsIfActivated(event);
          }

          private void pushToBStatsIfActivated(PathingStartFindEvent event) {
            if (event.getPathfinderConfiguration().isBStats()) {
              BStatsHandler.increasePathCount();
            }
          }
        });
  }
}
