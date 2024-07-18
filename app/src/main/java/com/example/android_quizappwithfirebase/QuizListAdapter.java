package com.example.android_quizappwithfirebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_quizappwithfirebase.activities.QuizActivity;
import com.example.android_quizappwithfirebase.model.QuizModel;

import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.MyViewHolder> {

    private final List<QuizModel> quizModelList;

    // Constructor to initialize the Adapter with the list of quiz items
    public QuizListAdapter(List<QuizModel> quizModelList) {
        this.quizModelList = quizModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item and create a new ViewHolder
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_recycler_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Bind data of a quiz item to the ViewHolder
        holder.bind(quizModelList.get(position));
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the list
        return quizModelList.size();
    }

    // ViewHolder class to hold the view components of each item in the RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView quizTitleText;
        private final TextView quizSubtitleText;
        private final TextView quizTimeText;

        public MyViewHolder(View itemView) {
            super(itemView);
            // Initialize TextViews from the item layout
            quizTitleText = itemView.findViewById(R.id.quiz_title_text);
            quizSubtitleText = itemView.findViewById(R.id.quiz_subtitle_text);
            quizTimeText = itemView.findViewById(R.id.quiz_time_text);
        }

        // Method to bind data of a quiz item to the ViewHolder
        public void bind(QuizModel model) {
            // Set title, subtitle, and time of the quiz to corresponding TextViews
            quizTitleText.setText(model.getTitle());
            quizSubtitleText.setText(model.getSubtitle());
            quizTimeText.setText(model.getTime() + " min");

            // Handle click event when an item is clicked
            itemView.setOnClickListener(v -> {
                // Get context from the view
                Context context = v.getContext();
                // Create an intent to start QuizActivity
                Intent intent = new Intent(context, QuizActivity.class);
                // Set the list of questions and time for QuizActivity using static methods
                QuizActivity.setQuestionModelList(model.getQuestionList());
                QuizActivity.setTime(model.getTime());
                // Start the new Activity
                context.startActivity(intent);
            });
        }
    }
}
