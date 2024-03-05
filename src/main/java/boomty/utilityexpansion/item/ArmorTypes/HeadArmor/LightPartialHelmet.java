package boomty.utilityexpansion.item.ArmorTypes.HeadArmor;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

/**
 * For partial helmets made with weaker materials i.e. bronze/copper
 */
public class LightPartialHelmet extends ModArmor {
    public LightPartialHelmet(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder, new float[]{0.5F, 0});
    }
}
