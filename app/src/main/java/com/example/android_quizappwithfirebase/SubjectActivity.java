package com.example.android_quizappwithfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_quizappwithfirebase.databinding.ActivitySubjectBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SubjectActivity extends AppCompatActivity {
    private ActivitySubjectBinding binding;
    private List<QuizModel> quizModelList;
    private QuizListAdapter adapter;

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
            int image = intent.getIntExtra("image", R.mipmap.ic_logo);
            binding.ImageSlide.setImageResource(image);
            binding.subjectName.setText(name);
        }

        // Khởi tạo danh sách quizModelList
        quizModelList = new ArrayList<>();

        // Lấy dữ liệu từ Firebase
        getDataFromFirebase();
    }

    // Thiết lập RecyclerView
    private void setupRecyclerView() {
        // Ẩn ProgressBar khi đã lấy được dữ liệu
        binding.progressBar.setVisibility(View.GONE);

        // Khởi tạo và gán adapter cho RecyclerView
        adapter = new QuizListAdapter(quizModelList);
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
                    QuizModel quizModel = snapshot.getValue(QuizModel.class);
                    if (quizModel != null) {
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
}
