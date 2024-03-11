package boomty.utilityexpansion.mixin;
import boomty.utilityexpansion.item.ModItemPairs;
import boomty.utilityexpansion.packets.PacketHandler;
import boomty.utilityexpansion.packets.ServerboundArmorUpdatePacket;
import boomty.utilityexpansion.util.EquipmentSlotConverter;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;
import java.util.Objects;

/**
 * Part of the multi-part equipment system. Prevents user from removing lower part of a garment
 * when its corresponding part is still equipped and equips the corresponding part when the other
 * is equipped. (Only applies in survival)
 */
@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin <T extends AbstractContainerMenu> extends Screen implements MenuAccess<T> {
    @Shadow public abstract T getMenu();
    protected AbstractContainerScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    // user cannot remove the corresponding part. Currently hardcoded for adding leg component only (needs change if corresponding
    // component is not leg)
    @Inject(method = "slotClicked", at = @At(value = "HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    public void slotClicked(Slot p_97778_, int p_97779_, int p_97780_, ClickType p_97781_, CallbackInfo ci){
        // p_97779_ is slotId
        // p_97780_ is the mouse button
        ModItemPairs modItems = ModItemPairs.getInstance();
        Map<Item, Item> correspondingItems = modItems.getCorrespondingItemStack();
        Player player = this.getMinecraft().player;

        if (p_97778_ != null) {
            assert player != null;
            Item slotItem = p_97778_.getItem().getItem();

            /*
            Prevent player from removing leg portion when chest item is there.
            Check if the leg item exists as a value within the corresponding item map and the chest slot is not empty
            */
            if (correspondingItems.containsValue(slotItem) && Objects.requireNonNull(player)
                    .getItemBySlot(EquipmentSlot.CHEST) != ItemStack.EMPTY) {
                ci.cancel();
            }
            // if player removes chestplate
            else if (p_97779_ == 6 && correspondingItems.containsKey(slotItem)) {
                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);

                PacketHandler.INSTANCE.sendToServer(
                        new ServerboundArmorUpdatePacket(ItemStack.EMPTY, EquipmentSlotConverter
                                .getSlotIdFromEquipmentSlot(EquipmentSlot.LEGS)));
            }
        }
    }
}