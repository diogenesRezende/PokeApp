package com.diogenes.pokeapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.adapter.PokeListAdapter;
import com.diogenes.pokeapp.api.client.ClientApi;
import com.diogenes.pokeapp.api.definition.PokemonInterface;
import com.diogenes.pokeapp.listener.RecyclerViewTouchListener;
import com.diogenes.pokeapp.model.Pokemon;
import com.diogenes.pokeapp.model.PokemonList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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
        mRvPokeList.addOnItemTouchListener(
                new RecyclerViewTouchListener(getApplicationContext(), mRvPokeList,
                        new RecyclerViewTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Intent intent = new Intent(MainActivity.this, PokemonDetailActivity.class);
                                intent.putExtra("pokemon", listPokemon.get(position));
                                startActivity(intent);
                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));

        getData();
    }


    public void getData() {
        PokemonInterface pokemonInterface = ClientApi.getClient().create(PokemonInterface.class);
        Call<PokemonList> callPokemonList = pokemonInterface.getPokemonList();
        callPokemonList.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if (response.isSuccessful()) {
                    PokemonList list = response.body();
                    adapter.addPokemonOnView(list.getPokemons());
                    for (Pokemon pokemon : list.getPokemons()) {
                        Log.d(TAG, "onResponse: " + pokemon.getName());
                    }
                } else {
                    Log.d(TAG, "onResponse: error  " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
