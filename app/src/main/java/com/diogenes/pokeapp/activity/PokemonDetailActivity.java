package com.diogenes.pokeapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.adapter.AbilityListAdapter;
import com.diogenes.pokeapp.adapter.MoveListAdapter;
import com.diogenes.pokeapp.adapter.StatListAdapter;
import com.diogenes.pokeapp.api.client.ClientApi;
import com.diogenes.pokeapp.api.definition.PokemonInterface;
import com.diogenes.pokeapp.model.Ability;
import com.diogenes.pokeapp.model.GenericCommonEntity;
import com.diogenes.pokeapp.model.Move;
import com.diogenes.pokeapp.model.Pokemon;
import com.diogenes.pokeapp.model.Stat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends AppCompatActivity {

    private static final String TAG = "PokemonDetailActivity";
    private TextView mTvPokemonName;
    private TextView mTvPokemonTypes;
    private TextView mTvPokemonWeight;
    private ImageView mIvPokemon;
    private RecyclerView mRvAbility;
    private List<Ability> mAbilities = new ArrayList<Ability>();
    private AbilityListAdapter mAbilityListAdapter;
    private RecyclerView mRvStat;
    private List<Stat> mStats = new ArrayList<Stat>();
    private StatListAdapter mStatListAdapter;
    private RecyclerView mRvMove;
    private List<Move> mMoves = new ArrayList<Move>();
    private MoveListAdapter mMoveListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //others
        mIvPokemon = (ImageView) findViewById(R.id.iv_detail_pokemon);
        mTvPokemonName = (TextView) findViewById(R.id.tv_detail_name);
        mTvPokemonTypes = (TextView) findViewById(R.id.tv_detail_types);
        mTvPokemonWeight = (TextView) findViewById(R.id.tv_detail_weight);

        //abilities
        mRvAbility = (RecyclerView) findViewById(R.id.rv_detail_abilities);
        mAbilityListAdapter = new AbilityListAdapter(mAbilities);
        RecyclerView.LayoutManager abilitiesLayoutManager = new LinearLayoutManager(this);
        mRvAbility.setLayoutManager(abilitiesLayoutManager);
        mRvAbility.setItemAnimator(new DefaultItemAnimator());
        mRvAbility.setAdapter(mAbilityListAdapter);

        //stats
        mRvStat = (RecyclerView) findViewById(R.id.rv_detail_stats);
        mStatListAdapter = new StatListAdapter(mStats);
        RecyclerView.LayoutManager statsLayoutManager = new LinearLayoutManager(this);
        mRvStat.setLayoutManager(statsLayoutManager);
        mRvStat.setItemAnimator(new DefaultItemAnimator());
        mRvStat.setAdapter(mStatListAdapter);

        //moves
        mRvMove = (RecyclerView) findViewById(R.id.rv_detail_moves);
        mMoveListAdapter = new MoveListAdapter(mMoves);
        RecyclerView.LayoutManager movesLayoutManager = new LinearLayoutManager(this);
        mRvMove.setLayoutManager(movesLayoutManager);
        mRvMove.setItemAnimator(new DefaultItemAnimator());
        mRvMove.setAdapter(mMoveListAdapter);

        Intent intent = getIntent();
        GenericCommonEntity pokemon = (GenericCommonEntity) intent.getSerializableExtra("pokemon");
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
        Picasso.with(this)
                .load(pokemon.getSprites().getFrontDefault())
                .resize(64, 64)
                .into(mIvPokemon);
        mTvPokemonName.setText(pokemon.getName().toUpperCase());
        mTvPokemonTypes.setText(pokemon.typesToString());
        mTvPokemonWeight.setText(pokemon.getWeight().toString());
        mAbilityListAdapter.addAbilitiesOnView(pokemon.getAbilities());
        mStatListAdapter.addStatsOnView(pokemon.getStats());
        mMoveListAdapter.addMovesOnView(pokemon.getMoves());
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
