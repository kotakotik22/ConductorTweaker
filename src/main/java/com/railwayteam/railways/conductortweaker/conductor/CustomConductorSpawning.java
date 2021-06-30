package com.railwayteam.railways.conductortweaker.conductor;

import com.railwayteam.railways.conductortweaker.ConductorTweakerSetup;
import com.railwayteam.railways.entities.conductor.ConductorEntity;
import com.railwayteam.railways.items.engineers_cap.EngineersCapItem;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CustomConductorSpawning {
    @SubscribeEvent
    public static void rightClickedItem(PlayerInteractEvent.RightClickBlock event) {
        if(!ModList.get().isLoaded("contenttweaker")) return;
        ItemStack stack = event.getItemStack();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        PlayerEntity player = event.getPlayer();
        for(String id : ConductorTweakerSetup.CONDUCTOR_BUILDERS.keySet()) {
            EntityEntry<CustomConductorEntity> entity = ConductorTweakerSetup.CUSTOM_CONDUCTORS.get(id);
            ConductorBuilder builder = ConductorTweakerSetup.CONDUCTOR_BUILDERS.get(id);
            if(!builder.madeWithStructure) return;
            boolean f = false;
            DyeColor color1 = null;
            if(builder.spawnItem.contains("-color-")) {
                for(DyeColor color : DyeColor.values()) {
                    if(new ResourceLocation(builder.spawnItem.replace("-color-", color.getTranslationKey())).equals(stack.getItem().getRegistryName())) {
                        f = true;
                        color1 = color;
                        break;
                    }
                }
                if(color1 == null) {
                    f = new ResourceLocation(builder.spawnItem).equals(stack.getItem().getRegistryName());
                }
            } else {
                f = new ResourceLocation(builder.spawnItem).equals(stack.getItem().getRegistryName());
            }

            if(f) {
                BlockPos[] toRemove = EngineersCapItem.getBlocksToRemove(world, pos, ((world1, pos1) -> world1.getBlockState(pos1).getBlock().getRegistryName().equals(new ResourceLocation(builder.structureBlock))));
                if(toRemove.length > 0) {
                    for(BlockPos pos1 : toRemove) world.breakBlock(pos1, false, player);
                    CustomConductorEntity entity1 = CustomConductorEntity.spawn(world, pos.getX(), pos.getY(), pos.getZ(), color1 == null ? builder.item.getColor(ConductorEntity.getDefaultColor()) : color1, builder);
                    if(!player.isCreative()) {
                        stack.shrink(1);
                    }
                    return; // dont check for any other conductors if spawned here
                }
            }
        }
    }
}
