package boomty.utilityexpansion.item.armorTypes;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

/*
* Armors that implement this interface are armors that can be attached with non-essential attachments such as plumes or
* belts on chestplates
* */
public interface AttachableArmor {
    List<RegistryObject<? extends ArmorItem>> getPossibleAttachments();
}
