package com.samleighton.trackandtriumph.entities;

import com.samleighton.trackandtriumph.TrackandTriumph;
import com.samleighton.trackandtriumph.entities.projectiles.Bullet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TTEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.createEntities(TrackandTriumph.MODID);

    public static final Supplier<EntityType<Bullet>> BULLET = ENTITY_TYPES.register("bullet_762", resourceLocation ->
            EntityType.Builder.<Bullet>of(Bullet::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build(ResourceKey.create(ENTITY_TYPES.getRegistryKey(), resourceLocation))
    );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
