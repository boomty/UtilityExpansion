package boomty.utilityexpansion.Events;

import boomty.utilityexpansion.registry.ItemRegistry;
import boomty.utilityexpansion.utilityexpansion;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= utilityexpansion.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void equipTunic(LivingEquipmentChangeEvent event)
    {
        Item TunicLegs = ItemRegistry.tunic_legs.get();
        //Initialize player object
        Player player = (Player) event.getEntity();
        //Check if the chest slot and leg slot are empty
        if (player.getItemBySlot(event.getSlot()).isEmpty() && player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
            //equip chestpiece onto chest slot
            player.setItemSlot(event.getSlot(), player.getItemInHand(InteractionHand.MAIN_HAND));
            //equip lower part on to leg slot
            player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(TunicLegs));
        }
    }
    
    @SubscribeEvent
    public static void equipLorica(LivingEquipmentChangeEvent event)
    {
        Item LoricaLegs = ItemRegistry.lorica_legs.get();
        //Initialize player object
        Player player = (Player) event.getEntity();
        //Check if the chest slot and leg slot are empty
        if (player.getItemBySlot(event.getSlot()).isEmpty() && player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
            //equip chestpiece onto chest slot
            player.setItemSlot(event.getSlot(), player.getItemInHand(InteractionHand.MAIN_HAND));
            //equip lower part on to leg slot
            player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(LoricaLegs));
        }
    }
}
