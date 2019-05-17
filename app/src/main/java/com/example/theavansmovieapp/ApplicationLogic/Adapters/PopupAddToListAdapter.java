package com.example.theavansmovieapp.ApplicationLogic.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theavansmovieapp.ApplicationLogic.Listeners.PopupAddToListListener;
import com.example.theavansmovieapp.Domain.Account;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.GUI.Animations.PopupAddMovie;
import com.example.theavansmovieapp.R;

import java.util.List;
/** gemaakt door Anjuli **/
//Adapter voor recyclerview van lijsten waaraan een film kan toegevoegd worden in de popupaddtoList activity
public class PopupAddToListAdapter extends RecyclerView.Adapter<PopupAddToListAdapter.PopViewHolder> {

    private List<AccountList> lists;
    private PopupAddMovie activity;
    private int filmID;

    public PopupAddToListAdapter(List<AccountList> lists, PopupAddMovie activity, int filmID) {
        this.lists = lists;
        this.filmID = filmID;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.simple_item_layout, viewGroup, false);
        return new PopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PopViewHolder popViewHolder, int i) {

        AccountList oneList = lists.get(i);

        popViewHolder.title.setText(oneList.getName());
        PopupAddToListListener listener = new PopupAddToListListener(oneList, activity, filmID);
        popViewHolder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class PopViewHolder extends RecyclerView.ViewHolder{

        private TextView title;

        public PopViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.popup_select_list_tv);
        }
    }

}

