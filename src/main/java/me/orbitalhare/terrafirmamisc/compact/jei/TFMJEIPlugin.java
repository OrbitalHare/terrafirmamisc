package me.orbitalhare.terrafirmamisc.compact.jei;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import me.orbitalhare.terrafirmamisc.Terrafirmamisc;
import me.orbitalhare.terrafirmamisc.common.TFMHelpers;
import me.orbitalhare.terrafirmamisc.common.recipes.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.KnappingRecipe;
import net.dries007.tfc.compat.jei.category.KnappingRecipeCategory;
import net.dries007.tfc.util.Helpers;

@JeiPlugin
public class TFMJEIPlugin implements IModPlugin {

    private static <C extends Container, T extends Recipe<C>> List<T> getRecipes(net.minecraft.world.item.crafting.RecipeType<T> type)
    {
        ClientLevel level = Minecraft.getInstance().level;
        assert level != null;
        return level.getRecipeManager().getAllRecipesFor(type);
    }

    private static <C extends Container, T extends Recipe<C>> List<T> getRecipes(net.minecraft.world.item.crafting.RecipeType<T> type, Predicate<T> filter)
    {
        return getRecipes(type).stream().filter(filter).collect(Collectors.toList());
    }

    private static void addCatalystTag(IRecipeCatalystRegistration r, TagKey<Item> tag, RecipeType<?> recipeType)
    {
        Helpers.getAllTagValues(tag, ForgeRegistries.ITEMS).forEach(item -> r.addRecipeCatalyst(new ItemStack(item), recipeType));
    }

    private static <T> RecipeType<T> type(String name, Class<T> tClass)
    {
        return RecipeType.create(Terrafirmamisc.MOD_ID, name, tClass);
    }

    private static final ResourceLocation STRAW_TEXTURE = Helpers.identifier("textures/gui/knapping/straw.png");

    public static final RecipeType<KnappingRecipe> STRAW_KNAPPING = type("straw_knapping", KnappingRecipe.class);

    @Override
    public ResourceLocation getPluginUid()
    {
        return TFMHelpers.identifier("jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration r)
    {
        IGuiHelper gui = r.getJeiHelpers().getGuiHelper();
        r.addRecipeCategories(new KnappingRecipeCategory<>(STRAW_KNAPPING, gui, new ItemStack(TFCItems.STRAW.get()), STRAW_TEXTURE, null));
    }

    @Override
    public void registerRecipes(IRecipeRegistration r)
    {
        r.addRecipes(STRAW_KNAPPING, getRecipes(TFMRecipeTypes.STRAW_KNAPPING.get()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration r)
    {
        cat(r, TFCItems.STRAW.get(), STRAW_KNAPPING);
    }


    private static void cat(IRecipeCatalystRegistration r, Supplier<? extends Block> supplier, RecipeType<?> type)
    {
        r.addRecipeCatalyst(new ItemStack(supplier.get()), type);
    }

    private static void cat(IRecipeCatalystRegistration r, Item item, RecipeType<?> type)
    {
        r.addRecipeCatalyst(new ItemStack(item), type);
    }

}
