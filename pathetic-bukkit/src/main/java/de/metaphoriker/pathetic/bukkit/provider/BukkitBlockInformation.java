package de.metaphoriker.pathetic.bukkit.provider;

import de.metaphoriker.pathetic.api.wrapper.BlockInformation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;

@Getter
@RequiredArgsConstructor
public class BukkitBlockInformation implements BlockInformation {

  private final Material material;
  private final BlockState blockState;

  @Override
  public boolean isPassable() {
    return !material.isSolid();
  }
}
