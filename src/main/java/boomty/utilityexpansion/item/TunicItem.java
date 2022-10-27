package boomty.utilityexpansion.item;

import boomty.utilityexpansion.utilityexpansion;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class TunicItem extends GeoArmorItem implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    public TunicItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder.tab(utilityexpansion.utilexpanseitemgroup));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<TunicItem>(this, "controller",
                20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

//    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, utilityexpansion.MOD_ID);
//
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level p_40395_, Player p_40396_, InteractionHand p_40397_) {
//
//        //second solution
////        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(utilityexpansion.MOD_ID, "geo/tunic_item.geo.json"));
////        ItemStack itemstack2 = new ItemStack(item);
//
//        //Sets itemstack equal to the item currently held by player "p_40396_"
//        ItemStack itemstack = p_40396_.getItemInHand(p_40397_);
//        //creates a new tunicItem with the type Item
//        Item tunicItem = new TunicItem(ArmorMaterials.LEATHER, EquipmentSlot.LEGS, new Item.Properties());
//        //Uses ItemStack constructor to create a new ItemStack with an Item
//        ItemStack itemstack2 = new ItemStack(tunicItem);
//        //Finds the equipment slot designated to a chestplate (chest slot)
//        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(itemstack);
//        //
//        ItemStack itemstack3 = p_40396_.getItemBySlot(equipmentslot);
//        ItemStack itemstack4 = p_40396_.getItemBySlot(equipmentslot.LEGS);
//
//        //Checks if chestplate and legging slot is empty
//        if (itemstack3.isEmpty() && itemstack4.isEmpty())
//        {
//            p_40396_.setItemSlot(equipmentslot, itemstack.copy());
//            //Put "tunic legging" into leg slot
//            //Make sure to change itemstack.copy()
//            p_40396_.setItemSlot(equipmentslot.LEGS, itemstack2.copy());
//
//            if (!p_40395_.isClientSide())
//            {
//                p_40396_.awardStat(Stats.ITEM_USED.get(this));
//            }
//            itemstack.setCount(0);
//            return InteractionResultHolder.sidedSuccess(itemstack, p_40395_.isClientSide());
//        }
//        else
//        {
//            return InteractionResultHolder.fail(itemstack);
//        }
//    }
//
//    @SubscribeEvent
//    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
//        GeoArmorRenderer.registerArmorRenderer(TunicItem.class, () -> new TunicItemRenderer());
//    }
//
//    public static void register(IEventBus eventBus) {
//        ITEMS.register(eventBus);
//    }
}
