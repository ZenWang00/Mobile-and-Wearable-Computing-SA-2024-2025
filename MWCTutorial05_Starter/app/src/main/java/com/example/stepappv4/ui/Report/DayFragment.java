package com.example.stepappv4.ui.Report;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.stepappv4.StepAppOpenHelper;
import com.example.stepappv4.databinding.FragmentDayBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DayFragment extends Fragment {
    private FragmentDayBinding binding;
    private AnyChartView anyChartView;
    private StepAppOpenHelper dbHelper;
    Date cDate = new Date();
    String current_time = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDayBinding.inflate(inflater, container, false);
        Log.d("DayFragment", "onCreateView: View successfully created");
        View root = binding.getRoot();

        anyChartView = binding.dayBarChart;
        anyChartView.setBackgroundColor("#00000000");
        anyChartView.setProgressBar(binding.progressBar);

        dbHelper = new StepAppOpenHelper(getContext());
        Cartesian cartesian = createColumnChart();
        anyChartView.setChart(cartesian);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Cartesian createColumnChart() {
        Map<String, Integer> stepsByDay = dbHelper.loadStepsByDate(getContext(), current_time);
        Cartesian cartesian = AnyChart.column();
        List<DataEntry> data = new ArrayList<>();
        if (stepsByDay != null) {
            for (Map.Entry<String, Integer> entry : stepsByDay.entrySet()) {
                data.add(new ValueDataEntry(entry.getKey(), entry.getValue()));
            }
        }
        Column column = cartesian.column(data);
        column.fill("#1EB980");
        column.stroke("#1EB980");
        cartesian.background().fill("#00000000");
        column.tooltip()
                .titleFormat("Date: {%X}")
                .format("{%Value} steps")
                .anchor(Anchor.RIGHT_BOTTOM);

        column.tooltip()
                .position(Position.RIGHT_TOP)
                .offsetX(0d)
                .offsetY(5d);
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);
        cartesian.yScale().minimum(0);


        cartesian.yAxis(0).title("Number of steps");
        cartesian.xAxis(0).title("Date");

        cartesian.animation(true);


        return cartesian;
    }
}
