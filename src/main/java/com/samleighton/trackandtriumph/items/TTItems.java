package com.samleighton.trackandtriumph.items;

import com.samleighton.trackandtriumph.TrackandTriumph;
import com.samleighton.trackandtriumph.items.weapons.AssaultRifleItem;
import com.samleighton.trackandtriumph.items.weapons.projectiles.BulletItem;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TTItems {

    // Registry
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TrackandTriumph.MODID);

    // In-Game items
    public static final DeferredItem<Item> AK47 = ITEMS.register("ak47", (resourceLocation ->
            new AssaultRifleItem(new Item.Properties().setId(ResourceKey.create(ITEMS.getRegistryKey(), resourceLocation)).durability(500))
    ));
    public static final DeferredItem<Item> BULLET = ITEMS.register("bullet_762", (resourceLocation ->
            new BulletItem(new Item.Properties().setId(ResourceKey.create(ITEMS.getRegistryKey(), resourceLocation)))
    ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
