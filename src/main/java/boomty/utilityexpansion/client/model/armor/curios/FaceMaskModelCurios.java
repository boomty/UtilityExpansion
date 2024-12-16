package boomty.utilityexpansion.client.model.armor.curios;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;

public class FaceMaskModelCurios extends HumanoidModel<LivingEntity> {
	public FaceMaskModelCurios(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createLayer() {
		CubeDeformation cube = new CubeDeformation(0.0F);
		MeshDefinition meshdefinition = HumanoidModel.createMesh(cube, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("bipedHead", CubeListBuilder.create().
				texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F,
						8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F,
				0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	@Nonnull
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.head);
	}
}