package boomty.utilityexpansion.item.attachments;

import boomty.utilityexpansion.item.armorTypes.AttachableArmor;
import boomty.utilityexpansion.util.attachmentSystem.AttachmentListCache;
import boomty.utilityexpansion.util.attachmentSystem.ItemStackAttachments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

/**
 * Contains functions to add/remove attachments given an itemstack and the registryObject for the attachment
 */
public class AttachmentUtil {
    public static boolean addAttachment(ItemStack itemstack, RegistryObject<? extends ArmorItem> registryObject) {
        Item item = itemstack.getItem();

        if (item instanceof AttachableArmor) {
            if (((AttachableArmor) item).getPossibleAttachments().contains(registryObject)) {
                // save itemstack and its attachments to AttachmentListCache
                AttachmentListCache cache = AttachmentListCache.getInstance();

                ItemStackAttachments itemStackAttachments = cache.attachmentListMap.get(itemstack);

                // result will return false if the registryObject is not a possible attachment
                itemStackAttachments.addAttachment(itemstack, registryObject);

                // save to cache
                cache.attachmentListMap.put(itemstack, itemStackAttachments);

                // save registryObjStr to nbt data
                String registryObjStr = Objects.requireNonNull(registryObject.get().getRegistryName()).toString();
                CompoundTag nbtData = new CompoundTag();
                nbtData.putString(registryObjStr, registryObjStr);

                itemstack.setTag(nbtData);
                return true;
            }
        }
        return false;
    }

    public static boolean removeAttachment(ItemStack itemstack, RegistryObject<? extends ArmorItem> registryObject) {
        Item item = itemstack.getItem();

        if (item instanceof AttachableArmor) {
            if (((AttachableArmor) item).getPossibleAttachments().contains(registryObject)) {
                // remove the attachment in the AttachmentListCache
                AttachmentListCache cache = AttachmentListCache.getInstance();

                ItemStackAttachments itemStackAttachments = cache.attachmentListMap.get(itemstack);

                itemStackAttachments.removeAttachment(registryObject);

                CompoundTag nbtData = itemstack.getTag();
                String registryObjStr = Objects.requireNonNull(registryObject.get().getRegistryName()).toString();

                if (nbtData != null && nbtData.contains(registryObjStr)) {
                    nbtData.remove(registryObjStr);
                    return true;
                }
            }
        }
        return false;
    }
}
