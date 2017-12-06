package dailynews.penlymeng.com.dailylearning.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dailynews.penlymeng.com.dailylearning.R;
import dailynews.penlymeng.com.dailylearning.adapter.RecentNewsAdapter;

/**
 * Created by l.pen on 12/4/2017.
 */

public class RecentNewsFragmentLayout extends Fragment {


    RecyclerView mRecyclerView;
    ArrayList<String> listNewsCategories;
    RecentNewsAdapter recentNewsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = container.getContext();
        View view = inflater.inflate(R.layout.recent_news_fragment_layout, container, false);

        listNewsCategories = new ArrayList<>();
        listNewsCategories.add("general");
        listNewsCategories.add("politics");
        listNewsCategories.add("business");
        listNewsCategories.add("technology");
        listNewsCategories.add("entertainment");
        listNewsCategories.add("sport");
        listNewsCategories.add("music");
        listNewsCategories.add("gaming");
        listNewsCategories.add("science-and-nature");
        listNewsCategories.add("health-and-medical");

        mRecyclerView = view.findViewById(R.id.recent_news_recyclerview);

        recentNewsAdapter = new RecentNewsAdapter(listNewsCategories);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(recentNewsAdapter);



        return view;
    }


}
