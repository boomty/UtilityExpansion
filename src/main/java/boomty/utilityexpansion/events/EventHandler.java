package boomty.utilityexpansion.events;

import boomty.utilityexpansion.client.InitializeKeys;
import net.minecraft.core.NonNullList;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import boomty.utilityexpansion.UtilityExpansion;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static boomty.utilityexpansion.util.HitLocationCalculator.arrowDamagedPart;

@Mod.EventBusSubscriber(modid = UtilityExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class EventHandler {
    @SubscribeEvent
    public static void onPlayerPickupItem(PlayerEvent.ItemPickupEvent event) {
        Player player = event.getPlayer();
        NonNullList<ItemStack> playerArmorList = player.getInventory().armor;
        System.out.println(playerArmorList.size());

        // Access the original player's inventory
        for (ItemStack item : playerArmorList) {
            if (!item.isEmpty()) {
                System.out.println(item.getItem().getDescription().getString());
            }
        }
    }

    @SubscribeEvent
    public static void onEntityShot(LivingAttackEvent event) {
        if (event.getSource() instanceof IndirectEntityDamageSource projectile) {
            if (projectile.getEntity() instanceof AbstractArrow arrow) {
                if (event.getEntity() instanceof LivingEntity entity) {
                    // just calculate the position of the arrow relative to the player
                    Vec3 arrowPos = arrow.position();
                    Vec3 entityPos = entity.position();

                    System.out.println("Entity pos x: " + entityPos.x + " pos y: " + entityPos.z);
                    System.out.println("Arrow pos x: " + arrowPos.x + " pos y: " + arrowPos.z);

                    if (arrowDamagedPart(arrow, entity) == 2) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
