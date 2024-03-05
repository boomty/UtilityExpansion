package boomty.utilityexpansion.item.ArmorTypes.BodyArmor;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

/**
 * For armors with small plates protecting vital areas (pectorale armor)
 */
public class LightPlateArmorItem extends ModArmor {
    public LightPlateArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder, new float[]{0.2F, 0});
    }
}
