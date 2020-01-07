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
import com.star.batman.Model.Pojo.FilmWrapper;
import com.star.batman.R;
import com.star.batman.View.Activity.FilmDetailActivity;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.MovieRow> {


    private FilmWrapper m_filmWrapper;
    private Activity m_activity;

    public FilmsAdapter(Activity activity, FilmWrapper filmWrapper)
    {
        m_filmWrapper=filmWrapper;
        m_activity=activity;
    }

    @NonNull
    @Override
    public FilmsAdapter.MovieRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_film, parent, false);

        return new MovieRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsAdapter.MovieRow holder, int position) {

        Film film=m_filmWrapper.getmFilms().get(position);

        holder.lblTitle.setText(film.getTitle());
        holder.lblType.setText(film.getType());
        if (film.getYear().length()>4)
        {
            holder.lblYear.setTextSize(13);
        }
        else
        {
            holder.lblYear.setTextSize(16);
        }
        holder.lblYear.setText(film.getYear());
        Glide.with(m_activity).load(film.getPoster()).into(holder.imgPoster);

        holder.vwParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(m_activity, FilmDetailActivity.class);
                intent.putExtra("imdbID",film.getImdbID());
                Pair<View,String> p1 = Pair.create(holder.imgPoster,"poster");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(m_activity, p1);
                m_activity.startActivity(intent,options.toBundle());
            }
        });


    }

    @Override
    public int getItemCount() {
        return m_filmWrapper.getmFilms().size();
    }

    public class MovieRow extends RecyclerView.ViewHolder {

        ImageView imgPoster;
        TextView lblTitle;
        TextView lblYear;
        TextView lblType;
        RelativeLayout vwParent;


        public MovieRow(View itemView) {
            super(itemView);

            vwParent=itemView.findViewById(R.id.vwParent);
            imgPoster=itemView.findViewById(R.id.imgPoster);
            lblTitle=itemView.findViewById(R.id.lblTitle);
            lblYear=itemView.findViewById(R.id.lblYear);
            lblType=itemView.findViewById(R.id.lblType);


        }
    }

}
