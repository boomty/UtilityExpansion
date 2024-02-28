package boomty.utilityexpansion.registry;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.client.renderer.armor.RomanArmorRenderer;
import boomty.utilityexpansion.client.renderer.armor.TunicItemRenderer;
import boomty.utilityexpansion.item.RomanArmorItem;
import boomty.utilityexpansion.item.TunicItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ItemRegistry{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UtilityExpansion.MOD_ID);

    public static final RegistryObject<RomanArmorItem> galea = ITEMS.register("galea",
            () -> new RomanArmorItem(ArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Properties()));

    public static final RegistryObject<RomanArmorItem> lorica_segmentata = ITEMS.register("lorica_segmentata",
            () -> new RomanArmorItem(ArmorMaterials.IRON, EquipmentSlot.CHEST, new Item.Properties()));
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

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(RomanArmorItem.class, () -> new RomanArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(TunicItem.class, () -> new TunicItemRenderer());
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
