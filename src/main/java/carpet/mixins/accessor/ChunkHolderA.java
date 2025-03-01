package carpet.mixins.accessor;

import net.minecraft.server.ChunkHolder;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChunkHolder.class)
public interface ChunkHolderA {
    @Accessor("blocksChanged")
    int getBlocksChanged();

    @Accessor("chunk")
    WorldChunk getChunk();

    @Accessor("chunk")
    void setChunk(WorldChunk worldChunk);

    @Accessor("populated")
    void setPopulated(boolean bl);
}
