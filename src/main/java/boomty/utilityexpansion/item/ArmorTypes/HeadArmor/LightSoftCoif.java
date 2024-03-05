package boomty.utilityexpansion.item.ArmorTypes.HeadArmor;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

/**
 * For armors made of cloths and leather
 */
public class LightSoftCoif extends ModArmor {
    public LightSoftCoif(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder, float[] weaponResistance) {
        super(materialIn, slot, builder, new float[]{0.2F, 0});
    }
}
