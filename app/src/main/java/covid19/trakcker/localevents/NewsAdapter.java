package covid19.trakcker.localevents;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.covid19.trakcker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>
{
    private List<NewsDataProvider> listItems;
    private Context context;

    public NewsAdapter(List<NewsDataProvider> listItems, Context context)
    {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_items, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        NewsDataProvider listItem = listItems.get(position);
        holder.txtViewTitle.setText(listItem.getTitle());
        holder.txtDescription.setText(listItem.getDescription());
        holder.txtContent.setText(listItem.getContent());
        holder.txtUrl.setText(Html.fromHtml(listItem.getUrl()));
        holder.txtPublishedAt.setText(listItem.getPublishedAt());
        Picasso.with(holder.image.getContext()).load(listItem.getUrlToImage()).into(holder.image);
        boolean isExpanded = listItems.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtViewTitle, txtDescription, txtContent, txtUrl, txtPublishedAt;
        public LinearLayout linearLayout;
        ConstraintLayout expandableLayout;
        ImageView image;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            txtViewTitle = (TextView) itemView.findViewById(R.id.txtViewTitle);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
            txtUrl = (TextView) itemView.findViewById(R.id.txtUrl);
            txtUrl.setMovementMethod(LinkMovementMethod.getInstance());
            txtUrl.setClickable(true);
            txtPublishedAt = (TextView) itemView.findViewById(R.id.txtPublishedAt);
            image = (ImageView) itemView.findViewById(R.id.image);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutNews);
            expandableLayout = itemView.findViewById(R.id.expandableLayoutNews);

            txtViewTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsDataProvider np = listItems.get(getAdapterPosition());
                    np.setExpanded(!np.isExpanded());
                    notifyDataSetChanged();getAdapterPosition();
                }
            });
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsDataProvider np = listItems.get(getAdapterPosition());
                    np.setExpanded(!np.isExpanded());
                    notifyDataSetChanged();getAdapterPosition();
                }
            });
        }
    }
}
