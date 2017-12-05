package dailynews.penlymeng.com.dailylearning.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import dailynews.penlymeng.com.dailylearning.R;
import dailynews.penlymeng.com.dailylearning.adapter.SourceNewsAdapter;
import dailynews.penlymeng.com.dailylearning.adapter.TopNewsAdapter;
import dailynews.penlymeng.com.dailylearning.model.SourceNews;
import dailynews.penlymeng.com.dailylearning.model.TopNews;
import dailynews.penlymeng.com.dailylearning.service.CoreService;
import dailynews.penlymeng.com.dailylearning.service.ServiceGenerator;
import jp.wasabeef.recyclerview.animators.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by l.pen on 12/4/2017.
 */

public class TopNewsFragmentLayout extends Fragment {


    public static final String TAG = "ooo";
    RecyclerView mRecyclerView;
    TopNewsAdapter mTopNewsAdapter;
    AnimationAdapter animatorAdapter;
    TopNews topNews;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = container.getContext();
        View view = inflater.inflate(R.layout.top_news_fragment_layout,container,false);

        mRecyclerView = view.findViewById(R.id.top_news_recyclerview);
        ServiceGenerator.setBaseUrl(getResources().getString(R.string.base_url_news));
        CoreService coreService = ServiceGenerator.createService(CoreService.class);

        topNews = new TopNews();
        mTopNewsAdapter = new TopNewsAdapter(topNews);

        animatorAdapter = new ScaleInAnimationAdapter(mTopNewsAdapter);
        animatorAdapter.setDuration(500);
        animatorAdapter.setFirstOnly(false);
        animatorAdapter.setInterpolator(new DecelerateInterpolator());



        mRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(animatorAdapter);

        coreService.listTopNews(getString(R.string.api_key)).enqueue(new Callback<TopNews>() {
            @Override
            public void onResponse(Call<TopNews> call, Response<TopNews> response) {
                if(response.body().status.equalsIgnoreCase("ok")){
                    topNews = response.body();
                    mTopNewsAdapter.setDataSource(topNews);
                    animatorAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onResponse size 1: " + topNews.articles.size());
                    Log.d(TAG, "onResponse size 2: " + mTopNewsAdapter.getItemCount());
                }

                Log.d(TAG, "onResponse: " + response.body().status);
            }

            @Override
            public void onFailure(Call<TopNews> call, Throwable t) {
                Log.d(TAG, "onError: " + t.getMessage());
            }
        });

        return view;
    }
}
