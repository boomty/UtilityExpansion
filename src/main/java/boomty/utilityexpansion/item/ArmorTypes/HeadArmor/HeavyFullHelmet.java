package boomty.utilityexpansion.item.ArmorTypes.HeadArmor;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

/**
 * For medieval helmets with full head coverage using steel
 */
public class HeavyFullHelmet extends ModArmor {
    public HeavyFullHelmet(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder, float[] weaponResistance) {
        super(materialIn, slot, builder, new float[]{5F, 1F});
    }
}
