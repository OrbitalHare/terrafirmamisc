package me.orbitalhare.terrafirmamisc.common.recipes;

import java.util.function.Supplier;


import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//import net.dries007.tfc.common.recipes.SimpleItemRecipe;
import net.dries007.tfc.common.recipes.KnappingRecipe;

import static me.orbitalhare.terrafirmamisc.Terrafirmamisc.MOD_ID;

public class TFMRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);

    public static final RegistryObject<KnappingRecipe.Serializer> STRAW_KNAPPING = register("straw_knapping", () -> new KnappingRecipe.Serializer(TFMRecipeTypes.STRAW_KNAPPING));

    private static <S extends RecipeSerializer<?>> RegistryObject<S> register(String name, Supplier<S> factory)
    {
        return RECIPE_SERIALIZERS.register(name, factory);
    }
}
