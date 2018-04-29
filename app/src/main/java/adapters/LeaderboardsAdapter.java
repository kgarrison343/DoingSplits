package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kgarrison.doingsplits.R;
import models.RunCategory;

/*
 * Created by Kyle Garrison on 4/29/2018.
 */
public class LeaderboardsAdapter extends
        RecyclerView.Adapter<LeaderboardsAdapter.ViewHolder> {

    private List<RunCategory> mCategories;


    public LeaderboardsAdapter(List<RunCategory> categories) {
        mCategories = categories;
    }

    public void setCategories(List<RunCategory> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View leaderboardView = inflater.inflate(R.layout.leaderboard_list_content, parent, false);

        ViewHolder viewHolder = new ViewHolder(leaderboardView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RunCategory category = mCategories.get(position);

        holder.idText.setText(category.id);
        holder.contentText.setText(category.name);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idText;
        public TextView contentText;

        public ViewHolder(View itemView) {
            super(itemView);

            idText = (TextView) itemView.findViewById(R.id.id_text);
            contentText = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
