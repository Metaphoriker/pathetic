package xyz.ollieee.nms.v1_17;

import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.chunk.ChunkStatus;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import xyz.ollieee.api.snapshot.ChunkSnapshotGrabber;

public class OneSeventeenSnapshotGrabber implements ChunkSnapshotGrabber {


    @Override
    public ChunkSnapshot getSnapshot(World world, int chunkX, int chunkZ) {
        try {

            WorldServer server = ((CraftWorld) world).getHandle();
            CraftChunk newCraftChunk = new CraftChunk(server, chunkX, chunkZ);

            server.getChunkProvider().getChunkAt(chunkX, chunkZ, ChunkStatus.m, true);
            return newCraftChunk.getChunkSnapshot();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
