package cf.homeit.rating.Fragmnts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import cf.homeit.rating.Adapters.RatingListAdapter;
import cf.homeit.rating.DataModel.RatingList;
import cf.homeit.rating.R;
import cf.homeit.rating.SQLiteHelper.DBHelper;

import static cf.homeit.rating.Extends.SupportVoids.onNavigateTo;
import static cf.homeit.rating.Extends.SupportVoids.onRotateScreen;
import static cf.homeit.rating.Extends.SupportVoids.showToast;
import static cf.homeit.rating.SQLiteHelper.DBHelper.COL2;
import static cf.homeit.rating.SQLiteHelper.DBHelper.COL3;

public class ViewRatingFragment extends Fragment{
    private MaterialTextView avgRating;
//    private String startDate, endDate, currentDateTime;
//    Calendar dateAndTime=Calendar.getInstance();
    DBHelper dbHelper;
    FloatingActionButton fab;
    Button showAnalytics;
    RecyclerView mRecyclerView;
    RatingListAdapter readingListAdapter;
    ArrayList<RatingList> arrayList = new ArrayList<>();
    public Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                onNavigateTo(requireActivity(),R.id.main_container,R.id.raitingFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        onRotateScreen(getActivity(), "portrait");
        Context context = getActivity().getApplicationContext();
        dbHelper = new DBHelper(context);
        return inflater.inflate(R.layout.view_rating_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab);
        showAnalytics = view.findViewById(R.id.showAnalytics);
        avgRating = view.findViewById(R.id.avgRating);
        mRecyclerView = view.findViewById(R.id.lvRating);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor cAverage = db.query("rating_cassa",new String[] {"AVG(r_value) AS r_value"},
                null, null, null, null, null);
        logCursor(cAverage);
        cAverage.close();

        setmRecyclerView();
        fab.setOnClickListener(v -> onNavigateTo(requireActivity(),R.id.main_container,R.id.settingsFragment));
        showAnalytics.setOnClickListener(v -> {
            //            Cursor cDateAverage = db.query(TABLE_NAME,new String[] {"AVG("+COL2+") AS "+COL2+"WHERE "+COL3+" BETWEEN LIKE "+startDate+"'%'"+" AND LIKE"+endDate+"'%"},
//                    null, null, null, null, null);
//            logCursor(cDateAverage);
//            cDateAverage.close();
            onNavigateTo(requireActivity(),R.id.main_container,R.id.analitycsFragment);
        });
    }

    @SuppressLint("SetTextI18n")
    private void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                String avg;
                try {
                    do {
                        str = "";
                        avg ="";
                        for (String cn : c.getColumnNames()) {
                            str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                            avg = avg.concat(c.getString((c.getColumnIndex(cn))));
                        }
                        avgRating.setText(avg);
                    } while (c.moveToNext());
                }catch (Exception e) {
                    e.printStackTrace();
                    showToast(requireActivity().getApplicationContext(),e.toString(), "e");
                }

            }
        } else{
            showToast(requireActivity(),getString(R.string.avg_error),"e");
            avgRating.setText("null");
        }
    }
    private void setmRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        RatingList ratingList = new RatingList();
        readingListAdapter = new RatingListAdapter(mContext, arrayList);
        final Cursor data = dbHelper.getData();
        while (data.moveToNext()) {
            RatingList list = new RatingList();
            // add proper column name.
            String rValue = data.getString(data.getColumnIndexOrThrow(COL2));
            String rTime = data.getString(data.getColumnIndexOrThrow(COL3));
            // I am assuming the method name here, add your own method names.
            list.setrValue(rValue);
            list.setrTime(rTime);
            arrayList.add(list);
        }
        while (data.moveToNext()) {
            arrayList.add(ratingList);
        }
        if (data.getCount() != 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mRecyclerView.setAdapter(readingListAdapter);

        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
    }

//    public void setDate(Context context ,View v) {
//        new DatePickerDialog(context, myCallBack,
//                dateAndTime.get(Calendar.YEAR),
//                dateAndTime.get(Calendar.MONTH),
//                dateAndTime.get(Calendar.DAY_OF_MONTH))
//                .show();
//    }
//    private void setInitialDateTime() {
//        currentDateTime = DateUtils.formatDateTime(requireActivity().getApplicationContext(),
//                dateAndTime.getTimeInMillis(),
//                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
//    }
//
//    DatePickerDialog.OnDateSetListener myCallBack = (view, year, monthOfYear, dayOfMonth) -> {
//        dateAndTime.set(Calendar.YEAR, year);
//        dateAndTime.set(Calendar.MONTH, monthOfYear);
//        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        setInitialDateTime();
//    };
}
