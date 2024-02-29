package boomty.utilityexpansion.client.renderer.armor;

import boomty.utilityexpansion.client.model.armor.GaleaTypeHHelmetModel;
import boomty.utilityexpansion.item.ArmorItems.HeadArmor.Galea;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GaleaTypeHHelmetRenderer extends GeoArmorRenderer<Galea>{
    public GaleaTypeHHelmetRenderer() {

        super(new GaleaTypeHHelmetModel());

        this.headBone = "armorHead";
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(Galea.class, () -> new GaleaTypeHHelmetRenderer());
    }
}
