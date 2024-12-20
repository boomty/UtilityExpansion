package boomty.utilityexpansion.item.armorItems.headArmor;

import boomty.utilityexpansion.item.armorTypes.headArmor.EnclosedHelmet;
import boomty.utilityexpansion.item.armorTypes.headArmor.HeavyPartialHelmet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

public class ArmetItalian extends HeavyPartialHelmet implements EnclosedHelmet {
    public ArmetItalian(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }
}
