package boomty.utilityexpansion.item.armorTypes.headArmor;

import boomty.utilityexpansion.client.renderer.armor.VisoredHelmetRenderer;
import boomty.utilityexpansion.events.Publisher;
import boomty.utilityexpansion.events.Subscriber;
import boomty.utilityexpansion.events.VisorEventHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class VisoredHelmet extends HeavyPartialHelmet implements EnclosedHelmet, Publisher, Subscriber {
    private static ItemStack currentItemStack = null;
    private AnimationState previousState = null;
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

    @Override
    public void update() {
        // update occurs whenever the visor keybind is released
        CompoundTag nbtData = currentItemStack.getTag();
        if (nbtData != null) {
            // set the flag eventfulfilled to be false so animation can run
            if (nbtData.getBoolean("eventFulfilled")) {
                nbtData.putBoolean("eventFulfilled", false);

                currentItemStack.setTag(nbtData);
            }
        }
    }

    private boolean getAnimationCondition(AnimationEvent<?> event, CompoundTag nbtData, int flag) {
        // for the first if-else-if statement in VisoredHelmet#Predicate to see if animation should be run
        if (!nbtData.getBoolean("isRunning")
                && event.getController().getAnimationState() == AnimationState.Stopped && flag == 0) {
            return true;
        }
        // for the if statement in VisoredHelmet#Predicate to modify nbt data once animations have stopped
        else return nbtData.getBoolean("isRunning")
                && event.getController().getAnimationState() == AnimationState.Stopped && flag == 1;
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        // AnimationEvent occurs every tick
        CompoundTag nbtData = currentItemStack.getTag();

        if (nbtData != null && correspondingRenderer instanceof VisoredHelmetRenderer) {
            if (previousState == null) {
                previousState = event.getController().getAnimationState();
            }

            if (((VisoredHelmetRenderer) correspondingRenderer).getLivingEntity() instanceof Player) {
                boolean isVisorUp = nbtData.getBoolean("isVisorUp");
                boolean eventFulfilled = nbtData.getBoolean("eventFulfilled");

                // decide animation based on current visor state, and if the event hasn't been fulfilled yet
                if (isVisorUp && !eventFulfilled && getAnimationCondition(event, nbtData, 0)) {
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_close", false));
                    nbtData.putBoolean("isRunning", true);
                }
                else if (!isVisorUp && !eventFulfilled && getAnimationCondition(event, nbtData, 0)) {
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.visor_open", false));
                    nbtData.putBoolean("isRunning", true);
                }

                System.out.println(event.getController().getAnimationState());
                // once the animation has stopped
                if (!eventFulfilled && getAnimationCondition(event, nbtData, 1)) {
                    nbtData.putBoolean("isRunning", false);
                    nbtData.putBoolean("eventFulfilled", true);
                    nbtData.putBoolean("isVisorUp", !isVisorUp);
                    correspondingRenderer.update();
                }

                currentItemStack.setTag(nbtData);
            }
        }


        return PlayState.CONTINUE;
    }
//    @Override
//    public void appendHoverText(ItemStack itemStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
//        if (hasVisor) { pTooltipComponents.add(new TextComponent("- visor")); }
//        if (hasWrapper) { pTooltipComponents.add(new TextComponent("- wrapper")); }
//        super.appendHoverText(itemStack, pLevel, pTooltipComponents, pIsAdvanced);
//    }

}
