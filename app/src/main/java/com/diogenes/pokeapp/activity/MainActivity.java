package com.diogenes.pokeapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.adapter.PokeListAdapter;
import com.diogenes.pokeapp.api.client.ClientApi;
import com.diogenes.pokeapp.api.definition.PokemonInterface;
import com.diogenes.pokeapp.model.Pokemon;
import com.diogenes.pokeapp.model.PokemonList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvPokeList;
    private PokeListAdapter adapter;
    private List<Pokemon> listPokemon = new ArrayList<Pokemon>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvPokeList = (RecyclerView) findViewById(R.id.rv_pokelist);
        adapter = new PokeListAdapter(listPokemon);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mRvPokeList.setLayoutManager(layoutManager);
        mRvPokeList.setItemAnimator(new DefaultItemAnimator());
        mRvPokeList.setAdapter(adapter);

        getData();
    }


    public void getData(){
        PokemonInterface pokemonInterface = ClientApi.getClient().create(PokemonInterface.class);
        for (int i = 0; i<= 30;i++){
            Call<Pokemon> list = pokemonInterface.getPokemon(i);
            list.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if (response.isSuccessful()) {
                        Pokemon pokemon = response.body();

                        listPokemon.add(pokemon);
                        adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {

                }
            });
        }
    }
}
