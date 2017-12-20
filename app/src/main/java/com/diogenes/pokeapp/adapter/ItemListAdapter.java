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
import com.diogenes.pokeapp.model.Item;
import com.diogenes.pokeapp.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by diogenes on 16/12/17.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.PokeListViewHolder> {
    private static final String TAG = "ItemListAdapter";
    private List<GenericCommonEntity> mItemList;


    public class PokeListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView type;
        public ImageView ivItem;

        public PokeListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            ivItem = (ImageView) itemView.findViewById(R.id.iv_item);
        }
    }

    public ItemListAdapter(List<GenericCommonEntity> mItemList) {
        this.mItemList = mItemList;
    }

    @Override
    public PokeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item, parent, false);
        return new PokeListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PokeListViewHolder holder, int position) {
        GenericCommonEntity item = mItemList.get(position);
        holder.name.setText(item.getName().toUpperCase());

        PokemonInterface service = ClientApi.getClient().create(PokemonInterface.class);
        Call<Item> callItem = service.getItemByName(item.getName());
        callItem.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()) {
                    Picasso.with(holder.ivItem.getContext())
                            .load(response.body().getSprites().getDefault())
                            .resize(96, 96)
                            .into(holder.ivItem);
                } else {
                    Log.d(TAG, "onResponse: error" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void addItemOnView(List<GenericCommonEntity> list) {
        mItemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
