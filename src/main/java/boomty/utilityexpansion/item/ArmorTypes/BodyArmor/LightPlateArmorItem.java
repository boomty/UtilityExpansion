package boomty.utilityexpansion.item.ArmorTypes.BodyArmor;

import boomty.utilityexpansion.item.ArmorTypes.ModArmor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

import javax.annotation.Nullable;
import java.util.List;

/**
 * For armors with small plates protecting vital areas (pectorale armor)
 */
public class LightPlateArmorItem extends ModArmor {
    public LightPlateArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder, new float[]{0.2F, 0});
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TextComponent(this.getWeaponResistance()[0] * 10 + "% sword damage reduction" ));
        pTooltipComponents.add(new TextComponent(this.getWeaponResistance()[1] * 10 + "% blunt weapon damage reduction" ));
        super.appendHoverText(itemStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
