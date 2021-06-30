package com.railwayteam.railways.conductortweaker.skin;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister(modDeps = {"railways"})
@ZenCodeType.Name("mods.railways.conductor.ConductorSkinBuilder")
public class ConductorSkinBuilder {
    @ZenCodeType.Field
    public String name;

    @ZenCodeType.Constructor
    public ConductorSkinBuilder(String name) {
        this.name = name;
    }

    @ZenCodeType.Method
    public ConductorSkinBuilder name(String name) {
        this.name = name;
        return this;
    }
}
