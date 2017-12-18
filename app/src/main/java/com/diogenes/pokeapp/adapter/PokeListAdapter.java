package com.diogenes.pokeapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.api.client.ClientApi;
import com.diogenes.pokeapp.api.definition.PokemonInterface;
import com.diogenes.pokeapp.model.Pokemon;
import com.diogenes.pokeapp.model.PokemonForm;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by diogenes on 16/12/17.
 */

public class PokeListAdapter extends RecyclerView.Adapter<PokeListAdapter.PokeListViewHolder> {
    private static final String TAG = "PokeListAdapter";
    private List<Pokemon> mPokemonList;


    public class PokeListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView type;
        public ImageView ivPokemon;

        public PokeListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            type = (TextView) itemView.findViewById(R.id.tv_type);
            ivPokemon = (ImageView) itemView.findViewById(R.id.iv_pokemon);
        }
    }

    public PokeListAdapter(List<Pokemon> mPokemonList) {
        this.mPokemonList = mPokemonList;
    }

    @Override
    public PokeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.pokemon, parent, false);
        return new PokeListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PokeListViewHolder holder, int position) {
        Pokemon pokemon = mPokemonList.get(position);
        holder.name.setText(pokemon.getName().toUpperCase());

        PokemonInterface service = ClientApi.getClient().create(PokemonInterface.class);
        Call<PokemonForm> callForm = service.getPokemonFormByName(pokemon.getName());
        callForm.enqueue(new Callback<PokemonForm>() {
            @Override
            public void onResponse(Call<PokemonForm> call, Response<PokemonForm> response) {
                if (response.isSuccessful()) {
                    Picasso.with(holder.ivPokemon.getContext())
                            .load(response.body().getSprites().getFrontDefault())
                            .resize(64, 64)
                            .into(holder.ivPokemon);
                } else {
                    Log.d(TAG, "onResponse: error" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonForm> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void addPokemonOnView(List<Pokemon> list) {
        mPokemonList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }
}
