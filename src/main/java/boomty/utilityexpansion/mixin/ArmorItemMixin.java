package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin extends Item {
    ItemStack lorica_legs = new ItemStack(ItemRegistry.lorica_legs.get());
    ItemStack lorica_segmentata = new ItemStack(ItemRegistry.lorica_segmentata.get());

    public ArmorItemMixin(Properties p_41383_, int hasLegComponent) {
        super(p_41383_);
    }

    // if player equips armor with corresponding leg part the leg part will go on as well
//    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
//    public void set(Player instance, EquipmentSlot p_36161_, ItemStack p_36162_) {
//        // If the item in hand is a lorica segmentata
//        if (ItemStack.matches(p_36162_, lorica_segmentata)) {
//            // save item currently on leg slot
//            ItemStack saveItem = instance.getItemBySlot(EquipmentSlot.LEGS);
//            // set leg slot to corresponding armor
//            instance.setItemSlot(EquipmentSlot.LEGS, lorica_legs);
//            // give user the saved item in their mainhand
//            instance.addItem(saveItem);
//        }
//        instance.setItemSlot(p_36161_, p_36162_);
//    }
}
