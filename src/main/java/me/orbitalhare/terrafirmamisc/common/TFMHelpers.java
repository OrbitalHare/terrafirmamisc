package me.orbitalhare.terrafirmamisc.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import net.dries007.tfc.util.JsonHelpers;

import static me.orbitalhare.terrafirmamisc.terrafirmamisc.MODID;

public class TFMHelpers {

    public static ResourceLocation identifier(String id)
    {
        return new ResourceLocation(MODID, id);
    }

    public static ResourceLocation[] arrayOfResourceLocationsFromJson(JsonObject json, String field)
    {
        final JsonArray array = JsonHelpers.getAsJsonArray(json, field);
        final ResourceLocation[] textures = new ResourceLocation[array.size()];
        int i = 0;
        for (JsonElement element : array)
        {
            textures[i] = new ResourceLocation(element.getAsString());
            i++;
        }
        return textures;
    }

    public static ResourceLocation[] arrayOfResourceLocationsFromNetwork(FriendlyByteBuf buffer)
    {
        final int length = buffer.readVarInt();
        if (length == 0) return new ResourceLocation[] {};
        final ResourceLocation[] textures = new ResourceLocation[length];
        for (int i = 0; i < length; i++)
        {
            textures[i] = new ResourceLocation(buffer.readUtf());
        }
        return textures;
    }


}
