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

                    System.out.println("Entity pos x: " + entityPos.x + " pos y: " + entityPos.z);
                    System.out.println("Arrow pos x: " + arrowPos.x + " pos y: " + arrowPos.z);

                    Line lineSeg = getShoulderAxis(entity);
                    Line shiftedLine = shiftLine(lineSeg, entity, arrow);

                    if (arrowPos.y > entityPos.y + 0.61875) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    private static float convertAngle(LivingEntity entity) {
        float entityYawDeg = entity.yBodyRot;
        if (entityYawDeg > 360) {
            entityYawDeg %= 360;
        }
        if (entityYawDeg < 0) {
            entityYawDeg += 360;
        }

        return entityYawDeg;
    }

    private static float getEquivalentAngle(float angle) {
        // narrow angles down to the second and third quadrants only
        if (angle > 180) {
            angle = (angle + 180) % 360;
        }
        System.out.println(angle);
        // if the angle is less than 90 deg use the complementary angle
        if (angle < 90) {
            angle = 90 - angle;
        }
        else if (angle > 90) {
            angle = angle - 90;
        }

        return angle;
    }

    private static Line getShoulderAxis(LivingEntity entity) {
        final double torsoRadius = 0.2813;
        Vec3 entityPos = entity.position();

        // get original angle
        float entityYawDeg = convertAngle(entity);

        // get the angle of the line that goes across the torso (perpendicular angle)
        float originalPerpendicularYawDeg = (entityYawDeg + 90) % 360;

        // get equivalent angle that is easier to work with
        double perpendicularYawDeg = getEquivalentAngle(originalPerpendicularYawDeg);
        double perpendicularYawRad = Math.toRadians(perpendicularYawDeg);

        double horizontalComponent = torsoRadius * Math.cos(perpendicularYawRad);
        double verticalComponent = torsoRadius * Math.sin(perpendicularYawRad);

        // apply correct sign to the components based on angle
        horizontalComponent = -horizontalComponent;

        if (originalPerpendicularYawDeg > 90) {
            verticalComponent = -verticalComponent;
        }

        // calculate two x,y coordinates
        double minX = entityPos.x + horizontalComponent;
        double minZ = entityPos.z + verticalComponent;

        double maxX = entityPos.x - horizontalComponent;
        double maxZ = entityPos.z - verticalComponent;

        System.out.println("Entity yaw: " + entityYawDeg);
        System.out.println("Perpendicular yaw: " + perpendicularYawDeg);
        System.out.println("Original line minX: " + minX + " minZ: " + minZ + " maxX: " + maxX + " maxZ: " + maxZ);
        System.out.println();
        return new Line(minX, minZ, maxX, maxZ);
    }

    private static Line shiftLine(Line shoulderAxis, LivingEntity entity, AbstractArrow arrow) {
        Vec3 arrowPos = arrow.position();
        Vec3 entityPos = entity.position();
        // get angle that torso is facing
        float entityYawDeg = convertAngle(entity);
        System.out.println("Entity yaw: " + entityYawDeg);
        // convert angle to equivalent value
        entityYawDeg = getEquivalentAngle(entityYawDeg);
        // convert to radians
        double entityYawRad = Math.toRadians(entityYawDeg);

        // plug in the y coordinate from the arrow to see where the current torso line and the arrow intersect
        System.out.println("Y-intercept: " + shoulderAxis.getIntercept() + " slope: " + shoulderAxis.getSlope());
        // get the x coordinate when the z coordinate equals the arrow's z coordinate (also make it + b not - b because z is inverted)
        double x = (-arrow.position().z - shoulderAxis.getIntercept())/shoulderAxis.getSlope();
        System.out.println(-arrow.position().z + "" + "" + shoulderAxis.getIntercept() + "" + "/" + shoulderAxis.getSlope());
        System.out.println("x: " + x);
        // find the x distance between the arrow and the torso line
        double distance = Math.abs(arrowPos.x) - Math.abs(x);
        System.out.println("Distance: " + distance);
        double verticalComponent;
        double horizontalComponent = distance * Math.cos(entityYawRad);

        if (distance != 0) {
            verticalComponent = distance * Math.sin(entityYawDeg);
        }
        // if distance is 0, it means that it is hitting the arm
        else {
            return null;
        }

        // need to fix comparison to check the angle rather than the arrow's position
        if (arrowPos.x < entityPos.x) {
            horizontalComponent = -horizontalComponent;
        }
        if (arrowPos.z < entityPos.z) {
            verticalComponent = -verticalComponent;
        }

        System.out.println("horizontal component: " + horizontalComponent);
        System.out.println("vertical component: " + verticalComponent);

        double minX = shoulderAxis.getMinX() + horizontalComponent;
        double minZ = shoulderAxis.getMinY() + verticalComponent;

        double maxX = shoulderAxis.getMaxX() + horizontalComponent;
        double maxZ = shoulderAxis.getMaxY() + verticalComponent;

        // need to fix vertical component adding

        System.out.println("Entity yaw: " + entityYawDeg);
        System.out.println("Shifted line minX: " + minX + " minZ: " + minZ + " maxX: " + maxX + " maxZ: " + maxZ);
        System.out.println();
        return new Line(minX, minZ, maxX, maxZ);
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
