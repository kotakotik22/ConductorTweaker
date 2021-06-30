package com.railwayteam.railways.conductortweaker.conductor;

import com.railwayteam.railways.conductortweaker.CustomConductorCapTexture;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenCodeType.Name("mods.railways.conductor.Conductor")
public class CustomConductor {
    @ZenCodeType.Field
    public final String name;
    @ZenCodeType.Field
    public final boolean madeWithStructure;
    @ZenCodeType.Field
    public final String structureBlock;
    @ZenCodeType.Field
    public final String spawnItem;
    @ZenCodeType.Field
    public final ResourceLocation texture;
    @ZenCodeType.Field
    public final CustomConductorCapTexture<ConductorBuilder> capTexture;
    @ZenCodeType.Field
    public final ConductorItemBuilder item;

    public CustomConductor(String name, boolean madeWithStructure, String structureBlock, String spawnItem, ResourceLocation texture, CustomConductorCapTexture<ConductorBuilder> capTexture, ConductorItemBuilder item) {
        this.name = name;
        this.madeWithStructure = madeWithStructure;
        this.structureBlock = structureBlock;
        this.spawnItem = spawnItem;
        this.texture = texture;
        this.capTexture = capTexture;
        this.item = item;

        if(item != null) {
            item.build(name);
        }

//        ConductorTweaker.customConductors.add(this);
    }
}
