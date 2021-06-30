package com.railwayteam.railways.conductortweaker.conductor;

import com.blamejared.contenttweaker.items.ItemBuilder;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.railwayteam.railways.Railways;
import com.railwayteam.railways.conductortweaker.ConductorTweakerSetup;
import com.railwayteam.railways.conductortweaker.CustomConductorCapTexture;
import com.railwayteam.railways.conductortweaker.CustomConductorRenderer;
import com.railwayteam.railways.entities.conductor.ConductorEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister(modDeps = {"railways", "contenttweaker"})
@ZenCodeType.Name("mods.railways.conductor.ConductorBuilder")
public class ConductorBuilder {
    @ZenCodeType.Field
    public String name;
    @ZenCodeType.Field
    public boolean madeWithStructure = true;
    @ZenCodeType.Field
    public String structureBlock = "create:andesite_casing";
    @ZenCodeType.Field
    public String spawnItem = "railways:engineers_cap_-color-";
    @ZenCodeType.Field
    public ResourceLocation texture = new ResourceLocation(Railways.MODID, ConductorEntity.name);
    @ZenCodeType.Field
    public CustomConductorCapTexture<ConductorBuilder> capTexture = new CustomConductorCapTexture<>(this);
    @ZenCodeType.Field
    @ZenCodeType.Nullable
    public ConductorItemBuilder item = new ItemBuilder().withType(ConductorItemBuilder.class);

    {
        item.builder = this;
    }

    @ZenCodeType.Constructor
    public ConductorBuilder(String name) {
        this.name = name;
    }

    @ZenCodeType.Method
    public ConductorBuilder name(String name) {
        this.name = name;
        return this;
    }

    @ZenCodeType.Method
    public ConductorBuilder notMadeWithStructure() {
        this.madeWithStructure = false;
        return this;
    }

    @ZenCodeType.Method
    public ConductorBuilder structureBlock(String block) {
        this.structureBlock = block;
        return this;
    }

    @ZenCodeType.Method
    public ConductorBuilder spawnItem(String item) {
        this.spawnItem = item;
        return this;
    }

    public ConductorBuilder texture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    @ZenCodeType.Method
    public ConductorBuilder texture(String texture) {
        return texture(new ResourceLocation("contenttweaker", texture));
    }

    @ZenCodeType.Method
    public ConductorBuilder setCap(CustomConductorCapTexture<ConductorBuilder> cap) {
        this.capTexture = cap;
        return this;
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<ConductorBuilder> cap() {
        CustomConductorCapTexture<ConductorBuilder> cap = new CustomConductorCapTexture<>(this);
        setCap(cap);
        return cap;
    }

    @ZenCodeType.Method
    public ConductorBuilder setItem(ConductorItemBuilder itemBuilder) {
        this.item = itemBuilder;
        item.builder = this;
        return this;
    }

    @ZenCodeType.Method
    public ConductorBuilder noItem() {
        return setItem(null);
    }

    @ZenCodeType.Method
    public ConductorItemBuilder item() {
        this.item = new ItemBuilder().withType(ConductorItemBuilder.class);
        item.builder = this;
        return item;
    }

    @ZenCodeType.Method
    public void build() {
        ConductorTweakerSetup.registerConductor(name, (entityType, world) -> new CustomConductorEntity(entityType, world, this), EntityClassification.MISC, ($) -> {}, (m) -> new CustomConductorRenderer(m, this));
        ConductorTweakerSetup.CONDUCTOR_BUILDERS.put(name, this);
        if(item != null) item.build(name);
        //        return new CustomConductor(name, madeWithStructure, structureBlock, spawnItem, texture, capTexture);
    }
}
