package dailynews.penlymeng.com.dailylearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dailynews.penlymeng.com.dailylearning.R;

/**
 * Created by l.pen on 12/4/2017.
 */

public class SourceNewsFragmentLayout extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.source_news_fragment_layout,container,false);
        return view;
    }

}
