package com.samleighton.trackandtriumph.datagen;

import com.samleighton.trackandtriumph.TrackandTriumph;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, TrackandTriumph.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // TODO: Write things for adding mod item tags here.
    }
}
