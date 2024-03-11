package boomty.utilityexpansion.util;

import net.minecraft.world.entity.EquipmentSlot;

public class SlotIdConverter {
    public static EquipmentSlot getEquipmentSlotFromSlotId(int slotId) {
        if (slotId == 5) {
            return EquipmentSlot.HEAD;
        }
        else if (slotId == 6) {
            return EquipmentSlot.CHEST;
        }
        else if (slotId == 7) {
            return EquipmentSlot.LEGS;
        }
        else if (slotId == 8) {
            return EquipmentSlot.FEET;
        }
        else {
            return null;
        }
    }
}
