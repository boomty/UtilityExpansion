package boomty.utilityexpansion.item.armorItems.headArmor;

import boomty.utilityexpansion.item.armorTypes.AttachableArmor;
import boomty.utilityexpansion.item.armorTypes.headArmor.VisoredHelmet;
import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

//public class ArmetItalian extends HeavyPartialHelmet implements EnclosedHelmet, Publisher, Subscriber {
public class ArmetItalian extends VisoredHelmet implements AttachableArmor {
    // deprecated
    private List<RegistryObject<? extends ArmorItem>> attachmentList = new ArrayList<>();

    private List<RegistryObject<? extends ArmorItem>> possibleAttachments = new ArrayList<>();

    public ArmetItalian(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
        possibleAttachments.add(ItemRegistry.lorica_segmentata);
        attachmentList.add(ItemRegistry.lorica_segmentata);
    }

    public List<RegistryObject<? extends ArmorItem>> getPossibleAttachments() {
        return possibleAttachments;
    }


    //    public static boolean isVisorUp = true;
//    public static boolean eventFulfilled = true;
//    private static boolean wasRunning = false;
//    private static Subscriber armetItalianRenderer;
//
//    public ArmetItalian(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
//        super(materialIn, slot, builder);
//        VisorEventHandler.attachSubscriber(this);
//    }
//
//    public static void attachSubscriber(Subscriber subscriber) {
//       armetItalianRenderer = subscriber;
//    }
//
//    @Override
//    public void update() {
//        if (eventFulfilled) {
//            isVisorUp = !isVisorUp;
//            eventFulfilled = false;
//        }
//    }

//    @Override
//    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
//        // AnimationEvent occurs every tick
//
//        // decide animation based on current visor state, and if the event hasn't been fulfilled yet
//        if (isVisorUp && !eventFulfilled) {
//            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_close", false));
////            System.out.println("up");
//        }
//        else if (!isVisorUp && !eventFulfilled) {
//            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_open", false));
////            System.out.println("down");
//        }
//
////        System.out.println(event.getController().getAnimationState());
//        // keep track of the animation state to know when the animation was completed
//        if (event.getController().getAnimationState() == AnimationState.Running) {
//            wasRunning = true;
//        }
//        // once the animation has stopped
//        else if (event.getController().getAnimationState() == AnimationState.Stopped && wasRunning) {
//            wasRunning = false;
//            eventFulfilled = true;
//            armetItalianRenderer.update();
//        }
//
//        return PlayState.CONTINUE;
//    }
}
