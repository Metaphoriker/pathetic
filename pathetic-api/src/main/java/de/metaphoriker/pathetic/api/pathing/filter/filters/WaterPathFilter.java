package de.metaphoriker.pathetic.api.pathing.filter.filters;

import lombok.NonNull;
import org.bukkit.Material;
import de.metaphoriker.pathetic.api.pathing.filter.PathValidationContext;
import de.metaphoriker.pathetic.api.pathing.filter.PathFilter;
import de.metaphoriker.pathetic.api.provider.BlockProvider;
import de.metaphoriker.pathetic.api.wrapper.PathPosition;

/** A PathFilter implementation that determines if a path is through water. */
public class WaterPathFilter implements PathFilter {

  @Override
  public boolean filter(@NonNull PathValidationContext pathValidationContext) {
    BlockProvider blockProvider = pathValidationContext.getBlockProvider();
    PathPosition pathPosition = pathValidationContext.getPosition();

    return blockProvider.getBlock(pathPosition).getBlockInformation().getMaterial()
        == Material.WATER;
  }
}
