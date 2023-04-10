package me.orbitalhare.terrafirmamisc.common.misc;



import me.orbitalhare.terrafirmamisc.TFMTags;
import me.orbitalhare.terrafirmamisc.common.container.TFMContainerProviders;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.BlockItemPlacement;
import net.dries007.tfc.util.InteractionManager;
import net.minecraft.world.item.crafting.Ingredient;


public class TFMInteractionManager {
    
    public static void init()
    {
        InteractionManager.register(Ingredient.of(TFMTags.Items.STRAW_KNAPPING), false, true, InteractionManager.createKnappingInteraction((stack, player) -> stack.getCount() >= 5, TFMContainerProviders.STRAW_KNAPPING));
    }
}
