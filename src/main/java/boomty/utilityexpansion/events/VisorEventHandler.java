package boomty.utilityexpansion.events;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.client.InitializeKeys;
import boomty.utilityexpansion.client.renderer.armor.VisoredHelmetRenderer;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import boomty.utilityexpansion.item.armorTypes.headArmor.VisoredHelmet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = UtilityExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VisorEventHandler implements Publisher {
    private static Subscriber subscriber = null;
    private static boolean previousState = false;
    private static UUID playerUUID = null;
    private static ItemStack headArmor = null;
    private static int counter = 0;

    public static void attachSubscriber(Subscriber subscriber) {
        if (subscriber instanceof VisoredHelmet) {
            VisorEventHandler.subscriber = subscriber;
        }
    }

    public static void publish() {
        // only update visored helmets that are currently equipped
        if (subscriber instanceof VisoredHelmet && headArmor != null) {
            if (((VisoredHelmet) subscriber).getRegistryName() == headArmor.getItem().getRegistryName()) {
                if (subscriber instanceof VisoredHelmet) {
                    VisoredHelmet.updatePlayerUUID(playerUUID);
                    subscriber.update();
                }
            }
        }
    }

    @SubscribeEvent
    public static void inventoryTracker(PlayerTickEvent e) {
        ItemStack prevHeadArmor = headArmor;
        playerUUID = e.player.getUUID();

        if (prevHeadArmor == null) {
            headArmor = e.player.getInventory().armor.get(3);
            VisoredHelmet.updateItemStack(headArmor);
        }

        headArmor = e.player.getInventory().armor.get(3);

        if (headArmor != prevHeadArmor) {
            VisoredHelmet.updateItemStack(headArmor);
        }
    }

    private static void initializeNBT(CompoundTag nbtData, ItemStack headArmor) {
        nbtData.putBoolean("eventFulfilled", true);
        nbtData.putBoolean("hasVisor", true);
        nbtData.putBoolean("hasWrapper", true);
        nbtData.putBoolean("isVisorUp", false);
//        nbtData.putBoolean("isRunning", false);
//        nbtData.putBoolean("animationQueued", false);

        headArmor.setTag(nbtData);
    }

    @SubscribeEvent
    public static void visorEvent(TickEvent.ClientTickEvent e) {
        if (headArmor != null) {
            // ensure that the current subscriber renderer is for the player
            if (headArmor.getItem() instanceof VisoredHelmet) {
                CompoundTag nbtData = headArmor.getTag();

                if (nbtData != null) {
                    // if the itemstack's nbt is not initialized yet
                    if (!nbtData.contains("eventFulfilled")) {
                        initializeNBT(nbtData, headArmor);
                    }
                    // ensure that this is a new event not a previous one that hasn't been fulfilled yet (prevent spamming)
//                    else if (nbtData.getBoolean("eventFulfilled") && !nbtData.getBoolean("animationQueued")) {
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
