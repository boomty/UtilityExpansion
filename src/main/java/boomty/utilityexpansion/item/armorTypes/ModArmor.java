package boomty.utilityexpansion.item.armorTypes;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

/**
 * Mod armor all armors extend this class. Adds attribute for specific weapon protection.
 */
public abstract class ModArmor extends GenericArmor {
    // special weapon type resistances: 1. sword; 2. blunt
    private float[] weaponResistance;

    protected boolean isArrowResistant;

    public ModArmor(ArmorMaterial materialIn, EquipmentSlot slot, Item.Properties builder, float[] weaponResistance) {
        super(materialIn, slot, builder);
        this.weaponResistance = weaponResistance;
        this.isArrowResistant = false;
    }

    public float[] getWeaponResistance() {
        return weaponResistance;
    }

    public boolean isArrowResistant() {
        return isArrowResistant;
    }
}
