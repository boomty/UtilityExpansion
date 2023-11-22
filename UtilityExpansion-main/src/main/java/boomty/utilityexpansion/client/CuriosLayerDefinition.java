package boomty.utilityexpansion.client;

import boomty.utilityexpansion.UtilityExpansion;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class CuriosLayerDefinition {
    public static final ModelLayerLocation
        LORICA_SEGMENTATA = createLayerLocation("lorica_segmentata"),
        TUNIC = createLayerLocation("tunic");


    public static ModelLayerLocation createLayerLocation(String name) {
        return new ModelLayerLocation(new ResourceLocation(UtilityExpansion.MOD_ID, name), name);
    }
}
