package boomty.utilityexpansion.client.model.armor.curios;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;

public class TunicUpperModel extends HumanoidModel<LivingEntity> {
    public TunicUpperModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        CubeDeformation cube = new CubeDeformation(0.0F);
        MeshDefinition meshdefinition = HumanoidModel.createMesh(cube, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

//        partdefinition.addOrReplaceChild("body", CubeListBuilder.create()

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, cube),
            PartPose.offset(0.0F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 16)
            .addBox(4.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cube),
            PartPose.offset(0.0F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 16)
            .addBox(-8.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cube),
            PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    @Nonnull
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    @Nonnull
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList
                .of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.hat);
    }
}
