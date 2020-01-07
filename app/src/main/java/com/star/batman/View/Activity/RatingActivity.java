package com.star.batman.View.Activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.star.batman.Model.Pojo.FilmDetail;
import com.star.batman.R;
import com.star.batman.View.Adapter.FilmsAdapter;
import com.star.batman.View.Adapter.RatingsAdapter;

import java.util.Arrays;
import java.util.List;

public class RatingActivity extends Activity {

    RecyclerView recyclerViewRatings;
    RatingsAdapter ratingsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        BindView();

        initializeActivity();

    }

    private void BindView()
    {
        recyclerViewRatings=findViewById(R.id.recyclerViewRatings);
    }

    private void initializeActivity()
    {
        prepareRecyclerView(getDataFromIntent());
    }

    private List<FilmDetail.Rating> getDataFromIntent()
    {
        return Arrays.asList(new Gson().fromJson(getIntent().getStringExtra("Ratings"), FilmDetail.Rating[].class));
    }

    private void prepareRecyclerView(List<FilmDetail.Rating> ratings)
    {
        ratingsAdapter = new RatingsAdapter(ratings);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerViewRatings.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerViewRatings.setLayoutManager(new GridLayoutManager(this, 1));

        }
        recyclerViewRatings.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRatings.setAdapter(ratingsAdapter);
    }


}
