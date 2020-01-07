package com.star.batman.View.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.star.batman.Model.Pojo.Film;
import com.star.batman.Model.Pojo.FilmDetail;
import com.star.batman.Model.Pojo.FilmWrapper;
import com.star.batman.R;
import com.star.batman.View.Activity.FilmDetailActivity;

import java.util.List;

public class RatingsAdapter extends RecyclerView.Adapter<RatingsAdapter.RatingRow> {


    private List<FilmDetail.Rating> m_ratings;
    private Activity m_activity;

    public RatingsAdapter( List<FilmDetail.Rating> ratings)
    {
        m_ratings = ratings;
    }

    @NonNull
    @Override
    public RatingsAdapter.RatingRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rating, parent, false);

        return new RatingRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingsAdapter.RatingRow holder, int position) {

        FilmDetail.Rating rating=m_ratings.get(position);

        holder.lblSource.setText(rating.getSource());
        holder.lblValue.setText(rating.getValue());

    }

    @Override
    public int getItemCount() {
        return m_ratings.size();
    }

    public class RatingRow extends RecyclerView.ViewHolder {

        TextView lblSource,lblValue;


        public RatingRow(View itemView) {
            super(itemView);

            lblSource = itemView.findViewById(R.id.lblSource);
            lblValue = itemView.findViewById(R.id.lblValue);


        }
    }

}
