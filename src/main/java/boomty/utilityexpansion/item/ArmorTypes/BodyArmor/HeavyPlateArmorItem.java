package boomty.utilityexpansion.item.ArmorTypes.BodyArmor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

/**
 * For thick plate armors (medieval plate armors) that offer very good protection against swords and decent
 * protection against blunt attacks
 */
public class HeavyPlateArmorItem extends GeoArmorItem {
    public HeavyPlateArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }
}
