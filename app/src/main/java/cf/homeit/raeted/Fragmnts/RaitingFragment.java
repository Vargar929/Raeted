package cf.homeit.raeted.Fragmnts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.sql.Timestamp;

import cf.homeit.raeted.R;
import cf.homeit.raeted.Sqlite.DBHelper;

public class RaitingFragment extends Fragment implements View.OnClickListener {
    private DBHelper mydb ;
    private Timestamp timestamp;
    NavController navController;
    CardView cvBad, cvGood, cvNice;
    public RaitingFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rating_fragment, container, false);
        mydb = new DBHelper(getActivity().getApplicationContext());

        cvBad = view.findViewById(R.id.cv_bad);
        cvGood = view.findViewById(R.id.cv_good);
        cvNice = view.findViewById(R.id.cv_nice);
        return view;
    }

    private void onRateDone(String val, String time){
        if(mydb.insertRating(val,time)){
            Toast.makeText(getActivity().getApplicationContext(), "Ваша оценка принята! Спасибо.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        String val;
        switch (v.getId()) {
            case R.id.cv_bad:
                // кнопка ОК
                timestamp = new Timestamp(System.currentTimeMillis());
                val = "1";
                onRateDone(val, timestamp.toString());
                break;
            case R.id.cv_good:
                // кнопка Cancel
                timestamp = new Timestamp(System.currentTimeMillis());
                val = "2";
                onRateDone(val, timestamp.toString());
                break;
            case R.id.cv_nice:
                // кнопка Cancel
                timestamp = new Timestamp(System.currentTimeMillis());
                val = "3";
                onRateDone(val, timestamp.toString());
                break;
            case R.id.view_all_results:
                navController = Navigation.findNavController(requireActivity(), R.id.first_nav_host);
                navController.navigate(R.id.unlockAppFragment);
                break;
        }
    }
}
