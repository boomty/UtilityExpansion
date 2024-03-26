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
public abstract class ModArmor extends GeoArmorItem implements IAnimatable{
    private AnimationFactory factory = new AnimationFactory(this);
    // special weapon type resistances: 1. sword; 2. blunt
    private float[] weaponResistance;

    protected boolean isArrowResistant;

    public ModArmor(ArmorMaterial materialIn, EquipmentSlot slot, Item.Properties builder, float[] weaponResistance) {
        super(materialIn, slot, builder.tab(UtilityExpansion.utilexpanseitemgroup));
        this.weaponResistance = weaponResistance;
        this.isArrowResistant = false;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<ModArmor>(this, "controller",
                20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    public float[] getWeaponResistance() {
        return weaponResistance;
    }

    public boolean isArrowResistant() {
        return isArrowResistant;
    }
}
