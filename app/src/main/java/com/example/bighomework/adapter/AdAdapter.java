package com.example.bighomework.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bighomework.fragment.AdFragment1;
import com.example.bighomework.fragment.AdFragment2;
import com.example.bighomework.fragment.AdFragment3;

public class AdAdapter extends FragmentStateAdapter {


    public AdAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new AdFragment1();
            case 1:
                return new AdFragment2();
            case 2:
                return new AdFragment3();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        final int maxValue = 3;
        return maxValue;
    }
}
