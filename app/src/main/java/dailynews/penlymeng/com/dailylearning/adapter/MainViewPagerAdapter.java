package dailynews.penlymeng.com.dailylearning.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import dailynews.penlymeng.com.dailylearning.fragment.RecentNewsFragmentLayout;
import dailynews.penlymeng.com.dailylearning.fragment.SourceNewsFragmentLayout;
import dailynews.penlymeng.com.dailylearning.fragment.TopNewsFragmentLayout;

/**
 * Created by l.pen on 12/4/2017.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    String[] pageTitle = {"Recent News","Top News","Source News"};

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (position){
            case 0:
                fragment = new RecentNewsFragmentLayout();
                break;
            case 1:
                fragment = new TopNewsFragmentLayout();
                break;
            case 2:
                fragment = new SourceNewsFragmentLayout();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
}
