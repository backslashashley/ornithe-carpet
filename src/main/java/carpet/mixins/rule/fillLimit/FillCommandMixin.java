package carpet.mixins.rule.fillLimit;

import carpet.CarpetSettings;
import net.minecraft.server.command.FillCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FillCommand.class)
public abstract class FillCommandMixin {
    @ModifyConstant(method = "run", constant = @Constant(intValue = 32768), expect = 2)
    public int modifyFillLimit(int constant) {
        return CarpetSettings.fillLimit;
    }
}
