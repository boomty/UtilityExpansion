package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.item.ModItems;
import boomty.utilityexpansion.registry.ItemRegistry;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Objects;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin <T extends AbstractContainerMenu> extends Screen implements MenuAccess<T> {
    @Shadow public abstract T getMenu();
    ItemStack lorica_legs = new ItemStack(ItemRegistry.lorica_legs.get());
    ItemStack lorica_segmentata = new ItemStack(ItemRegistry.lorica_segmentata.get());

    protected AbstractContainerScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

//    @Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;slotClicked(Lnet/minecraft/world/inventory/Slot;IILnet/minecraft/world/inventory/ClickType;)V"))
//    public void slotClicked(AbstractContainerScreen<T> instance, Slot p_97778_, int p_97779_, int p_97780_, ClickType p_97781_){
//        if (p_97778_ != null) {
//            p_97779_ = p_97778_.index;
//        }
//
//        if (!ItemStack.matches(p_97778_.getItem(), lorica_legs))
//            this.minecraft.gameMode.handleInventoryMouseClick(this.getMenu().containerId, p_97779_, p_97780_, p_97781_, this.minecraft.player);
//    }

    // method for equipping and unequipping corresponding armor 
    @Inject(method = "slotClicked", at = @At(value = "HEAD"), cancellable = true)
    public void slotClicked(Slot p_97778_, int p_97779_, int p_97780_, ClickType p_97781_, CallbackInfo ci){
        ModItems modItems = new ModItems();
        Hashtable<String, String> itemMap = modItems.getItemMap();
        Hashtable<String, ItemStack> correspondingItemStack = modItems.getCorrespondingItemStack();

        AbstractContainerScreen<T> instance = ((AbstractContainerScreen<T>) (Object) this);
        if (p_97778_ != null) {
            String slotItem = p_97778_.getItem().getItem().getDescriptionId();

            // prevent player from removing leg portion
            // ItemStack.matches(p_97778_.getItem(), lorica_legs)
            if (itemMap.containsValue(slotItem)) {
                ci.cancel();
            }
            // if player is manually dragging item into armor slot
            // p_97779_ == 6 && ItemStack.matches(instance.getMenu().getCarried(), lorica_segmentata)
            else if (p_97779_ == 6 && itemMap.containsKey(instance.getMenu().getCarried().getDescriptionId())) {
                // need to check to make sure there are at least two empty slots in inventory
                instance.getMinecraft().player.setItemSlot(EquipmentSlot.LEGS, correspondingItemStack.get(itemMap.get(instance.getMenu().getCarried().getDescriptionId())));
            }
            // if player shift clicks item into slot
            // p_97781_ == ClickType.QUICK_MOVE && ItemStack.matches(p_97778_.getItem(), lorica_segmentata) && p_97779_ != 6
            else if (p_97781_ == ClickType.QUICK_MOVE && itemMap.containsKey(slotItem) && p_97779_ != 6) {
                ((AbstractContainerScreen<T>) (Object) this).getMinecraft().player.setItemSlot(EquipmentSlot.LEGS, correspondingItemStack.get(itemMap.get(slotItem)));
            }
            // if player removes chestplate by pressing hotbar key
            // p_97779_ == 6 && p_97781_ == ClickType.SWAP && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
            else if (p_97779_ == 6 && p_97781_ == ClickType.SWAP && itemMap.containsKey(slotItem)) {
                ((AbstractContainerScreen<T>) (Object) this).getMinecraft().player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
            }
            // if player removes chestplate by clicking
            // p_97779_ == 6 && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)
            else if (p_97779_ == 6 && itemMap.containsKey(slotItem)) {
                ((AbstractContainerScreen<T>) (Object) this).getMinecraft().player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
            }
        }
    }

}
