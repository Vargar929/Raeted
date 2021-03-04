package cf.homeit.raeted.Fragmnts;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.sql.Timestamp;

import cf.homeit.raeted.R;
import cf.homeit.raeted.SQLiteHelper.DBHelper;

public class ViewRatingFragment extends Fragment {
    private static final String TAG = "ViewRatingFragment";
    private MaterialTextView avgRating;
    private Timestamp timestamp;
    DBHelper dbHelper;
    FloatingActionButton fab;

    public ViewRatingFragment(){ }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_rating_fragment, container, false);
        avgRating = view.findViewById(R.id.avgRating);
        timestamp = new Timestamp(System.currentTimeMillis());

        dbHelper = new DBHelper(getActivity().getApplicationContext());
        fab = view.findViewById(R.id.fab);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor b = db.query("rating_cassa",new String[] {"AVG(r_value) AS r_value"},
                null, null, null, null, null);

        logCursor(b);
        b.close();
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NavController navController;
                navController = Navigation.findNavController(requireActivity(), R.id.first_nav_host);
                navController.navigate(R.id.settingsFragment);            }
        });
        return view;
    }

    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                String avg;
                do {
                    str = "";
                    avg ="";

                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                        avg=avg.concat(c.getString((c.getColumnIndex(cn))));
                    }
                    Log.d(TAG, str);
                    avgRating.setText(avg);
                } while (c.moveToNext());
            }
        } else{
            Log.d(TAG, "Cursor is null");
            avgRating.setText("null");
        }
    }
}
