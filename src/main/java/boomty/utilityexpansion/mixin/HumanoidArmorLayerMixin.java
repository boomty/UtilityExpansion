package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.client.renderer.armor.VisoredHelmetRenderer;
import boomty.utilityexpansion.events.Subscriber;
import boomty.utilityexpansion.item.armorTypes.AttachableArmor;
import boomty.utilityexpansion.item.armorTypes.headArmor.VisoredHelmet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import java.util.List;

@Mixin(HumanoidArmorLayer.class)

public abstract class HumanoidArmorLayerMixin <T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    @Shadow protected abstract void renderModel(PoseStack p_117107_, MultiBufferSource p_117108_, int p_117109_, boolean p_117111_, Model p_117112_, float p_117114_, float p_117115_, float p_117116_, ResourceLocation armorResource);

    @Shadow public abstract ResourceLocation getArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type);

    public HumanoidArmorLayerMixin(RenderLayerParent<T, M> p_117346_) {
        super(p_117346_);
    }

    @Inject(method = "renderArmorPiece", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IZLnet/minecraft/client/model/Model;FFFLnet/minecraft/resources/ResourceLocation;)V",
    ordinal = 2, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void renderArmorPiece(PoseStack p_117119_, MultiBufferSource p_117120_, T p_117121_, EquipmentSlot p_117122_,
                                 int p_117123_, A p_117124_, CallbackInfo ci, ItemStack itemstack, ArmorItem armoritem,
                                 Model model, boolean flag, boolean flag1) {
        if (model instanceof GeoArmorRenderer<?> && model instanceof VisoredHelmetRenderer
                && armoritem instanceof VisoredHelmet) {
            VisoredHelmet.attachSubscriber((Subscriber) model);
            ((VisoredHelmetRenderer) model).setItemStack(itemstack);
            ((Subscriber) model).update();
        }
    }

    /*
    Method: renderArmorPiece
    Returns: void
    Purpose: When a player equips an armor piece that has attachment(s) on it or has a corresponding part, that attachment/part will be rendered on the player
     */
//    @Inject(method = "renderArmorPiece", at = @At(value = "INVOKE",
//            target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IZLnet/minecraft/client/model/Model;FFFLnet/minecraft/resources/ResourceLocation;)V",
//            ordinal=2, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
//    public void renderArmorPiece(PoseStack p_117119_, MultiBufferSource p_117120_, T p_117121_, EquipmentSlot p_117122_,
//                                 int p_117123_, A p_117124_, CallbackInfo ci, ItemStack itemstack, ArmorItem armoritem,
//                                 Model model, boolean flag1) {
//        if (armoritem instanceof AttachableArmor) {
//
//            List<RegistryObject<? extends ArmorItem>> attachmentList = ((AttachableArmor) armoritem).getAttachmentList();
//
//            for (RegistryObject<? extends ArmorItem> registryObject : attachmentList) {
//                ItemStack attachmentItemStack = new ItemStack(registryObject.get());
//                net.minecraft.client.model.Model attachmentModel = net.minecraftforge.client.ForgeHooksClient
//                        .getArmorModel(p_117121_, attachmentItemStack, ((ArmorItem) attachmentItemStack.getItem()).getSlot(), p_117124_);
//
//                renderModel(p_117119_, p_117120_, p_117123_, flag1, attachmentModel, 1.0F, 1.0F, 1.0F,
//                        getArmorResource(p_117121_, attachmentItemStack, p_117122_, null));
//            }
//        }
//    }
}
