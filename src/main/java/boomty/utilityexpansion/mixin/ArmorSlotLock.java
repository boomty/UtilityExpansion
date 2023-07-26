package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

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
    // if the player tries to remove the tunic legs or lorica legs they will not be able to
    @Redirect(method = "tryRemove", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;mayPickup(Lnet/minecraft/world/entity/player/Player;)Z"))
    public boolean tryRemove(Slot instance, Player p_40228_) {
        ItemStack itemstack = instance.getItem();
        boolean returnValue;

        if (!itemstack.isEmpty() && !p_40228_.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack))
            returnValue = false;
        else returnValue = itemstack.getItem() != ItemRegistry.tunic_legs.get() && itemstack.getItem() != ItemRegistry.lorica_legs.get();
        return returnValue;
    }

    // if the tunic or lorica segmentata is being removed, the leg part will be removed too
    @Inject(method = "tryRemove", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;remove(I)Lnet/minecraft/world/item/ItemStack;"))
    public void tryRemove(int p_150642_, int p_150643_, Player p_150644_, CallbackInfoReturnable<Optional<ItemStack>> cir) {
        if (p_150644_.getItemBySlot(EquipmentSlot.CHEST).getItem() == ItemRegistry.tunic.get() || p_150644_.getItemBySlot(EquipmentSlot.CHEST).getItem() == ItemRegistry.lorica_segmentata.get())
            p_150644_.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
    }
}