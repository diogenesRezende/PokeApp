
package com.diogenes.pokeapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonForm {

    @SerializedName("sprites")
    @Expose
    private Sprites sprites;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("pokemon")
    @Expose
    private Pokemon pokemon;
    @SerializedName("name")
    @Expose
    private String name;

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
