package com.diogenes.pokeapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.adapter.ItemListAdapter;
import com.diogenes.pokeapp.adapter.PokeListAdapter;
import com.diogenes.pokeapp.api.client.ClientApi;
import com.diogenes.pokeapp.api.definition.PokemonInterface;
import com.diogenes.pokeapp.model.GenericCommonEntity;
import com.diogenes.pokeapp.model.ItemList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemActivity extends AppCompatActivity {

    private static final String TAG = "ItemActivity";

    private RecyclerView mRvItemList;
    private ItemListAdapter adapter;
    private List<GenericCommonEntity> listItem = new ArrayList<GenericCommonEntity>();
    private int offset;
    private boolean canLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRvItemList = (RecyclerView) findViewById(R.id.rv_itemlist);
        adapter = new ItemListAdapter(listItem);
        final GridLayoutManager layoutManager = new GridLayoutManager(this,3);

        mRvItemList.setLayoutManager(layoutManager);
        mRvItemList.setItemAnimator(new DefaultItemAnimator());
        mRvItemList.setAdapter(adapter);
        mRvItemList.setHasFixedSize(true);
        mRvItemList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastItemCount = layoutManager.findFirstVisibleItemPosition();
                    if (canLoad){
                        if ((visibleItemCount+pastItemCount)>= totalItemCount){
                            canLoad = false;
                            offset+=20;
                            getData(offset);
                        }
                    }
                }
            }
        });
        canLoad = true;
        offset = 0;
        getData(offset);
    }


    public void getData(int offset) {
        PokemonInterface pokemonInterface = ClientApi.getClient().create(PokemonInterface.class);
        Call<ItemList> callPokemonList = pokemonInterface.getItemList(20,offset);
        callPokemonList.enqueue(new Callback<ItemList>() {
            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {
                canLoad = true;
                if (response.isSuccessful()) {
                    ItemList list = response.body();
                    adapter.addItemOnView(list.getResults());
                } else {
                    Toast.makeText(getBaseContext(),"Error loading information! Check your connection!",Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onResponse: error  " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {
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
