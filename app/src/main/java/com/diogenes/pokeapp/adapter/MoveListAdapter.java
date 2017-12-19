package com.diogenes.pokeapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diogenes.pokeapp.R;
import com.diogenes.pokeapp.model.Ability;
import com.diogenes.pokeapp.model.Move;

import java.util.List;

/**
 * Created by diogenes on 16/12/17.
 */

public class MoveListAdapter extends RecyclerView.Adapter<MoveListAdapter.PokeListViewHolder> {
    private static final String TAG = "MoveListAdapter";
    private List<Move> mMoveList;


    public class PokeListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;


        public PokeListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_detail_move_name);
        }
    }

    public MoveListAdapter(List<Move> moveList) {
        this.mMoveList = moveList;
    }
    public void addMovesOnView(List<Move> list) {
        mMoveList.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public PokeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.move, parent, false);
        return new PokeListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PokeListViewHolder holder, int position) {
        Move move = mMoveList.get(position);
        holder.name.setText(move.getMove().getName().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return mMoveList.size();
    }
}
