package com.example.localevents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListCasesAdapter extends RecyclerView.Adapter<ListCasesAdapter.ViewHolder>
{
    private List<ListCasesDataProvider> listItems;
    private Context context;

    public ListCasesAdapter(List<ListCasesDataProvider> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListCasesDataProvider listItem = listItems.get(position);
        holder.textViewHead.setText(listItem.getCountryName());
        holder.textViewDescription.setText(listItem.getCases());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDescription;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
        }
    }
}
