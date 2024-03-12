package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.item.ModItemPairs;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

    /*
    Method: set
    Return: void
    Purpose: When main part of an armor pair is equipped, its corresponding part will also be equipped. This covers the
    case when a player right-clicks a piece of armor outside the inventory ui.
     */
    @Inject(method = "setItemSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;set(ILjava/lang/Object;)Ljava/lang/Object;", ordinal = 2))
    public void set(EquipmentSlot p_36161_, ItemStack p_36162_, CallbackInfo ci) {
        ModItemPairs modItems = ModItemPairs.getInstance();
        Map<Item, Item> correspondingItems = modItems.getCorrespondingItemStack();
        Player player = ((Player)(Object)this);
        ItemStack legSlotItemStack = player.getItemBySlot(EquipmentSlot.LEGS);

        if (correspondingItems.containsKey(p_36162_.getItem())) {
            if (!legSlotItemStack.isEmpty() && legSlotItemStack.getItem() != correspondingItems.get(p_36162_.getItem())) {
                if (numOfEmptySlots() >= 1) {
                    player.addItem(legSlotItemStack);
                }
                else {
                    // if player's inventory is full drop the item on the ground
                    Level level = player.getLevel();
                    double x = player.getX();
                    double y = player.getY();
                    double z = player.getZ();

                    ItemEntity itemEntity = new ItemEntity(level, x, y, z, legSlotItemStack);

                    level.addFreshEntity(itemEntity);
                }
            }
            player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(correspondingItems.get(p_36162_.getItem())));
        }
    }

    public int numOfEmptySlots() {
        NonNullList<ItemStack> playerInventory = ((Player) (Object) this).getInventory().items;
        int numOfEmptySlots = 0;

        for (ItemStack item : playerInventory) {
            if (ItemStack.matches(new ItemStack(Items.AIR), item)) {
                numOfEmptySlots++;
            }
        }

        return numOfEmptySlots;
    }
}