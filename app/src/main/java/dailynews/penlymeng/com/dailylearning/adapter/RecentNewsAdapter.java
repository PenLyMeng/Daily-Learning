package dailynews.penlymeng.com.dailylearning.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

import dailynews.penlymeng.com.dailylearning.R;
import dailynews.penlymeng.com.dailylearning.model.News;
import dailynews.penlymeng.com.dailylearning.service.CoreService;
import dailynews.penlymeng.com.dailylearning.service.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by l.pen on 12/5/2017.
 */

public class RecentNewsAdapter extends RecyclerView.Adapter<RecentNewsAdapter.RecentNewsViewHolder>  {

    ArrayList<String> listNewsCategories;
    Context context;

    public RecentNewsAdapter(ArrayList<String> listNewsCategories){
        this.listNewsCategories = listNewsCategories;

    }
    public void setDataSource(ArrayList<String> listNewsCategories){
        this.listNewsCategories = listNewsCategories;
    }


    @Override
    public RecentNewsAdapter.RecentNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new RecentNewsViewHolder(layoutInflater.inflate(R.layout.recent_news_row_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecentNewsAdapter.RecentNewsViewHolder holder, int position) {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        final int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder().beginConfig()
               /* thickness in px */
                .bold()
                .withBorder(0)
                .endConfig()
                .buildRect( listNewsCategories.get(position) , color);
        holder.imgNewsCategories.setImageDrawable(drawable);
        holder.mCardView.setBackgroundColor(color);


        if(holder.coreService == null){
            holder.coreService = ServiceGenerator.createService(CoreService.class);
            holder.coreService.listNewsByCategories(context.getString(R.string.api_key),listNewsCategories.get(position) ,"en").enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    if(response.body().status.equalsIgnoreCase("ok")){
                        holder.news = response.body();
                        holder.mRecenNewsItemAdapter.setDataSource(holder.news);
                        holder.mRecenNewsItemAdapter.notifyDataSetChanged();
                    }

                    Log.d("ooo", "onResponse: " + response.body().status);
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    Log.d("ooo", "onError: " + t.getMessage());
                }
            });
        }




        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;


        TranslateAnimation anim = new TranslateAnimation(-500, width, 0, 0);

        anim.setFillEnabled(true);
        anim.setFillAfter(true);


        anim.setRepeatCount(Animation.INFINITE);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        anim.setDuration(8000);

        holder.imgNewsCategories.startAnimation(anim);

    }

    @Override
    public int getItemCount() {
        return listNewsCategories.size();
    }

    class RecentNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imgNewsCategories;
        RecyclerView mRecyclerView;
        RecenNewsItemAdapter mRecenNewsItemAdapter;
        CardView mCardView;
        News news;

        CoreService coreService ;


        public RecentNewsViewHolder(View itemView) {
            super(itemView);

            imgNewsCategories = itemView.findViewById(R.id.img_news_categories);
            mRecyclerView = itemView.findViewById(R.id.recent_news_item_recyclerview);
            mCardView = itemView.findViewById(R.id.card_news_row);

            news = new News();

            mRecenNewsItemAdapter = new RecenNewsItemAdapter(news);
            mRecyclerView.setHasFixedSize(true);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);

            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mRecenNewsItemAdapter);


            ServiceGenerator.setBaseUrl(itemView.getContext().getResources().getString(R.string.base_url_news));



        }

    }
}
