package com.example.android_quizappwithfirebase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_quizappwithfirebase.QuizListAdapter;
import com.example.android_quizappwithfirebase.model.QuizModel;
import com.example.android_quizappwithfirebase.R;
import com.example.android_quizappwithfirebase.databinding.ActivitySubjectBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubjectActivity extends AppCompatActivity {
    private ActivitySubjectBinding binding;
    private List<QuizModel> quizModelList;
    private static final HashMap<String, Integer> SUBJECT_IDS = new HashMap<>();

    static {
        // Mapping subject names to their IDs
        SUBJECT_IDS.put("Toán Học", 1);
        SUBJECT_IDS.put("Văn Học", 2);
        SUBJECT_IDS.put("Tiếng Anh", 3);
        SUBJECT_IDS.put("Vật Lý", 4);
        SUBJECT_IDS.put("Hoá Học", 5);
        SUBJECT_IDS.put("Sinh Học", 6);
        SUBJECT_IDS.put("Lịch Sử", 7);
        SUBJECT_IDS.put("Địa Lý", 8);
        SUBJECT_IDS.put("Giáo Dục Công Dân", 9);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize and set the layout for the binding variable
        binding = ActivitySubjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Hide the action bar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Get intent data
        Intent intent = this.getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int image = intent.getIntExtra("image", R.drawable.image_logo);
            binding.ImageSlide.setImageResource(image);
            binding.subjectName.setText(name);
        }

        // Initialize quizModelList
        quizModelList = new ArrayList<>();

        // Fetch data from Firebase
        getDataFromFirebase();
        onResume();
    }

    // Set up the RecyclerView
    private void setupRecyclerView() {
        // Hide ProgressBar once data is fetched
        binding.progressBar.setVisibility(View.GONE);

        // Initialize and set the adapter for RecyclerView
        QuizListAdapter adapter = new QuizListAdapter(quizModelList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    // Method to fetch data from Firebase
    private void getDataFromFirebase() {
        // Show ProgressBar while data is being fetched
        binding.progressBar.setVisibility(View.VISIBLE);

        // Reference the root node of the Firebase Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // Listen for data retrieval success
        databaseReference.get().addOnSuccessListener(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                // Iterate through each child node in dataSnapshot
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Parse dataSnapshot into QuizModel object
                    String subjectName = binding.subjectName.getText().toString();
                    int subjectId = getSubjectId(subjectName);
                    String strSubjectId = String.valueOf(subjectId);
                    QuizModel quizModel = snapshot.getValue(QuizModel.class);
                    if (quizModel != null && quizModel.getId().equals(strSubjectId)) {
                        // Add quizModel to the list
                        quizModelList.add(quizModel);
                    }
                }
            }
            // Set up RecyclerView after data is fetched
            setupRecyclerView();
        }).addOnFailureListener(e -> {
            // Handle any errors during data retrieval
        });
    }

    // Method to get the subject ID from the subject name
    public static int getSubjectId(String subjectName) {
        return SUBJECT_IDS.getOrDefault(subjectName, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
