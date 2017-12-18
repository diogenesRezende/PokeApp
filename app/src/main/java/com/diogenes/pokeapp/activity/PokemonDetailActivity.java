package com.diogenes.pokeapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.api.client.ClientApi;
import com.diogenes.pokeapp.api.definition.PokemonInterface;
import com.diogenes.pokeapp.model.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends AppCompatActivity {

    private static final String TAG = "PokemonDetailActivity";
    private TextView mTvPokemonName;
    private TextView mTvPokemonTypes;
    private TextView mTvPokemonAttack;
    private TextView mTvPokemonDefense;
    private TextView mTvPokemonSpeed;
    private ImageView mIvPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mTvPokemonName = (TextView) findViewById(R.id.tv_detail_name);
        mTvPokemonTypes = (TextView) findViewById(R.id.tv_detail_types);
        mTvPokemonAttack = (TextView) findViewById(R.id.tv_detail_attack);
        mTvPokemonDefense = (TextView) findViewById(R.id.tv_detail_defense);
        mTvPokemonSpeed = (TextView) findViewById(R.id.tv_detail_speed);
        mIvPokemon = (ImageView) findViewById(R.id.iv_detail_pokemon);

        Intent intent = getIntent();
        Pokemon pokemon = (Pokemon) intent.getSerializableExtra("pokemon");
        getaData(pokemon.getName());
    }

    public void getaData(String pokemonName) {
        PokemonInterface pokemonInterface = ClientApi.getClient().create(PokemonInterface.class);
        Call<Pokemon> callDetailPokemon = pokemonInterface.getPokemonByName(pokemonName);
        callDetailPokemon.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    setDetailOnViews(response.body());
                } else {
                    Log.d(TAG, "onResponse: error" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    public void setDetailOnViews(Pokemon pokemon){
        mTvPokemonName.setText(pokemon.getName().toUpperCase());
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
