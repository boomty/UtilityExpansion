package boomty.utilityexpansion.events;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import boomty.utilityexpansion.util.Line;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import boomty.utilityexpansion.UtilityExpansion;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Abs;

import java.util.List;
import java.util.Random;

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

//    @SubscribeEvent
//    public static void onArrowHit(ProjectileImpactEvent event) {
//        HitResult rayTraceResult = event.getRayTraceResult();
//        if (rayTraceResult.getType() == HitResult.Type.ENTITY && event.getProjectile() instanceof Arrow arrow) {
//            event.setCanceled(true);
//        }
//    }

//    @SubscribeEvent
//    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
//        Player player = event.player;
//        if (!player.level.isClientSide ) {
//            Vec3 pos = player.position();
//            System.out.println("pos x " + pos.x);
//            System.out.println("pos y " + pos.y);
//            System.out.println("pos z " + pos.z);
//
//            List<Arrow> arrows = player.level.getEntitiesOfClass(Arrow.class, new AABB(pos.x - 5, pos.y - 5,
//                    pos.z - 5, pos.x + 5, pos.y + 5, pos.z + 5));
//
//            for (Arrow arrow : arrows) {
//                if (arrow.getOwner() != player) {
//                    System.out.println("Arrow detected!");
//                    arrow.setDeltaMovement(arrow.getDeltaMovement().x - player.getDeltaMovement().x,
//                            arrow.getDeltaMovement().y - player.getDeltaMovement().y,
//                            arrow.getDeltaMovement().z - player.getDeltaMovement().z);
//                }
//            }
//        }
//    }

    @SubscribeEvent
    public static void onEntityShot(LivingAttackEvent event) {
        if (event.getSource() instanceof IndirectEntityDamageSource projectile) {
            if (projectile.getEntity() instanceof AbstractArrow arrow) {
                if (event.getEntity() instanceof LivingEntity entity) {
                    // just calculate the position of the arrow relative to the player
                    Vec3 arrowPos = arrow.position();
                    Vec3 entityPos = entity.position();

                    System.out.println("Entity yaw: " + entity.yBodyRot % 360.0);
                    System.out.println("Entity pos x: " + entityPos.x + " pos y: " + entityPos.z);
                    System.out.println("Arrow pos x: " + arrowPos.x + " pos y: " + arrowPos.z);

                    Line lineSeg = getShoulderAxis(entity);

                    if (arrowPos.y > entityPos.y + 0.61875) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
    private static Line getShoulderAxis(LivingEntity entity) {
        final double torsoRadius = 0.2813;
        Vec3 entityPos = entity.position();

        // get the angle the torso is facing at
        float perpendicularYawDeg = entity.yBodyRot + 90;
        double perpendicularYawRad = (perpendicularYawDeg * Math.PI)/180;

        // calculate two x,y coordinates
        double minX = -entityPos.x - torsoRadius;
        double minY = -entityPos.z - torsoRadius * Math.tan(perpendicularYawRad);
        double maxX = entityPos.x + torsoRadius;
        double maxY = entityPos.z + torsoRadius * Math.tan(perpendicularYawRad);

        // make coordinates negative if necessary
        if (entityPos.x < 0) {
            minX = -minX;
            minY = -minY;
        }
        if (entityPos.y < 0) {
            maxX = -maxX;
            maxY = -maxY;
        }

        System.out.println("minX: " + minX + " minY: " + minY + " maxX: " + maxX + " maxY: " + maxY);
        return new Line(minX, minY, maxX, maxY);
    }

//    private int arrowDamagedPart(AbstractArrow arrow, LivingEntity entity) {
//        Vec3 arrowPos = arrow.position();
//        Vec3 playerPos = entity.position();
//
//        // hit head
//        if (arrowPos.y > playerPos.y + 1.2375) {
//
//        }
//        // hit torso or arms
//        else if (arrowPos.y > playerPos.y + 0.61875) {
//
//        }
//        // hit legs
//        else {
//
//        }
//    }
//
//    private boolean isHitArm(AbstractArrow arrow, LivingEntity entity) {
//        // get direction entity is heading in
//        Vec2 entityDirection = entity.getRotationVector();
//        // get the perpendicular vector
//        Vec2 perpendicularVec = new Vec2(-entityDirection.y, entityDirection.x).normalized();
//        // get entity position
//        Vec3 entityPos = entity.position();
//
//        // calculate line that goes across entity body
//        double torsoRadius = 0.2813;
//        double minX = entityPos.x + torsoRadius * perpendicularVec.x;
//        double minY = entityPos.y + torsoRadius * perpendicularVec.y;
//        double maxX = entityPos.x - torsoRadius * perpendicularVec.x;
//        double maxY = entityPos.y - torsoRadius * perpendicularVec.y;
//
//        Line entityLine = new Line(minX, minY, maxX, maxY);
//
//
//    }

//    @SubscribeEvent
//    public static void equipCorrespondingComponent(LivingEquipmentChangeEvent event) {
//        ModItemPairs modItems = ModItemPairs.getInstance();
//        Map<Item, Item> correspondingItems = modItems.getCorrespondingItemStack();
//
//        if (event.getEntity() instanceof Player player && !player.getLevel().isClientSide) {
//            EquipmentSlot slot = event.getSlot();
//            ItemStack itemStack = player.getItemBySlot(slot);
//
//            if (correspondingItems.containsKey(itemStack.getItem())) {
//                ItemStack correspondingItem = new ItemStack(correspondingItems.get(itemStack.getItem()));
//                ItemStack currentItem = player.getItemBySlot(EquipmentSlot.LEGS);
//                System.out.println(currentItem + "hi");
//
//                if (currentItem.isEmpty()) {
//                    System.out.println("hihi");
//                    player.setItemSlot(EquipmentSlot.LEGS, correspondingItem);
//                }
//                else if (!correspondingItems.containsValue(currentItem.getItem())) {
//                    player.addItem(currentItem);
//                    player.setItemSlot(EquipmentSlot.LEGS, correspondingItem);
//                }
//            }
//        }
//    }

}
