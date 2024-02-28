package boomty.utilityexpansion.item.ArmorTypes.BodyArmor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

/**
 * For armors with small plates protecting vital areas (pectorale armor)
 */
public class LightPlateArmorItem extends GeoArmorItem {
    public LightPlateArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }
}
