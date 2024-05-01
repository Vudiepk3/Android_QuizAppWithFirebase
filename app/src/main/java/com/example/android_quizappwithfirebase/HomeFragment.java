package com.example.android_quizappwithfirebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.android_quizappwithfirebase.Activities.SubjectActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private final ArrayList<CardView> cardViews = new ArrayList<>();
    private final String[] subjectNames = {
            "Toán Học",
            "Văn Học",
            "Vật Lý",
            "Hoá Học",
            "Tiếng Anh",
            "Sinh Học",
            "Lịch Sử",
            "Địa Lý",
            "Giáo Dục Công Dân"
    };
    private final int[] imageResIds = {
            R.drawable.image_maths,
            R.drawable.image_literature,
            R.drawable.image_physics,
            R.drawable.image_chemistry,
            R.drawable.image_english,
            R.drawable.image_biology,
            R.drawable.image_history,
            R.drawable.image_geography,
            R.drawable.image_civiceducation
    };

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeViews(view);
        setupClickListeners();
        ImageSlider imageSlider = view.findViewById(R.id.ImageSlide);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        for (int imageResId : imageResIds) {
            slideModels.add(new SlideModel(imageResId, ScaleTypes.FIT));
        }
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        imageSlider.setItemClickListener(i -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sv.haui.edu.vn/register/"));
            startActivity(intent);
        });
        return view;
    }

    private void initializeViews(View view) {
        for (int id : new int[]{R.id.mathsCard, R.id.physicsCard, R.id.chemistryCard, R.id.englishCard,
                R.id.biologyCard, R.id.historyCard, R.id.geographyCard, R.id.civicEducationCard}) {
            cardViews.add(view.findViewById(id));
        }
    }

    private void setupClickListeners() {
        for (int i = 0; i < cardViews.size(); i++) {
            final int index = i;
            cardViews.get(i).setOnClickListener(v -> navigateToSubject(imageResIds[index], subjectNames[index]));
        }
    }

    private void navigateToSubject(int imageResId, String subjectName) {
        Intent subjectActivity = new Intent(getActivity(), SubjectActivity.class);
        subjectActivity.putExtra("image", imageResId);
        subjectActivity.putExtra("name", subjectName);
        startActivity(subjectActivity);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Kết thúc ứng dụng khi thoát khỏi Activity
    }
}
