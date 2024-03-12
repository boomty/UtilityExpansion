package boomty.utilityexpansion.mixin;

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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F", shift = At.Shift.BEFORE))
    public void calculateDamageReduction(DamageSource p_21240_, float p_21241_, CallbackInfo ci) {
        System.out.println(p_21240_.getEntity());
        if (p_21240_ instanceof EntityDamageSource entityDamageSource) {
            if (entityDamageSource.getEntity() instanceof LivingEntity livingEntity) {
                Item attackWeapon = livingEntity.getItemBySlot(EquipmentSlot.MAINHAND).getItem();
                LivingEntity recipient = ((LivingEntity) (Object) this);

                Item helmetItem = recipient.getItemBySlot(EquipmentSlot.HEAD).getItem();
                Item chestItem = recipient.getItemBySlot(EquipmentSlot.CHEST).getItem();
                Item legItem = recipient.getItemBySlot(EquipmentSlot.LEGS).getItem();
                Item footItem = recipient.getItemBySlot(EquipmentSlot.FEET).getItem();

                if (attackWeapon instanceof SwordItem) {
                    
                }
                else if (attackWeapon instanceof BluntWeapon) {

                }
                else if (WeaponTypes.getInstance().getBluntWeapons().contains(attackWeapon)) {

                }
            }
        }
    }
}
