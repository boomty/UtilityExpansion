package boomty.utilityexpansion.client.model.armor;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.item.armorItems.bodyArmor.LoricaSegmentata;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LoricaSegmentataModel extends AnimatedGeoModel<LoricaSegmentata> {

    @Override
    public ResourceLocation getModelLocation(LoricaSegmentata object) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "geo/lorica_segmentata.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LoricaSegmentata object) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "textures/item/lorica_segmentata_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LoricaSegmentata animatable) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "animations/armor_animation.json");
    }
}
