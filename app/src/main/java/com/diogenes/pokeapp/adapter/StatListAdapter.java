package com.diogenes.pokeapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.model.Ability;
import com.diogenes.pokeapp.model.Stat;

import java.util.List;

/**
 * Created by diogenes on 16/12/17.
 */

public class StatListAdapter extends RecyclerView.Adapter<StatListAdapter.PokeListViewHolder> {
    private static final String TAG = "StatListAdapter";
    private List<Stat> mStatList;


    public class PokeListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView base;


        public PokeListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_detail_stats_name);
            base = (TextView) itemView.findViewById(R.id.tv_detail_stats_base);
        }
    }

    public StatListAdapter(List<Stat> statList) {
        this.mStatList = statList;
    }
    public void addStatsOnView(List<Stat> list) {
        mStatList.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public PokeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.stat, parent, false);
        return new PokeListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PokeListViewHolder holder, int position) {
        Stat stat = mStatList.get(position);
        holder.name.setText(stat.getStat().getName().toUpperCase());
        holder.base.setText(stat.getBaseStat().toString());
    }

    @Override
    public int getItemCount() {
        return mStatList.size();
    }
}
