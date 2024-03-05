package boomty.utilityexpansion.item.ArmorTypes.HeadArmor;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

/**
 * Partial helmets made with strong materials i.e. steel
 */
public class HeavyPartialHelmet extends ModArmor {
    public HeavyPartialHelmet(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder, float[] weaponResistance) {
        super(materialIn, slot, builder, new float[]{3F, 0.5F});
    }
}
