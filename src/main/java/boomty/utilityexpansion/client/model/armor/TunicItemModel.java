package boomty.utilityexpansion.client.model.armor;

import boomty.utilityexpansion.item.TunicItem;
import boomty.utilityexpansion.utilityexpansion;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TunicItemModel extends AnimatedGeoModel<TunicItem> {

        @Override
        public ResourceLocation getModelLocation(TunicItem object) {
            return new ResourceLocation(utilityexpansion.MOD_ID, "geo/tunic_item.geo.json");
        }

        @Override
        public ResourceLocation getTextureLocation(TunicItem object) {
            return new ResourceLocation(utilityexpansion.MOD_ID, "textures/item/tunic_texture.png");
        }

        @Override
        public ResourceLocation getAnimationFileLocation(TunicItem animatable) {
            return new ResourceLocation(utilityexpansion.MOD_ID, "animations/armor_animation.json");
        }
}

