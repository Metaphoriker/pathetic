package de.metaphoriker.pathetic.bukkit.provider;

import de.metaphoriker.pathetic.api.provider.NavigationPoint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;

@Getter
@RequiredArgsConstructor
public class BukkitNavigationPoint implements NavigationPoint {

  private final Material material;
  private final BlockState blockState;

  @Override
  public boolean isTraversable() {
    return !material.isSolid();
  }
}
