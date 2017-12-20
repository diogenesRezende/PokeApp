package com.diogenes.pokeapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.adapter.PokeListAdapter;
import com.diogenes.pokeapp.api.client.ClientApi;
import com.diogenes.pokeapp.api.definition.PokemonInterface;
import com.diogenes.pokeapp.listener.RecyclerViewTouchListener;
import com.diogenes.pokeapp.model.GenericCommonEntity;
import com.diogenes.pokeapp.model.PokemonList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonActivity extends AppCompatActivity {

    private static final String TAG = "PokemonActivity";

    private RecyclerView mRvPokeList;
    private PokeListAdapter adapter;
    private List<GenericCommonEntity> listPokemon = new ArrayList<GenericCommonEntity>();
    private int offset;
    private boolean canLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRvPokeList = (RecyclerView) findViewById(R.id.rv_pokelist);
        adapter = new PokeListAdapter(listPokemon);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);

        mRvPokeList.setLayoutManager(layoutManager);
        mRvPokeList.setItemAnimator(new DefaultItemAnimator());
        mRvPokeList.setAdapter(adapter);
        mRvPokeList.setHasFixedSize(true);
        mRvPokeList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastItemCount = layoutManager.findFirstVisibleItemPosition();
                    if (canLoad) {
                        if ((visibleItemCount + pastItemCount) >= totalItemCount) {
                            canLoad = false;
                            offset += 20;
                            getData(offset);
                        }
                    }
                }
            }
        });
        mRvPokeList.addOnItemTouchListener(
                new RecyclerViewTouchListener(getApplicationContext(), mRvPokeList,
                        new RecyclerViewTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Intent intent = new Intent(PokemonActivity.this, PokemonDetailActivity.class);
                                intent.putExtra("pokemon", listPokemon.get(position));
                                startActivity(intent);
                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));
        canLoad = true;
        offset = 0;
        getData(offset);
    }


    public void getData(int offset) {
        PokemonInterface pokemonInterface = ClientApi.getClient().create(PokemonInterface.class);
        Call<PokemonList> callPokemonList = pokemonInterface.getPokemonList(20, offset);
        callPokemonList.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                canLoad = true;
                if (response.isSuccessful()) {
                    PokemonList list = response.body();
                    adapter.addPokemonOnView(list.getPokemons());
                } else {
                    Toast.makeText(getBaseContext(),"Error loading information! Check your connection!",Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onResponse: error  " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                canLoad = true;
                Toast.makeText(getBaseContext(),"Error loading information! Check your connection!",Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
        return;
    }
}
