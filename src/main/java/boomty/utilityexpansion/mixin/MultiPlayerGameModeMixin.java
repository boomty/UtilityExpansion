package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.item.ModItemPairs;
import boomty.utilityexpansion.packets.UtilityExpansionPacketHandler;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.simple.SimpleChannel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {
    @Shadow @Final private ClientPacketListener connection;
    @Unique
    private static Int2ObjectMap<ItemStack> objInt2ObjectMap = new Int2ObjectOpenHashMap<>();

    @Inject(method = "handleInventoryMouseClick", at =@At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;send(Lnet/minecraft/network/protocol/Packet;)V", shift = At.Shift.BEFORE))
    public void addCorrespondingPart(int p_171800_, int p_171801_, int p_171802_, ClickType p_171803_, Player p_171804_, CallbackInfo ci) {
        ModItemPairs modItems = ModItemPairs.getInstance();
        Map<Item, Item> correspondingItems = modItems.getCorrespondingItemStack();

        ItemStack chestItemStack = p_171804_.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legItemStack = p_171804_.getItemBySlot(EquipmentSlot.LEGS);

        System.out.println(chestItemStack);
        System.out.println(legItemStack);
        System.out.println(correspondingItems.containsKey(chestItemStack.getItem()));
        System.out.println(legItemStack.isEmpty());

        if (p_171804_.getLevel() instanceof ClientLevel) {
            // if player is equipping corresponding item and leg slot is empty
            if (correspondingItems.containsKey(chestItemStack.getItem()) && legItemStack.isEmpty()) {
                System.out.println(correspondingItems.get(chestItemStack.getItem()).getDescriptionId());
                ItemStack newLegItemStack = new ItemStack(correspondingItems.get(chestItemStack.getItem()));

                p_171804_.setItemSlot(EquipmentSlot.LEGS, newLegItemStack);

                // need to send packet to server side
            }
        }
    }

//    @ModifyArg(method = "handleInventoryMouseClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;send(Lnet/minecraft/network/protocol/Packet;)V", shift = At.Shift.BEFORE), index = 6)
//    public Int2ObjectMap<ItemStack> modifyMap(Int2ObjectMap<ItemStack> int2ObjectMap) {
//        int2ObjectMap.put(7, objInt2ObjectMap.get(7));
//        objInt2ObjectMap.clear();
//        System.out.println(int2ObjectMap.get(7));
//        return int2ObjectMap;
//    }
}