package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.UtilityExpansion;
import boomty.utilityexpansion.item.armorTypes.AttachableArmor;
import boomty.utilityexpansion.item.armorTypes.GenericArmor;
import boomty.utilityexpansion.item.armorTypes.ModArmor;
import boomty.utilityexpansion.registry.ItemRegistry;
import boomty.utilityexpansion.util.ItemRendererMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import java.util.Map;

@Mixin(GeoArmorRenderer.class)

public abstract class GeoArmorRendererMixin<T>{
    @Shadow protected ItemStack itemStack;
    @Shadow protected LivingEntity entityLiving;

    @Inject(method="render", at = @At(value = "TAIL"), remap = false)
    @SuppressWarnings("unchecked")
    public void render(float partialTick, PoseStack poseStack, VertexConsumer buffer, int packedLight, CallbackInfo ci) {
        if (itemStack.getItem() instanceof AttachableArmor) {
            for (String item : ((AttachableArmor) itemStack.getItem()).getAttachmentList()) {
                Map<String, Item> itemStackMap = ItemRendererMap.getInstance().itemStackMap;
                Item correspondingItem = itemStackMap.get(item);
                if (correspondingItem instanceof ArmorItem) {
                    ItemStack equipItem = new ItemStack(correspondingItem);
                    GeoArmorRenderer<? extends ArmorItem> renderer = GeoArmorRenderer
                            .getRenderer((Class<? extends ArmorItem>)correspondingItem.getClass(), entityLiving);
                    renderer.applyEntityStats(renderer)              // .applyEntityStats(new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))
                            .setCurrentItem(entityLiving, equipItem, LivingEntity.getEquipmentSlotForItem(equipItem))
                            .applySlot(LivingEntity.getEquipmentSlotForItem(equipItem));

                    renderer.render(partialTick, poseStack, buffer, packedLight);
                }
            }
        }
    }
}
