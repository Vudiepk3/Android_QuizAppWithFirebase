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
        // Khởi tạo và gán layout cho biến binding
        binding = ActivitySubjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = this.getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            int image = intent.getIntExtra("image", R.drawable.image_logo);
            binding.ImageSlide.setImageResource(image);
            binding.subjectName.setText(name);
        }
        // Khởi tạo danh sách quizModelList
        quizModelList = new ArrayList<>();

        // Lấy dữ liệu từ Firebase
        getDataFromFirebase();
        onResume();
    }

    // Thiết lập RecyclerView
    private void setupRecyclerView() {
        // Ẩn ProgressBar khi đã lấy được dữ liệu
        binding.progressBar.setVisibility(View.GONE);

        // Khởi tạo và gán adapter cho RecyclerView
        QuizListAdapter adapter = new QuizListAdapter(quizModelList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    // Phương thức lấy dữ liệu từ Firebase
    private void getDataFromFirebase() {
        // Hiển thị ProgressBar khi đang lấy dữ liệu
        binding.progressBar.setVisibility(View.VISIBLE);

        // Tham chiếu đến node root của Firebase Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // Lắng nghe sự kiện lấy dữ liệu thành công
        databaseReference.get().addOnSuccessListener(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                // Duyệt qua mỗi child node trong dataSnapshot
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Parse dataSnapshot thành đối tượng QuizModel
                    String subjectName = binding.subjectName.getText().toString();
                    int subjectId = getSubjectId(subjectName);
                    String strSubjectId = String.valueOf(subjectId);
                    QuizModel quizModel = snapshot.getValue(QuizModel.class);
                    if (quizModel != null && quizModel.getId().equals(strSubjectId)) {
                        // Thêm quizModel vào danh sách
                        quizModelList.add(quizModel);
                    }
                }
            }
            // Thiết lập RecyclerView sau khi đã lấy được dữ liệu
            setupRecyclerView();
        }).addOnFailureListener(e -> {
            // Xử lý khi có lỗi xảy ra trong quá trình lấy dữ liệu
        });
    }
    public static int getSubjectId(String subjectName) {
        return SUBJECT_IDS.getOrDefault(subjectName, 0);
    }
    protected  void onResume(){
        super.onResume();
    }
}
