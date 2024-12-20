package boomty.utilityexpansion.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * Packet that handles when armor pairs are being equipped or unequipped.
 */

public class ServerboundCuriosInventoryUpdatePacket {
    public final ItemStack itemStack;
    public final byte[] identifier;
    public final int index;
    public ServerboundCuriosInventoryUpdatePacket(ItemStack itemStack, byte[] identifier, int index) {
        this.itemStack = itemStack;
        this.identifier = identifier;
        this.index = index;
    }

    public ServerboundCuriosInventoryUpdatePacket(FriendlyByteBuf buffer) {
        this(buffer.readItem(), buffer.readByteArray(), buffer.readInt());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeItemStack(itemStack, true);
        buffer.writeByteArray(identifier);
        buffer.writeInt(index);
    }

    /*
    Method: handle
    Return: boolean
    Purpose: Update player inventory on server side based on packet info
     */
    @SuppressWarnings("ConstantConditions")
    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            CuriosApi.getCuriosHelper().setEquippedCurio(player, new String(identifier), index, itemStack);
        });
        ctx.get().setPacketHandled(true);
        return success.get();
    }
}
