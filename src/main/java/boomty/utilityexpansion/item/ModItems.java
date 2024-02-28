package boomty.utilityexpansion.item;

import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.world.item.ItemStack;

import java.util.Hashtable;
import java.util.Map;

/**
 * Contains a map of all the mod items and its corresponding part. Used for multi-part simultaneous
 * armor equipment, i.e. tunic upper and lower simultaneous equipment.
 */

public class ModItems {
    // itemPairs allows user to find the register ID of a corresponding armor item
    private Map<String, String> itemPairs = new Hashtable<>();
    // correspondingItemStack allows user to get Itemstack from register ID
    private Map<String, ItemStack> correspondingItemStack = new Hashtable<>();

    public ModItems() {
        itemPairs.put("item.utilityexpansion.lorica_segmentata", "item.utilityexpansion.lorica_legs");
        itemPairs.put("item.utilityexpansion.tunic", "item.utilityexpansion.tunic_legs");

        correspondingItemStack.put("item.utilityexpansion.lorica_legs", new ItemStack(ItemRegistry.lorica_legs.get()));
        correspondingItemStack.put("item.utilityexpansion.tunic_legs", new ItemStack(ItemRegistry.tunic_legs.get()));
    }

    public Map<String, String> getItemMap() {
        return itemPairs;
    }
    public Map<String, ItemStack> getCorrespondingItemStack() { return correspondingItemStack; }
}
