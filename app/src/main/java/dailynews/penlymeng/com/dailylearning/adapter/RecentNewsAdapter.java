package dailynews.penlymeng.com.dailylearning.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by l.pen on 12/5/2017.
 */

public class RecentNewsAdapter extends RecyclerView.Adapter<RecentNewsAdapter.RecentNewsViewHolder>  {

    @Override
    public RecentNewsAdapter.RecentNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecentNewsAdapter.RecentNewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecentNewsViewHolder extends RecyclerView.ViewHolder {

        public RecentNewsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
