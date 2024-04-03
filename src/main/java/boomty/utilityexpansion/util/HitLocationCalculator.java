package boomty.utilityexpansion.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.Vec3;

/**
 * Calculate the body part that was hit by an arrow
 */

public class HitLocationCalculator {
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

    private static float narrowAngle(float angle) {
        // narrow angles down to the second and third quadrants only
        if (angle > 180) {
            return (angle + 180) % 360;
        }
        else {
            return angle;
        }
    }

    private static float getEquivalentAngle(float angle) {
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
        originalPerpendicularYawDeg = narrowAngle(originalPerpendicularYawDeg);

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

    private static boolean comparePosition(Vec3 arrowPos, Vec3 entityPos) {
        double xDiff = Math.abs(Math.abs(arrowPos.x) - Math.abs(entityPos.x));
        double zDiff = Math.abs(Math.abs(arrowPos.z) - Math.abs(entityPos.z));

        return xDiff > zDiff;
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

        // find the coordinates of the point at which the arrow intersects with the shoulder line
        double correspondingCoordinate, distance;
        // plug in the y coordinate from the arrow to see where the current torso line and the arrow intersect
        if (!comparePosition(arrowPos, entityPos)) {
            correspondingCoordinate = (-arrowPos.z - shoulderAxis.getIntercept())/shoulderAxis.getSlope();
            System.out.println("Corresponding Component: " + -arrow.position().z + " - " + shoulderAxis.getIntercept() + "/" + shoulderAxis.getSlope() + " = " + correspondingCoordinate);
            distance = Math.abs(arrowPos.x) - Math.abs(correspondingCoordinate);
            System.out.println("Distance: " + Math.abs(arrowPos.x) + " - " + Math.abs(correspondingCoordinate) + " = " + distance);
        }
        // plug in the x coordinate from the arrow to see where the current torso line and the arrow intersect
        else {
            correspondingCoordinate = shoulderAxis.getSlope() * arrowPos.x + shoulderAxis.getIntercept();
            System.out.println("Corresponding Component: " + shoulderAxis.getSlope() + " * " + arrowPos.x + " + " + shoulderAxis.getIntercept() + " = " + correspondingCoordinate);
            distance = Math.abs(arrowPos.z) - Math.abs(correspondingCoordinate);
            System.out.println("Distance: " + Math.abs(arrowPos.z) + " - " + Math.abs(correspondingCoordinate) + " = " + distance);
        }

        // use distance to find hypotenuse of triangle
        double hypotenuse = distance * Math.cos(entityYawRad);

        double verticalComponent;
        double horizontalComponent = hypotenuse * Math.cos(entityYawRad);

        if (distance != 0) {
            verticalComponent = hypotenuse * Math.sin(entityYawRad);
        }
        // if distance is 0, it means that it is hitting the arm
        else {
            return null;
        }

        if (arrowPos.x > entityPos.x) {
            horizontalComponent = -horizontalComponent;
        }
        if (arrowPos.z > entityPos.z) {
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

    private static boolean isHitArm(AbstractArrow arrow, LivingEntity entity) {
        Line lineSeg = getShoulderAxis(entity);
        Line shiftedLine = shiftLine(lineSeg, entity, arrow);

        Vec3 arrowPos = arrow.position();

        if (shiftedLine == null) {
            return true;
        }
        if (arrowPos.x > shiftedLine.getMaxX() || arrowPos.x < shiftedLine.getMinX()) {
            return true;
        }
        if (arrowPos.z < shiftedLine.getMaxY() || arrowPos.z > shiftedLine.getMinY()) {
            return true;
        }

        return false;
    }

    public static int arrowDamagedPart(AbstractArrow arrow, LivingEntity entity) {
        Vec3 arrowPos = arrow.position();
        Vec3 playerPos = entity.position();

        // hit head
        if (arrowPos.y > playerPos.y + 1.2375) {
            return 0;
        }
        // hit torso or arms
        else if (arrowPos.y > playerPos.y + 0.61875) {
            if (isHitArm(arrow, entity)) {
                return 1;
            }
            else {
                return 2;
            }
        }
        // hit legs
        else {
            return 3;
        }
    }
}
