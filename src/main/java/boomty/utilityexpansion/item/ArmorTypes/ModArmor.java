package boomty.utilityexpansion.item.ArmorTypes;

import boomty.utilityexpansion.UtilityExpansion;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

import java.awt.*;

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
