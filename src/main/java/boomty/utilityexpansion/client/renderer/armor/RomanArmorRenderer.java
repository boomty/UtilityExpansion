package boomty.utilityexpansion.client.renderer.armor;

import boomty.utilityexpansion.client.model.armor.RomanArmorModel;
import boomty.utilityexpansion.item.RomanArmorItem;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;



public class RomanArmorRenderer extends GeoArmorRenderer<RomanArmorItem> {
    public RomanArmorRenderer() {

        super(new RomanArmorModel());

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
        GeoArmorRenderer.registerArmorRenderer(RomanArmorItem.class, () -> new RomanArmorRenderer());
    }
}

