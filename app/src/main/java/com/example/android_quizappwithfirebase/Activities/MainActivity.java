package com.example.android_quizappwithfirebase.Activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.android_quizappwithfirebase.Fragment.ChatGPTFragment;
import com.example.android_quizappwithfirebase.Fragment.CivicEducationFragment;
import com.example.android_quizappwithfirebase.Fragment.GroupFragment;
import com.example.android_quizappwithfirebase.Fragment.HomeFragment;
import com.example.android_quizappwithfirebase.R;
import com.example.android_quizappwithfirebase.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.entrance) {
                replaceFragment(new CivicEducationFragment());
            } else if (item.getItemId() == R.id.group) {
                replaceFragment(new GroupFragment());
            } else if (item.getItemId() == R.id.chatgpt) {
                replaceFragment(new ChatGPTFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}