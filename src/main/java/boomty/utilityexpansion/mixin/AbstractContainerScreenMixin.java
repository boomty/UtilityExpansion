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
                ItemStack carryItem = instance.getMenu().getCarried();
                setSlots(player, carryItem, new ItemStack(correspondingItems.get(carryItem.getItem())),
                        player.getItemBySlot(EquipmentSlot.LEGS), correspondingItems, p_97779_, p_97780_, p_97781_,
                        player.getInventory().armor);
            }
            // if player shift clicks item into slot, ensure that the chest slot is not taken
            // p_97781_ == ClickType.QUICK_MOVE && ItemStack.matches(p_97778_.getItem(), lorica_segmentata) && p_97779_ != 6
            else if (p_97781_ == ClickType.QUICK_MOVE && correspondingItems.containsKey(slotItem) && p_97779_ != 6
                    && Objects.requireNonNull(player).getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
                setSlots(player, new ItemStack(slotItem), new ItemStack(correspondingItems.get(slotItem)),
                        player.getItemBySlot(EquipmentSlot.LEGS), correspondingItems, p_97779_, p_97780_, p_97781_,
                        player.getInventory().armor);
            }
            // if player removes chestplate by pressing hotbar key
            // p_97779_ == 6 && p_97781_ == ClickType.SWAP && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
            else if (p_97779_ == 6 && p_97781_ == ClickType.SWAP && correspondingItems.containsKey(slotItem)) {
//                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
                player.getInventory().armor.set(EquipmentSlot.LEGS.getIndex(), ItemStack.EMPTY);
//                sendPackets(player, this.getMenu().containerId, p_97779_, p_97780_, p_97781_, player.containerMenu);
            }
            // if player removes chestplate by clicking
            // p_97779_ == 6 && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
            else if (p_97779_ == 6 && correspondingItems.containsKey(slotItem)) {
//                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
                player.getInventory().armor.set(EquipmentSlot.LEGS.getIndex(), ItemStack.EMPTY);
//                sendPackets(player, this.getMenu().containerId, p_97779_, p_97780_, p_97781_, player.containerMenu);
            }
        }
    }

    private void setSlots(Player player, ItemStack mainComponent, ItemStack correspondingComponent, ItemStack currentItem,
                          Map<Item, Item> correspondingItemStack, int slotNum, int buttonNum, ClickType clickType,
                          NonNullList<ItemStack> armorSlots) {
        // if player has something already in their leg slot, make sure there is at least one slot empty
        if (numOfEmptySlots() >= 1 && player.getItemBySlot(EquipmentSlot.LEGS) != ItemStack.EMPTY) {
            // set leg slot with corresponding item
//            player.setItemSlot(EquipmentSlot.LEGS, correspondingComponent);
            player.getInventory().armor.set(EquipmentSlot.LEGS.getIndex(), correspondingComponent);
            // return saved item to player
            if (!correspondingItemStack.containsKey(correspondingComponent.getItem())) {
                player.addItem(currentItem);
            }
            sendPackets(player, this.getMenu().containerId, slotNum, buttonNum, clickType, player.containerMenu,
                    armorSlots, mainComponent, correspondingComponent);
        }
        // if the player has nothing in their leg slot
        else if (player.getItemBySlot(EquipmentSlot.LEGS) == ItemStack.EMPTY){
//            player.setItemSlot(EquipmentSlot.LEGS, correspondingComponent);
            player.getInventory().armor.set(EquipmentSlot.LEGS.getIndex(), correspondingComponent);
//            sendPackets(player, this.getMenu().containerId, slotNum, buttonNum, clickType, player.containerMenu,
//                    armorSlots, mainComponent, correspondingComponent);
        }
    }

    private void sendPackets(Player player, int windowId, int slotNum, int buttonNum, ClickType clickType,
                             AbstractContainerMenu menu, NonNullList<ItemStack> armorSlots, ItemStack component1,
                             ItemStack component2) {
        NonNullList<Slot> nonnulllist = menu.slots;
        int i = nonnulllist.size();
        List<ItemStack> list = Lists.newArrayListWithCapacity(i);

        for(Slot slot : nonnulllist) {
            list.add(slot.getItem().copy());
        }

        Int2ObjectMap<ItemStack> int2objectmap = new Int2ObjectOpenHashMap<>();

        int2objectmap.put(6, component1);
        int2objectmap.put(7, component2);

        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.connection.send(new ServerboundContainerClickPacket(windowId, menu.getStateId(), slotNum, buttonNum, clickType, menu.getCarried().copy(), int2objectmap));
        }
        else {
            minecraft.getInstance().getConnection().send(new ServerboundContainerClickPacket(windowId, menu.getStateId(), slotNum, buttonNum, clickType, menu.getCarried().copy(), int2objectmap));
        }

        NonNullList<ItemStack> playerArmorList = player.getInventory().armor;
        System.out.println(playerArmorList.size());

        // Access the original player's inventory
        for (ItemStack item : playerArmorList) {
            if (!item.isEmpty()) {
                System.out.println(item.getItem().getDescription().getString());
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
