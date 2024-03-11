package boomty.utilityexpansion.util;

import net.minecraft.world.entity.EquipmentSlot;

public class EquipmentSlotConverter {
    public static int getSlotIdFromEquipmentSlot(EquipmentSlot equipmentSlot) {
        if (equipmentSlot == EquipmentSlot.HEAD) {
            return 5;
        }
        else if (equipmentSlot == EquipmentSlot.CHEST) {
            return 6;
        }
        else if (equipmentSlot == EquipmentSlot.LEGS) {
            return 7;
        }
        else if (equipmentSlot == EquipmentSlot.FEET) {
            return 8;
        }
        else {
            return -1;
        }
    }
}
