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
import dailynews.penlymeng.com.dailylearning.model.TopNews;

/**
 * Created by l.pen on 12/5/2017.
 */

public class TopNewsAdapter  extends RecyclerView.Adapter<TopNewsAdapter.TopNewsViewHolder> {

    TopNews topNews;
    Context context;

    public TopNewsAdapter(TopNews topNews){
        this.topNews = topNews;
    }


    public void setDataSource(TopNews topNews){
        this.topNews = topNews;
    }

    @Override
    public TopNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new TopNewsViewHolder(inflater.inflate(R.layout.top_news_row_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(TopNewsViewHolder holder, int position) {
        TopNews.Articles articles = topNews.articles.get(position);

        Picasso.with(context)
                .load(articles.urlToImage)
                .placeholder(R.drawable.img_gift)
                .error(R.drawable.img_gift)
                .into( holder.imgArticleLogo);

        holder.txtArticleTitle.setText(articles.title);
        holder.txtArticleDesc.setText(articles.description);



    }

    @Override
    public int getItemCount() {
        return topNews.articles.size();
    }





    class TopNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imgArticleLogo;
        TextView txtArticleTitle;
        TextView txtArticleDesc;

        public TopNewsViewHolder(View itemView) {
            super(itemView);

            imgArticleLogo = itemView.findViewById(R.id.img_news_logo);
            txtArticleTitle = itemView.findViewById(R.id.txt_news_name);
            txtArticleDesc = itemView.findViewById(R.id.txt_news_desc);

        }
    }
}
