package com.example.android_quizappwithfirebase;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_quizappwithfirebase.model.UniversityModel;

import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder> {

    private List<UniversityModel> mList;

    public UniversityAdapter(FragmentActivity activity, List<UniversityModel> mList) {
        this.mList = mList;
    }

    public static class UniversityViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView titleTv;

        public UniversityViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logoIv);
            titleTv = itemView.findViewById(R.id.titleTv);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilteredList(List<UniversityModel> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_universities, parent, false);
        return new UniversityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityViewHolder holder, int position) {
        UniversityModel universityModel = mList.get(position);
        holder.logo.setImageResource(universityModel.getLogo());
        holder.titleTv.setText(universityModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
