package com.railwayteam.railways.conductortweaker.conductor;

import com.blamejared.contenttweaker.VanillaFactory;
import com.blamejared.contenttweaker.api.items.ItemTypeBuilder;
import com.blamejared.contenttweaker.items.ItemBuilder;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister(modDeps = {"railways", "contenttweaker"})
@ZenCodeType.Name("mods.railways.conductor.ConductorItem")
public class ConductorItemBuilder extends ItemTypeBuilder {
    @ZenCodeType.Field
    public ConductorBuilder builder;
    @ZenCodeType.Field
    public String color;
    @ZenCodeType.Field
    public boolean generateModel = true;
//    @ZenCodeType.Field
//    public ResourceLocation texture;

    public ConductorItemBuilder(ItemBuilder itemBuilder, ConductorBuilder builder) {
        super(itemBuilder);
        this.builder = builder;
    }

    public ConductorItemBuilder(ItemBuilder itemBuilder) {
        super(itemBuilder);
    }

    @ZenCodeType.Method
    public ConductorItemBuilder color(String color) {
        this.color = color;
        return this;
    }

    @ZenCodeType.Method
    public ConductorItemBuilder noModel() {
        this.generateModel = false;
        return this;
    }

//    public ConductorItemBuilder texture(ResourceLocation texture) {
//        this.texture = texture;
//        return this;
//    }

//    public ConductorItemBuilder texture(String texture) {
//        return texture(new ResourceLocation("contenttweaker"));
//    }

    public DyeColor getColor(DyeColor defaultColor) {
        return DyeColor.byTranslationKey(color, defaultColor);
    }

    @ZenCodeType.Method("backToEntity")
    public ConductorBuilder buildAndBackToEntity(String name) {
        build(name);
        return builder;
    }

    public ResourceLocation loc;

    @Override
    public void build(ResourceLocation location) {
        loc = location;
        VanillaFactory.queueItemForRegistration(new CustomConductorItem(this.builder, location));
    }
}
