package boomty.utilityexpansion.events;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.client.InitializeKeys;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import boomty.utilityexpansion.item.armorTypes.headArmor.VisoredHelmet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
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
        ItemStack prevHeadArmor = headArmor;

        if (prevHeadArmor == null) {
            headArmor = e.player.getInventory().armor.get(3);
            VisoredHelmet.updateItemStack(headArmor);
        }

        headArmor = e.player.getInventory().armor.get(3);

        if (headArmor != prevHeadArmor) {
            VisoredHelmet.updateItemStack(headArmor);
        }
    }

    @SubscribeEvent
    public static void visorEvent(TickEvent.ClientTickEvent e) {
        // ensure that this is a new event not a previous one that hasn't been fulfilled yet (prevent spamming)
        if (headArmor != null) {
            if (headArmor.getItem() instanceof VisoredHelmet) {
                CompoundTag nbtData = headArmor.getTag();

                if (nbtData != null) {
                    if (!nbtData.contains("eventFulfilled")) {
                        nbtData.putBoolean("eventFulfilled", true);
                        nbtData.putBoolean("hasVisor", true);
                        nbtData.putBoolean("isVisorUp", false);
                        nbtData.putBoolean("isRunning", false);

                        headArmor.setTag(nbtData);
                    }
                    else if (nbtData.getBoolean("eventFulfilled")) {
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
        }
    }
}
