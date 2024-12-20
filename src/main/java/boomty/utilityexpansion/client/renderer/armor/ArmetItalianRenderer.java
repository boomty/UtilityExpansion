package boomty.utilityexpansion.client.renderer.armor;

import boomty.utilityexpansion.client.model.armor.ArmetItalianModel;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ArmetItalianRenderer extends GeoArmorRenderer<ArmetItalian> {
    public ArmetItalianRenderer() {
        super(new ArmetItalianModel());
        this.headBone = "armorHead";
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(ArmetItalian.class, ArmetItalianRenderer::new);
    }
}
