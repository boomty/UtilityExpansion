package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.item.ModItems;
import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin <T extends AbstractContainerMenu> extends Screen implements MenuAccess<T> {
    @Shadow public abstract T getMenu();
    ItemStack lorica_legs = new ItemStack(ItemRegistry.lorica_legs.get());
    ItemStack lorica_segmentata = new ItemStack(ItemRegistry.lorica_segmentata.get());
    protected AbstractContainerScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

//    @Redirect(method = "mouseReleased", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;slotClicked(Lnet/minecraft/world/inventory/Slot;IILnet/minecraft/world/inventory/ClickType;)V"))
//    public void slotClicked(AbstractContainerScreen<T> instance, Slot p_97778_, int p_97779_, int p_97780_, ClickType p_97781_){
//        if (p_97778_ != null) {
//            p_97779_ = p_97778_.index;
//        }
//
//        ModItems modItems = new ModItems();
//        Hashtable<String, String> itemMap = modItems.getItemMap();
//        Hashtable<String, ItemStack> correspondingItemStack = modItems.getCorrespondingItemStack();
//        String slotItem = p_97778_.getItem().getItem().getDescriptionId();
//        Player player = instance.getMinecraft().player;
//
//        if (p_97778_ != null) {
//            System.out.println(numOfEmptySlots());
//
//            // if player is manually dragging item into armor slot
//            // p_97779_ == 6 && ItemStack.matches(instance.getMenu().getCarried(), lorica_segmentata)
//            if (p_97779_ == 6 && itemMap.containsKey(instance.getMenu().getCarried().getDescriptionId())) {
//                // if player has something in the leg slot already, check to make sure there is at least 1 empty slot
//                if (numOfEmptySlots() >= 1 && player.getItemBySlot(EquipmentSlot.LEGS) != ItemStack.EMPTY) {
//                    // corresponding leg item
//                    ItemStack legArmor = correspondingItemStack.get(itemMap.get(instance.getMenu().getCarried().getDescriptionId()));
//                    // save current item in leg slot
//                    ItemStack currentItem = player.getItemBySlot(EquipmentSlot.LEGS);
//                    // set leg slot with corresponding item
//                    player.setItemSlot(EquipmentSlot.LEGS, legArmor);
//                    // return saved item to player
//                    if (!correspondingItemStack.containsValue(legArmor))
//                        player.addItem(currentItem);
//                }
//                // if the player has nothing in their leg slot
//                else if (player.getItemBySlot(EquipmentSlot.LEGS) == ItemStack.EMPTY) {
//                    player.setItemSlot(EquipmentSlot.LEGS, correspondingItemStack.get(itemMap.get(instance.getMenu().getCarried().getDescriptionId())));
//                }
//            }
//            // if player shift clicks item into slot
//            // p_97781_ == ClickType.QUICK_MOVE && ItemStack.matches(p_97778_.getItem(), lorica_segmentata) && p_97779_ != 6
//            else if (p_97781_ == ClickType.QUICK_MOVE && itemMap.containsKey(slotItem) && p_97779_ != 6) {
//                // if player has something already in their leg slot, make sure there is at least one slot empty
//                if (numOfEmptySlots() >= 1 && player.getItemBySlot(EquipmentSlot.LEGS) != ItemStack.EMPTY) {
//                    ItemStack legArmor = correspondingItemStack.get(itemMap.get(slotItem));
//                    // save current item in leg slot
//                    ItemStack currentItem = player.getItemBySlot(EquipmentSlot.LEGS);
//                    // set leg slot with corresponding item
//                    player.setItemSlot(EquipmentSlot.LEGS, legArmor);
//                    // return saved item to player
//                    if (!correspondingItemStack.containsKey(legArmor.getDescriptionId()))
//                        player.addItem(currentItem);
//                }
//                // if the player has nothing in their leg slot
//                else if (player.getItemBySlot(EquipmentSlot.LEGS) == ItemStack.EMPTY){
//                    player.setItemSlot(EquipmentSlot.LEGS, correspondingItemStack.get(itemMap.get(slotItem)));
//                }
//            }
//            // if player removes chestplate by pressing hotbar key
//            // p_97779_ == 6 && p_97781_ == ClickType.SWAP && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
//            else if (p_97779_ == 6 && p_97781_ == ClickType.SWAP && itemMap.containsKey(slotItem)) {
//                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
//            }
//            // if player removes chestplate by clicking
//            // p_97779_ == 6 && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
//            else if (p_97779_ == 6 && itemMap.containsKey(slotItem)) {
//                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
//            }
//        }
//
//        if (!itemMap.containsValue(slotItem))
//            this.minecraft.gameMode.handleInventoryMouseClick(this.getMenu().containerId, p_97779_, p_97780_, p_97781_, this.minecraft.player);
//    }

    // user cannot remove the lorica_legs
    @Inject(method = "slotClicked", at = @At(value = "HEAD"), cancellable = true)
    public void slotClicked(Slot p_97778_, int p_97779_, int p_97780_, ClickType p_97781_, CallbackInfo ci){
        ModItems modItems = new ModItems();
        Map<String, String> itemMap = modItems.getItemMap();
        Map<String, ItemStack> correspondingItemStack = modItems.getCorrespondingItemStack();

        AbstractContainerScreen<T> instance = ((AbstractContainerScreen<T>) (Object) this);
        if (p_97778_ != null) {
            Player player = instance.getMinecraft().player;
            String slotItem = p_97778_.getItem().getItem().getDescriptionId();

            // prevent player from removing leg portion when chest item is there
            // ItemStack.matches(p_97778_.getItem(), lorica_legs)
            if (itemMap.containsValue(slotItem) && player.getItemBySlot(EquipmentSlot.CHEST) != ItemStack.EMPTY) {
                ci.cancel();
            }
            // if player is manually dragging item into armor slot
            // p_97779_ == 6 && ItemStack.matches(instance.getMenu().getCarried(), lorica_segmentata)
            else if (p_97779_ == 6 && itemMap.containsKey(instance.getMenu().getCarried().getDescriptionId())) {
                // if player has something in the leg slot already, check to make sure there is at least 1 empty slot
                if (numOfEmptySlots() >= 1 && player.getItemBySlot(EquipmentSlot.LEGS) != ItemStack.EMPTY) {
                    // corresponding leg item
                    ItemStack legArmor = correspondingItemStack.get(itemMap.get(instance.getMenu().getCarried().getDescriptionId()));
                    // save current item in leg slot
                    ItemStack currentItem = player.getItemBySlot(EquipmentSlot.LEGS);
                    // set leg slot with corresponding item
                    player.setItemSlot(EquipmentSlot.LEGS, legArmor);
                    // return saved item to player
                    if (!correspondingItemStack.containsValue(legArmor))
                        player.addItem(currentItem);
                }
                // if the player has nothing in their leg slot
                else if (player.getItemBySlot(EquipmentSlot.LEGS) == ItemStack.EMPTY) {
                    player.setItemSlot(EquipmentSlot.LEGS, correspondingItemStack.get(itemMap.get(instance.getMenu().getCarried().getDescriptionId())));
                }
            }
            // if player shift clicks item into slot
            // p_97781_ == ClickType.QUICK_MOVE && ItemStack.matches(p_97778_.getItem(), lorica_segmentata) && p_97779_ != 6
            else if (p_97781_ == ClickType.QUICK_MOVE && itemMap.containsKey(slotItem) && p_97779_ != 6) {
                // if player has something already in their leg slot, make sure there is at least one slot empty
                if (numOfEmptySlots() >= 1 && player.getItemBySlot(EquipmentSlot.LEGS) != ItemStack.EMPTY) {
                    ItemStack legArmor = correspondingItemStack.get(itemMap.get(slotItem));
                    // save current item in leg slot
                    ItemStack currentItem = player.getItemBySlot(EquipmentSlot.LEGS);
                    // set leg slot with corresponding item
                    player.setItemSlot(EquipmentSlot.LEGS, legArmor);
                    // return saved item to player
                    if (!correspondingItemStack.containsKey(legArmor.getDescriptionId()))
                        player.addItem(currentItem);
                }
                // if the player has nothing in their leg slot
                else if (player.getItemBySlot(EquipmentSlot.LEGS) == ItemStack.EMPTY){
                    player.setItemSlot(EquipmentSlot.LEGS, correspondingItemStack.get(itemMap.get(slotItem)));
                }
            }
            // if player removes chestplate by pressing hotbar key
            // p_97779_ == 6 && p_97781_ == ClickType.SWAP && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
            else if (p_97779_ == 6 && p_97781_ == ClickType.SWAP && itemMap.containsKey(slotItem)) {
                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
            }
            // if player removes chestplate by clicking
            // p_97779_ == 6 && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
            else if (p_97779_ == 6 && itemMap.containsKey(slotItem)) {
                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
            }
        }
    }

    public int numOfEmptySlots() {
        NonNullList<ItemStack> playerInventory = ((AbstractContainerScreen<T>) (Object) this).getMinecraft().player.getInventory().items;
        int numOfEmptySlots = 0;

        for (ItemStack item : playerInventory) {
            if (ItemStack.matches(new ItemStack(Items.AIR), item)) {
                numOfEmptySlots++;
            }
        }

        return numOfEmptySlots;
    }

}
