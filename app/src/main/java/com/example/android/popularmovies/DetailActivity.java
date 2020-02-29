package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.constants.Constants;
import com.example.android.customarrayadapter.PopularMovies;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.titleDetail)
    TextView titleTextView;
    @BindView(R.id.relDateDetail)
    TextView releaseDateTextView;
    @BindView(R.id.plotDetail)
    TextView plotTextView;
    @BindView(R.id.ratingDetail)
    TextView ratingTextView;
    @BindView(R.id.imageDetail)
    ImageView imageOfMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Bind with ButterKnife
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);

        //Setup tool bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        Intent intent = getIntent();
        PopularMovies popularMovie = intent.getParcelableExtra(Constants.POPULAR_MOVIE);

        //Get data from Parcelable Object
        String movieTitle = popularMovie.getTitle();
        String moviePosterUrl = popularMovie.getPosterImage();
        String moviePlot = popularMovie.getPlotSynopsis();
        String movieReleaseDate = popularMovie.getReleaseDate();
        String movieRating = popularMovie.getUserRating();


        int imageResourceId = getResources().getIdentifier(moviePosterUrl, "drawable", getPackageName());

        //USING BUTTER KNIFE TO BIND OBJECTS
        imageOfMovie.setImageResource(imageResourceId);
        titleTextView.setText(movieTitle);
        releaseDateTextView.setText(movieReleaseDate);
        plotTextView.setText(moviePlot);
        ratingTextView.setText(movieRating);

        //USING PICASSO TO LOAD IMAGES
        Picasso.get()
                .load(popularMovie.getPosterImage())
                .into(imageOfMovie);




    }
}
