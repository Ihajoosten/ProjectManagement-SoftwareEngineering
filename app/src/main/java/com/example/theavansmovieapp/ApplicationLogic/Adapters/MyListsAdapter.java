/*
Gemaakt door: Stefan Ilmer
Datum laatste update: 02-04-2019

Gecontroleerd door:
Gecontroleerd op:
*/

package com.example.theavansmovieapp.ApplicationLogic.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.theavansmovieapp.ApplicationLogic.Listeners.GoToListContentListener;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.GUI.Activity.MylistActivity;
import com.example.theavansmovieapp.R;


import java.util.List;
/** gemaakt door Anjuli **/
//Adapter voor MylistActivity: plaats een viewholder object in de recyclerview waarin lijsten worden weergegeven

public class MyListsAdapter extends RecyclerView.Adapter<MyListsAdapter.ListsViewHolder>
{
    private List<AccountList> lists;
    private Context context;
    private MylistActivity activity;

    public MyListsAdapter(MylistActivity activity, List<AccountList> accountLists, Context context)
    {
        this.lists = accountLists;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public ListsViewHolder onCreateViewHolder(ViewGroup parent, int i)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mylist_items, parent, false);

        return new ListsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListsViewHolder holder, int i)
    {
        AccountList accountList = lists.get(i);
        holder.title.setText(accountList.getName());
        holder.description.setText(accountList.getDescription());

        GoToListContentListener listener = new GoToListContentListener(activity, accountList, context);
        holder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount()
    {
        return lists.size();
    }

    public class ListsViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title;
        public TextView description;

        public ListsViewHolder(View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.mylists_lijst_titel);
            description = itemView.findViewById(R.id.mylists_lijst_beschrijving);
        }
    }
}
