package boomty.utilityexpansion.item.armorItems.headArmor;

import boomty.utilityexpansion.events.Publisher;
import boomty.utilityexpansion.events.Subscriber;
import boomty.utilityexpansion.events.VisorEventHandler;
import boomty.utilityexpansion.item.armorTypes.headArmor.EnclosedHelmet;
import boomty.utilityexpansion.item.armorTypes.headArmor.HeavyPartialHelmet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import java.util.concurrent.atomic.AtomicBoolean;

public class ArmetItalian extends HeavyPartialHelmet implements EnclosedHelmet, Publisher, Subscriber {
    public static boolean isVisorUp = true;
    public static boolean eventFulfilled = true;
    private static boolean wasTransitioning = false;
    private static Subscriber armetItalianRenderer;

    public ArmetItalian(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
        VisorEventHandler.attachSubscriber(this);
    }

    public static void attachSubscriber(Subscriber subscriber) {
       armetItalianRenderer = subscriber;
    }

    @Override
    public void update() {
        if (eventFulfilled) {
            isVisorUp = !isVisorUp;
            eventFulfilled = false;
        }
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (isVisorUp && !eventFulfilled) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_close", false));
//            System.out.println("up");
        }
        else if (!isVisorUp && !eventFulfilled) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_open", false));
//            System.out.println("down");
        }

//        System.out.println(event.getController().getAnimationState());
        if (event.getController().getAnimationState() == AnimationState.Transitioning) {
            wasTransitioning = true;
        }
        else if (event.getController().getAnimationState() == AnimationState.Stopped && wasTransitioning) {
            wasTransitioning = false;
            eventFulfilled = true;
            armetItalianRenderer.update();
        }

        return PlayState.CONTINUE;
    }
}
