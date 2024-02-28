package boomty.utilityexpansion.item.ArmorTypes.BodyArmor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

/**
 * Maille type armors (chainmail, scale, lamellar). Provides decent protection against sword cuts and
 * some thrusts
 */
public class MediumMailleArmorItem extends GeoArmorItem {
    public MediumMailleArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }
}
