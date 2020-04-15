package app.indiacoronatracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.indiacoronatracker.Activities.DetailNewsWebviewActivity;
import app.indiacoronatracker.Models.NewsModel;
import app.indiacoronatracker.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<NewsModel> list;
    private Context context;

    public NewsAdapter(ArrayList<NewsModel> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        View view = inflater.inflate(R.layout.item_news, parent, false);

        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

        final NewsModel model = list.get(position);

        holder.title.setText(model.getNews_title());
        holder.desc.setText(model.getNews_description());
        Picasso.get().load(model.getImg_url()).placeholder(context.getResources().getDrawable(R.drawable.coronavirus)).into(holder.news_img);

        holder.news_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailNewsWebviewActivity.class);
                i.putExtra("url", model.getNews_url());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc;
        ImageView news_img;
        CardView news_parent;

        ViewHolder(View view) {
            super(view);

            //TextView
            title = view.findViewById(R.id.news_title);
            desc = view.findViewById(R.id.news_description);
            news_img = view.findViewById(R.id.news_image);
            news_parent = view.findViewById(R.id.cv_news_parent);


        }
    }

}
