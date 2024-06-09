package com.example.android_quizappwithfirebase.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.android_quizappwithfirebase.GroupAdapter;
import com.example.android_quizappwithfirebase.model.GroupModel;
import com.example.android_quizappwithfirebase.R;
import com.example.android_quizappwithfirebase.databinding.FragmentGroupBinding;

import java.util.ArrayList;

public class GroupFragment extends Fragment {

    FragmentGroupBinding binding;
    GroupAdapter listAdapter;
    ArrayList<GroupModel> dataArrayList = new ArrayList<>();
    GroupModel listData;


    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Rename and change types of parameters
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout của Fragment và bind dữ liệu
        binding = FragmentGroupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        int[] imageList = {R.drawable.image_maths, R.drawable.image_literature, R.drawable.image_english,
                R.drawable.image_physics, R.drawable.image_chemistry, R.drawable.image_biology,
                R.drawable.image_history,R.drawable.image_geography,R.drawable.image_civiceducation};

        String[] groupNames = {"Toán Học", "Văn Học", "Vật Lý",
                        "Hoá Học", "Tiếng Anh", "Sinh Học",
                        "Lịch Sử", "Địa Lý", "Giáo Dục Công Dân"};

        for (int i = 0; i < imageList.length; i++){
            listData = new GroupModel(groupNames[i],imageList[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new GroupAdapter(requireActivity(), dataArrayList);

        return view; // Trả về view đã inflate
    }

}