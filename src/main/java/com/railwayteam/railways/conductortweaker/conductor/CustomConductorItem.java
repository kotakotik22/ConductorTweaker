package com.railwayteam.railways.conductortweaker.conductor;

import com.blamejared.contenttweaker.api.items.IIsCotItem;
import com.blamejared.contenttweaker.api.resources.ResourceType;
import com.blamejared.contenttweaker.api.resources.WriteableResource;
import com.blamejared.contenttweaker.api.resources.WriteableResourceTemplate;
import com.railwayteam.railways.Railways;
import com.railwayteam.railways.entities.conductor.ConductorEntity;
import com.railwayteam.railways.items.ConductorItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;

public class CustomConductorItem extends ConductorItem implements IIsCotItem {
    public final ConductorBuilder entityBuilder;

    public CustomConductorItem(ConductorBuilder entityBuilder, ResourceLocation location) {
        super(entityBuilder.item.getItemBuilder().getItemProperties(), entityBuilder.item.getColor(DyeColor.BLUE));
        this.entityBuilder = entityBuilder;
        this.setRegistryName(location);
    }

    @Override
    public ItemStack create(ConductorEntity entity) {
        ItemStack stack = new ItemStack(this);
        putEntityDataInItem(stack, entity);
        return stack;
    }

    @Nonnull
    @Override
    public Collection<WriteableResource> getResourcePackResources() {
        Collection<WriteableResource> out = new ArrayList<>();
        if(!entityBuilder.item.generateModel) return out;
        ResourceLocation id = getRegistryNameNonNull();
        out.add(new WriteableResourceTemplate(ResourceType.ASSETS,
                id, "models", "item").withTemplate(ResourceType.ASSETS,
                new ResourceLocation(Railways.MODID, "models/item/conductor")).setLocationProperty(id));

        return out;
    }

    @Nonnull
    @Override
    public Collection<WriteableResource> getDataPackResources() {
        return new ArrayList<>();
    }

    @Override
    public CustomConductorEntity spawnEntity(PlayerEntity plr, ItemStack stack, Vector3d pos) {
        CustomConductorEntity entity = CustomConductorEntity.spawn(plr.world, pos, ConductorEntity.getDefaultColor(), entityBuilder);
        entity.setColor(color);
//        entity.itemBuilder = builder;
//        entity.builder = entityBuilder;
        return entity;
    }
}
