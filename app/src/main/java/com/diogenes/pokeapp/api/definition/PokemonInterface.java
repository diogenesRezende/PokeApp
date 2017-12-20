package com.diogenes.pokeapp.api.definition;

import com.diogenes.pokeapp.model.Item;
import com.diogenes.pokeapp.model.ItemList;
import com.diogenes.pokeapp.model.Pokemon;
import com.diogenes.pokeapp.model.PokemonList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by diogenes on 16/12/17.
 */

public interface PokemonInterface {
    @GET("api/v2/pokemon/")
    Call<PokemonList> getPokemonList(@Query("limit")int limit, @Query("offset")int offset);
    @GET("api/v2/item/")
    Call<ItemList> getItemList(@Query("limit")int limit, @Query("offset")int offset);
    @GET("api/v2/pokemon/{name}")
    Call<Pokemon> getPokemonByName(@Path("name")String name);
    @GET("api/v2/item/{name}")
    Call<Item> getItemByName(@Path("name")String name);
}
