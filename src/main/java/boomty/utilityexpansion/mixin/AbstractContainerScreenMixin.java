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

    // user cannot remove the lorica_legs
    @Inject(method = "slotClicked", at = @At(value = "HEAD"), cancellable = true)
    public void slotClicked(Slot p_97778_, int p_97779_, int p_97780_, ClickType p_97781_, CallbackInfo ci){
        if (p_97778_ != null){
            // prevent player from removing leg portion
            if (ItemStack.matches(p_97778_.getItem(), lorica_legs)) {
                ci.cancel();
            }
            // if player is manually dragging item into armor slot
            else if (p_97779_ == 6 && ItemStack.matches(((AbstractContainerScreen<T>) (Object) this).getMenu().getCarried(), lorica_segmentata)) {
                // need to check to make sure there are at least two empty slots in inventory
                ((AbstractContainerScreen<T>) (Object) this).getMinecraft().player.setItemSlot(EquipmentSlot.LEGS, lorica_legs);
            }
            // if player shift clicks item into slot
            else if (p_97781_ == ClickType.QUICK_MOVE && ItemStack.matches(p_97778_.getItem(), lorica_segmentata) && p_97779_ != 6) {
                ((AbstractContainerScreen<T>) (Object) this).getMinecraft().player.setItemSlot(EquipmentSlot.LEGS, lorica_legs);
            }
            // if player removes chestplate by pressing hotbar key
            else if (p_97779_ == 6 && p_97781_ == ClickType.SWAP && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)) {
                ((AbstractContainerScreen<T>) (Object) this).getMinecraft().player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
            }
            // if player removes chestplate by clicking
            else if (p_97779_ == 6 && ItemStack.matches(p_97778_.getItem(), lorica_segmentata)) {
                ((AbstractContainerScreen<T>) (Object) this).getMinecraft().player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
            }
        }
    }

}
