package com.example.android_quizappwithfirebase.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import com.example.android_quizappwithfirebase.R;

import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private final ArrayList<CardView> cardViews = new ArrayList<>();
    private final String[] subjectNames = {
            "Toán Học",
            "Văn Học",
            "Tiếng Anh",
            "Vật Lý",
            "Hoá Học",
            "Sinh Học",
            "Lịch Sử",
            "Địa Lý",
            "Giáo Dục Công Dân"
    };
    private final int[] imageResIds = {
            R.drawable.image_maths,
            R.drawable.image_literature,
            R.drawable.image_english,
            R.drawable.image_physics,
            R.drawable.image_chemistry,
            R.drawable.image_biology,
            R.drawable.image_history,
            R.drawable.image_geography,
            R.drawable.image_civiceducation
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadImageSlide();
        loadQuiz();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Học, Học Nữa, Học Mãi");
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }
    private void loadImageSlide(){
        ImageSlider imageSlider = findViewById(R.id.ImageSlide);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        //for (int imageResId : imageResIds) {
        //slideModels.add(new SlideModel(imageResId, ScaleTypes.FIT));
        //}
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fir-slideimagewithfirebase.appspot.com/o/banner_image%2Fbanner_image_one?alt=media&token=7746e1f0-433d-419e-a254-efa2b72557ee", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fir-slideimagewithfirebase.appspot.com/o/banner_image%2Fbanner_image_two?alt=media&token=98d0632d-ad3d-4161-b011-2251fa4662f8", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fir-slideimagewithfirebase.appspot.com/o/banner_image%2Fbanner_image_three?alt=media&token=6fccfa77-6878-4646-8a58-a15fb029634b", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fir-slideimagewithfirebase.appspot.com/o/banner_image%2Fbanner_image_four?alt=media&token=c088bbf3-347c-46a8-8b1e-0f25094d35fa", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        imageSlider.setItemClickListener(i -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sv.haui.edu.vn/register/"));
            startActivity(intent);
        });

    }

    private final int[] cardViewIds = {
            R.id.mathsCard,
            R.id.literatureCard,
            R.id.englishCard,
            R.id.physicsCard,
            R.id.chemistryCard,
            R.id.biologyCard,
            R.id.historyCard,
            R.id.geographyCard,
            R.id.civicEducationCard
    };

    private void loadQuiz() {
        for (int i = 0; i < cardViewIds.length; i++) {
            CardView cardView = findViewById(cardViewIds[i]);
            final int index = i; // Lưu index cho OnClickListener
            cardView.setOnClickListener(v -> navigateToSubject(imageResIds[index], subjectNames[index]));
        }
    }

    private void navigateToSubject(int imageResId, String subjectName) {
        Intent subjectActivity = new Intent(MainActivity.this, SubjectActivity.class);
        subjectActivity.putExtra("image", imageResId);
        subjectActivity.putExtra("name", subjectName);
        startActivity(subjectActivity);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
