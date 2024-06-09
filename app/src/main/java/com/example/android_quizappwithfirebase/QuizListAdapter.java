package com.example.android_quizappwithfirebase;

import android.annotation.SuppressLint;
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

    // Constructor để khởi tạo Adapter với danh sách các mục bài kiểm tra
    public QuizListAdapter(List<QuizModel> quizModelList) {
        this.quizModelList = quizModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo một ViewHolder mới bằng cách inflate layout của mỗi item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_recycler_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Gắn dữ liệu của một mục vào ViewHolder
        holder.bind(quizModelList.get(position));
    }

    @Override
    public int getItemCount() {
        // Trả về số lượng mục trong danh sách
        return quizModelList.size();
    }

    // Lớp ViewHolder để giữ các thành phần view của mỗi item trong RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView quizTitleText;
        private final TextView quizSubtitleText;
        private final TextView quizTimeText;

        public MyViewHolder(View itemView) {
            super(itemView);
            quizTitleText = itemView.findViewById(R.id.quiz_title_text);
            quizSubtitleText = itemView.findViewById(R.id.quiz_subtitle_text);
            quizTimeText = itemView.findViewById(R.id.quiz_time_text);
        }

        // Phương thức này gắn dữ liệu của một mục vào ViewHolder
        @SuppressLint("SetTextI18n")
        public void bind(QuizModel model) {
            // Gán tiêu đề, phụ đề và thời gian của bài kiểm tra vào các TextView tương ứng
            quizTitleText.setText(model.getTitle());
            quizSubtitleText.setText(model.getSubtitle());
            quizTimeText.setText(model.getTime() + " min");

            // Xử lý sự kiện khi một mục được nhấn
            itemView.setOnClickListener(v -> {
                // Lấy context từ view
                Context context = v.getContext();
                // Tạo một intent để chuyển đến QuizActivity
                Intent intent = new Intent(context, QuizActivity.class);
                // Thiết lập danh sách các câu hỏi và thời gian cho QuizActivity
                QuizActivity.setQuestionModelList(model.getQuestionList());
                QuizActivity.setTime(model.getTime());
                // Bắt đầu Activity mới
                context.startActivity(intent);
            });
        }
    }
}
