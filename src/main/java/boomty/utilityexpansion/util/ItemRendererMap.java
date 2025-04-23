package boomty.utilityexpansion.util;

import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemRendererMap {
    private static ItemRendererMap instance;
    public final Map<String, Item> itemStackMap;

    private ItemRendererMap() {
        this.itemStackMap = new HashMap<>();
        itemStackMap.put(String.valueOf(ItemRegistry.lorica_segmentata.get().getRegistryName()), ItemRegistry.lorica_segmentata.get());
    }

    public static ItemRendererMap getInstance() {
        if (instance == null) {
            synchronized (ItemRendererMap.class) {
                if (instance == null) {
                    instance = new ItemRendererMap();
                }
            }
        }
        return instance;
    }

}
