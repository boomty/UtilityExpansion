package boomty.utilityexpansion.client;

import boomty.utilityexpansion.UtilityExpansion;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class CuriosLayerDefinition {
    public static final ModelLayerLocation
        LORICA_SEGMENTATA = createLayerLocation("lorica_segmentata"),
        TUNIC = createLayerLocation("tunic"),
        FACE_MASK = createLayerLocation("face_mask");

    public static ModelLayerLocation createLayerLocation(String name) {
        return new ModelLayerLocation(new ResourceLocation(UtilityExpansion.MOD_ID, name), name);
    }
}
