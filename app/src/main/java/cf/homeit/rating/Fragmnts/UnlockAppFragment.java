package cf.homeit.rating.Fragmnts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.hanks.passcodeview.PasscodeView;

import cf.homeit.rating.R;

import static cf.homeit.rating.Extends.SupportVoids.onRotateScreen;
import static cf.homeit.rating.Extends.SupportVoids.showToast;

public class UnlockAppFragment extends Fragment {
    PasscodeView passcodeView;
    NavController navController;
    SharedPreferences sharedPreferences;
    Context context,spContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        onRotateScreen(getActivity(),"portrait");
        View view = inflater.inflate(R.layout.unlock_fragment, container, false);
//        onRotateScreen(getActivity(),"portrait");

        context = requireActivity().getApplicationContext();
        spContext  = requireActivity();
        passcodeView = view.findViewById(R.id.passcode_view);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(spContext);
        String pinCode=sharedPreferences.getString(getString(R.string.name_pin_preference),getString(R.string.default_pass));
        String pinLength =sharedPreferences.getString(getString(R.string.name_pin_length_preference),getString(R.string.default_pass_length));

        passcodeView.setPasscodeLength(Integer.parseInt(pinLength))
                .setLocalPasscode(pinCode)
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        showToast(context,getString(R.string.wrong_password),"e");
                    }
                    @Override
                    public void onSuccess(String number) {
                        navController = Navigation.findNavController(requireActivity(),
                                R.id.main_container);
                        navController.navigate(R.id.viewRatingFragment);
                    }
                });
        return view;
    }
}
