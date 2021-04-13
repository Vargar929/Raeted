package cf.homeit.rating.Fragmnts;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Calendar;

import cf.homeit.rating.Extends.DatePickerFragment;
import cf.homeit.rating.R;

import static cf.homeit.rating.Extends.SupportVoids.showToast;

public class AnalyticsFragment extends Fragment {
    private Chip dateStart,dateEnd;
    public String dateStartArg;
    public String dateEndArg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.analytics_fragment,container,false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dateStart = view.findViewById(R.id.date_start);
        dateEnd = view.findViewById(R.id.date_end);
        GraphView graph = view.findViewById(R.id.graph);
        Button showResult = view.findViewById(R.id.btnShowResult);
        dateStart.setOnClickListener(v->{
            DatePickerFragment date = new DatePickerFragment();
            Calendar calender = Calendar.getInstance();
            Bundle args = new Bundle();
            args.putInt("year", calender.get(Calendar.YEAR));
            args.putInt("month", calender.get(Calendar.MONTH));
            args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
            date.setArguments(args);
            date.setCallBack(onDateStart);
            date.show(getChildFragmentManager(), "Date Picker");
        });
        dateEnd.setOnClickListener(v->{
            DatePickerFragment date = new DatePickerFragment();
            Calendar calender = Calendar.getInstance();
            Bundle args = new Bundle();
            args.putInt("year", calender.get(Calendar.YEAR));
            args.putInt("month", calender.get(Calendar.MONTH));
            args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
            date.setArguments(args);
            date.setCallBack(onDateEnd);
            date.show(getChildFragmentManager(), "Date Picker");
        });
        showResult.setOnClickListener(v->{
//            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
////                    new DataPoint(1, 1.56),
////                    new DataPoint(2, 5.95),
////                    new DataPoint(3, 3.555)
////            });
////            graph.addSeries(series);
////            series.setValueDependentColor(data -> Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100));
////
////            series.setSpacing(50);
////
////// draw values on top
////            series.setDrawValuesOnTop(true);
////            series.setValuesOnTopColor(Color.RED);
            if (Integer.parseInt(dateEndArg) < Integer.parseInt(dateStartArg)){
                showToast(getActivity().getApplicationContext(), String.valueOf(dateEndArg), "i");
            }else {
                showToast(getActivity().getApplicationContext(), String.valueOf(dateEndArg), "e");
            }

        });
    }


    private final DatePickerDialog.OnDateSetListener onDateEnd = new DatePickerDialog.OnDateSetListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (view.isShown()) { ///if the datepicker is on the screen
                dateEndArg = dayOfMonth +"" +(monthOfYear+1) +""+ (year);
                dateEnd.setText("End date:" +  dayOfMonth + "-" +(monthOfYear+1) + "-" + (year));
            }
        }
    };
    private final DatePickerDialog.OnDateSetListener onDateStart = new DatePickerDialog.OnDateSetListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (view.isShown()) { ///if the datepicker is on the screen
                dateStartArg = dayOfMonth +"" +(monthOfYear+1) +""+ (year);
                dateStart.setText("Start date:" +  dayOfMonth + "-" +(monthOfYear+1) + "-" + (year));
            }
        }
    };
//    DatePickerDialog.OnDateSetListener onSetDateStartArg = (view, year, monthOfYear, dayOfMonth) ->
//            dateStartArg = dayOfMonth + "-" +(monthOfYear+1)
//                    + "-" + (year);
//    DatePickerDialog.OnDateSetListener onSetDateEndArg = (view, year, monthOfYear, dayOfMonth) ->
//            dateEndArg= dayOfMonth + "-" +(monthOfYear+1)
//                    + "-" + (year);

}
