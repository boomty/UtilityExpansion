package boomty.utilityexpansion.item.armorTypes.headArmor;

import boomty.utilityexpansion.client.renderer.armor.VisoredHelmetRenderer;
import boomty.utilityexpansion.events.Publisher;
import boomty.utilityexpansion.events.Subscriber;
import boomty.utilityexpansion.events.VisorEventHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class VisoredHelmet extends HeavyPartialHelmet implements EnclosedHelmet, Publisher, Subscriber {
    private static ItemStack currentItemStack = null;
    private static UUID playerUUID = null;
//    private AnimationState previousState = null;
    private static Subscriber correspondingRenderer;

    public VisoredHelmet(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
        VisorEventHandler.attachSubscriber(this);
    }

    public static void attachSubscriber(Subscriber subscriber) {
        correspondingRenderer = subscriber;
    }

    public static void updateItemStack(ItemStack itemStack) {
        currentItemStack = itemStack;
    }

    public static void updatePlayerUUID(UUID uuid) {
        playerUUID = uuid;
    }

    public static LivingEntity getEntityLiving() {
        if (correspondingRenderer instanceof VisoredHelmetRenderer) {
            return ((VisoredHelmetRenderer) correspondingRenderer).getLivingEntity();
        }
        return null;
    }

    @Override
    public void update() {
        // update occurs whenever the visor keybind is released
        CompoundTag nbtData = currentItemStack.getTag();
        if (nbtData != null) {
            // set the flag eventfulfilled to be false and animationQueued to true so animation can run
//            if (nbtData.getBoolean("eventFulfilled") && !nbtData.getBoolean("animationQueued")) {
            if (nbtData.getBoolean("eventFulfilled")) {
                nbtData.putBoolean("eventFulfilled", false);
//                nbtData.putBoolean("animationQueued", true);

                currentItemStack.setTag(nbtData);
            }
        }
    }

    /*
    * Plays animation based on the state of the item that calls the animation
    * */
    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        // AnimationEvent occurs every tick
        CompoundTag nbtData = currentItemStack.getTag();
        LivingEntity entity = getEntityLiving();

        if (nbtData != null && correspondingRenderer instanceof VisoredHelmetRenderer && entity instanceof Player) {
            if (entity.getUUID() == playerUUID) {
                boolean isVisorUp = nbtData.getBoolean("isVisorUp");
                boolean eventFulfilled = nbtData.getBoolean("eventFulfilled");

                if (!eventFulfilled) {
                    nbtData.putBoolean("eventFulfilled", true);
                    nbtData.putBoolean("isVisorUp", !isVisorUp);
                }

                correspondingRenderer.update();

                currentItemStack.setTag(nbtData);
            }


//            if (previousState == null) {
//                previousState = event.getController().getAnimationState();
//            }

//            boolean isVisorUp = nbtData.getBoolean("isVisorUp");
//            boolean eventFulfilled = nbtData.getBoolean("eventFulfilled");
//            boolean isRunning = nbtData.getBoolean("isRunning");
//            boolean animationQueued = nbtData.getBoolean("animationQueued");

            // decide animation based on current visor state, and if the event hasn't been fulfilled yet
//            if (!eventFulfilled && animationQueued && !isRunning) {
//                event.getController().markNeedsReload();
//
//                if (isVisorUp) {
//                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_close", false));
//                }
//                else {
//                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_open", false));
//                }
//
//                nbtData.putBoolean("isRunning", true);
//            }
//
//            System.out.println(event.getController().getAnimationState());

//            if (event.getController().getAnimationState() == AnimationState.Stopped
//                    && isRunning) {
//                nbtData.putBoolean("isRunning", false);
//                nbtData.putBoolean("eventFulfilled", true);
//                nbtData.putBoolean("animationQueued", false);
//                nbtData.putBoolean("isVisorUp", !isVisorUp);
//                correspondingRenderer.update();
//            }

//            if (((VisoredHelmetRenderer) correspondingRenderer).getLivingEntity() instanceof Player) {
//
//            }
        }
        
        return PlayState.CONTINUE;
    }

//    private boolean getAnimationCondition(AnimationEvent<?> event, CompoundTag nbtData, int flag) {
//        // for the first if-else-if statement in VisoredHelmet#Predicate to see if animation should be run
//        if (!nbtData.getBoolean("isRunning")
//                && event.getController().getAnimationState() == AnimationState.Stopped && flag == 0) {
//            return true;
//        }
//        // for the if statement in VisoredHelmet#Predicate to modify nbt data once animations have stopped
//        else return nbtData.getBoolean("isRunning") && previousState == AnimationState.Running
//                && event.getController().getAnimationState() == AnimationState.Stopped && flag == 1;
//    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (currentItemStack != null) {
            CompoundTag nbtData = currentItemStack.getTag();

            if (nbtData != null) {
                if (nbtData.getBoolean("hasVisor")) { pTooltipComponents.add(new TextComponent("[+ visor]")); }
                if (nbtData.getBoolean("hasWrapper")) { pTooltipComponents.add(new TextComponent("[+ wrapper]")); }
                super.appendHoverText(itemStack, pLevel, pTooltipComponents, pIsAdvanced);
            }
        }
    }

}
