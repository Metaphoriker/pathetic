package de.metaphoriker.api.pathing.filter.filters;

import lombok.NonNull;
import org.bukkit.Material;
import de.metaphoriker.api.pathing.filter.PathValidationContext;
import de.metaphoriker.api.pathing.filter.PathFilter;
import de.metaphoriker.api.snapshot.SnapshotManager;
import de.metaphoriker.api.wrapper.PathPosition;

/** A PathFilter implementation that determines if a path is through water. */
public class WaterPathFilter implements PathFilter {

  @Override
  public boolean filter(@NonNull PathValidationContext pathValidationContext) {
    SnapshotManager snapshotManager = pathValidationContext.getSnapshotManager();
    PathPosition pathPosition = pathValidationContext.getPosition();

    return snapshotManager.getBlock(pathPosition).getBlockInformation().getMaterial()
        == Material.WATER;
  }
}
