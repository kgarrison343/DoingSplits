package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kgarrison.doingsplits.R;
import models.Game;

/*
 * Created by Kyle Garrison on 4/29/2018.
 */
public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private List<Game> mGames;


    public GamesAdapter(List<Game> categories) {
        mGames = categories;
    }

    public void setGames(List<Game> categories) {
        mGames = categories;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.leaderboard_list_content, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game category = mGames.get(position);

        holder.idText.setText(category.get_name());
        holder.contentText.setText(category.getConsolesToString());
    }

    @Override
    public int getItemCount() {
        return mGames.size();
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