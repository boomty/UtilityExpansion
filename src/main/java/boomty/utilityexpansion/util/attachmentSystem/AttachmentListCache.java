package boomty.utilityexpansion.util.attachmentSystem;

import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * A cache containing a map that maps an itemstack to its corresponding ItemStackAttachments object. This done so that
 * functions searching for all attachments on an item won't need to iterate through all possible attachments to an
 * itemstack after the first time accessing that itemstack.
 */
public class AttachmentListCache {
    public Map<ItemStack, ItemStackAttachments> attachmentListMap;

    private static AttachmentListCache INSTANCE;

    private AttachmentListCache() {
        attachmentListMap = new HashMap<>();
    }

    public static AttachmentListCache getInstance() {
        if (INSTANCE == null) {
            synchronized (AttachmentListCache.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AttachmentListCache();
                }
            }
        }
        return INSTANCE;
    }
}
