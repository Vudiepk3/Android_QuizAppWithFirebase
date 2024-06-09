package com.example.android_quizappwithfirebase.activities;

import static java.lang.String.format;
import static java.lang.String.valueOf;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_quizappwithfirebase.model.QuestionModel;
import com.example.android_quizappwithfirebase.R;
import com.example.android_quizappwithfirebase.databinding.ActivityQuizBinding;
import com.example.android_quizappwithfirebase.databinding.ItemScoreDialogBinding;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    // Danh sách câu hỏi và thời gian bài kiểm tra
    private static List<QuestionModel> questionModelList;
    private static String time;
    private ActivityQuizBinding binding;
    private int currentQuestionIndex = 0;
    private String selectedAnswer = "";
    private int score = 0;

    // Phương thức này sẽ được gọi từ bên ngoài để thiết lập danh sách các câu hỏi
    public static void setQuestionModelList(List<QuestionModel> questionList) {
        questionModelList = questionList;
    }
    // Phương thức này sẽ được gọi từ bên ngoài để thiết lập thời gian cho bài kiểm tra
    public static void setTime(String time) {
        QuizActivity.time = time;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        // Thiết lập sự kiện lắng nghe cho các nút và bắt đầu bài kiểm tra
        binding.btn0.setOnClickListener(this);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.nextBtn.setOnClickListener(this);
        binding.endBtn.setOnClickListener(this);

        loadQuestions(); // Tải câu hỏi
        startTimer(); // Bắt đầu đếm thời gian

    }

    // Phương thức này khởi động đồng hồ đếm thời gian
    private void startTimer() {
        long totalTimeInMillis = Integer.parseInt(time) * 60 * 1000L;
        new CountDownTimer(totalTimeInMillis, 1000L) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                long remainingSeconds = seconds % 60;
                binding.timerIndicatorTextview.setText(format("%02d:%02d", minutes, remainingSeconds));
            }

            @Override
            public void onFinish() {
                finishQuiz();
            }
        }.start();
    }

    // Phương thức này tải câu hỏi tiếp theo
    @SuppressLint("SetTextI18n")
    private void loadQuestions() {
        selectedAnswer = "";
        if (currentQuestionIndex == questionModelList.size()) {
            finishQuiz(); // Kết thúc bài kiểm tra nếu đã trả lời hết câu hỏi
            return;
        }
        // Hiển thị câu hỏi và các tùy chọn
        binding.questionIndicatorTextview.setText("Question " + (currentQuestionIndex + 1) + "/ " + questionModelList.size());
        binding.questionProgressIndicator.setProgress((int) ((currentQuestionIndex / (float) questionModelList.size()) * 100));
        binding.questionTextview.setText(questionModelList.get(currentQuestionIndex).getQuestion());
        List<String> options = questionModelList.get(currentQuestionIndex).getOptions();
        binding.btn0.setText(options.get(0));
        binding.btn1.setText(options.get(1));
        binding.btn2.setText(options.get(2));
        binding.btn3.setText(options.get(3));
    }

    @Override
    public void onClick(View view) {
        // Đặt màu nền cho tất cả các nút thành màu xám
        binding.btn0.setBackgroundColor(getColor(R.color.gray));
        binding.btn1.setBackgroundColor(getColor(R.color.gray));
        binding.btn2.setBackgroundColor(getColor(R.color.gray));
        binding.btn3.setBackgroundColor(getColor(R.color.gray));

        try {
            if (view.getId() == R.id.next_btn) {
                // Nếu nút "Next" được nhấn
                if (selectedAnswer.isEmpty()) {
                    // Kiểm tra xem người dùng đã chọn câu trả lời chưa
                    Toast.makeText(getApplicationContext(), "Làm Ơn Chọn Đáp Án Để Tiếp Tục", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectedAnswer.equals(questionModelList.get(currentQuestionIndex).getCorrect())) {
                    // Nếu câu trả lời đúng, tăng điểm số
                    score++;
                    Log.i("Điểm của bài kiểm tra", valueOf(score));
                }
                currentQuestionIndex++; // Chuyển sang câu hỏi tiếp theo
                loadQuestions(); // Tải câu hỏi mới
            } else if(view.getId() ==R.id.end_btn){
                onExitButtonClick();
            } else {
                // Nếu một tùy chọn khác được chọn
                selectedAnswer = ((Button) view).getText().toString();
                view.setBackgroundColor(getColor(R.color.orange)); // Đặt màu nền của nút đã chọn
            }
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi Ứng Dụng", Toast.LENGTH_SHORT).show();
        }
    }
    // Phương thức này hiển thị kết quả sau khi hoàn thành bài kiểm tra
    @SuppressLint("SetTextI18n")
    private void finishQuiz() {
        try {
            int totalQuestions = questionModelList.size();
            int percentage = (int) (((float) score / totalQuestions) * 100);

            // Tạo hộp thoại hiển thị kết quả
            ItemScoreDialogBinding dialogBinding = ItemScoreDialogBinding.inflate(getLayoutInflater());
            dialogBinding.scoreProgressIndicator.setProgress(percentage);
            dialogBinding.scoreProgressText.setText(percentage + " %");
            dialogBinding.scoreTitle.setText("Chúc mừng ban đã hoàn thành bài thi");
            dialogBinding.scoreTitle.setTextColor(Color.RED);
            dialogBinding.scoreSubtitle.setText(score + " trên " + totalQuestions + " chính xác");
            dialogBinding.finishBtn.setOnClickListener(v -> finish()); // Đóng activity khi nút "Finish" được nhấn

            new AlertDialog.Builder(this)
                    .setView(dialogBinding.getRoot())
                    .setCancelable(false)
                    .show();
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi Ứng Dụng", Toast.LENGTH_SHORT).show();
        }
    }
    // Phương thức này hiển thị hộp thoại xác nhận khi người dùng muốn thoát khỏi bài kiểm tra
    private void showExitConfirmationDialog() {

        new AlertDialog.Builder(this)
                .setMessage("Bạn có chắc chắn muốn thoát khỏi bài kiểm tra không?")
                .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kết thúc Activity nếu người dùng chọn thoát 
                        finishQuiz();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    // Phương thức này được gọi khi người dùng nhấn nút "Back" trên thiết bị
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        // Hiển thị hộp thoại xác nhận khi người dùng nhấn nút "Back" trên thiết bị
        showExitConfirmationDialog();
    }

    // Phương thức này được gọi khi người dùng nhấn nút "Thoát" trên giao diện
    public void onExitButtonClick() {
        // Hiển thị hộp thoại xác nhận khi người dùng nhấn nút "Thoát" trên giao diện
        showExitConfirmationDialog();
    }

}
