package com.example.stepappv4.ui.Report;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.stepappv4.R;
import com.example.stepappv4.databinding.FragmentReportBinding;
import com.example.stepappv4.ui.Report.ReportPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ReportFragment extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private FragmentReportBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentReportBinding.inflate(inflater, container, false);
        Log.d("ReportFragment", "onCreateView: View successfully created");
        View root = binding.getRoot();
        viewPager = root.findViewById(R.id.view_pager);
        tabLayout = root.findViewById(R.id.tab_layout);


        ReportPagerAdapter adapter = new ReportPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("hours");
            } else if (position == 1) {
                tab.setText("days");
            }
        }).attach();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
