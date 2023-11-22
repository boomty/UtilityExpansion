package boomty.utilityexpansion.client.model.armor.curios;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;

public class LoricaSegmentataModel extends HumanoidModel<LivingEntity> {
	public LoricaSegmentataModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		CubeDeformation cube = new CubeDeformation(0.0F);
		MeshDefinition meshdefinition = HumanoidModel.createMesh(cube, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(92, 48).addBox(-4.5F, 4.3F, -2.8F, 8.9F, 1.2F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(7, 0).addBox(-4.5F, 5.5F, -2.7F, 8.9F, 1.3F, 4.9F, new CubeDeformation(0.0F))
				.texOffs(93, 55).addBox(-4.5F, 6.8F, -2.7F, 8.9F, 1.3F, 4.9F, new CubeDeformation(0.0F))
				.texOffs(93, 10).addBox(-4.4F, 8.1F, -2.6F, 8.7F, 1.3F, 4.8F, new CubeDeformation(0.0F))
				.texOffs(93, 20).addBox(-4.4F, 9.4F, -2.6F, 8.7F, 1.3F, 4.8F, new CubeDeformation(0.0F))
				.texOffs(93, 34).addBox(-4.5F, 10.7F, -2.6F, 8.9F, 1.55F, 4.8F, new CubeDeformation(0.0F))
				.texOffs(14, 57).mirror().addBox(-4.2F, 10.7F, -2.8F, 8.3F, 1.3F, 0.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(14, 84).mirror().addBox(-4.2F, 10.7F, 2.2F, 8.3F, 1.3F, 0.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 23).addBox(1.4F, 10.7F, -3.3F, 0.7F, 1.3F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(25, 26).addBox(0.5F, 10.7F, -3.3F, 0.7F, 1.3F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(28, 29).addBox(-0.4F, 10.7F, -3.3F, 0.7F, 1.3F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(31, 32).addBox(-1.3F, 10.7F, -3.3F, 0.7F, 1.3F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(34, 35).addBox(-2.2F, 10.7F, -3.3F, 0.7F, 1.3F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(15, 82).mirror().addBox(-2.3F, 10.7F, -3.0F, 4.5F, 1.3F, 0.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(21, 39).mirror().addBox(2.2F, 10.8F, -2.9F, 1.9F, 1.1F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(15, 98).mirror().addBox(2.2F, 10.8F, 2.3F, 1.9F, 1.1F, 0.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(17, 116).mirror().addBox(0.1F, 10.8F, 2.3F, 1.9F, 1.1F, 0.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 114).mirror().addBox(-4.2F, 10.8F, -2.9F, 1.9F, 1.1F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(16, 101).mirror().addBox(-4.2F, 10.8F, 2.3F, 1.9F, 1.1F, 0.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 106).mirror().addBox(-2.1F, 10.8F, 2.3F, 1.9F, 1.1F, 0.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(37, 24).addBox(-3.3F, 1.2F, -3.7F, 2.1F, 1.8F, 0.45F, new CubeDeformation(0.0F))
				.texOffs(30, 114).addBox(-3.3F, 1.2F, 2.5F, 2.1F, 1.8F, 0.45F, new CubeDeformation(0.0F))
				.texOffs(22, 106).mirror().addBox(0.0F, 3.0F, -3.55F, 2.9F, 1.3F, 0.35F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(31, 108).addBox(-3.3F, 3.0F, -3.55F, 3.3F, 1.3F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(48, 107).mirror().addBox(0.1F, 5.5F, -3.1F, 2.9F, 1.3F, 0.15F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(105, 40).mirror().addBox(0.1F, 8.1F, -2.8F, 2.9F, 1.3F, 0.05F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(37, 81).addBox(-4.2F, 3.0F, -3.1F, 8.4F, 1.3F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(71, 0).addBox(-4.2F, 1.2F, 2.2F, 8.4F, 3.1F, 0.4F, new CubeDeformation(0.0F))
				.texOffs(14, 76).addBox(-4.4F, 4.3F, 2.2F, 8.7F, 1.2F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(69, 83).addBox(-4.2F, 4.3F, -2.9F, 8.4F, 1.2F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(80, 76).mirror().addBox(-4.4F, 6.8F, -2.8F, 8.7F, 1.3F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(75, 76).addBox(-4.2F, 6.8F, 2.2F, 8.3F, 1.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(104, 76).mirror().addBox(-4.4F, 8.1F, -2.7F, 8.7F, 1.3F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(78, 41).mirror().addBox(-4.4F, 5.5F, -2.8F, 8.7F, 1.3F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(73, 77).addBox(-4.4F, 5.5F, 2.2F, 8.7F, 1.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(101, 81).addBox(-4.0F, 3.0F, -3.2F, 8.0F, 1.3F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(96, 2).addBox(-4.0F, 1.1F, 2.6F, 8.0F, 3.2F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(17, 82).mirror().addBox(-4.0F, 4.3F, -2.95F, 8.0F, 1.2F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(70, 51).addBox(-4.0F, 4.3F, 2.25F, 8.0F, 1.2F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(46, 82).mirror().addBox(-4.0F, 5.5F, -2.95F, 8.0F, 1.3F, 0.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(71, 54).addBox(-4.0F, 5.5F, 2.15F, 8.0F, 1.3F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(79, 82).mirror().addBox(-4.0F, 8.1F, -2.75F, 8.0F, 1.3F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(79, 84).addBox(-4.0F, 8.1F, 2.25F, 8.0F, 1.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(77, 84).addBox(-4.0F, 8.1F, 2.15F, 8.0F, 1.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(59, 42).addBox(-3.7F, 3.0F, -3.3F, 7.0F, 1.3F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(29, 71).addBox(-3.3F, 3.0F, -3.2F, 6.2F, 1.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(17, 49).addBox(-3.7F, 4.3F, -3.05F, 7.0F, 1.2F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(74, 37).addBox(-3.7F, 4.3F, 2.35F, 7.0F, 1.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(82, 70).addBox(-3.2F, 4.3F, 2.45F, 6.0F, 1.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-1.5F, 4.6F, 2.65F, 0.4F, 0.7F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-1.5F, 5.8F, 2.55F, 0.4F, 0.7F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-1.5F, 7.1F, 2.45F, 0.4F, 0.7F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(1.2F, 7.1F, 2.45F, 0.4F, 0.7F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(1.2F, 8.4F, 2.35F, 0.4F, 0.7F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-1.5F, 8.4F, 2.35F, 0.4F, 0.7F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(1.2F, 4.6F, 2.65F, 0.4F, 0.7F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(107, 12).addBox(-1.1F, 4.8F, 2.65F, 2.3F, 0.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(108, 15).addBox(-1.1F, 6.0F, 2.55F, 2.3F, 0.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(109, 20).addBox(-1.1F, 7.3F, 2.45F, 2.3F, 0.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(110, 23).addBox(-1.1F, 8.6F, 2.35F, 2.3F, 0.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(1.2F, 5.8F, 2.55F, 0.4F, 0.7F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(30, 89).addBox(-3.3F, 4.3F, -3.25F, 6.2F, 1.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(47, 65).mirror().addBox(-3.7F, 6.8F, -2.85F, 7.0F, 1.3F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(76, 86).addBox(-3.7F, 6.8F, 2.25F, 7.0F, 1.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(80, 41).addBox(-3.2F, 6.8F, 2.35F, 6.0F, 1.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(81, 85).mirror().addBox(-3.7F, 5.5F, -3.05F, 7.0F, 1.3F, 0.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(72, 86).addBox(-3.7F, 5.5F, 2.25F, 7.0F, 1.3F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(81, 63).addBox(-3.2F, 5.5F, 2.35F, 6.0F, 1.3F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(68, 93).addBox(4.4F, 1.3F, -2.6F, 0.1F, 3.0F, 4.5F, new CubeDeformation(0.0F))
				.texOffs(93, 53).addBox(4.4F, 4.3F, -2.6F, 0.1F, 1.2F, 4.5F, new CubeDeformation(0.0F))
				.texOffs(93, 94).addBox(4.4F, 5.5F, -2.6F, 0.05F, 1.3F, 4.5F, new CubeDeformation(0.0F))
				.texOffs(93, 94).addBox(-4.55F, 5.5F, -2.6F, 0.05F, 1.3F, 4.5F, new CubeDeformation(0.0F))
				.texOffs(93, 0).addBox(-4.6F, 4.3F, -2.6F, 0.1F, 1.2F, 4.5F, new CubeDeformation(0.0F))
				.texOffs(12, 93).mirror().addBox(-4.6F, 1.3F, -2.6F, 0.1F, 3.0F, 4.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(30, 20).addBox(4.5F, 1.3F, -2.3F, 0.1F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(38, 68).addBox(-4.7F, 1.3F, -2.3F, 0.1F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(67, 93).addBox(-4.5F, -0.1F, -2.9F, 0.3F, 4.4F, 5.1F, new CubeDeformation(0.0F))
				.texOffs(69, 93).addBox(4.2F, -0.1F, -2.8F, 0.2F, 4.4F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(8, 27).addBox(-2.8F, -0.3F, -0.9F, 0.6F, 0.1F, 0.7F, new CubeDeformation(0.0F))
				.texOffs(8, 27).addBox(-2.8F, -0.3F, 0.1F, 0.6F, 0.1F, 0.8F, new CubeDeformation(0.0F))
				.texOffs(112, 112).addBox(2.2F, -0.3F, 0.1F, 0.6F, 0.1F, 0.7F, new CubeDeformation(0.0F))
				.texOffs(112, 112).addBox(2.2F, -0.3F, -0.9F, 0.6F, 0.1F, 0.7F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(2.0F, -0.3F, -1.2F, 1.0F, 0.1F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-3.0F, -0.3F, -1.2F, 1.0F, 0.1F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-3.0F, -0.3F, 0.9F, 1.0F, 0.1F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(2.0F, -0.3F, 0.8F, 1.0F, 0.1F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(2.0F, -0.3F, -0.2F, 1.0F, 0.1F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-3.0F, -0.3F, -0.2F, 1.0F, 0.1F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(29, 71).addBox(-4.2F, 0.6F, -3.0F, 3.0F, 2.4F, 5.2F, new CubeDeformation(0.0F))
				.texOffs(8, 67).mirror().addBox(1.2F, 0.6F, -3.0F, 3.0F, 2.4F, 5.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(41, 71).mirror().addBox(-1.2F, 0.6F, -2.8F, 2.4F, 0.6F, 5.2F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(98, 24).addBox(-1.8F, 0.0F, -2.5F, 3.6F, 0.6F, 5.2F, new CubeDeformation(0.0F))
				.texOffs(92, 72).addBox(1.8F, -0.1F, -2.5F, 2.4F, 0.7F, 4.7F, new CubeDeformation(0.0F))
				.texOffs(6, 74).mirror().addBox(-4.2F, -0.1F, -2.5F, 2.4F, 0.7F, 4.7F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(74, 15).addBox(-1.2F, 1.2F, -3.2F, 1.2F, 1.8F, 5.4F, new CubeDeformation(0.0F))
				.texOffs(23, 88).addBox(0.0F, 1.2F, -3.6F, 1.2F, 1.8F, 5.8F, new CubeDeformation(0.0F))
				.texOffs(34, 118).addBox(-1.2F, 1.2F, -3.65F, 1.2F, 1.8F, 0.45F, new CubeDeformation(0.0F))
				.texOffs(118, 118).addBox(-2.2F, 1.9F, -3.85F, 0.6F, 0.4F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(91, 118).addBox(-2.2F, 2.7F, -3.45F, 0.6F, 0.8F, 0.15F, new CubeDeformation(0.0F))
				.texOffs(118, 118).addBox(-2.2F, 3.8F, -3.65F, 0.6F, 0.1F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(23, 24).addBox(-2.2F, 4.7F, -3.35F, 0.6F, 0.4F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(118, 118).addBox(1.6F, 3.8F, -3.65F, 0.6F, 0.1F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(70, 71).addBox(1.6F, 2.5F, -3.75F, 0.6F, 1.3F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(73, 74).addBox(-2.2F, 2.5F, -3.75F, 0.6F, 1.3F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(22, 21).mirror().addBox(1.6F, 4.7F, -3.35F, 0.6F, 0.4F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(118, 118).addBox(1.6F, 2.7F, -3.45F, 0.6F, 0.8F, 0.15F, new CubeDeformation(0.0F))
				.texOffs(72, 118).addBox(1.6F, 1.9F, -3.85F, 0.6F, 0.4F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-2.2F, 1.9F, 2.65F, 0.6F, 0.4F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(1.6F, 1.9F, 2.65F, 0.6F, 0.4F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(118, 118).addBox(1.3F, 1.9F, -3.85F, 0.1F, 0.4F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(118, 118).addBox(1.3F, 1.8F, -3.85F, 0.3F, 0.1F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(83, 118).addBox(1.3F, 2.3F, -3.85F, 0.3F, 0.1F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(4, 5).addBox(-1.5F, 2.3F, 2.85F, 0.3F, 0.1F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(1, 2).addBox(-1.5F, 1.8F, 2.85F, 0.3F, 0.1F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-1.3F, 1.9F, 2.85F, 0.1F, 0.4F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(7, 8).addBox(-1.5F, 1.9F, 2.85F, 0.1F, 0.4F, 0.55F, new CubeDeformation(0.0F))
				.texOffs(75, 81).addBox(-1.6F, 1.9F, -3.75F, 3.2F, 0.4F, 0.45F, new CubeDeformation(0.0F))
				.texOffs(117, 109).addBox(-1.6F, 1.9F, 3.05F, 3.2F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(105, 106).addBox(-1.6F, 3.4F, -3.65F, 3.2F, 0.4F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(27, 3).mirror().addBox(-1.6F, 4.7F, -3.3F, 3.2F, 0.4F, 0.05F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(118, 118).addBox(-0.1F, 3.4F, -3.7F, 0.1F, 0.4F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(33, 34).addBox(-0.1F, 4.7F, -3.35F, 0.1F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(4, 119).addBox(0.1F, 3.4F, -3.7F, 0.1F, 0.4F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(36, 37).addBox(0.1F, 4.7F, -3.35F, 0.1F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(111, 118).addBox(-0.1F, 3.3F, -3.7F, 0.3F, 0.1F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(30, 31).addBox(-0.1F, 4.6F, -3.35F, 0.3F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(1, 119).mirror().addBox(-0.1F, 3.8F, -3.7F, 0.3F, 0.1F, 0.35F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(27, 28).addBox(-0.1F, 5.1F, -3.35F, 0.3F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(67, 68).addBox(-2.2F, 5.9F, -3.25F, 0.6F, 0.4F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(64, 65).addBox(0.1F, 5.9F, -3.2F, 0.1F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(61, 62).addBox(-0.1F, 5.9F, -3.2F, 0.1F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(58, 59).addBox(-0.1F, 6.3F, -3.2F, 0.3F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(52, 53).addBox(-0.1F, 5.8F, -3.2F, 0.3F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(52, 107).mirror().addBox(-1.6F, 5.9F, -3.15F, 3.2F, 0.4F, 0.05F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(48, 49).addBox(1.6F, 5.9F, -3.25F, 0.6F, 0.4F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(100, 101).addBox(1.6F, 7.1F, -2.95F, 0.6F, 0.4F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(97, 98).addBox(-2.2F, 7.1F, -2.95F, 0.6F, 0.4F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(94, 95).addBox(-0.1F, 7.5F, -2.95F, 0.3F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(91, 92).addBox(-0.1F, 7.0F, -2.95F, 0.3F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(88, 89).addBox(0.1F, 7.1F, -2.95F, 0.1F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(85, 86).addBox(-0.1F, 7.1F, -2.95F, 0.1F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(85, 107).mirror().addBox(-1.6F, 7.1F, -2.9F, 3.2F, 0.4F, 0.05F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(109, 110).addBox(-2.2F, 8.6F, -2.9F, 0.6F, 0.4F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(109, 50).mirror().addBox(-1.6F, 8.6F, -2.85F, 3.2F, 0.4F, 0.05F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(105, 106).addBox(1.6F, 8.6F, -2.9F, 0.6F, 0.4F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-0.1F, 9.0F, -2.9F, 0.3F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(0.1F, 8.6F, -2.9F, 0.1F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(118, 120).addBox(-0.1F, 8.6F, -2.9F, 0.1F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(112, 113).addBox(-0.1F, 8.5F, -2.9F, 0.3F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(29, 74).addBox(1.2F, 1.2F, -3.65F, 2.4F, 1.8F, 0.65F, new CubeDeformation(0.0F))
				.texOffs(23, 114).addBox(1.2F, 1.2F, 2.35F, 2.4F, 1.8F, 0.65F, new CubeDeformation(0.0F))
				.texOffs(76, 118).mirror().addBox(3.6F, 1.2F, -3.45F, 0.3F, 1.8F, 0.45F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(26, 118).addBox(3.9F, 1.2F, -3.05F, 0.3F, 1.8F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(30, 118).addBox(-4.2F, 1.2F, -3.05F, 0.3F, 1.8F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(4, 118).addBox(-3.9F, 1.2F, -3.15F, 0.3F, 1.8F, 0.15F, new CubeDeformation(0.0F))
				.texOffs(91, 86).addBox(-3.6F, 1.2F, -3.25F, 2.4F, 1.8F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(22, 91).addBox(-3.6F, 0.8F, -3.35F, 2.4F, 0.4F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(25, 98).addBox(1.2F, 0.8F, -3.35F, 2.4F, 0.4F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(46, 118).addBox(3.6F, 0.9F, -3.05F, 0.5F, 0.3F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(58, 118).addBox(3.6F, 0.9F, -3.1F, 0.2F, 0.3F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(118, 118).addBox(-3.8F, 0.9F, -3.1F, 0.2F, 0.3F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(52, 118).addBox(-4.1F, 0.9F, -3.05F, 0.5F, 0.3F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(35, 22).addBox(-3.6F, 0.7F, -3.25F, 2.4F, 0.1F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(32, 77).addBox(1.2F, 0.7F, -3.25F, 2.4F, 0.1F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(27, 111).addBox(1.8F, 0.2F, -2.65F, 2.4F, 0.4F, 0.15F, new CubeDeformation(0.0F))
				.texOffs(7, 51).mirror().addBox(1.8F, -0.2F, -2.35F, 2.4F, 0.1F, 4.35F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(94, 81).addBox(-4.2F, -0.2F, -2.35F, 2.4F, 0.1F, 4.35F, new CubeDeformation(0.0F))
				.texOffs(29, 110).addBox(-4.2F, 0.2F, -2.65F, 2.4F, 0.4F, 0.15F, new CubeDeformation(0.0F))
				.texOffs(31, 11).addBox(-4.2F, 0.5F, -2.95F, 2.4F, 0.1F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(33, 14).addBox(1.8F, 0.5F, -2.95F, 2.4F, 0.1F, 0.35F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorBody_r1 = body.addOrReplaceChild("armorBody_r1", CubeListBuilder.create().texOffs(10, 11).addBox(-4.0F, -0.1F, 1.75F, 1.1F, 0.4F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(118, 118).addBox(3.3F, -0.1F, -2.05F, 1.1F, 0.4F, 0.05F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.6807F, 0.0F));

		PartDefinition armorBody_r2 = body.addOrReplaceChild("armorBody_r2", CubeListBuilder.create().texOffs(115, 116).addBox(0.8F, 2.8F, -3.0F, 0.4F, 0.1F, 0.05F, new CubeDeformation(0.0F))
				.texOffs(81, 82).addBox(0.8F, 1.3F, -3.05F, 0.4F, 0.1F, 0.05F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.9F, 0.2F, 0.0F, 0.2618F, 0.0F));

		PartDefinition armorBody_r3 = body.addOrReplaceChild("armorBody_r3", CubeListBuilder.create().texOffs(55, 56).addBox(0.8F, 2.8F, -3.1F, 0.4F, 0.1F, 0.05F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.2F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition armorBody_r4 = body.addOrReplaceChild("armorBody_r4", CubeListBuilder.create().texOffs(39, 40).addBox(0.8F, 2.8F, -3.05F, 0.4F, 0.1F, 0.05F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -0.2F, 0.0F, 0.2618F, 0.0F));

		PartDefinition armorBody_r5 = body.addOrReplaceChild("armorBody_r5", CubeListBuilder.create().texOffs(10, 12).addBox(0.8F, 1.5F, -3.5F, 0.4F, 0.1F, 0.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -0.1F, 0.0F, 0.2618F, 0.0F));

		PartDefinition armorBody_r6 = body.addOrReplaceChild("armorBody_r6", CubeListBuilder.create().texOffs(78, 84).addBox(-4.0F, 7.2F, 2.75F, 8.0F, 1.3F, 0.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.0698F, -0.0054F, -0.008F));

		PartDefinition armorBody_r7 = body.addOrReplaceChild("armorBody_r7", CubeListBuilder.create().texOffs(8, 81).addBox(-4.0F, 7.2F, -3.25F, 8.0F, 1.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(7, 34).addBox(-4.4F, 7.2F, -3.2F, 8.7F, 1.3F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(9, 54).addBox(-1.3F, 7.2F, -3.3F, 2.9F, 1.3F, 0.05F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0698F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-4.0F, 2.0F, 0.0F));

		PartDefinition armorRightArm_r1 = right_arm.addOrReplaceChild("armorRightArm_r1", CubeListBuilder.create().texOffs(113, 98).addBox(3.2F, -3.3F, -0.15F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 0.6F, 0.3F, -1.3534F, 0.0322F, -0.0728F));

		PartDefinition armorRightArm_r2 = right_arm.addOrReplaceChild("armorRightArm_r2", CubeListBuilder.create().texOffs(112, 98).addBox(-4.4F, -3.9F, -0.35F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.1F, 0.3F, -1.3528F, -0.0833F, -0.044F));

		PartDefinition armorRightArm_r3 = right_arm.addOrReplaceChild("armorRightArm_r3", CubeListBuilder.create().texOffs(113, 98).addBox(-5.8F, -3.7F, -0.15F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, 0.0F, 0.3F, -1.3528F, -0.0833F, -0.044F));

		PartDefinition armorRightArm_r4 = right_arm.addOrReplaceChild("armorRightArm_r4", CubeListBuilder.create().texOffs(59, 89).addBox(-6.0F, -3.2F, -1.55F, 2.4F, 0.1F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, 0.0F, 0.3F, -1.0292F, -0.0206F, -0.0293F));

		PartDefinition armorRightArm_r5 = right_arm.addOrReplaceChild("armorRightArm_r5", CubeListBuilder.create().texOffs(3, 88).addBox(-4.8F, -3.4F, -1.35F, 2.4F, 0.1F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.3F, -0.9245F, 0.0093F, -0.0113F));

		PartDefinition armorRightArm_r6 = right_arm.addOrReplaceChild("armorRightArm_r6", CubeListBuilder.create().texOffs(120, 120).addBox(-4.2F, -3.6F, -0.45F, 1.2F, 0.1F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-4.0F, -3.6F, -1.75F, 0.8F, 0.1F, 1.35F, new CubeDeformation(0.0F))
				.texOffs(107, 87).addBox(-4.8F, -3.5F, -1.75F, 2.4F, 0.1F, 2.95F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.3F, -0.2448F, 0.0093F, -0.0113F));

		PartDefinition armorRightArm_r7 = right_arm.addOrReplaceChild("armorRightArm_r7", CubeListBuilder.create().texOffs(50, 89).addBox(-6.1F, -3.2F, -1.75F, 2.4F, 0.1F, 2.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, 0.0F, 0.3F, -0.2448F, 0.0093F, -0.0113F));

		PartDefinition armorRightArm_r8 = right_arm.addOrReplaceChild("armorRightArm_r8", CubeListBuilder.create().texOffs(65, 38).addBox(3.4F, -3.2F, -1.55F, 2.4F, 0.1F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 0.6F, 0.3F, -1.0292F, 0.0206F, -0.0614F));

		PartDefinition armorRightArm_r9 = right_arm.addOrReplaceChild("armorRightArm_r9", CubeListBuilder.create().texOffs(75, 89).addBox(3.5F, -3.2F, -1.75F, 2.4F, 0.1F, 2.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 0.6F, 0.3F, -0.2448F, -0.0093F, -0.0614F));

		PartDefinition armorRightArm_r10 = right_arm.addOrReplaceChild("armorRightArm_r10", CubeListBuilder.create().texOffs(9, 90).addBox(2.9F, -3.1F, -1.75F, 2.4F, 0.1F, 2.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 0.8F, 0.3F, -0.2448F, -0.0093F, -0.1138F));

		PartDefinition armorRightArm_r11 = right_arm.addOrReplaceChild("armorRightArm_r11", CubeListBuilder.create().texOffs(65, 113).addBox(2.8F, -3.0F, -1.45F, 2.4F, 0.1F, 1.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 0.8F, 0.3F, -1.0292F, 0.0206F, -0.1138F));

		PartDefinition armorRightArm_r12 = right_arm.addOrReplaceChild("armorRightArm_r12", CubeListBuilder.create().texOffs(66, 113).addBox(2.8F, -3.0F, -1.45F, 2.4F, 0.1F, 1.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 1.9F, 0.3F, -1.0292F, 0.0206F, -0.3058F));

		PartDefinition armorRightArm_r13 = right_arm.addOrReplaceChild("armorRightArm_r13", CubeListBuilder.create().texOffs(12, 90).addBox(2.9F, -3.1F, -1.75F, 2.4F, 0.1F, 2.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 1.9F, 0.3F, -0.2448F, -0.0093F, -0.3058F));

		PartDefinition armorRightArm_r14 = right_arm.addOrReplaceChild("armorRightArm_r14", CubeListBuilder.create().texOffs(67, 0).addBox(2.9F, -3.5F, -0.85F, 2.4F, 0.1F, 1.35F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 1.9F, 0.3F, 0.4009F, -0.0093F, -0.3058F));

		PartDefinition armorRightArm_r15 = right_arm.addOrReplaceChild("armorRightArm_r15", CubeListBuilder.create().texOffs(35, 17).addBox(3.5F, -3.6F, -0.85F, 2.4F, 0.1F, 1.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 0.6F, 0.3F, 0.4009F, -0.0093F, -0.0614F));

		PartDefinition armorRightArm_r16 = right_arm.addOrReplaceChild("armorRightArm_r16", CubeListBuilder.create().texOffs(64, 113).addBox(2.9F, -3.5F, -0.85F, 2.4F, 0.1F, 1.35F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 0.8F, 0.3F, 0.4009F, -0.0093F, -0.1138F));

		PartDefinition armorRightArm_r17 = right_arm.addOrReplaceChild("armorRightArm_r17", CubeListBuilder.create().texOffs(34, 107).addBox(-6.1F, -3.6F, -0.85F, 2.4F, 0.1F, 1.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, 0.0F, 0.3F, 0.4009F, 0.0093F, -0.0113F));

		PartDefinition armorRightArm_r18 = right_arm.addOrReplaceChild("armorRightArm_r18", CubeListBuilder.create().texOffs(45, 98).addBox(-4.8F, -3.8F, -0.85F, 2.4F, 0.1F, 1.55F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(-4.2F, -3.9F, -1.15F, 1.2F, 0.1F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(55, 113).addBox(-4.0F, -3.9F, -0.85F, 0.8F, 0.1F, 1.55F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.3F, 0.4009F, 0.0093F, -0.0113F));

		PartDefinition armorRightArm_r19 = right_arm.addOrReplaceChild("armorRightArm_r19", CubeListBuilder.create().texOffs(65, 85).addBox(-4.8F, -3.8F, -1.55F, 2.4F, 0.1F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.3F, 0.9245F, 0.0093F, -0.0113F));

		PartDefinition armorRightArm_r20 = right_arm.addOrReplaceChild("armorRightArm_r20", CubeListBuilder.create().texOffs(68, 114).addBox(3.0F, -3.4F, -0.45F, 2.4F, 0.1F, 1.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 1.9F, 0.3F, 0.9245F, -0.0093F, -0.3058F));

		PartDefinition armorRightArm_r21 = right_arm.addOrReplaceChild("armorRightArm_r21", CubeListBuilder.create().texOffs(63, 92).addBox(3.0F, -3.4F, -0.45F, 2.4F, 0.1F, 1.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 0.8F, 0.3F, 0.9245F, -0.0093F, -0.1138F));

		PartDefinition armorRightArm_r22 = right_arm.addOrReplaceChild("armorRightArm_r22", CubeListBuilder.create().texOffs(89, 89).mirror().addBox(3.5F, -3.5F, -1.55F, 2.4F, 0.1F, 2.65F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.9F, 0.6F, 0.3F, 0.9245F, -0.0093F, -0.0614F));

		PartDefinition armorRightArm_r23 = right_arm.addOrReplaceChild("armorRightArm_r23", CubeListBuilder.create().texOffs(44, 89).addBox(-6.1F, -3.5F, -1.55F, 2.4F, 0.1F, 2.65F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, 0.0F, 0.3F, 0.9245F, -0.0093F, -0.0113F));

		PartDefinition armorRightArm_r24 = right_arm.addOrReplaceChild("armorRightArm_r24", CubeListBuilder.create().texOffs(82, 98).addBox(3.3F, -3.9F, -1.35F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9F, 0.6F, 0.3F, 1.5098F, -0.0475F, -0.0614F));

		PartDefinition armorRightArm_r25 = right_arm.addOrReplaceChild("armorRightArm_r25", CubeListBuilder.create().texOffs(106, 98).mirror().addBox(-5.9F, -4.0F, -1.35F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.1F, 0.0F, 0.3F, 1.5098F, 0.0475F, -0.0405F));

		PartDefinition armorRightArm_r26 = right_arm.addOrReplaceChild("armorRightArm_r26", CubeListBuilder.create().texOffs(51, 98).addBox(-4.6F, -4.2F, -1.35F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.3F, 1.5098F, 0.0475F, -0.0405F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(4.0F, 2.0F, 0.0F));

		PartDefinition armorLeftArm_r1 = left_arm.addOrReplaceChild("armorLeftArm_r1", CubeListBuilder.create().texOffs(0, 0).addBox(4.2F, -3.1F, -1.75F, 2.4F, 0.1F, 2.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, -1.0F, 0.3F, -0.2448F, -0.0093F, 0.308F));

		PartDefinition armorLeftArm_r2 = left_arm.addOrReplaceChild("armorLeftArm_r2", CubeListBuilder.create().texOffs(112, 98).addBox(2.5F, -3.8F, -0.85F, 2.4F, 0.1F, 1.55F, new CubeDeformation(0.0F))
				.texOffs(57, 113).addBox(3.3F, -3.9F, -0.85F, 0.8F, 0.1F, 1.55F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(3.1F, -3.9F, -1.15F, 1.2F, 0.1F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(33, 112).addBox(3.5F, -3.6F, -0.85F, 2.4F, 0.1F, 1.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, 0.1F, 0.3F, 0.4009F, -0.0093F, 0.0113F));

		PartDefinition armorLeftArm_r3 = left_arm.addOrReplaceChild("armorLeftArm_r3", CubeListBuilder.create().texOffs(34, 89).addBox(3.4F, -3.2F, -1.55F, 2.4F, 0.1F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, 0.1F, 0.3F, -1.0292F, 0.0206F, 0.0293F));

		PartDefinition armorLeftArm_r4 = left_arm.addOrReplaceChild("armorLeftArm_r4", CubeListBuilder.create().texOffs(3, 68).mirror().addBox(2.5F, -3.4F, -1.25F, 2.4F, 0.1F, 2.75F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.1F, 0.1F, 0.3F, -0.9245F, -0.0093F, 0.0113F));

		PartDefinition armorLeftArm_r5 = left_arm.addOrReplaceChild("armorLeftArm_r5", CubeListBuilder.create().texOffs(65, 54).addBox(2.1F, -4.0F, -0.15F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(100, 98).addBox(3.2F, -3.7F, -0.15F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, 0.1F, 0.3F, -1.3528F, 0.0833F, 0.044F));

		PartDefinition armorLeftArm_r6 = left_arm.addOrReplaceChild("armorLeftArm_r6", CubeListBuilder.create().texOffs(113, 90).addBox(3.2F, -3.6F, -0.15F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, 0.1F, 0.3F, -1.3528F, 0.0833F, 0.0963F));

		PartDefinition armorLeftArm_r7 = left_arm.addOrReplaceChild("armorLeftArm_r7", CubeListBuilder.create().texOffs(0, 90).addBox(3.4F, -3.2F, -1.55F, 2.4F, 0.1F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, 0.1F, 0.3F, -1.0292F, 0.0206F, 0.0817F));

		PartDefinition armorLeftArm_r8 = left_arm.addOrReplaceChild("armorLeftArm_r8", CubeListBuilder.create().texOffs(36, 30).addBox(4.1F, -3.1F, -1.55F, 2.4F, 0.1F, 1.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -0.1F, 0.3F, -1.0292F, 0.0206F, 0.1166F));

		PartDefinition armorLeftArm_r9 = left_arm.addOrReplaceChild("armorLeftArm_r9", CubeListBuilder.create().texOffs(62, 89).addBox(4.1F, -3.1F, -1.55F, 2.4F, 0.1F, 1.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, -1.0F, 0.3F, -1.0292F, 0.0206F, 0.326F));

		PartDefinition armorLeftArm_r10 = left_arm.addOrReplaceChild("armorLeftArm_r10", CubeListBuilder.create().texOffs(60, 80).addBox(4.1F, -3.5F, -0.45F, 2.4F, 0.1F, 1.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, -1.0F, 0.3F, 0.9245F, -0.0093F, 0.308F));

		PartDefinition armorLeftArm_r11 = left_arm.addOrReplaceChild("armorLeftArm_r11", CubeListBuilder.create().texOffs(58, 113).addBox(4.1F, -3.5F, -0.45F, 2.4F, 0.1F, 1.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -0.1F, 0.3F, 0.9245F, -0.0093F, 0.0986F));

		PartDefinition armorLeftArm_r12 = left_arm.addOrReplaceChild("armorLeftArm_r12", CubeListBuilder.create().texOffs(61, 113).addBox(4.2F, -3.5F, -0.85F, 2.4F, 0.1F, 1.35F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, -1.0F, 0.3F, 0.4009F, -0.0093F, 0.308F));

		PartDefinition armorLeftArm_r13 = left_arm.addOrReplaceChild("armorLeftArm_r13", CubeListBuilder.create().texOffs(59, 69).addBox(4.2F, -3.5F, -0.85F, 2.4F, 0.1F, 1.35F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -0.1F, 0.3F, 0.4009F, -0.0093F, 0.0986F));

		PartDefinition armorLeftArm_r14 = left_arm.addOrReplaceChild("armorLeftArm_r14", CubeListBuilder.create().texOffs(37, 113).addBox(3.5F, -3.6F, -0.85F, 2.4F, 0.1F, 1.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, 0.1F, 0.3F, 0.4009F, -0.0093F, 0.0637F));

		PartDefinition armorLeftArm_r15 = left_arm.addOrReplaceChild("armorLeftArm_r15", CubeListBuilder.create().texOffs(105, 89).mirror().addBox(3.5F, -3.6F, -1.55F, 2.4F, 0.1F, 2.65F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6F, 0.1F, 0.3F, 0.9245F, -0.0093F, 0.0637F));

		PartDefinition armorLeftArm_r16 = left_arm.addOrReplaceChild("armorLeftArm_r16", CubeListBuilder.create().texOffs(105, 89).addBox(4.2F, -3.1F, -1.75F, 2.4F, 0.1F, 2.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -0.1F, 0.3F, -0.2448F, -0.0093F, 0.0986F));

		PartDefinition armorLeftArm_r17 = left_arm.addOrReplaceChild("armorLeftArm_r17", CubeListBuilder.create().texOffs(96, 89).mirror().addBox(3.5F, -3.2F, -1.75F, 2.4F, 0.1F, 2.85F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6F, 0.1F, 0.3F, -0.2448F, -0.0093F, 0.0637F));

		PartDefinition armorLeftArm_r18 = left_arm.addOrReplaceChild("armorLeftArm_r18", CubeListBuilder.create().texOffs(28, 89).addBox(3.5F, -3.2F, -1.75F, 2.4F, 0.1F, 2.85F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(3.1F, -3.6F, -0.45F, 1.2F, 0.1F, 0.35F, new CubeDeformation(0.0F))
				.texOffs(107, 88).addBox(2.5F, -3.5F, -1.75F, 2.4F, 0.1F, 2.95F, new CubeDeformation(0.0F))
				.texOffs(120, 120).addBox(3.3F, -3.6F, -1.75F, 0.8F, 0.1F, 1.35F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, 0.1F, 0.3F, -0.2448F, -0.0093F, 0.0113F));

		PartDefinition armorLeftArm_r19 = left_arm.addOrReplaceChild("armorLeftArm_r19", CubeListBuilder.create().texOffs(107, 88).addBox(2.5F, -3.8F, -1.55F, 2.4F, 0.1F, 2.75F, new CubeDeformation(0.0F))
				.texOffs(21, 89).addBox(3.5F, -3.5F, -1.55F, 2.4F, 0.1F, 2.65F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, 0.1F, 0.3F, 0.9245F, -0.0093F, 0.0113F));

		PartDefinition armorLeftArm_r20 = left_arm.addOrReplaceChild("armorLeftArm_r20", CubeListBuilder.create().texOffs(113, 98).addBox(3.3F, -4.0F, -1.35F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(112, 67).addBox(2.3F, -4.2F, -1.35F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, 0.1F, 0.3F, 1.5098F, -0.0475F, 0.0405F));

		PartDefinition armorLeftArm_r21 = left_arm.addOrReplaceChild("armorLeftArm_r21", CubeListBuilder.create().texOffs(55, 94).mirror().addBox(3.3F, -3.9F, -1.35F, 2.4F, 0.1F, 2.25F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6F, 0.1F, 0.3F, 1.5098F, -0.0475F, 0.0929F));

		PartDefinition armorLeftArm_r22 = left_arm.addOrReplaceChild("armorLeftArm_r22", CubeListBuilder.create().texOffs(122, 122).addBox(-10.6F, -1.2F, 2.0F, 0.8F, 0.3F, 0.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
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