package boomty.utilityexpansion.events;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import boomty.utilityexpansion.UtilityExpansion;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UtilityExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class EventHandler {
    @SubscribeEvent
    public static void onPlayerPickupItem(PlayerEvent.ItemPickupEvent event) {
        Player player = event.getPlayer();
        NonNullList<ItemStack> playerArmorList = player.getInventory().armor;
        System.out.println(playerArmorList.size());

        // Access the original player's inventory
        for (ItemStack item : playerArmorList) {
            if (!item.isEmpty()) {
                System.out.println(item.getItem().getDescription().getString());
            }
        }
    }

//    @SubscribeEvent
//    public static void equipCorrespondingComponent(LivingEquipmentChangeEvent event) {
//        ModItemPairs modItems = ModItemPairs.getInstance();
//        Map<Item, Item> correspondingItems = modItems.getCorrespondingItemStack();
//
//        if (event.getEntity() instanceof Player player && !player.getLevel().isClientSide) {
//            EquipmentSlot slot = event.getSlot();
//            ItemStack itemStack = player.getItemBySlot(slot);
//
//            if (correspondingItems.containsKey(itemStack.getItem())) {
//                ItemStack correspondingItem = new ItemStack(correspondingItems.get(itemStack.getItem()));
//                ItemStack currentItem = player.getItemBySlot(EquipmentSlot.LEGS);
//                System.out.println(currentItem + "hi");
//
//                if (currentItem.isEmpty()) {
//                    System.out.println("hihi");
//                    player.setItemSlot(EquipmentSlot.LEGS, correspondingItem);
//                }
//                else if (!correspondingItems.containsValue(currentItem.getItem())) {
//                    player.addItem(currentItem);
//                    player.setItemSlot(EquipmentSlot.LEGS, correspondingItem);
//                }
//            }
//        }
//    }

}
