package com.railwayteam.railways.conductortweaker;

import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenCodeType.Name("mods.railways.conductor.CapTexture")
public class CustomConductorCapTexture<T> {
    @ZenCodeType.Field
    public final T builder;
    @ZenCodeType.Field
    public int colorRed = 1;
    @ZenCodeType.Field
    public int colorGreen = 1;
    @ZenCodeType.Field
    public int colorBlue = 1;
    @ZenCodeType.Field
    public ResourceLocation texture;
    @ZenCodeType.Field
    public boolean useTexture = true;
    @ZenCodeType.Field
    public boolean rainbow = false;
    @ZenCodeType.Field
    public boolean jeb = true;

    {
        defaultTexture();
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> red(int r) {
        colorRed = r;
        return this;
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> green(int g) {
        colorGreen = g;
        return this;
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> blue(int b) {
        colorBlue = b;
        return this;
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> color(int r, int g, int b) {
        red(r); green(g); return blue(b);
    }

    public CustomConductorCapTexture<T> texture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> texture(String texture) {
//        this.texture = new ResourceLocation("contenttweaker", texture);
//        return this;
        return texture("contenttweaker", texture);
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> texture(String namespace, String texture) {
        return texture(new ResourceLocation(namespace, texture));
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> useColor() {
        useTexture = false;
        return this;
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> alwaysRainbow() {
        rainbow = true;
        return this;
    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> disableJeb() {
        jeb = false;
        return this;
    }

//    @ZenCodeType.Method
//    public CustomConductorCapTexture<T> appendColor() {
//        colorTexture = true;
//        return this;
//    }

    @ZenCodeType.Method
    public CustomConductorCapTexture<T> defaultTexture() {
        return texture(new ResourceLocation("railways", "textures/models/armor/-color-_golem_hat.png"));
    }

    @ZenCodeType.Constructor
    public CustomConductorCapTexture(T builder) {
        this.builder = builder;
    }

    @ZenCodeType.Method
    public T build() {
        return builder;
    }
}
