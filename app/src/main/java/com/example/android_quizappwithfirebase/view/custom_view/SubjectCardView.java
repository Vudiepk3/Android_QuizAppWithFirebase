package com.example.android_quizappwithfirebase.view.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_quizappwithfirebase.databinding.ViewSubjectCardBinding;

public class SubjectCardView extends FrameLayout {
    private ViewSubjectCardBinding binding;

    public SubjectCardView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SubjectCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SubjectCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        binding = ViewSubjectCardBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void setSubjectText(String text) {
        binding.subjectText.setText(text);
    }

    public void setSubjectImage(int resId) {
        binding.subjectImage.setImageResource(resId);
    }
}