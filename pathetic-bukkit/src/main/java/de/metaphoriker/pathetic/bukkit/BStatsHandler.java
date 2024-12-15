package de.metaphoriker.pathetic.bukkit;

import de.metaphoriker.pathetic.Pathetic;
import lombok.experimental.UtilityClass;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bstats.charts.SingleLineChart;
import org.bukkit.plugin.java.JavaPlugin;

@UtilityClass
public class BStatsHandler {

  private Metrics metrics;

  private int paths;

  private void init(JavaPlugin javaPlugin) {
    if (metrics != null) return;

    metrics = new Metrics(javaPlugin, 20529);
    metrics.addCustomChart(
        new SingleLineChart(
            "total_paths",
            () -> {
              int totalPaths = paths;
              paths = 0;
              return totalPaths;
            }));
    metrics.addCustomChart(new SimplePie("pathetic-engine_version", Pathetic::getEngineVersion));
  }

  private void makeSureBStatsIsInitialized() {
    if (!PatheticBukkit.isInitialized())
      throw new IllegalStateException("Pathetic has not been initialized yet");

    init(PatheticBukkit.getPluginInstance());
  }

  public synchronized void increasePathCount() {
    makeSureBStatsIsInitialized();
    paths++;
  }
}
