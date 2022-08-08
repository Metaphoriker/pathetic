package xyz.ollieee.nms.v1_8;

import net.minecraft.server.v1_8_R3.Chunk;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import xyz.ollieee.api.snapshot.ChunkSnapshotGrabber;

public class OneEightSnapshotGrabber implements ChunkSnapshotGrabber {

    @Override
    public ChunkSnapshot getSnapshot(World world, int chunkX, int chunkZ) {
        try {
            WorldServer server = ((CraftWorld) world).getHandle();
            Chunk chunk = server.chunkProviderServer.getChunkAt(chunkX, chunkZ);

            return chunk.bukkitChunk.getChunkSnapshot();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
