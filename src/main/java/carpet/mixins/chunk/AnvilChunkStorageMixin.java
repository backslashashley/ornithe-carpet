package carpet.mixins.chunk;

import carpet.commands.ChunkCommand;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.chunk.storage.AnvilChunkStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(AnvilChunkStorage.class)
public class AnvilChunkStorageMixin {
    @Inject(method = "loadChunk(Lnet/minecraft/world/World;IILnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/world/chunk/WorldChunk;", at = @At("RETURN"), cancellable = true)
    public void removeChunk(World world, int chunkX, int chunkZ, NbtCompound nbt, CallbackInfoReturnable<WorldChunk> cir) {
        if (ChunkCommand.removeChunk.contains(ChunkPos.toLong(chunkX, chunkZ))) {
            cir.setReturnValue(null);
            ChunkCommand.removeChunk.remove(ChunkPos.toLong(chunkX, chunkZ));
        }
    }
}
