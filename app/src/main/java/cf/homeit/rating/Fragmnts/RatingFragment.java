package cf.homeit.rating.Fragmnts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import cf.homeit.rating.R;
import cf.homeit.rating.SQLiteHelper.DBHelper;

import static cf.homeit.rating.Extends.SupportVoids.debugMode;
import static cf.homeit.rating.Extends.SupportVoids.onNavigateTo;
import static cf.homeit.rating.Extends.SupportVoids.onRotateScreen;
import static cf.homeit.rating.Extends.SupportVoids.showToast;

public class RatingFragment extends Fragment {
    DBHelper dbHelper;
    CardView cvBad, cvGood, cvNice;
    ImageView unlock;
    Context context,spContext;
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
        onRotateScreen(getActivity(),"landscape");
        return inflater.inflate(R.layout.rating_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = requireActivity().getApplicationContext();
        spContext = requireActivity();
        dbHelper = new DBHelper(context);
        cvBad = view.findViewById(R.id.cv_bad);
        cvGood = view.findViewById(R.id.cv_good);
        cvNice = view.findViewById(R.id.cv_nice);
        unlock = view.findViewById(R.id.view_all_results);
        cvBad.setOnClickListener(v -> {
            String val = "1";
            onRateDone(val);
        });
        cvGood.setOnClickListener(v -> {
            String val = "2";
            onRateDone(val);
        });
        cvNice.setOnClickListener(v -> {
            String val = "3";
            onRateDone(val);
        });
        unlock.setOnClickListener(v ->{
            if (debugMode){
                onNavigateTo(requireActivity(),R.id.main_container,  R.id.viewRatingFragment);
            }else{
                onNavigateTo(requireActivity(),R.id.main_container,  R.id.unlockAppFragment);
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        dbHelper = new DBHelper(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        dbHelper = new DBHelper(context);
    }

    private void onRateDone(String val){
        if(dbHelper.addData(val)){
            onNavigateTo(requireActivity(),R.id.main_container,R.id.rateSuccess);
        }else {
            showToast(context,getString(R.string.rate_error_text),"e");
        }
    }
}
