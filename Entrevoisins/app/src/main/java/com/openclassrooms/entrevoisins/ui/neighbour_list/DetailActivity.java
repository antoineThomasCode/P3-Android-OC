package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String NEIGHBOUR_ID = "NEIGHBOUR_ID";
    private Neighbour neighbour = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView backArrow = findViewById(R.id.backArrow);

        TextView textName = findViewById(R.id.neightbourName);
        TextView textLocation = findViewById(R.id.location);
        TextView textNumber = findViewById(R.id.phoneNumber);
        TextView textNetwork = findViewById(R.id.network);
        TextView textAboutMe = findViewById(R.id.aboutMe);
        ImageView headerImage = findViewById(R.id.headerImage);
        Intent intent = getIntent();
        FloatingActionButton favoriteButton = findViewById(R.id.favoriteButton);


        if (intent != null) {
            long neighbourId = intent.getLongExtra(NEIGHBOUR_ID, 0L); // 0 is the default value if no value is passed
            neighbour = DI.getNeighbourApiService().getNeighbourById(neighbourId);
            textName.setText(neighbour.getName());
            textLocation.setText(neighbour.getAddress());
            textNumber.setText(neighbour.getPhoneNumber());
            textNetwork.setText(String.format("www.facebook.fr/%s", neighbour.getName()));
            textAboutMe.setText(neighbour.getAboutMe());
            Picasso.get().load(neighbour.getAvatarUrl()).into(headerImage);
        }


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (neighbour.isFavorite()) {
                    DI.getNeighbourApiService().deleteFavoriteNeighbour(neighbour);
                } else {
                    // DI.getNeighbourApiService().
                }
            }
        });
    }
}