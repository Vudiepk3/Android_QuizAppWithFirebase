package com.example.android_quizappwithfirebase.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import androidx.fragment.app.Fragment;

import com.example.android_quizappwithfirebase.R;
import com.example.android_quizappwithfirebase.UniversityAdapter;
import com.example.android_quizappwithfirebase.model.UniversityModel;

public class UniversityFragment extends Fragment {
        private RecyclerView recyclerView;
        private SearchView searchView;
        private UniversityAdapter listAdapter;
        private List<UniversityModel> mList = new ArrayList<>(); // Ensure mList is initialized

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_university, container, false);
            recyclerView = view.findViewById(R.id.recyclerView);
            searchView = view.findViewById(R.id.searchView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            addDataToList();
            listAdapter = new UniversityAdapter(getActivity(),mList);
            recyclerView.setAdapter(listAdapter);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    filterList(newText);
                    return true;
                }
            });
            return view;
        }

        private void filterList(String query) {
            if (query != null) {
                List<UniversityModel> filteredList = new ArrayList<>();
                for (UniversityModel item : mList) {
                    if (item.getTitle().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                        filteredList.add(item);
                    }
                }
                if (filteredList.isEmpty()) {
                    Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
                } else {
                    listAdapter.setFilteredList(filteredList);
                }
            }
        }

        private void addDataToList() {
                int[] imageList = {
                        R.drawable.image_maths, R.drawable.image_literature, R.drawable.image_english,
                        R.drawable.image_physics, R.drawable.image_chemistry, R.drawable.image_biology,
                        R.drawable.image_history, R.drawable.image_geography, R.drawable.image_civiceducation
                };

                String[] groupNames = {
                        "Toán Học", "Văn Học", "Vật Lý",
                        "Hoá Học", "Tiếng Anh", "Sinh Học",
                        "Lịch Sử", "Địa Lý", "Giáo Dục Công Dân"
                };

                for (int i = 0; i < imageList.length; i++) {
                    mList.add(new UniversityModel(groupNames[i], imageList[i]));
                }

            }


}


