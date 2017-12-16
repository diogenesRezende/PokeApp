package com.diogenes.pokeapp.api.definition;

import com.diogenes.pokeapp.model.Pokemon;
import com.diogenes.pokeapp.model.PokemonList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by diogenes on 16/12/17.
 */

public interface PokemonInterface {
    @GET("api/v2/pokemon/")
    Call<PokemonList> getPokemonList();
    @GET("api/v2/pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id")int id);
}
