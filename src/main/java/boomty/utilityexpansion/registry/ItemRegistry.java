package boomty.utilityexpansion.registry;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.item.armorItems.bodyArmor.LoricaSegmentata;
import boomty.utilityexpansion.item.armorItems.curios.FaceMaskItem;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import boomty.utilityexpansion.item.armorItems.headArmor.Galea;
import boomty.utilityexpansion.item.RomanArmorItem;
import boomty.utilityexpansion.item.TunicItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UtilityExpansion.MOD_ID);

    public static final RegistryObject<FaceMaskItem> face_mask = ITEMS.register("face_mask",
            () -> new FaceMaskItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties()));

    public static final RegistryObject<ArmetItalian> armet_italian = ITEMS.register("armet_italian",
            () -> new ArmetItalian(ArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Properties()));

    public static final RegistryObject<Galea> galea = ITEMS.register("galea",
            () -> new Galea(ArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Properties()));

    public static final RegistryObject<LoricaSegmentata> lorica_segmentata = ITEMS.register("lorica_segmentata",
            () -> new LoricaSegmentata(ArmorMaterials.IRON, EquipmentSlot.CHEST, new Item.Properties()));

    public static final RegistryObject<RomanArmorItem> lorica_legs = ITEMS.register("lorica_legs",
            () -> new RomanArmorItem(ArmorMaterials.IRON, EquipmentSlot.LEGS, new Item.Properties()));

    public static final RegistryObject<RomanArmorItem> caligae = ITEMS.register("caligae",
            () -> new RomanArmorItem(ArmorMaterials.IRON, EquipmentSlot.FEET, new Item.Properties()));

    public static final RegistryObject<TunicItem> tunic = ITEMS.register("tunic",
            () -> new TunicItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Properties()));

//    public static final RegistryObject<TunicItem> tunicItem = ITEMS.register("tunic_item", () -> new TunicItem(new Item.Properties()));

    public static final RegistryObject<TunicItem> tunic_legs = ITEMS.register("tunic_legs",
            () -> new TunicItem(ArmorMaterials.LEATHER, EquipmentSlot.LEGS, new Item.Properties()));

    public static final RegistryObject<Item> gladius = ITEMS.register("gladius",
            () -> new SwordItem(Tiers.IRON, 7, 1.6f,
                    new Item.Properties().tab(UtilityExpansion.utilexpanseitemgroup).stacksTo(1)));

    public static final RegistryObject<Item> spatha = ITEMS.register("spatha",
            () -> new SwordItem(Tiers.IRON, 7, 1.6f,
                    new Item.Properties().tab(UtilityExpansion.utilexpanseitemgroup).stacksTo(1)));

    public static final RegistryObject<ShieldItem> scutum = ITEMS.register("scutum",
            () -> new ShieldItem(new Item.Properties().tab(UtilityExpansion.utilexpanseitemgroup).stacksTo(1)));


//    public static final RegistryObject<Item> animated_item = ITEMS.register("animated_item",
//            () -> new TestingItem(Tiers.IRON, 7, 1.6f,
//                    new Item.Properties().tab(utilityexpansion.utilexpanseitemgroup).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
