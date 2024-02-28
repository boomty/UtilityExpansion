package boomty.utilityexpansion.item;

import boomty.utilityexpansion.UtilityExpansion;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class TunicItem extends GeoArmorItem implements IAnimatable, ICurioItem {

    private AnimationFactory factory = new AnimationFactory(this);

    public TunicItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder.tab(UtilityExpansion.utilexpanseitemgroup));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<TunicItem>(this, "controller",
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
}
