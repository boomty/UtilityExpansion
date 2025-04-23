package boomty.utilityexpansion.events;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.client.InitializeKeys;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UtilityExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttachmentScreenEventHandler {
    private static boolean previousState = false;
    private static int counter = 0;

    @SubscribeEvent
    public static void screenKeyPressed(TickEvent.ClientTickEvent e) {
        boolean currentState = InitializeKeys.toggleVisor.isDown();
        if (currentState != previousState) {
            previousState = currentState;
            counter++;
            if (counter == 2) {
                
                counter = 0;
            }
        }
    }
}
