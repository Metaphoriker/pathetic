package xyz.ollieee.model.snapshot;

import lombok.NonNull;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.block.Block;
import xyz.ollieee.Pathetic;
import xyz.ollieee.api.snapshot.MaterialParser;
import xyz.ollieee.api.wrapper.PathBlockType;

public class ModernMaterialParser implements MaterialParser {

    @Override
    public @NonNull Material getMaterial(@NonNull ChunkSnapshot snapshot, int x, int y, int z) {
        try {
            return snapshot.getBlockType(x, y, z);
        }catch (Exception ignored) {
            Pathetic.getPluginLogger().warning("Error Fetching block: (X: " + x + ",Y: " + y + ",Z: " + z + ")");
            return Material.STONE;
        }
    }

    @Override
    public boolean isAir(@NonNull Block block) {
        return block.getType().isAir();
    }

    @Override
    public boolean isLiquid(@NonNull Block block) {
        return block.isLiquid();
    }

    @Override
    public boolean isPassable(@NonNull Block block) {
        return block.isPassable();
    }

    @Override
    public boolean isSolid(@NonNull Block block) {
        return block.getType().isSolid();
    }

    @Override
    public boolean isSolid(@NonNull Material material) {
        return material.isSolid();
    }

    @Override
    public boolean isAir(@NonNull Material material) {
        return material.isAir();
    }

    @Override
    public PathBlockType getPathBlockType(@NonNull Material material) {

        if (isAir(material))
            return PathBlockType.AIR;

        switch (material) {
            case WATER:
            case LAVA:
                return PathBlockType.LIQUID;
            case GRASS:
            case TALL_GRASS:
                return PathBlockType.OTHER;
            default:
                return PathBlockType.SOLID;
        }
    }
}
