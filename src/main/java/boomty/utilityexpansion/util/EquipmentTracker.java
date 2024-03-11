package boomty.utilityexpansion.util;

public class EquipmentTracker {
    private static EquipmentTracker instance;
    private static int lastEquipped;
    private EquipmentTracker() {
        lastEquipped = 0;
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EquipmentTracker();
        }
    }

    public static EquipmentTracker getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    public void setLastEquipped(int val) {
        lastEquipped = val;
    }

    public int getLastEquipped() {
        return lastEquipped;
    }
}
