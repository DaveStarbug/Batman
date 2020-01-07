package com.star.batman.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.star.batman.Model.Pojo.FilmDetail;
import com.star.batman.R;
import com.star.batman.ViewModel.FilmViewModel;

public class FilmDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgPoster;
    TextView lblTitle,lblYear,lblRated,lblReleased,lblRuntime,lblGenre,lblDirector,lblWriter,lblActors,lblPlot,lblLanguage,
    lblCountry,lblAwards,lblMetaScore,lblimdbRating,lblimdbVotes,lblType,lblDvd,lblBoxOffice,lblProduction,lblWebsite;

    Button btnViewRatings;

    FilmViewModel filmViewModel;

    FilmDetail m_filmDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        BindView();

        getFilmDetail();



    }

    private void BindView()
    {
        imgPoster=findViewById(R.id.imgPoster);
        lblTitle=findViewById(R.id.lblTitle);lblYear=findViewById(R.id.lblYear);
        lblRated=findViewById(R.id.lblRated);lblReleased=findViewById(R.id.lblReleased);
        lblRuntime=findViewById(R.id.lblRuntime);lblGenre=findViewById(R.id.lblGenre);
        lblDirector=findViewById(R.id.lblDirector);lblWriter=findViewById(R.id.lblWriter);
        lblActors=findViewById(R.id.lblActors);lblPlot=findViewById(R.id.lblPlot);
        lblLanguage=findViewById(R.id.lblLanguage);lblCountry=findViewById(R.id.lblCountry);
        lblAwards=findViewById(R.id.lblAwards);lblMetaScore=findViewById(R.id.lblMetaScore);
        lblimdbRating=findViewById(R.id.lblimdbRating);lblimdbVotes=findViewById(R.id.lblimdbVotes);
        lblType=findViewById(R.id.lblType);lblDvd=findViewById(R.id.lblDvd);
        lblBoxOffice=findViewById(R.id.lblBoxOffice);lblProduction=findViewById(R.id.lblProduction);
        lblWebsite=findViewById(R.id.lblWebsite);
        btnViewRatings=findViewById(R.id.btnViewRatings);

        filmViewModel= ViewModelProviders.of(FilmDetailActivity.this).get(FilmViewModel.class);

        btnViewRatings.setOnClickListener(this);

    }

    private void getFilmDetail()
    {
        String imdbID=getIntent().getExtras().getString("imdbID");

        filmViewModel.getFilmDetail(imdbID).observe(this, new Observer<FilmDetail>() {
            @Override
            public void onChanged(FilmDetail filmDetail) {
                prePareActivity(filmDetail);
            }
        });


    }

    private void prePareActivity(FilmDetail filmDetail)
    {
        Glide.with(this).load(filmDetail.getPoster()).into(imgPoster);
        if (filmDetail.getTitle().length()>28)
        {
            lblTitle.setTextSize(10);
        }
        else lblTitle.setTextSize(13);
        lblTitle.setText(filmDetail.getTitle());
        lblYear.setText("Year: "+filmDetail.getYear());
        lblRated.setText("Rated: "+filmDetail.getRated());
        lblReleased.setText("Released: "+filmDetail.getReleased());
        lblRuntime.setText("Runtime: "+filmDetail.getRuntime());
        lblGenre.setText("Genre: "+filmDetail.getGenre());
        lblDirector.setText("Director: "+filmDetail.getDirector());
        lblWriter.setText("Writer: "+filmDetail.getWriter());
        lblActors.setText("Actors: "+filmDetail.getActors());
        lblPlot.setText("Plot: "+filmDetail.getPlot());
        lblLanguage.setText("Language: "+filmDetail.getLanguage());
        lblCountry.setText("Country: "+filmDetail.getCountry());
        lblAwards.setText("Awards: "+filmDetail.getAwards());
        lblMetaScore.setText("MetaScore: "+filmDetail.getMetascore());
        lblimdbRating.setText("imdbRating: "+filmDetail.getImdbRating());
        lblimdbVotes.setText("imdbVotes: "+filmDetail.getImdbVotes());
        lblType.setText("Type: "+filmDetail.getType());
        lblDvd.setText("DVD: "+filmDetail.getDVD());
        lblBoxOffice.setText("BoxOffice: "+filmDetail.getBoxOffice());
        lblProduction.setText("Production: "+filmDetail.getProduction());
        lblWebsite.setText("Website: "+filmDetail.getWebsite());

        m_filmDetail = filmDetail;
    }

    private void startRatingActivity()
    {
        Intent intent = new Intent(FilmDetailActivity.this,RatingActivity.class);
        intent.putExtra("Ratings", new Gson().toJson( m_filmDetail.getRatings()));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnViewRatings:
                startRatingActivity();
                break;
        }
    }
}
