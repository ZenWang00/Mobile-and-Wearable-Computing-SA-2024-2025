package com.example.stepappv4.ui.Report;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.stepappv4.ui.Report.ReportFragment;

public class ReportPagerAdapter extends FragmentStateAdapter {

    public ReportPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("ReportPagerAdapter", "Creating fragment at position: " + position);
        switch (position) {
            case 0:
                return new HourFragment();
            case 1:
                return new com.example.stepappv4.ui.Report.DayFragment();
            default:
                throw new IllegalArgumentException("Invalid position");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
