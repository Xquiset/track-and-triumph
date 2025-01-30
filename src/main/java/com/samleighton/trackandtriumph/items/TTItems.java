package com.samleighton.trackandtriumph.items;

import com.samleighton.trackandtriumph.TrackandTriumph;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TTItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TrackandTriumph.MODID);

    public static final DeferredItem<Item> CUSTOM_STICK = ITEMS.register("custom_stick", (resourceLocation) -> {
        ResourceKey<Item> aStickKey = ResourceKey.create(ITEMS.getRegistryKey(), resourceLocation);
        return new Item(new Item.Properties().setId(aStickKey));
    });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
