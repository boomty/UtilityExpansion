package boomty.utilityexpansion.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashSet;
import java.util.Set;

/**
 * Weapon types for damage calculation
 */
public class WeaponTypes {
    private final Set<Item> bluntWeapons;
    private static WeaponTypes instance;

    // singleton pattern, access through getInstance()
    private WeaponTypes() {
        bluntWeapons = new HashSet<>();
        instantiateSets();
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new WeaponTypes();
        }
    }

    public static WeaponTypes getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private void instantiateSets() {
        // add blunt weapons into blunt weapon set
        bluntWeapons.add(Items.NETHERITE_PICKAXE);
        bluntWeapons.add(Items.DIAMOND_PICKAXE);
        bluntWeapons.add(Items.GOLDEN_PICKAXE);
        bluntWeapons.add(Items.IRON_PICKAXE);
        bluntWeapons.add(Items.STONE_PICKAXE);
        bluntWeapons.add(Items.WOODEN_PICKAXE);

        bluntWeapons.add(Items.NETHERITE_AXE);
        bluntWeapons.add(Items.DIAMOND_AXE);
        bluntWeapons.add(Items.GOLDEN_AXE);
        bluntWeapons.add(Items.IRON_AXE);
        bluntWeapons.add(Items.STONE_AXE);
        bluntWeapons.add(Items.WOODEN_AXE);

        bluntWeapons.add(Items.NETHERITE_HOE);
        bluntWeapons.add(Items.DIAMOND_HOE);
        bluntWeapons.add(Items.GOLDEN_HOE);
        bluntWeapons.add(Items.IRON_HOE);
        bluntWeapons.add(Items.STONE_HOE);
        bluntWeapons.add(Items.WOODEN_HOE);

        bluntWeapons.add(Items.NETHERITE_SHOVEL);
        bluntWeapons.add(Items.DIAMOND_SHOVEL);
        bluntWeapons.add(Items.GOLDEN_SHOVEL);
        bluntWeapons.add(Items.IRON_SHOVEL);
        bluntWeapons.add(Items.STONE_SHOVEL);
        bluntWeapons.add(Items.WOODEN_SHOVEL);
    }

    public Set<Item> getBluntWeapons() {
        return bluntWeapons;
    }
}
