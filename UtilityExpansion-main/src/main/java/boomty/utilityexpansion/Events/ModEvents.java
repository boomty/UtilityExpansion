package boomty.utilityexpansion.Events;

import boomty.utilityexpansion.UtilityExpansion;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= UtilityExpansion.MOD_ID)
public class ModEvents {
//    @SubscribeEvent
//    public static void unequipArmor(ScreenEvent.MouseClickedEvent mouseClickedEvent) {
//        if (mouseClickedEvent.getScreen() instanceof InventoryScreen) {
//            ModItems modItems = new ModItems();
//            Hashtable<String, String> itemMap = modItems.getItemMap();
//            Player player = mouseClickedEvent.getScreen().getMinecraft().player;
//            if (!itemMap.containsKey(player.getItemBySlot(EquipmentSlot.CHEST).getItem().getRegistryName())
//            && itemMap.containsValue(player.getItemBySlot(EquipmentSlot.LEGS))) {
//                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
//            }
//        }
//    }

//    @SubscribeEvent
//    public static void equipTunic(LivingEquipmentChangeEvent event)
//    {
//        if (event.getEntity() instanceof Player player)
//        {
//            Item tunicItem = ItemRegistry.tunic.get();
//            Item tunicLegsItem = ItemRegistry.tunic_legs.get();
//
//            // if player equips tunic and there is nothing in the leg slot
//            if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == tunicItem && !player.hasItemInSlot(EquipmentSlot.LEGS)) {
//                //equip lower part on to leg slot
//                player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(tunicLegsItem));
//            }
//            // if the player equips a tunic and there is something already in the leg slot
//            else if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == tunicItem && player.getItemBySlot(EquipmentSlot.LEGS).getItem() != tunicLegsItem)
//            {
//                // save the item currently on leg slot
//                ItemStack currentLeggings = player.getItemBySlot(EquipmentSlot.LEGS);
//                player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(tunicLegsItem));
//                player.setItemSlot(EquipmentSlot.MAINHAND, currentLeggings);
//            }
//            // if the player unequips the tunic
//            else if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() != tunicItem && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == tunicLegsItem) {
//                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
//            }
//            else if (player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == tunicLegsItem) {
//                player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public static void equipLorica(LivingEquipmentChangeEvent event)
//    {
//        if (event.getEntity() instanceof Player player)
//        {
//            Item loricaLegs = ItemRegistry.lorica_legs.get();
//            Item loricaSegmentata = ItemRegistry.lorica_segmentata.get();
//
//            // if player equips tunic and there is nothing in the leg slot
//            if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == loricaSegmentata && !player.hasItemInSlot(EquipmentSlot.LEGS)) {
//                //equip lower part on to leg slot
//                player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(loricaLegs));
//            }
//            // if the player equips a tunic and there is something already in the leg slot
//            else if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == loricaSegmentata && player.getItemBySlot(EquipmentSlot.LEGS).getItem() != loricaLegs)
//            {
//                // save the item currently on leg slot
//                ItemStack currentLeggings = player.getItemBySlot(EquipmentSlot.LEGS);
//                player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(loricaLegs));
//                player.setItemSlot(EquipmentSlot.MAINHAND, currentLeggings);
//            }
//            // if the player unequips the tunic
//            else if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() != loricaSegmentata && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == loricaLegs) {
//                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
//            }
//            else if (player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == loricaLegs) {
//                player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public static void equipArmorStand(LivingEquipmentChangeEvent event)
//    {
//        if (event.getEntity() instanceof ArmorStand armorStand)
//        {
//            Item loricaLegs = ItemRegistry.lorica_legs.get();
//            Item loricaSegmentata = ItemRegistry.lorica_segmentata.get();
//
//            Item tunicItem = ItemRegistry.tunic.get();
//            Item tunicLegsItem = ItemRegistry.tunic_legs.get();
//
//            //Check if the chest slot and leg slot are empty
//            if (armorStand.getItemBySlot(EquipmentSlot.CHEST).getItem() == loricaSegmentata) {
//                //equip lower part on to leg slot
//                armorStand.setItemSlot(EquipmentSlot.LEGS, new ItemStack(loricaLegs));
//            }
//            else if (armorStand.hasItemInSlot(EquipmentSlot.LEGS) && armorStand.getItemBySlot(EquipmentSlot.LEGS).getItem() == loricaLegs)
//            {
//                armorStand.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
//            }
//            else if (armorStand.getItemBySlot(EquipmentSlot.CHEST).getItem() == tunicItem) {
//                //equip lower part on to leg slot
//                armorStand.setItemSlot(EquipmentSlot.LEGS, new ItemStack(tunicLegsItem));
//            }
//            else if (armorStand.hasItemInSlot(EquipmentSlot.LEGS) && armorStand.getItemBySlot(EquipmentSlot.LEGS).getItem() == tunicLegsItem)
//            {
//                armorStand.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
//            }
//        }
//    }


//    @SubscribeEvent
//    public static void equipArmorStand(PlayerInteractEvent.EntityInteract event)
//    {
//        Entity entity = event.getTarget();
//        if (entity.getType() == EntityType.ARMOR_STAND)
//        {
//            //Declare itemstacks for the items to be checked for
//            ItemStack LoricaSegmentata = new ItemStack(ItemRegistry.lorica_segmentata.get());
//            ItemStack Tunic = new ItemStack(ItemRegistry.tunic.get());
//            //Check if the item that is being equipped is one of the declared itemstacks
//            if (event.getItemStack().equals(Tunic) || event.getItemStack().equals(LoricaSegmentata))
//            {
//                //Get the player that is interacting with the item
//                Player player = event.getPlayer();
//                //Get the interaction hand
//                InteractionHand hand = event.getHand();
//                //Create an itemstack for the item in hand
//                ItemStack item = player.getItemInHand(hand);
//                //Get the item's slot
//                EquipmentSlot slot = LivingEntity.getEquipmentSlotForItem(item);
//                //Create an itemstack for the slot
//                ItemStack slotItemStack = player.getItemBySlot(slot);
//                //Create an itemstack for the leg slot
//                ItemStack legSlot = player.getItemBySlot(EquipmentSlot.LEGS);
//                //Check if the itemstack that the user is trying to equip the armor is empty
//                if (slotItemStack.isEmpty() && legSlot.isEmpty())
//                {
//                    if (event.getItemStack() == LoricaSegmentata)
//                    {
//                        Item LoricaLegs = ItemRegistry.lorica_legs.get();
//                        //equip chestpiece onto chest slot
//                        entity.setItemSlot(slot, player.getItemInHand(InteractionHand.MAIN_HAND));
//                        //equip lower part on to leg slot
//                        entity.setItemSlot(EquipmentSlot.LEGS, new ItemStack(LoricaLegs));
//                    }
//                    else if(event.getItemStack() == Tunic)
//                    {
//                        Item TunicLegs = ItemRegistry.tunicLegs.get();
//                        //equip chestpiece onto chest slot
//                        player.setItemSlot(slot, player.getItemInHand(InteractionHand.MAIN_HAND));
//                        //equip lower part on to leg slot
//                        player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(TunicLegs));
//                    }
//                }
//            }
//        }
//    }
}
