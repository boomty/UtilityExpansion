package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.item.ModItemPairs;
import boomty.utilityexpansion.item.armorTypes.ModArmor;
import boomty.utilityexpansion.item.armorTypes.headArmor.EnclosedHelmet;
import boomty.utilityexpansion.packets.PacketHandler;
import boomty.utilityexpansion.packets.ServerboundArmorUpdatePacket;
import boomty.utilityexpansion.registry.ItemRegistry;
import boomty.utilityexpansion.util.EquipmentSlotConverter;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Map;
import java.util.Optional;

/**
 * Equips corresponding part when the main part is equipped. If there is already something
 * in the leg slot, it will be removed and added into the player's inventory.
 */
@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {

    /*
    Method: addCorrespondingPart
    Returns: void
    Purpose: (Server side) When main part of an armor pair is equipped, its corresponding part will also be equipped. This covers the
    case when a player is in the player inventory gui. Only applies in survival.
     */
    @Inject(method = "handleInventoryMouseClick", at =@At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/AbstractContainerMenu;clicked(IILnet/minecraft/world/inventory/ClickType;Lnet/minecraft/world/entity/player/Player;)V", shift = At.Shift.AFTER))
    public void addCorrespondingPart(int p_171800_, int p_171801_, int p_171802_, ClickType p_171803_, Player p_171804_, CallbackInfo ci) {
        ItemStack chestItemStack = p_171804_.getItemBySlot(EquipmentSlot.CHEST);
        // Equip corresponding item pairs for chest and legs
        if (chestItemStack.getItem() instanceof ModArmor) {
            ModItemPairs modItems = ModItemPairs.getInstance();
            Map<Item, Item> correspondingItems = modItems.getCorrespondingItemStack();

            ItemStack legItemStack = p_171804_.getItemBySlot(EquipmentSlot.LEGS);

            if (correspondingItems.containsKey(chestItemStack.getItem())) {
                ItemStack newLegItemStack = new ItemStack(correspondingItems.get(chestItemStack.getItem()));

                if (!legItemStack.isEmpty() && !correspondingItems.containsValue(legItemStack.getItem())) {
                    // slotId -1 signifies to packethandler to use the player.addItem method
                    PacketHandler.INSTANCE.sendToServer(
                            new ServerboundArmorUpdatePacket(legItemStack, -1));
                }

                // Send packet to server to update player armor
                PacketHandler.INSTANCE.sendToServer(
                        new ServerboundArmorUpdatePacket(newLegItemStack, EquipmentSlotConverter
                                .getSlotIdFromEquipmentSlot(EquipmentSlot.LEGS)));
            }
        }
        // Equip mask for enclosed helmets
        else if (p_171804_.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof EnclosedHelmet) {
            ItemStack faceMask = new ItemStack(ItemRegistry.face_mask.get());
            Optional<IItemHandlerModifiable> optional = CuriosApi.getCuriosHelper().getEquippedCurios(p_171804_).resolve();

            if (optional.isPresent()) {
                ItemStack headCurio = optional.get().getStackInSlot(0);

                System.out.println(ItemStack.EMPTY.getItem().getDescriptionId());
                // If something is already equipped on the curio head slot
                if (headCurio != ItemStack.EMPTY && headCurio.equals(faceMask) &&
                        p_171804_.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof EnclosedHelmet) {
                    System.out.println("Curio head slot: " + headCurio.getItem().getRegistryName());
                    PacketHandler.INSTANCE.sendToServer(
                            new ServerboundArmorUpdatePacket(headCurio, -1));
                }

                CuriosApi.getCuriosHelper().setEquippedCurio(p_171804_, "head", 0, faceMask);
            }
        }
    }
}