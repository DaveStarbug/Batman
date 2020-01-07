package com.star.batman.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.star.batman.Model.Pojo.FilmWrapper;
import com.star.batman.R;
import com.star.batman.View.Adapter.FilmsAdapter;
import com.star.batman.ViewModel.FilmViewModel;
import com.thekhaeng.slidingmenu.lib.SlidingMenu;

import java.util.List;

import tyrantgit.explosionfield.ExplosionField;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialSearchBar searchBar;

    RecyclerView recyclerViewFilms;

    FilmsAdapter filmsAdapter;

    FilmViewModel filmViewModel;

    SlidingMenu menu;

    Switch offlineModeSwitch;

    TextView lblAboutApp,lblMe;

    ImageView imgSlidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindView();

        getFilms("batman");

    }

    private void BindView()
    {

        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindOffset(200);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sliding_menu);


        searchBar=findViewById(R.id.searchBar);
        recyclerViewFilms=findViewById(R.id.recyclerViewListFilms);
        imgSlidingMenu=menu.findViewById(R.id.imgSlidingMenu);
        offlineModeSwitch=menu.findViewById(R.id.switchOfflineMode);
        lblAboutApp=menu.findViewById(R.id.lblAboutApp);
        lblMe=menu.findViewById(R.id.lblMe);

        filmViewModel= ViewModelProviders.of(this).get(FilmViewModel.class);

        Glide.with(this).asGif().load(R.raw.source).into(imgSlidingMenu);



        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                getFilms(s.toString());

            }
        });

        offlineModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Toast.makeText(getApplicationContext(),"Now data come from local database",Toast.LENGTH_SHORT).show();
                    batmanApplication.isOnlineMode=false;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Data come from API now",Toast.LENGTH_SHORT).show();
                    batmanApplication.isOnlineMode=true;
                }
            }
        });

        lblAboutApp.setOnClickListener(this);
        lblMe.setOnClickListener(this);

    }

    private void getFilms(String searchValue)
    {

        filmViewModel.getFilms(searchValue).observe(this, new Observer<FilmWrapper>() {
            @Override
            public void onChanged(FilmWrapper filmWrapper) {
                prepareRecyclerView(MainActivity.this,filmWrapper);
            }
        });

    }

    private void prepareRecyclerView( Activity activity, FilmWrapper filmWrapper) {

        filmsAdapter = new FilmsAdapter(activity,filmWrapper);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerViewFilms.setLayoutManager(new GridLayoutManager(this, 2));

        }
        recyclerViewFilms.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFilms.setAdapter(filmsAdapter);
        filmsAdapter.notifyDataSetChanged();

    }

    private void StartAboutAppActivity()
    {
        Intent intent = new Intent(MainActivity.this,AboutAppActivity.class);
        Pair<View,String> p1 = Pair.create(lblAboutApp,"aboutApp");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1);
        startActivity(intent,options.toBundle());
    }

    private void StartMeActivity()
    {
        Intent intent = new Intent(MainActivity.this,MeActivity.class);
        Pair<View,String> p1 = Pair.create(lblMe,"aboutMe");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1);
        startActivity(intent,options.toBundle());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.lblAboutApp:
                StartAboutAppActivity();
                break;
            case R.id.lblMe:
                StartMeActivity();
                break;
        }
    }
}
