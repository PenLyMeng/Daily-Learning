package dailynews.penlymeng.com.dailylearning.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dailynews.penlymeng.com.dailylearning.R;
import dailynews.penlymeng.com.dailylearning.model.News;
import dailynews.penlymeng.com.dailylearning.service.CoreService;
import dailynews.penlymeng.com.dailylearning.service.ServiceGenerator;

/**
 * Created by l.pen on 12/6/2017.
 */

public class RecenNewsItemAdapter extends RecyclerView.Adapter<RecenNewsItemAdapter.RecentNewsItemViewHolder> {

    Context context;
    News news;


    public RecenNewsItemAdapter(News news) {
        this.news = news;
    }

    public void setDataSource(News news) {
        this.news = news;
    }

    @Override
    public RecentNewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        return new RecentNewsItemViewHolder(layoutInflater.inflate(R.layout.recent_new_row_items_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(RecentNewsItemViewHolder holder, int position) {
        News.Articles articles = news.articles.get(position);

        if( articles.urlToImage != null ){
            Picasso.with(context)
                    .load(articles.urlToImage)
                    .placeholder(R.drawable.loading_gif)
                    .error(R.drawable.no_image_available)
                    .into(holder.imgNewsThumbnail);

        }


        holder.txtTitle.setText(articles.title);
        holder.txtDesc.setText(articles.description);

    }

    @Override
    public int getItemCount() {
        return news.articles.size();
    }

    class RecentNewsItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imgNewsThumbnail;
        TextView txtTitle;
        TextView txtDesc;


        public RecentNewsItemViewHolder(View itemView) {
            super(itemView);

            imgNewsThumbnail = itemView.findViewById(R.id.img_news_logo);
            txtTitle = itemView.findViewById(R.id.txt_news_name);
            txtDesc = itemView.findViewById(R.id.txt_news_desc);




        }

    }
}
