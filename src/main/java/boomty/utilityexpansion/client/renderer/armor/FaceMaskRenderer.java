package boomty.utilityexpansion.client.renderer.armor;

import boomty.utilityexpansion.client.model.armor.FaceMaskModel;
import boomty.utilityexpansion.item.ArmorItems.HeadArmor.FaceMaskItem;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class FaceMaskRenderer extends GeoArmorRenderer<FaceMaskItem> {
    public FaceMaskRenderer() {
        super(new FaceMaskModel());
        this.headBone = "armorHead";
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(FaceMaskItem.class, () -> new FaceMaskRenderer());
    }

}
