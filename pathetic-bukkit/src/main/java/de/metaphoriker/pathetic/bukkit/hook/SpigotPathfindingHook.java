package de.metaphoriker.pathetic.bukkit.hook;

import de.metaphoriker.pathetic.api.pathing.hook.PathfinderHook;
import de.metaphoriker.pathetic.api.pathing.hook.PathfindingContext;
import de.metaphoriker.pathetic.api.wrapper.Depth;
import de.metaphoriker.pathetic.bukkit.util.WatchdogUtil;

public class SpigotPathfindingHook implements PathfinderHook {

  @Override
  public void onPathfindingStep(PathfindingContext pathfindingContext) {
    tickWatchdogIfNeeded(pathfindingContext.getDepth());
  }

  private void tickWatchdogIfNeeded(Depth depth) {
    if (depth.getValue() % 500 == 0) {
      WatchdogUtil.tickWatchdog();
    }
  }
}
