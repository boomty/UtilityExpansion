package boomty.utilityexpansion.item;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TunicItem extends ArmorItem {

    public TunicItem(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_40395_, Player p_40396_, InteractionHand p_40397_) {

        //Sets itemstack equal to the item currently held by player "p_40396_"
        ItemStack itemstack = p_40396_.getItemInHand(p_40397_);

        //Sets itemstack2 equal to the corresponding "tunic legging"
//      ItemStack itemstack2 = ;

        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(itemstack);
        ItemStack itemstack1 = p_40396_.getItemBySlot(equipmentslot);
        ItemStack itemstack2 = p_40396_.getItemBySlot(equipmentslot.LEGS);

//        getItemBySlot method:
//        public ItemStack getItemBySlot(EquipmentSlot p_36257_) {
//            if (p_36257_ == EquipmentSlot.MAINHAND) {
//                return this.inventory.getSelected();
//            } else if (p_36257_ == EquipmentSlot.OFFHAND) {
//                return this.inventory.offhand.get(0);
//            } else {
//                return p_36257_.getType() == EquipmentSlot.Type.ARMOR ? this.inventory.armor.get(p_36257_.getIndex()) : ItemStack.EMPTY;
//            }
//        }

        //Checks if chestplate and legging slot is empty
        if (itemstack1.isEmpty() && itemstack2.isEmpty())
        {
            p_40396_.setItemSlot(equipmentslot, itemstack.copy());
            //Put "tunic legging" into leg slot
            //Make sure to change itemstack.copy()
            p_40396_.setItemSlot(equipmentslot.LEGS, itemstack.copy());

            if (!p_40395_.isClientSide())
            {
                p_40396_.awardStat(Stats.ITEM_USED.get(this));
            }
            itemstack.setCount(0);
            return InteractionResultHolder.sidedSuccess(itemstack, p_40395_.isClientSide());
        }
        else
        {
            return InteractionResultHolder.fail(itemstack);
        }
    }
}
