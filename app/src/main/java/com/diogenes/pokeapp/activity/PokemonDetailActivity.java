package com.diogenes.pokeapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.model.Pokemon;

public class PokemonDetailActivity extends AppCompatActivity {

    private static final String TAG = "PokemonDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        Intent intent = getIntent();

        Pokemon pokemon = (Pokemon) intent.getSerializableExtra("pokemon");

        Log.d(TAG, "onCreate: " + pokemon.toString());
        Toast.makeText(this, pokemon.toString(),Toast.LENGTH_LONG).show();


    }
}
