package com.example.android_quizappwithfirebase.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.android_quizappwithfirebase.R;
import com.example.android_quizappwithfirebase.databinding.ActivityMainBinding;
import com.example.android_quizappwithfirebase.view.custom_view.SubjectCardView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;
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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadImageSlide();
        loadQuiz();


        DrawerLayout drawerLayout = binding.drawerLayout;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void loadImageSlide() {
        ImageSlider imageSlider = binding.ImageSlide;
        ArrayList<SlideModel> slideModels = new ArrayList<>();
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

    private void loadQuiz() {
        for (int i = 0; i < cardViewIds.length; i++) {
            SubjectCardView cardView = findViewById(cardViewIds[i]);
            final int index = i; // Lưu index cho OnClickListener
            if (cardView != null) {
                cardView.setSubjectText(subjectNames[index]); // Đặt tên môn học
                cardView.setSubjectImage(imageResIds[index]); // Đặt ảnh môn học
                cardView.setOnClickListener(v -> navigateToSubject(imageResIds[index], subjectNames[index]));
            }
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
    @Override
    public void onDestroy(){
        super.onDestroy();
        binding = null;
    }
}