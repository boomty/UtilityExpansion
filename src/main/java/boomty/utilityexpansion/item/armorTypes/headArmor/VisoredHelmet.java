package boomty.utilityexpansion.item.armorTypes.headArmor;

import boomty.utilityexpansion.events.Publisher;
import boomty.utilityexpansion.events.Subscriber;
import boomty.utilityexpansion.events.VisorEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import javax.annotation.Nullable;
import java.util.List;

public class VisoredHelmet extends HeavyPartialHelmet implements EnclosedHelmet, Publisher, Subscriber {
    public static boolean hasWrapper = false;
    public static boolean hasVisor = false;
    public static boolean isVisorUp = false;
    public static boolean eventFulfilled = true;
    private static boolean wasRunning = false;
    private static Subscriber correspondingRenderer;

    public VisoredHelmet(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
        VisorEventHandler.attachSubscriber(this);
    }

    public static void attachSubscriber(Subscriber subscriber) {
        correspondingRenderer = subscriber;
    }

    public void setVisor(boolean hasVisor) {
        VisoredHelmet.hasVisor = hasVisor;
        if (hasVisor) {
            isVisorUp = true;
            VisorEventHandler.attachSubscriber(this);
        }
        else {
            isVisorUp = false;
            VisorEventHandler.removeSubscriber(this);
        }
    }

    public void setWrapper(boolean hasWrapper) {
        VisoredHelmet.hasWrapper = hasWrapper;
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
        // AnimationEvent occurs every tick

        // decide animation based on current visor state, and if the event hasn't been fulfilled yet
        if (isVisorUp && !eventFulfilled) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_close", false));
//            System.out.println("up");
        }
        else if (!isVisorUp && !eventFulfilled) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_open", false));
//            System.out.println("down");
        }

        System.out.println(event.getController().getAnimationState());
        // keep track of the animation state to know when the animation was completed
        if (event.getController().getAnimationState() == AnimationState.Running) {
            wasRunning = true;
        }
        // once the animation has stopped
        else if (event.getController().getAnimationState() == AnimationState.Stopped && wasRunning) {
            wasRunning = false;
            eventFulfilled = true;
            correspondingRenderer.update();
        }

        return PlayState.CONTINUE;
    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (hasVisor) { pTooltipComponents.add(new TextComponent("- visor")); }
        if (hasWrapper) { pTooltipComponents.add(new TextComponent("- wrapper")); }
        super.appendHoverText(itemStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}
