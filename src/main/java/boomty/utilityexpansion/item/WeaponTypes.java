package boomty.utilityexpansion.item;

import java.util.HashSet;
import java.util.Set;

/**
 * Weapon types for damage calculation
 */
public class WeaponTypes {
    private final Set<String> swords;
    private final Set<String> bluntWeapons;
    private static WeaponTypes instance;

    // singleton pattern, access through getInstance()
    private WeaponTypes() {
        swords = new HashSet<>();
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
        // add swords into sword set
        swords.add("item.utilityexpansion.gladius");
        swords.add("item.utilityexpansion.spatha");

        swords.add("item.minecraft.netherite_sword");
        swords.add("item.minecraft.diamond_sword");
        swords.add("item.minecraft.golden_sword");
        swords.add("item.minecraft.iron_sword");
        swords.add("item.minecraft.stone_sword");
        swords.add("item.minecraft.wooden_sword");

        // add blunt weapons into blunt weapon set
        bluntWeapons.add("item.minecraft.netherite_pickaxe");
        bluntWeapons.add("item.minecraft.diamond_pickaxe");
        bluntWeapons.add("item.minecraft.golden_pickaxe");
        bluntWeapons.add("item.minecraft.iron_pickaxe");
        bluntWeapons.add("item.minecraft.stone_pickaxe");
        bluntWeapons.add("item.minecraft.wooden_pickaxe");

        bluntWeapons.add("item.minecraft.netherite_axe");
        bluntWeapons.add("item.minecraft.diamond_axe");
        bluntWeapons.add("item.minecraft.golden_axe");
        bluntWeapons.add("item.minecraft.iron_axe");
        bluntWeapons.add("item.minecraft.stone_axe");
        bluntWeapons.add("item.minecraft.wooden_axe");

        bluntWeapons.add("item.minecraft.netherite_hoe");
        bluntWeapons.add("item.minecraft.diamond_hoe");
        bluntWeapons.add("item.minecraft.golden_hoe");
        bluntWeapons.add("item.minecraft.iron_hoe");
        bluntWeapons.add("item.minecraft.stone_hoe");
        bluntWeapons.add("item.minecraft.wooden_hoe");

        bluntWeapons.add("item.minecraft.netherite_shovel");
        bluntWeapons.add("item.minecraft.diamond_shovel");
        bluntWeapons.add("item.minecraft.golden_shovel");
        bluntWeapons.add("item.minecraft.iron_shovel");
        bluntWeapons.add("item.minecraft.stone_shovel");
        bluntWeapons.add("item.minecraft.wooden_shovel");
    }
}
