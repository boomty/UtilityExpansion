package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import boomty.utilityexpansion.item.BluntWeapon;
import boomty.utilityexpansion.item.WeaponTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    private float resultantDamage = 0;

    @Inject(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F", shift = At.Shift.BEFORE))
    public void calculateDamageReduction(DamageSource p_21240_, float p_21241_, CallbackInfo ci) {
        if (p_21240_ instanceof EntityDamageSource entityDamageSource) {
            if (entityDamageSource.getEntity() instanceof LivingEntity livingEntity) {
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

                p_21241_ -= (p_21241_*totalReduction);
                resultantDamage = p_21241_;
            }
        }
    }

    @ModifyArg(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"), index = 1)
    private float injectDamageArgument(float p_21163_) {
        return resultantDamage;
    }

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
}
