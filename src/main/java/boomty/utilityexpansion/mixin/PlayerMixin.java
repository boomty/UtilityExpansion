package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(method = "setItemSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;set(ILjava/lang/Object;)Ljava/lang/Object;", ordinal = 2))
    public void set(EquipmentSlot p_36161_, ItemStack p_36162_, CallbackInfo ci) {
        if (p_36162_.getItem() == ItemRegistry.tunic.get() || p_36162_.getItem() == ItemRegistry.lorica_segmentata.get())

    }
}
