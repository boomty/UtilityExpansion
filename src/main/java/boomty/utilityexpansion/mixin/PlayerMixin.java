package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.item.ModItemPairs;
import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    // when player equips lorica or tunic the leg section will be equipped as well
    @Inject(method = "setItemSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;set(ILjava/lang/Object;)Ljava/lang/Object;", ordinal = 2))
    public void set(EquipmentSlot p_36161_, ItemStack p_36162_, CallbackInfo ci) {
        ModItemPairs modItems = ModItemPairs.getInstance();
        Map<Item, Item> correspondingItems = modItems.getCorrespondingItemStack();
        Player player = ((Player)(Object)this);
        ItemStack legSlotItemStack = player.getItemBySlot(EquipmentSlot.LEGS);

        if (correspondingItems.containsKey(p_36162_.getItem())) {
            if (!legSlotItemStack.isEmpty() && legSlotItemStack.getItem() != correspondingItems.get(p_36162_.getItem())) {
                player.addItem(legSlotItemStack);
                System.out.println("b");
            }
            player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(correspondingItems.get(p_36162_.getItem())));
        }
    }
}