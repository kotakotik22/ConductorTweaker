package com.railwayteam.railways.conductortweaker.conductor;

import com.blamejared.contenttweaker.VanillaFactory;
import com.railwayteam.railways.conductortweaker.ConductorTweakerSetup;
import com.railwayteam.railways.entities.conductor.ConductorEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class CustomConductorEntity extends ConductorEntity {
    public final ConductorBuilder builder;

    public static CustomConductorEntity spawn(World world, double x, double y, double z, DyeColor color, ConductorBuilder builder) {
        CustomConductorEntity entity = new CustomConductorEntity(ConductorTweakerSetup.CUSTOM_CONDUCTORS.get(builder.name).get(), world, builder);
        entity.setPosition(x, y, z);

        world.addEntity(entity);
        entity.setColor(color);
        entity.updateCap();
        return entity;
    }

    public static CustomConductorEntity spawn(World world, Vector3d pos, DyeColor color, ConductorBuilder builder) {
        return spawn(world, pos.getX(), pos.getY(), pos.getZ(), color, builder);
    }

    public CustomConductorEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_, ConductorBuilder builder) {
        super(p_i48575_1_, p_i48575_2_);
        this.builder = builder;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return ((CustomConductorItem) VanillaFactory.REGISTRY.getItem(builder.item.loc)).create(this);
//        new ConductorItem(builder.item.getItemBuilder().getItemProperties(), th);
    }
}
