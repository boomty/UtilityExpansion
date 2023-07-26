package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    // when player equips lorica or tunic the leg section will be equipped as well
    @Inject(method = "setItemSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;set(ILjava/lang/Object;)Ljava/lang/Object;", ordinal = 2))
    public void set(EquipmentSlot p_36161_, ItemStack p_36162_, CallbackInfo ci) {
        if (p_36162_.getItem() == ItemRegistry.tunic.get())
            ((Player)(Object)this).setItemSlot(EquipmentSlot.LEGS, new ItemStack(ItemRegistry.tunic_legs.get()));
        else if (p_36162_.getItem() == ItemRegistry.lorica_segmentata.get())
            ((Player)(Object)this).setItemSlot(EquipmentSlot.LEGS, new ItemStack(ItemRegistry.lorica_legs.get()));
    }
}
