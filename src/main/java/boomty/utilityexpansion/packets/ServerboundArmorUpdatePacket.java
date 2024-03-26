package boomty.utilityexpansion.packets;

import boomty.utilityexpansion.util.SlotIdConverter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * Packet that handles when armor pairs are being equipped or unequipped.
 */

public class ServerboundArmorUpdatePacket {
    public final ItemStack itemStack;
    public final int slotId;
    public ServerboundArmorUpdatePacket(ItemStack itemStack, int slotId) {
        this.itemStack = itemStack;
        this.slotId = slotId;
    }

    public ServerboundArmorUpdatePacket(FriendlyByteBuf buffer) {
        this(buffer.readItem(), buffer.readInt());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeItemStack(itemStack, true);
        buffer.writeInt(slotId);
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
            if (slotId >= 5 && slotId <= 8) {
                EquipmentSlot equipmentSlot = SlotIdConverter.getEquipmentSlotFromSlotId(slotId);
                player.setItemSlot(equipmentSlot, itemStack);
            }
            else if (slotId == -1) {
                player.addItem(itemStack);
            }
        });
        ctx.get().setPacketHandled(true);
        return success.get();
    }
}
