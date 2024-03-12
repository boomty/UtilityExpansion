package boomty.utilityexpansion.client.renderer.armor;

import boomty.utilityexpansion.client.model.armor.GaleaTypeHHelmetModel;
import boomty.utilityexpansion.client.model.armor.LoricaSegmentataModel;
import boomty.utilityexpansion.item.ArmorItems.BodyArmor.LoricaSegmentata;
import boomty.utilityexpansion.item.ArmorItems.HeadArmor.Galea;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class LoricaSegmentataRenderer extends GeoArmorRenderer<LoricaSegmentata> {
    public LoricaSegmentataRenderer() {
        super(new LoricaSegmentataModel());
        this.bodyBone = "armorBody";
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(LoricaSegmentata.class, () -> new LoricaSegmentataRenderer());
    }
}
