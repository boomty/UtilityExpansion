package boomty.utilityexpansion.events;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.client.InitializeKeys;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import boomty.utilityexpansion.item.armorTypes.headArmor.VisoredHelmet;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = UtilityExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VisorEventHandler implements Publisher {
    private static List<Subscriber> subscriberList = new ArrayList<>();
    private static boolean previousState = false;
    private static ItemStack headArmor = null;
    private static int counter = 0;

    public static void attachSubscriber(Subscriber subscriber) {
        if (subscriber instanceof VisoredHelmet) {
            subscriberList.add(subscriber);
        }
    }

    public static void removeSubscriber(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    public static void publish() {
        for (Subscriber sub : subscriberList) {
            // only update visored helmets that are currently equipped
            if (sub instanceof VisoredHelmet && headArmor != null) {
                if (((VisoredHelmet) sub).getRegistryName() == headArmor.getItem().getRegistryName()) {
                    sub.update();
                }
            }
        }
    }

    @SubscribeEvent
    public static void inventoryTracker(PlayerTickEvent e) {
        headArmor = e.player.getInventory().armor.get(3);
    }

    @SubscribeEvent
    public static void visorEvent(TickEvent.ClientTickEvent e) {
        // ensure that this is a new event not a previous one that hasn't been fulfilled yet (prevent spamming)
        if (ArmetItalian.eventFulfilled) {
            // keep track of the state of the key to ensure that the action occurs only after the key is released
            boolean currentState = InitializeKeys.toggleVisor.isDown();
            if (currentState != previousState) {
                previousState = currentState;
                counter++;
                if (counter == 2) {
                    publish();
                    counter = 0;
                }
            }
        }
    }
}
