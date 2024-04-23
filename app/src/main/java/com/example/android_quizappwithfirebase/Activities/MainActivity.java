package com.example.android_quizappwithfirebase.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.android_quizappwithfirebase.R;

public class MainActivity extends AppCompatActivity {
    private CardView mathCardView,physicsCardView,englishCardView,biologycaCardView,// Tham chiếu đến CardView trong layout
            chemistryCardView,historyCardView,geographyCardView,civicCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // Khởi tạo các thành phần giao diện
        initializeViews();

        // Thiết lập bắt sự kiện khi click
        setupClickListeners();
    }

    // Khởi tạo các thành phần giao diện
    private void initializeViews() {
        mathCardView = findViewById(R.id.mathsCard); // Tìm CardView trong layout bằng id
        physicsCardView = findViewById(R.id.physicsCard);
        englishCardView = findViewById(R.id.englishCard);
        chemistryCardView = findViewById(R.id.chemistryCard);
        biologycaCardView = findViewById(R.id.biologyCard);
        historyCardView = findViewById(R.id.historyCard);
        geographyCardView = findViewById(R.id.geographyCard);
        civicCardView = findViewById(R.id.civicEducationCard);
    }

    // Thiết lập bắt sự kiện khi click
    // Thiết lập sự kiện click cho các CardView
    private void setupClickListeners() {
        // Gọi phương thức setupCardClickListener để thiết lập sự kiện click
        setupCardClickListener(mathCardView, R.drawable.image_maths, "Toán Học");
        setupCardClickListener(physicsCardView, R.drawable.image_physics, "Vật Lý");
        setupCardClickListener(chemistryCardView, R.drawable.image_chemistry, "Hoá Học");
        setupCardClickListener(englishCardView, R.drawable.image_english, "Tiếng Anh");
        setupCardClickListener(biologycaCardView, R.drawable.image_biology, "Sinh Học");
        setupCardClickListener(historyCardView, R.drawable.image_history, "Lịch Sử");
        setupCardClickListener(geographyCardView, R.drawable.image_geography, "Địa Lý");
        setupCardClickListener(civicCardView, R.drawable.image_civiceducation, "Giáo Dục Công Dân");

    }

    // Phương thức dùng để thiết lập sự kiện click cho một CardView cụ thể
    private void setupCardClickListener(CardView cardView, int imageResId, String subjectName) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức navigateToSubject khi CardView được click
                navigateToSubject(imageResId, subjectName);
            }
        });
    }

    // Phương thức để chuyển hướng sang SubjectActivity khi một CardView được click
    private void navigateToSubject(int imageResId, String subjectName) {
        // Tạo Intent để chuyển hướng sang SubjectActivity
        Intent subjectActivity = new Intent(MainActivity.this, SubjectActivity.class);
        // Đính kèm thông tin hình ảnh và tên môn học trong Intent
        subjectActivity.putExtra("image", imageResId);
        subjectActivity.putExtra("name", subjectName);
        // Khởi chạy SubjectActivity
        startActivity(subjectActivity);
    }
    protected void onStop() {
        super.onStop();
        // Kết thúc ứng dụng khi thoát khỏi Activity
        finish();
    }

}
