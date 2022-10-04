package boomty.utilityexpansion.client.model.armor;

import boomty.utilityexpansion.item.TestingItem;
import boomty.utilityexpansion.utilityexpansion;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TestingItemModel extends AnimatedGeoModel<TestingItem> {
    @Override
    public ResourceLocation getModelLocation(TestingItem object) {
        return new ResourceLocation(utilityexpansion.MOD_ID, "geo/gladius.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TestingItem object) {
        return new ResourceLocation(utilityexpansion.MOD_ID, "textures/item/gladius_item.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TestingItem animatable) {
        return new ResourceLocation(utilityexpansion.MOD_ID,"animations/sword_animation.json");
    }
}