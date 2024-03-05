package boomty.utilityexpansion.item.ArmorTypes.BodyArmor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

/**
 * For heavy plate armors (medieval) that also have lance braces. Buffs lance for user.
 */
public class HeavyPlateLanceCouchArmorItem extends HeavyPlateArmorItem {

    public HeavyPlateLanceCouchArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }
}
