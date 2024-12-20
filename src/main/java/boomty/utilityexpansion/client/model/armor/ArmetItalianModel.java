package boomty.utilityexpansion.client.model.armor;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArmetItalianModel extends AnimatedGeoModel<ArmetItalian> {
    @Override
    public ResourceLocation getModelLocation(ArmetItalian object) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "geo/armet_italian_no_attachments.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ArmetItalian object) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "textures/item/armet_italian_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ArmetItalian animatable) {
        return new ResourceLocation(UtilityExpansion.MOD_ID, "animations/armor_animation.json");
    }
}

