package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.item.ModItemPairs;
import boomty.utilityexpansion.packets.PacketHandler;
import boomty.utilityexpansion.packets.ServerboundArmorUpdatePacket;
import boomty.utilityexpansion.util.EquipmentSlotConverter;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

/**
 * Equips corresponding part when the main part is equipped. If there is already something
 * in the leg slot, it will be removed and added into the player's inventory.
 */
@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {
    /*
    Method: addCorrespondingPart
    Returns: void
    Purpose: When main part of an armor pair is equipped, its corresponding part will also be equipped. This covers the
    case when a player is in the player inventory gui. Only applies in survival.
     */
    @Inject(method = "handleInventoryMouseClick", at =@At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/AbstractContainerMenu;clicked(IILnet/minecraft/world/inventory/ClickType;Lnet/minecraft/world/entity/player/Player;)V", shift = At.Shift.AFTER))
    public void addCorrespondingPart(int p_171800_, int p_171801_, int p_171802_, ClickType p_171803_, Player p_171804_, CallbackInfo ci) {
        ModItemPairs modItems = ModItemPairs.getInstance();
        Map<Item, Item> correspondingItems = modItems.getCorrespondingItemStack();

        ItemStack chestItemStack = p_171804_.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legItemStack = p_171804_.getItemBySlot(EquipmentSlot.LEGS);

        if (correspondingItems.containsKey(chestItemStack.getItem())) {
            System.out.println(correspondingItems.get(chestItemStack.getItem()).getDescriptionId());
            ItemStack newLegItemStack = new ItemStack(correspondingItems.get(chestItemStack.getItem()));

            if (!legItemStack.isEmpty() && !correspondingItems.containsValue(legItemStack.getItem())) {
                // slotId -1 signifies to packethandler to use the player.addItem method
                PacketHandler.INSTANCE.sendToServer(
                        new ServerboundArmorUpdatePacket(legItemStack, -1));
            }

            // send packet to server to update player armor
            PacketHandler.INSTANCE.sendToServer(
                    new ServerboundArmorUpdatePacket(newLegItemStack, EquipmentSlotConverter
                            .getSlotIdFromEquipmentSlot(EquipmentSlot.LEGS)));
        }
    }
}