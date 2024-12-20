package boomty.utilityexpansion.client.renderer.armor.curios;

import boomty.utilityexpansion.client.CuriosLayerDefinition;
import boomty.utilityexpansion.client.model.armor.curios.FaceMaskModelCurios;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class FaceMaskRendererCurios implements ICurioRenderer {
    private final FaceMaskModelCurios model;

    public FaceMaskRendererCurios() {
        this.model = new FaceMaskModelCurios(
                Minecraft.getInstance().getEntityModels().bakeLayer(CuriosLayerDefinition.FACE_MASK));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext,
                                                                          PoseStack matrixStack,
                                                                          RenderLayerParent<T, M> renderLayerParent,
                                                                          MultiBufferSource renderTypeBuffer,
                                                                          int light, float limbSwing,
                                                                          float limbSwingAmount, float partialTicks,
                                                                          float ageInTicks, float netHeadYaw,
                                                                          float headPitch) {
        LivingEntity entity = slotContext.entity();

        model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        ICurioRenderer.followHeadRotations(entity, model.head);

        matrixStack.pushPose();

        // handle sneaking
        if (entity.isCrouching()) {
            matrixStack.translate(0, 0.25, 0);
        }

       // rotate model with the head
        matrixStack.mulPose(Vector3f.YP.rotation(model.head.yRot));
        matrixStack.mulPose(Vector3f.XP.rotation(model.head.xRot));

        matrixStack.translate(0, 0.25, 0.27);

        // translate and rotate
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0f));
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));

        matrixStack.scale(2f, 1f, 1f);

        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.HEAD, light,
                OverlayTexture.NO_OVERLAY, matrixStack, renderTypeBuffer, 0);
        matrixStack.popPose();

    }
}
