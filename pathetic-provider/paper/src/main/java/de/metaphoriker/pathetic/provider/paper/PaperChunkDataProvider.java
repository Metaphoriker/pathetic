package de.metaphoriker.pathetic.provider.paper;

import org.bukkit.Bukkit;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import de.metaphoriker.pathetic.api.snapshot.ChunkDataProvider;

public class PaperChunkDataProvider implements ChunkDataProvider {
  
  @Override
  public ChunkSnapshot getSnapshot(World world, int chunkX, int chunkZ) {
    if (Bukkit.isPrimaryThread()) {
      return world.getChunkAt(chunkX, chunkZ).getChunkSnapshot();
    } else {
      return world.getChunkAtAsyncUrgently(chunkX, chunkZ).join().getChunkSnapshot();
    }
  }
  
  @Override
  public BlockState getBlockState(ChunkSnapshot snapshot, int x, int y, int z) {
    return snapshot.getBlockData(x, y, z).createBlockState();
  }
}
