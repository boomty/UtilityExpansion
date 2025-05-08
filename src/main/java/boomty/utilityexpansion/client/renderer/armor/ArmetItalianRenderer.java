package boomty.utilityexpansion.client.renderer.armor;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.client.model.armor.ArmetItalianModel;
import boomty.utilityexpansion.events.Subscriber;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import boomty.utilityexpansion.item.armorTypes.headArmor.VisoredHelmet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ArmetItalianRenderer extends GeoArmorRenderer<ArmetItalian> implements Subscriber, VisoredHelmetRenderer {
    boolean initialized;

    public ArmetItalianRenderer() {
        super(new ArmetItalianModel());
        this.headBone = "armorHead";
        initialized = false;
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(ArmetItalian.class, ArmetItalianRenderer::new);
    }

    public LivingEntity getLivingEntity() {
        return entityLiving;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    private boolean shouldBeVisible(String bone) {
        CompoundTag nbtData = itemStack.getTag();

        if (nbtData != null && (nbtData.getBoolean("eventFulfilled") || !initialized)
                && entityLiving instanceof Player) {
            if (nbtData.getBoolean("hasVisor")) {
                if (!initialized) {
                    initialized = true;
                }

                if (nbtData.getBoolean("isVisorUp")) {
                    return !bone.equals("visor_up");
                }
                else {
                    return !bone.equals("visor_down");
                }
            }
        }
        else {
            GeoModel model = this.getGeoModelProvider().getModel(new ResourceLocation(UtilityExpansion.MOD_ID,
                    "geo/armet_italian.geo.json"));

            return model.getBone(bone).get().isHidden();
        }

        return false;
    }

    @Override
    public void update() {
        GeoModel model = this.getGeoModelProvider().getModel(new ResourceLocation(UtilityExpansion.MOD_ID,
                "geo/armet_italian.geo.json"));

        // check if the renderer has been assigned to an itemstack and if that itemstack is a visored helmet
        if (itemStack != null && itemStack.getItem() instanceof VisoredHelmet && entityLiving instanceof Player) {
//            CompoundTag nbtData = itemStack.getTag();

            // initialized allows for the visor to be rendered first even if a player does not press the keybind
            model.getBone("visor_up").get().setHidden(shouldBeVisible("visor_up"));
            model.getBone("visor_down").get().setHidden(shouldBeVisible("visor_down"));

//            if (nbtData != null && (nbtData.getBoolean("eventFulfilled") || !initialized)) {
//                if (nbtData.getBoolean("hasVisor")) {
//                    if (nbtData.getBoolean("isVisorUp")) {
//                        model.getBone("visor_up").get().setHidden(false);
//                        model.getBone("visor_down").get().setHidden(true);
//                    }
//                    else {
//                        model.getBone("visor_up").get().setHidden(true);
//                        model.getBone("visor_down").get().setHidden(false);
//                    }
//                }
//
//                if (!initialized) {
//                    initialized = true;
//                }
//            }
        }
        // if the itemstack has no nbt data
        else {
            model.getBone("visor_up").get().setHidden(true);
            model.getBone("visor_down").get().setHidden(true);
        }
    }
}
