package com.samleighton.trackandtriumph.datagen;

import com.samleighton.trackandtriumph.TrackandTriumph;
import com.samleighton.trackandtriumph.items.TTItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

public class ModItemModelProvider extends ModelProvider {
    public ModItemModelProvider(PackOutput output) {
        super(output, TrackandTriumph.MODID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        ItemModel.Unbaked ak47 = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(TTItems.AK47.get()));
        ItemModel.Unbaked pullingAk47 = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(TTItems.AK47.get(), "_pulling"));
        itemModels.itemModelOutput.accept(TTItems.AK47.get(), ItemModelUtils.conditional(ItemModelUtils.isUsingItem(), pullingAk47, ak47));

        ItemModel.Unbaked _762_bullet = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(TTItems.BULLET.get()));
        itemModels.itemModelOutput.accept(TTItems.BULLET.get(), _762_bullet);
    }
}
