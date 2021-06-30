package com.railwayteam.railways.conductortweaker;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.railwayteam.railways.conductortweaker.conductor.ConductorBuilder;
import com.railwayteam.railways.entities.conductor.ConductorEntity;
import com.railwayteam.railways.entities.conductor.ConductorEntityModel;
import com.railwayteam.railways.entities.conductor.EngineersCapLayer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;

public class CustomCapRenderer extends EngineersCapLayer {
    public final ConductorBuilder builder;

    public CustomCapRenderer(IEntityRenderer<ConductorEntity, ConductorEntityModel> p_i50926_1_, ConductorBuilder builder) {
        super(p_i50926_1_);
        this.builder = builder;
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int packedLight, ConductorEntity entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        capModel.setLivingAnimations(entity, p_225628_5_, p_225628_6_, p_225628_7_);
        capModel.setAngles(entity, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);

        IVertexBuilder ivertexbuilder = renderTypeBuffer.getBuffer(RenderType.getEntityCutoutNoCull
                (getTextureRainbowSupport(entity)));
        float r = 1;
        float g = 1;
        float b = 1;
        if (shouldBeRainbow(entity)) {
            int i = entity.ticksExisted / 25 + entity.getEntityId();
            int j = DyeColor.values().length;
            int k = i % j;
            int l = (i + 1) % j;
            float f3 = ((float)(entity.ticksExisted % 25) + p_225628_7_) / 25.0F;
            float[] afloat1 = SheepEntity.getDyeRgb(DyeColor.byId(k));
            float[] afloat2 = SheepEntity.getDyeRgb(DyeColor.byId(l));
            r = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
            g = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
            b = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
        } else if(!builder.capTexture.useTexture) {
            r = builder.capTexture.colorRed;
            g = builder.capTexture.colorGreen;
            b = builder.capTexture.colorBlue;
        }
        capModel.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
    }

    public boolean shouldBeRainbow(ConductorEntity entity) {
        return (entity.shouldBeRainbow() && builder.capTexture.jeb) || builder.capTexture.rainbow;
    }

    public ResourceLocation getTexture(String color) {
        ResourceLocation r = builder.capTexture.texture;
        r = new ResourceLocation(r.getNamespace(), r.getPath().replace("-color-", color));
        return r;
    }

    public ResourceLocation getTexture(DyeColor color) {
        return getTexture(color.getTranslationKey());
    }

    public ResourceLocation getTexture2(ConductorEntity entity) {
        return getTexture(entity.getColor());
    }

    public ResourceLocation getTextureRainbowSupport(ConductorEntity entity) {
        return shouldBeRainbow(entity) ? getCapTexture(DyeColor.WHITE) : getTexture2(entity);
    }
}
