package boomty.utilityexpansion.item.ArmorTypes.BodyArmor;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.item.GeoArmorItem;

/**
 * For plate armors that offer decent protection against swords, with little protection against blunt
 * attacks (lorica segmentata, lorica musculata, general bronze cuirasses)
 */
public class MediumPlateArmorItem extends ModArmor {
    public MediumPlateArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder, new float[]{2F, 0.5F});
    }
}
