package me.orbitalhare.terrafirmamisc.common.recipes;

import me.orbitalhare.terrafirmamisc.common.TFMHelpers;
import net.dries007.tfc.common.recipes.KnappingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static me.orbitalhare.terrafirmamisc.Terrafirmamisc.MOD_ID;

public class TFMRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, MOD_ID);

    public static final RegistryObject<RecipeType<KnappingRecipe>> STRAW_KNAPPING = register("straw_knapping");
    
    
    private static <R extends Recipe<?>> RegistryObject<RecipeType<R>> register(String name)
    {
        return RECIPE_TYPES.register(name, () -> new RecipeType<>() {
            @Override
            public String toString()
            {
                return TFMHelpers.identifier(name).toString();
            }
        });
    }
}
