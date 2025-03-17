package com.samleighton.trackandtriumph.entities.projectiles.client;

import com.samleighton.trackandtriumph.TrackandTriumph;
import com.samleighton.trackandtriumph.client.renderer.entity.state.BulletRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class BulletModel extends EntityModel<BulletRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TrackandTriumph.MODID, "bullet_762"), "main");


    public BulletModel(ModelPart root) {
        super(root, RenderType::entityCutout);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild(
                "back",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F),
                PartPose.offsetAndRotation(-11.0F, 0.0F, 0.0F, (float) (Math.PI / 4), 0.0F, 0.0F).withScale(0.8F)
        );
        CubeListBuilder cubelistbuilder = CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-12.0F, -2.0F, 0.0F, 16.0F, 4.0F, 0.0F, CubeDeformation.NONE, 1.0F, 0.8F);
        partdefinition.addOrReplaceChild("cross_1", cubelistbuilder, PartPose.rotation((float) (Math.PI / 4), 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("cross_2", cubelistbuilder, PartPose.rotation((float) (Math.PI * 3.0 / 4.0), 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition.transformed(transformer -> transformer.scaled(0.9F)), 32, 32);
    }

    public void setupAnim(BulletRenderState renderState) {
        super.setupAnim(renderState);
        if (renderState.shake > 0.0F) {
            float f = -Mth.sin(renderState.shake * 3.0F) * renderState.shake;
            this.root.zRot += f * (float) (Math.PI / 180.0);
        }
    }
}
