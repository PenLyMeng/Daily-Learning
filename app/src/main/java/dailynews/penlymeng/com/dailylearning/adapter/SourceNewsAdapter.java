package dailynews.penlymeng.com.dailylearning.adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import dailynews.penlymeng.com.dailylearning.R;
import dailynews.penlymeng.com.dailylearning.model.SourceNews;

/**
 * Created by l.pen on 12/5/2017.
 */

public class SourceNewsAdapter extends RecyclerView.Adapter<SourceNewsAdapter.SourceNewsViewHolder> {

    SourceNews sourceNews;

    public SourceNewsAdapter(SourceNews sourceNews){
        this.sourceNews = sourceNews;
    }

    public void setDataSource(SourceNews sourceNews){
        this.sourceNews = sourceNews;
    }

    @Override
    public SourceNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.source_news_row_layout,parent,false);



        return new SourceNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SourceNewsViewHolder holder, int position) {

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        String author = sourceNews.sources.get(position).name;
        author = author.replace(" ","");
        int length = 0;
        if(author.length()>=3){
           author = author.substring(0,3);
        }

        TextDrawable drawable = TextDrawable.builder().beginConfig()
                .withBorder(4) /* thickness in px */
                .toUpperCase()
                .endConfig()
                .buildRoundRect(author , color,10);
        holder.imgNewsLogo.setImageDrawable(drawable);


        holder.txtNewsAuthor.setText(sourceNews.sources.get(position).name);
        holder.txtAuthorWebsite.setText(sourceNews.sources.get(position).url);
        holder.txtNewsDesc.setText(sourceNews.sources.get(position).description);

    }

    @Override
    public int getItemCount() {
        return sourceNews.sources.size();
    }

    class SourceNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imgNewsLogo;
        TextView txtNewsAuthor;
        TextView txtAuthorWebsite;
        TextView txtNewsDesc;




        public SourceNewsViewHolder(View itemView) {
            super(itemView);
            imgNewsLogo = itemView.findViewById(R.id.img_news_logo);
            txtNewsAuthor = itemView.findViewById(R.id.txt_news_name);
            txtAuthorWebsite = itemView.findViewById(R.id.txt_news_website);
            txtNewsDesc = itemView.findViewById(R.id.txt_news_desc);

        }
    }

}
