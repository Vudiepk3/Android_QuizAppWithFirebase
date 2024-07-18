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

    // List of questions and the quiz time
    private static List<QuestionModel> questionModelList;
    private static String time;
    private ActivityQuizBinding binding;
    private int currentQuestionIndex = 0;
    private String selectedAnswer = "";
    private int score = 0;

    // This method is called from outside to set the list of questions
    public static void setQuestionModelList(List<QuestionModel> questionList) {
        questionModelList = questionList;
    }

    // This method is called from outside to set the quiz time
    public static void setTime(String time) {
        QuizActivity.time = time;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set click listeners for buttons and start the quiz
        binding.btn0.setOnClickListener(this);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.nextBtn.setOnClickListener(this);
        binding.endBtn.setOnClickListener(this);

        loadQuestions(); // Load questions
        startTimer(); // Start the timer
    }

    // This method starts the countdown timer
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
                finishQuiz(); // End the quiz when the time is up
            }
        }.start();
    }

    // This method loads the next question
    @SuppressLint("SetTextI18n")
    private void loadQuestions() {
        selectedAnswer = ""; // Reset the selected answer
        if (currentQuestionIndex == questionModelList.size()) {
            finishQuiz(); // End the quiz if all questions are answered
            return;
        }
        // Display the question and options
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
        // Set the background color of all buttons to gray
        binding.btn0.setBackgroundColor(getColor(R.color.gray));
        binding.btn1.setBackgroundColor(getColor(R.color.gray));
        binding.btn2.setBackgroundColor(getColor(R.color.gray));
        binding.btn3.setBackgroundColor(getColor(R.color.gray));

        try {
            if (view.getId() == R.id.next_btn) {
                // If the "Next" button is clicked
                if (selectedAnswer.isEmpty()) {
                    // Check if the user has selected an answer
                    Toast.makeText(getApplicationContext(), "Please select an answer to continue", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectedAnswer.equals(questionModelList.get(currentQuestionIndex).getCorrect())) {
                    // If the answer is correct, increase the score
                    score++;
                    Log.i("Quiz Score", valueOf(score));
                }
                currentQuestionIndex++; // Move to the next question
                loadQuestions(); // Load the next question
            } else if (view.getId() == R.id.end_btn) {
                onExitButtonClick(); // Call the method when the "End" button is clicked
            } else {
                // If another option is selected
                selectedAnswer = ((Button) view).getText().toString();
                view.setBackgroundColor(getColor(R.color.orange)); // Set the background color of the selected button
            }
        } catch (Exception e) {
            Toast.makeText(this, "Application error", Toast.LENGTH_SHORT).show();
        }
    }

    // This method displays the results after completing the quiz
    @SuppressLint("SetTextI18n")
    private void finishQuiz() {
        try {
            int totalQuestions = questionModelList.size();
            int percentage = (int) (((float) score / totalQuestions) * 100);

            // Create a dialog to display the results
            ItemScoreDialogBinding dialogBinding = ItemScoreDialogBinding.inflate(getLayoutInflater());
            dialogBinding.scoreProgressIndicator.setProgress(percentage);
            dialogBinding.scoreProgressText.setText(percentage + " %");
            dialogBinding.scoreTitle.setText("Congratulations, you have completed the quiz");
            dialogBinding.scoreTitle.setTextColor(Color.RED);
            dialogBinding.scoreSubtitle.setText(score + " out of " + totalQuestions + " correct");
            dialogBinding.finishBtn.setOnClickListener(v -> finish()); // Close the activity when the "Finish" button is clicked

            new AlertDialog.Builder(this)
                    .setView(dialogBinding.getRoot())
                    .setCancelable(false)
                    .show();
        } catch (Exception e) {
            Toast.makeText(this, "Application error", Toast.LENGTH_SHORT).show();
        }
    }

    // This method displays a confirmation dialog when the user wants to exit the quiz
    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit the quiz?")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishQuiz(); // End the activity if the user chooses to exit
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    // This method is called when the user clicks the "Exit" button on the interface
    public void onExitButtonClick() {
        showExitConfirmationDialog(); // Show the confirmation dialog when the user clicks the "Exit" button on the interface
    }
}
