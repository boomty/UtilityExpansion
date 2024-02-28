package boomty.utilityexpansion.item.ArmorTypes.BodyArmor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

/**
 * For armor items that are made of cloths/linen i.e. gambeson, linen cuirass, etc
 */
public class LightSoftArmorItem extends GeoArmorItem {
    public LightSoftArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }
}
