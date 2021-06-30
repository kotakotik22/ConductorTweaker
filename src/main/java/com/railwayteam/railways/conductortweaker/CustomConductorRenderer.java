package com.railwayteam.railways.conductortweaker;

import com.railwayteam.railways.conductortweaker.conductor.ConductorBuilder;
import com.railwayteam.railways.entities.conductor.ConductorEntity;
import com.railwayteam.railways.entities.conductor.ConductorEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CustomConductorRenderer extends MobRenderer<ConductorEntity, ConductorEntityModel> {
    public final ConductorBuilder builder;

    public CustomConductorRenderer(EntityRendererManager p_i50961_1_, ConductorBuilder builder) {
        super(p_i50961_1_, new ConductorEntityModel(), 0.2f);
        this.builder = builder;
        this.shadowSize = 0.4F;
        addLayer(new CustomCapRenderer(this, builder));
    }

    @Override
    public ResourceLocation getEntityTexture(ConductorEntity p_110775_1_) {
        return builder.texture;
    }
}
