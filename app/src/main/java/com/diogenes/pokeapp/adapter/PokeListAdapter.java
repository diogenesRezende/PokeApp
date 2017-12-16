package com.diogenes.pokeapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.model.Pokemon;

import java.util.List;

/**
 * Created by diogenes on 16/12/17.
 */

public class PokeListAdapter extends RecyclerView.Adapter<PokeListAdapter.PokeListViewHolder> {

    private List<Pokemon> mPokemonList;

    public class PokeListViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView type;
        public PokeListViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tv_name);
            type = (TextView)itemView.findViewById(R.id.tv_type);
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
    public void onBindViewHolder(PokeListViewHolder holder, int position) {
        Pokemon pokemon = mPokemonList.get(position);
        holder.name.setText(pokemon.getName());
        holder.type.setText(pokemon.getType());
    }


    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }
}
