package boomty.utilityexpansion.util.attachmentSystem;

import boomty.utilityexpansion.item.armorTypes.AttachableArmor;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of attachments to an itemstack
 */
public class ItemStackAttachments {
    private List<RegistryObject<? extends ArmorItem>> attachmentList;

    public ItemStackAttachments() {
        attachmentList = new ArrayList<>();
    }

    private boolean attachmentExists(ItemStack itemStack, RegistryObject<? extends ArmorItem> attachment) {
        Item currentItem = itemStack.getItem();

        if (currentItem instanceof AttachableArmor) {
            return ((AttachableArmor) currentItem).getPossibleAttachments().contains(attachment);
        }
        return false;
    }

    public boolean addAttachment(ItemStack itemStack, RegistryObject<? extends ArmorItem> attachment) {
        if (attachmentExists(itemStack, attachment)) {
            attachmentList.add(attachment);
            return true;
        }
        return false;
    }

    public boolean removeAttachment(RegistryObject<? extends ArmorItem> attachment) {
        return attachmentList.remove(attachment);
    }
}
