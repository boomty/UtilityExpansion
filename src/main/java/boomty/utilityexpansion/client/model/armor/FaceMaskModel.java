package boomty.utilityexpansion.client.model.armor;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.item.ArmorItems.HeadArmor.FaceMaskItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FaceMaskModel extends AnimatedGeoModel<FaceMaskItem> {
    @Override
    public ResourceLocation getModelLocation(FaceMaskItem object) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "geo/face_mask.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FaceMaskItem object) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "textures/item/mask_output.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FaceMaskItem animatable) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "animations/armor_animation.json");
    }
}
