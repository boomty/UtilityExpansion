package boomty.utilityexpansion.item.armorTypes.curios;

import boomty.utilityexpansion.item.armorTypes.headArmor.EnclosedHelmet;
import boomty.utilityexpansion.item.armorTypes.ModArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

/**
 * For decorative face masks (offers no protection for head)
 */
public class Mask extends ModArmor {

    public Mask(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder, new float[]{0, 0});
    }
}
