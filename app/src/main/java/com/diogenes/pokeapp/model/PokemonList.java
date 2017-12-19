
package com.diogenes.pokeapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonList {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("results")
    @Expose
    private List<GenericCommonEntity> pokemons = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<GenericCommonEntity> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<GenericCommonEntity> pokemons) {
        this.pokemons = pokemons;
    }

}
