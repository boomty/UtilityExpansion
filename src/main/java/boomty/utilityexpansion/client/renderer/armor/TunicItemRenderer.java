package boomty.utilityexpansion.client.renderer.armor;

import boomty.utilityexpansion.client.model.armor.TunicItemModel;
import boomty.utilityexpansion.item.RomanArmorItem;
import boomty.utilityexpansion.item.TunicItem;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class TunicItemRenderer extends GeoArmorRenderer<TunicItem> {

    public TunicItemRenderer() {

        super(new TunicItemModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(TunicItem.class, () -> new TunicItemRenderer());
//        GeoArmorRenderer.registerArmorRenderer(RomanArmorItem.class, () -> new RomanArmorRenderer());
    }
}

