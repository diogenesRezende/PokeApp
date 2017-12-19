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
import com.diogenes.pokeapp.model.Ability;
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

public class AbilityListAdapter extends RecyclerView.Adapter<AbilityListAdapter.PokeListViewHolder> {
    private static final String TAG = "AbilityListAdapter";
    private List<Ability> mAbilityList;


    public class PokeListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;


        public PokeListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_detail_ability_name);
        }
    }

    public AbilityListAdapter(List<Ability> abilityList) {
        this.mAbilityList = abilityList;
    }
    public void addAbilitiesOnView(List<Ability> list) {
        mAbilityList.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public PokeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.ability, parent, false);
        return new PokeListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PokeListViewHolder holder, int position) {
        Ability ability = mAbilityList.get(position);
        holder.name.setText(ability.getAbility().getName().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return mAbilityList.size();
    }
}
