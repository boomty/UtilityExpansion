package boomty.utilityexpansion.client.renderer.armor;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface VisoredHelmetRenderer {
    LivingEntity getLivingEntity();
    void setItemStack(ItemStack itemStack);
}
