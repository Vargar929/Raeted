package cf.homeit.raeted.Fragmnts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.hanks.passcodeview.PasscodeView;


import cf.homeit.raeted.R;

public class UnlockAppFragment extends Fragment {
    PasscodeView passcodeView;
    NavController navController;
    SharedPreferences sharedPreferences;

    public UnlockAppFragment(){ }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unlock_fragment, container, false);
        passcodeView = view.findViewById(R.id.passcode_view);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String pinCode=sharedPreferences.getString(getString(R.string.name_pin_preference),getString(R.string.default_pass));
        String pinLength =sharedPreferences.getString(getString(R.string.name_pin_length_preference),getString(R.string.default_pass_length));

        passcodeView.setPasscodeLength(Integer.parseInt(pinLength))
                .setLocalPasscode(pinCode)
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getActivity().getApplicationContext(),getString(R.string.wrong_password)
                                ,Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onSuccess(String number) {
                        navController = Navigation.findNavController(requireActivity(),
                                R.id.first_nav_host);
                        navController.navigate(R.id.viewRatingFragment);
                    }
                });
        return view;
   }
}
