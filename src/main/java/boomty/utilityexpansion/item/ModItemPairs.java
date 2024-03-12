package boomty.utilityexpansion.item;

import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a map of all the mod items and its corresponding part. Used for multi-part simultaneous
 * armor equipment, i.e. tunic upper and lower simultaneous equipment.
 */

public class ModItemPairs {
    // correspondingItemStack allows user to get Itemstack from register ID
    private final Map<Item, Item> correspondingItemStack;
    // static class instance
    private static ModItemPairs instance;

    private ModItemPairs() {
        correspondingItemStack = new HashMap<>();
        instantiateMaps();
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ModItemPairs();
        }
    }

    public static ModItemPairs getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    public Map<Item, Item> getCorrespondingItemStack() { return correspondingItemStack; }

    // see if possible to merge into one hashMap
    // change correspondingItemStack to store <Item, Item> instead of <String, ItemStack>
    // delete itemPairs
    private void instantiateMaps() {
        correspondingItemStack.put(ItemRegistry.lorica_segmentata.get(),
                ItemRegistry.lorica_legs.get());
        correspondingItemStack.put(ItemRegistry.tunic.get(),
                ItemRegistry.tunic_legs.get());
    }
}
