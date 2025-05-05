package boomty.utilityexpansion.util.attachmentSystem;

import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Contains a map that maps the string name of a registry object to the registry object itself.
 **/
public class AttachmentStringToRegistryObjMap {
    public static final Map<String, RegistryObject<? extends ArmorItem>> attachmentMap = new HashMap<>();
    private static AttachmentStringToRegistryObjMap INSTANCE;

    private AttachmentStringToRegistryObjMap() {
        attachmentMap.put(Objects.requireNonNull(ItemRegistry.lorica_segmentata.get().getRegistryName()).toString(),
                ItemRegistry.lorica_segmentata);
    }

    public static AttachmentStringToRegistryObjMap getInstance() {
        if (INSTANCE == null) {
            synchronized (AttachmentStringToRegistryObjMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AttachmentStringToRegistryObjMap();
                }
            }
        }

        return INSTANCE;
    }
}
