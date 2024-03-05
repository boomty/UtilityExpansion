package boomty.utilityexpansion.item.ArmorTypes.HeadArmor;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

/**
 * For partial helmets made of iron
 */
public class MediumPartialHelmet extends ModArmor {

    public MediumPartialHelmet(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder, new float[]{1.5F, 0});
    }
}
