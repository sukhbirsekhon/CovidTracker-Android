package com.example.localevents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListCasesAdapter extends RecyclerView.Adapter<ListCasesAdapter.ViewHolder>
{
    private List<ListCasesDataProvider> listItems;
    private Context context;

    public ListCasesAdapter(List<ListCasesDataProvider> listItems, Context context)
    {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        ListCasesDataProvider listItem = listItems.get(position);
        holder.textViewHead.setText(listItem.getCountryName());
        holder.textViewDescription.setText(listItem.getCases());
        holder.activeCases.setText(listItem.getActiveCases());
        holder.recoveredCases.setText(listItem.getRecoveredCases());
        holder.deaths.setText(listItem.getDeaths());
        holder.newCases.setText(listItem.getNewCases());
        holder.newDeaths.setText(listItem.getNewDeaths());
        holder.seriousCritical.setText(listItem.getSeriousCritical());
        holder.casesPerMillion.setText(listItem.getCasesPerMillion());

        boolean isExpanded =  listItems.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead, newCases, newDeaths, seriousCritical, casesPerMillion;
        public TextView textViewDescription, activeCases, recoveredCases, deaths;
        public LinearLayout linearLayout;
        ConstraintLayout expandableLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            activeCases = (TextView) itemView.findViewById(R.id.activeCases);
            recoveredCases = (TextView) itemView.findViewById(R.id.recoveredCases);
            deaths = (TextView) itemView.findViewById(R.id.deaths);
            newCases = (TextView) itemView.findViewById(R.id.todayCases);
            newDeaths = (TextView) itemView.findViewById(R.id.todayDeaths);
            seriousCritical = (TextView) itemView.findViewById(R.id.seriousCritical);
            casesPerMillion = (TextView) itemView.findViewById(R.id.casesPerMillion);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            textViewHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListCasesDataProvider ls = listItems.get(getAdapterPosition());
                    ls.setExpanded(!ls.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
