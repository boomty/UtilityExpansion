package boomty.utilityexpansion.events;

import boomty.utilityexpansion.registry.ItemRegistry;
import boomty.utilityexpansion.utilityexpansion;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= utilityexpansion.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void equipTunic(LivingEquipmentChangeEvent event)
    {
        if (event.getEntity() instanceof Player player)
        {
            Item tunicItem = ItemRegistry.tunic.get();
            Item tunicLegsItem = ItemRegistry.tunic_legs.get();

            if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == tunicItem) {
                //equip lower part on to leg slot
                player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(tunicLegsItem));
            }
            else if (player.hasItemInSlot(EquipmentSlot.LEGS) && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == tunicLegsItem)
            {
                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
            }
        }
    }

    @SubscribeEvent
    public static void equipLorica(LivingEquipmentChangeEvent event)
    {
        if (event.getEntity() instanceof Player player)
        {
            Item loricaLegs = ItemRegistry.lorica_legs.get();
            Item loricaSegmentata = ItemRegistry.lorica_segmentata.get();

            //Check if the chest slot and leg slot are empty
            if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == loricaSegmentata) {
                //equip lower part on to leg slot
                player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(loricaLegs));
            }
            else if (player.hasItemInSlot(EquipmentSlot.LEGS) && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == loricaLegs)
            {
                player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
            }
        }
    }

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
