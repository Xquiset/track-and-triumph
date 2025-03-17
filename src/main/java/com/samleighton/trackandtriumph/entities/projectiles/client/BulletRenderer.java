package com.samleighton.trackandtriumph.entities.projectiles.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.samleighton.trackandtriumph.TrackandTriumph;
import com.samleighton.trackandtriumph.client.renderer.entity.state.BulletRenderState;
import com.samleighton.trackandtriumph.entities.projectiles.Bullet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BulletRenderer extends EntityRenderer<Bullet, BulletRenderState> {
    private final BulletModel model;

    public BulletRenderer(EntityRendererProvider.Context rendererContext) {
        super(rendererContext);
        this.model = new BulletModel(rendererContext.bakeLayer(BulletModel.LAYER_LOCATION));
    }

    public void render(BulletRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(renderState.yRot - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(renderState.xRot));
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityCutout(this.getTextureLocation(renderState)));
        this.model.setupAnim(renderState);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    public BulletRenderState createRenderState() {
        return new BulletRenderState();
    }

    public ResourceLocation getTextureLocation(BulletRenderState renderState) {
        return ResourceLocation.fromNamespaceAndPath(TrackandTriumph.MODID, "textures/entity/projectiles/bullet_762.png");
    }

    public void extractRenderState(Bullet p_entity, BulletRenderState renderState, float partialTick) {
        super.extractRenderState(p_entity, renderState, partialTick);
        renderState.xRot = p_entity.getXRot(partialTick);
        renderState.yRot = p_entity.getYRot(partialTick);
        renderState.shake = (float)p_entity.shakeTime - partialTick;
    }
}
