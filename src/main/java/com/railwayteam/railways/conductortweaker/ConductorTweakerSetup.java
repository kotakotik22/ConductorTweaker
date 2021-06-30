package com.railwayteam.railways.conductortweaker;

import com.railwayteam.railways.Railways;
import com.railwayteam.railways.conductortweaker.conductor.ConductorBuilder;
import com.railwayteam.railways.conductortweaker.conductor.CustomConductorEntity;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.EntityBuilder;
import com.tterrag.registrate.util.OneTimeEventReceiver;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.HashMap;
import java.util.function.Consumer;

public class ConductorTweakerSetup {
    public static HashMap<String, EntityEntry<CustomConductorEntity>> CUSTOM_CONDUCTORS = new HashMap<>();
    public static HashMap<String, ConductorBuilder> CONDUCTOR_BUILDERS = new HashMap<>();

    public static Registrate registrate;

    public static void register(Registrate reg) {
        registrate = reg;
//        R_ENTITY_CUSTOM_CONDUCTOR = reg.entity(ConductorEntity.name + "_ct", CustomConductorEntity::new, EntityClassification.MISC)
//                .lang(ConductorEntity.defaultDisplayName).properties(p -> p.size(0.5F, 1.3F))
//                .register();
    }

    public static EntityEntry<CustomConductorEntity> registerConductor(String id, EntityType.IFactory<CustomConductorEntity> constructor, EntityClassification entityClassification, Consumer<EntityBuilder<CustomConductorEntity, Registrate>> configure, IRenderFactory<CustomConductorEntity> renderFactory) {
//        CustomConductorEntity::new;
        EntityBuilder<CustomConductorEntity, Registrate> entity = registrate.entity(id, constructor, entityClassification)
                .lang("Conductor")
                .properties(p -> p.size(0.5f, 1.3f));
        configure.accept(entity);
        EntityEntry<CustomConductorEntity> built = entity.register();
        CUSTOM_CONDUCTORS.put(id, built);
        OneTimeEventReceiver.addListener(Railways.MOD_EVENT_BUS, FMLClientSetupEvent.class, event -> {
            RenderingRegistry.registerEntityRenderingHandler(built.get(), renderFactory);
        });
        OneTimeEventReceiver.addListener(Railways.MOD_EVENT_BUS, EntityAttributeCreationEvent.class, event -> {
            event.put(built.get(), LivingEntity.createLivingAttributes().add(Attributes.GENERIC_FOLLOW_RANGE, 16).build());
        });

        return built;
    }
}
