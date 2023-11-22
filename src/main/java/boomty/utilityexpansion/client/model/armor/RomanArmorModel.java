package boomty.utilityexpansion.client.model.armor;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.item.RomanArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RomanArmorModel extends AnimatedGeoModel<RomanArmorItem> {
    @Override
    public ResourceLocation getModelLocation(RomanArmorItem object) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "geo/lorica_segmentata.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RomanArmorItem object) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "textures/item/lorica_segmentata_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RomanArmorItem animatable) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "animations/armor_animation.json");
    }
}