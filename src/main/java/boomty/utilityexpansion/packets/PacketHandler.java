package boomty.utilityexpansion.packets;

import boomty.utilityexpansion.UtilityExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(UtilityExpansion.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init() {
        int index = 0;
        INSTANCE.messageBuilder(ServerboundArmorUpdatePacket.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerboundArmorUpdatePacket::encode).decoder(ServerboundArmorUpdatePacket::new)
                .consumer(ServerboundArmorUpdatePacket::handle).add();
        INSTANCE.messageBuilder(ServerboundCuriosInventoryUpdatePacket.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerboundCuriosInventoryUpdatePacket::encode).decoder(ServerboundCuriosInventoryUpdatePacket::new)
                .consumer(ServerboundCuriosInventoryUpdatePacket::handle).add();
    }
}
