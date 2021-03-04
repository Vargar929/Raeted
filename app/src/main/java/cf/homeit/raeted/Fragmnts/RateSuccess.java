package cf.homeit.raeted.Fragmnts;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.google.android.material.textview.MaterialTextView;

import cf.homeit.raeted.R;

public class RateSuccess extends Fragment {
    private MaterialTextView timerCounter;
    SharedPreferences sharedPreferences;

    public RateSuccess(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        View view = inflater.inflate(R.layout.thank_fragment, container, false);
        timerCounter = view.findViewById(R.id.countTimer);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String time=sharedPreferences.getString(getString(R.string.name_ime_preference),getString(R.string.default_time_value));
        Long timeL= Long.parseLong(time);
        //Создаем таймер обратного отсчета на 20 секунд с шагом отсчета
        //в 1 секунду (задаем значения в миллисекундах):
            new CountDownTimer(timeL, 1000) {

                //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
                @SuppressLint("SetTextI18n")
                public void onTick(long millisUntilFinished) {
                    timerCounter.setText("Осталось: "
                            + millisUntilFinished / 1000 + " сек.");
                }
                //Задаем действия после завершения отсчета (высвечиваем надпись "Бабах!"):
                public void onFinish() {
                    getActivity().onBackPressed();
                    NavController navController;
                    navController = Navigation.findNavController(requireActivity(), R.id.first_nav_host);
                    navController.navigate(R.id.raitingFragment);
                }
            }
            .start();
        return view;
    }



}

