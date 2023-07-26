package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//@Mixin(Slot.class)
//public class ArmorSlotLock {
//
//    @Inject(at = @At(value = "HEAD"), method = "mayPickup", cancellable = true)
//    protected void mayPickup(@NotNull Player p_40228_, CallbackInfoReturnable<Boolean> cir) {
//        Item leggings = p_40228_.getItemBySlot(EquipmentSlot.LEGS).getItem();
//        cir.setReturnValue(leggings != ItemRegistry.tunic_legs.get());
//    }
//}

@Mixin(Slot.class)
public abstract class ArmorSlotLock {
    @Redirect(method = "tryRemove", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;mayPickup(Lnet/minecraft/world/entity/player/Player;)Z"))
    public boolean tryRemove(Slot instance, Player p_40228_) {
        ItemStack itemstack = instance.getItem();
        boolean returnValue;

        if (!itemstack.isEmpty() && !p_40228_.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack))
            returnValue = false;
        else returnValue = itemstack.getItem() != ItemRegistry.tunic_legs.get() && itemstack.getItem() != ItemRegistry.lorica_legs.get();
        return returnValue;
    }
}
