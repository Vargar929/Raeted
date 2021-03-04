package cf.homeit.raeted.Fragmnts;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.sql.Timestamp;

import cf.homeit.raeted.R;
import cf.homeit.raeted.SQLiteHelper.DBHelper;

public class RaitingFragment extends Fragment implements View.OnClickListener {
    private static final String TAG ="RatingFragment" ;
    DBHelper dbHelper;
    private Timestamp timestamp;
    NavController navController;
    CardView cvBad, cvGood, cvNice;
    ImageView unlock;
    public RaitingFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rating_fragment, container, false);
        dbHelper = new DBHelper(getActivity().getApplicationContext());

        cvBad = view.findViewById(R.id.cv_bad);
        cvGood = view.findViewById(R.id.cv_good);
        cvNice = view.findViewById(R.id.cv_nice);
        unlock = view.findViewById(R.id.view_all_results);
        cvBad.setOnClickListener(this);

        cvGood.setOnClickListener(this);

        cvNice.setOnClickListener(this);
        unlock.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        dbHelper = new DBHelper(getActivity().getApplicationContext());


    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        dbHelper = new DBHelper(getActivity().getApplicationContext());
    }

    private void onRateDone(String val, String time, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("r_value", val);
        cv.put("r_time", time);
        long rowID = db.insert("rating_cassa", null, cv);
        Log.d(TAG, "row inserted, ID = " + rowID);
        NavController navController;
        navController = Navigation.findNavController(requireActivity(), R.id.first_nav_host);
        navController.navigate(R.id.rateSuccess);
//        getActivity().onBackPressed();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String val;
        switch (v.getId()) {
            case R.id.cv_bad:
                // кнопка ОК
                timestamp = new Timestamp(System.currentTimeMillis());
                val = "1";
                onRateDone(val, timestamp.toString(),db);
                break;
            case R.id.cv_good:
                // кнопка Cancel
                timestamp = new Timestamp(System.currentTimeMillis());
                val = "2";
                onRateDone(val, timestamp.toString(),db);
                break;
            case R.id.cv_nice:
                // кнопка Cancel
                timestamp = new Timestamp(System.currentTimeMillis());
                val = "3";
                onRateDone(val, timestamp.toString(),db);
                break;
            case R.id.view_all_results:
                navController = Navigation.findNavController(requireActivity(), R.id.first_nav_host);
                navController.navigate(R.id.unlockAppFragment);
//                getActivity().onBackPressed();
                break;
        }
    }
}
