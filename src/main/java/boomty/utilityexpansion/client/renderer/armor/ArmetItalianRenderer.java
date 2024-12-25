package boomty.utilityexpansion.client.renderer.armor;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.client.model.armor.ArmetItalianModel;
import boomty.utilityexpansion.events.Subscriber;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ArmetItalianRenderer extends GeoArmorRenderer<ArmetItalian> implements Subscriber {
    public ArmetItalianRenderer() {
        super(new ArmetItalianModel());
        this.headBone = "armorHead";
        ArmetItalian.attachSubscriber(this);
        update();
    }

    public void close() {

    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(ArmetItalian.class, ArmetItalianRenderer::new);
    }

    @Override
    public void update() {
        GeoModel model = this.getGeoModelProvider().getModel(new ResourceLocation(UtilityExpansion.MOD_ID,
                "geo/armet_italian.geo.json"));
        if (ArmetItalian.eventFulfilled) {
            if (ArmetItalian.isVisorUp) {
                model.getBone("visor_up").get().setHidden(false);
                model.getBone("visor_down").get().setHidden(true);
            }
            else {
                model.getBone("visor_up").get().setHidden(true);
                model.getBone("visor_down").get().setHidden(false);
            }
        }
    }
}
