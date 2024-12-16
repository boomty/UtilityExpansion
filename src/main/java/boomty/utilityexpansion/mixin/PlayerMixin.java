package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import boomty.utilityexpansion.item.BluntWeapon;
import boomty.utilityexpansion.item.ModItemPairs;
import boomty.utilityexpansion.item.WeaponTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    // keep track of damage after reduction
    private float resultantDamage = 0;

    /*
    Method: calculateDamageReduction
    Returns: void
    Purpose: Calculate modded damage reduction based on its armor type. Armor type decides how much resistance it has to
    swords, blunt weapons, and projectiles.
     */
    @Inject(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F", shift = At.Shift.BEFORE))
    public void calculateDamageReduction(DamageSource p_21240_, float p_21241_, CallbackInfo ci) {
        if (p_21240_ instanceof EntityDamageSource entityDamageSource) {
            if (entityDamageSource.getEntity() instanceof LivingEntity livingEntity) {
                float maxReduction = 10;

                Item attackWeapon = livingEntity.getItemBySlot(EquipmentSlot.MAINHAND).getItem();
                LivingEntity recipient = ((LivingEntity) (Object) this);

                Item helmetItem = recipient.getItemBySlot(EquipmentSlot.HEAD).getItem();
                Item chestItem = recipient.getItemBySlot(EquipmentSlot.CHEST).getItem();
                Item legItem = recipient.getItemBySlot(EquipmentSlot.LEGS).getItem();
                Item footItem = recipient.getItemBySlot(EquipmentSlot.FEET).getItem();

                float totalReduction = 0;

                if (attackWeapon instanceof SwordItem) {
                    totalReduction = getTotalReduction(0, helmetItem, chestItem, legItem, footItem);
                }
                else if (attackWeapon instanceof BluntWeapon) {
                    totalReduction = getTotalReduction(1, helmetItem, chestItem, legItem, footItem);
                }
                else if (WeaponTypes.getInstance().getBluntWeapons().contains(attackWeapon)) {
                    totalReduction = getTotalReduction(1, helmetItem, chestItem, legItem, footItem);
                }

                // reduction points are out of 10 (max value is 10)
                if (totalReduction > maxReduction) {
                    totalReduction = maxReduction;
                }

                // totalReduction/maxReduction returns the percentage reduction the armor has to a weapon
                resultantDamage = p_21241_ - (p_21241_ * totalReduction/maxReduction);
            }
        }
    }

    /*
    Method: injectDamageArgument
    Returns: float
    Purpose: Inject resultant damage into getDamageAfterAbsorb(DamageSource, float) so that the damage reduction is used.
     */
    @ModifyArg(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"), index = 1)
    private float injectDamageArgument(float p_21163_) {
        return resultantDamage;
    }

    /*
    Method: getTotalReduction
    Returns: float
    Purpose: Adds up the total resistance points to the weapon used by attacking entity.
     */
    private float getTotalReduction(int index, Item helmetItem, Item chestItem, Item legItem, Item footItem) {
        float totalReduction = 0;

        if (helmetItem instanceof ModArmor modHelmet) {
            totalReduction += modHelmet.getWeaponResistance()[index];
        }
        if (chestItem instanceof ModArmor modChestArmor) {
            totalReduction += modChestArmor.getWeaponResistance()[index];
        }
        if (legItem instanceof ModArmor modLegArmor) {
            totalReduction += modLegArmor.getWeaponResistance()[index];
        }
        if (footItem instanceof ModArmor modFootArmor) {
            totalReduction += modFootArmor.getWeaponResistance()[index];
        }

        return totalReduction;
    }

    /*
    Method: set
    Return: void
    Purpose: (Client side) When main part of an armor pair is equipped, its corresponding part will also be equipped. This covers the
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