package boomty.utilityexpansion.mixin;
import boomty.utilityexpansion.item.ModItemPairs;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
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
    @SuppressWarnings("unchecked")
    @Inject(method = "slotClicked", at = @At(value = "HEAD"), cancellable = true)
    public void slotClicked(Slot p_97778_, int p_97779_, int p_97780_, ClickType p_97781_, CallbackInfo ci){
        // p_97779_ is slotId
        // p_97780_ is the mouse button
        ModItemPairs modItems = ModItemPairs.getInstance();
        Map<Item, Item> correspondingItems = modItems.getCorrespondingItemStack();
        MultiPlayerGameMode gamemode = this.minecraft.gameMode;
        AbstractContainerScreen<T> instance = ((AbstractContainerScreen<T>) (Object) this);
        Player player = instance.getMinecraft().player;

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
            // if player is manually dragging item into armor slot
            // p_97779_ == 6 && ItemStack.matches(instance.getMenu().getCarried(), lorica_segmentata)
            else if (p_97779_ == 6 && correspondingItems.containsKey(instance.getMenu().getCarried().getItem())) {
                Item carryItem = instance.getMenu().getCarried().getItem();
                int legSlotId = 7;
                int mouseButtonNum = 0;
                player.getInventory().armor.set(EquipmentSlot.LEGS.getIndex(), new ItemStack(correspondingItems.get(carryItem)));
                gamemode.handleInventoryMouseClick(instance.getMenu().containerId, legSlotId, mouseButtonNum, ClickType.PICKUP, player);

            }
            // if player shift clicks item into slot, ensure that the chest slot is not taken
            // p_97781_ == ClickType.QUICK_MOVE && ItemStack.matches(p_97778_.getItem(), lorica_segmentata) && p_97779_ != 6
            else if (p_97781_ == ClickType.QUICK_MOVE && correspondingItems.containsKey(slotItem) && p_97779_ != 6
                    && Objects.requireNonNull(player).getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
                int legSlotId = 7;
                int mouseButtonNum = 0;
                player.getInventory().armor.set(EquipmentSlot.LEGS.getIndex(), new ItemStack(correspondingItems.get(slotItem)));
                gamemode.handleInventoryMouseClick(instance.getMenu().containerId, legSlotId, mouseButtonNum, ClickType.PICKUP, player);
            }
            // if player removes chestplate by pressing hotbar key
            // p_97779_ == 6 && p_97781_ == ClickType.SWAP && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
            else if (p_97779_ == 6 && p_97781_ == ClickType.SWAP && correspondingItems.containsKey(slotItem)) {
                player.getInventory().armor.set(EquipmentSlot.LEGS.getIndex(), ItemStack.EMPTY);
            }
            // if player removes chestplate by clicking
            // p_97779_ == 6 && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
            else if (p_97779_ == 6 && correspondingItems.containsKey(slotItem)) {
                player.getInventory().armor.set(EquipmentSlot.LEGS.getIndex(), ItemStack.EMPTY);
            }
        }
    }



    public int numOfEmptySlots() {
        NonNullList<ItemStack> playerInventory =
                ((AbstractContainerScreen<T>) (Object) this).getMinecraft().player.getInventory().items;
        int numOfEmptySlots = 0;

        for (ItemStack item : playerInventory) {
            if (ItemStack.matches(new ItemStack(Items.AIR), item)) {
                numOfEmptySlots++;
            }
        }

        return numOfEmptySlots;
    }

}
