package com.diogenes.pokeapp.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.diogenes.pokeapp.R;

/**
 * Created by diogenes on 19/12/17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton mBtPokemon;
    private ImageButton mBtItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtPokemon = (ImageButton) findViewById(R.id.bt_pokemon);
        mBtItem = (ImageButton) findViewById(R.id.bt_item);
        mBtPokemon.setOnClickListener(this);
        mBtItem.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (isOnline()) {
            switch (view.getId()) {
                case R.id.bt_pokemon:
                    intent = new Intent(this, PokemonActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bt_item:
                    intent = new Intent(this, ItemActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }else {
            Toast.makeText(this,"Can not open without internet!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
