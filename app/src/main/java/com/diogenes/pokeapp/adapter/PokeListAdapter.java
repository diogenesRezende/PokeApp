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
import com.diogenes.pokeapp.model.GenericCommonEntity;
import com.diogenes.pokeapp.model.Pokemon;
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
    private List<GenericCommonEntity> mPokemonList;


    public class PokeListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView type;
        public ImageView ivPokemon;

        public PokeListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            ivPokemon = (ImageView) itemView.findViewById(R.id.iv_pokemon);
        }
    }

    public PokeListAdapter(List<GenericCommonEntity> mPokemonList) {
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
        GenericCommonEntity pokemon = mPokemonList.get(position);
        holder.name.setText(pokemon.getName().toUpperCase());

        PokemonInterface service = ClientApi.getClient().create(PokemonInterface.class);
        Call<Pokemon> callForm = service.getPokemonByName(pokemon.getName());
        callForm.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Picasso.with(holder.ivPokemon.getContext())
                            .load(response.body().getSprites().getFrontDefault())
                            .resize(96, 96)
                            .into(holder.ivPokemon);
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

    public void addPokemonOnView(List<GenericCommonEntity> list) {
        mPokemonList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }
}
