package com.example.android_quizappwithfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
public class GroupAdapter extends ArrayAdapter<GroupData> {
    public GroupAdapter(@NonNull Context context, ArrayList<GroupData> dataArrayList) {
        super(context, R.layout.item_subjects, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        GroupData listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_subjects, parent, false);
        }
        ImageView listImage = view.findViewById(R.id.listImage);
        TextView listName = view.findViewById(R.id.listName);
        assert listData != null;
        listImage.setImageResource(listData.image);
        listName.setText(listData.name);
        return view;
    }
}