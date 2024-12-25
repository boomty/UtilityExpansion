package boomty.utilityexpansion.events;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.client.InitializeKeys;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = UtilityExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VisorEventHandler implements Publisher {
    private static List<Subscriber> subscriberList = new ArrayList<>();
    private static boolean previousState = false;
    private static int counter = 0;

    public static void attachSubscriber(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    public static void removeSubscriber(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    public static void publish() {
        for (Subscriber sub : subscriberList) {
            sub.update();
        }
    }

    @SubscribeEvent
    public static void visorEvent(TickEvent.ClientTickEvent e) {
        if (ArmetItalian.eventFulfilled) {
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
