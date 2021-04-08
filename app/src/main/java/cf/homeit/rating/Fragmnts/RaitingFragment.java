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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import cf.homeit.rating.R;
import cf.homeit.rating.SQLiteHelper.DBHelper;

import static cf.homeit.rating.Extends.SupportVoids.debugLog;
import static cf.homeit.rating.Extends.SupportVoids.onRotateScreen;
import static cf.homeit.rating.Extends.SupportVoids.showToast;

public class RaitingFragment extends Fragment {
    DBHelper dbHelper;
    CardView cvBad, cvGood, cvNice;
    ImageView unlock;
    Context context,spContext;
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                NavController navController;
                navController = Navigation.findNavController(requireActivity(), R.id.main_container);
                navController.navigate(R.id.raitingFragment);
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
        navController = Navigation.findNavController(requireActivity(), R.id.main_container);
        cvBad.setOnClickListener(v -> {
            String val = "1";
            onRateDone(val);
            debugLog(this, "Vote val", val);

        });
        cvGood.setOnClickListener(v -> {
            String val = "2";
            onRateDone(val);
            debugLog(this, "Vote val", val);

        });
        cvNice.setOnClickListener(v -> {
            String val = "3";
            onRateDone(val);
            debugLog(this, "Vote val", val);

        });
        unlock.setOnClickListener(v -> navController.navigate(R.id.unlockAppFragment));

    }


    @Override
    public void onStart() {
        super.onStart();
        dbHelper = new DBHelper(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        debugLog(this, this + " onResume", "success");
        dbHelper = new DBHelper(context);
    }

    private void onRateDone(String val){
        if(dbHelper.addData(val)){
            navController.navigate(R.id.rateSuccess);
        }else {
            showToast(context,getString(R.string.rate_error_text),"e");
        }
    }
}
