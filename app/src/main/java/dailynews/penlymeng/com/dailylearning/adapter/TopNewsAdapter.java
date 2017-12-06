package dailynews.penlymeng.com.dailylearning.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dailynews.penlymeng.com.dailylearning.R;
import dailynews.penlymeng.com.dailylearning.callback.ListNewsItemListener;
import dailynews.penlymeng.com.dailylearning.model.News;

/**
 * Created by l.pen on 12/5/2017.
 */

public class TopNewsAdapter  extends RecyclerView.Adapter<TopNewsAdapter.TopNewsViewHolder> {

    News news;
    Context context;

    public TopNewsAdapter(News news){
        this.news = news;
    }


    public void setDataSource(News news){
        this.news = news;
    }

    @Override
    public TopNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new TopNewsViewHolder(inflater.inflate(R.layout.top_news_row_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(TopNewsViewHolder holder, int position) {
        News.Articles articles = news.articles.get(position);

        Picasso.with(context)
                .load(articles.urlToImage)
                .placeholder(R.drawable.loading_gif)
                .error(R.drawable.no_image_available)
                .into( holder.imgArticleLogo);

        holder.txtArticleTitle.setText(articles.title);
        holder.txtArticleDesc.setText(articles.description);
        holder.txtDate.setText(articles.publishedAt);



    }

    @Override
    public int getItemCount() {
        return news.articles.size();
    }





    class TopNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imgArticleLogo;
        TextView txtArticleTitle;
        TextView txtArticleDesc;
        TextView txtDate;


        public TopNewsViewHolder(final View itemView) {
            super(itemView);

            imgArticleLogo = itemView.findViewById(R.id.img_news_logo);
            txtArticleTitle = itemView.findViewById(R.id.txt_news_name);
            txtArticleDesc = itemView.findViewById(R.id.txt_news_desc);
            txtDate = itemView.findViewById(R.id.txt_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListNewsItemListener)itemView.getContext()).onNewsClickListener(getAdapterPosition());
                }
            });

        }
    }
}
