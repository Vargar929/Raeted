package cf.homeit.raeted.Fragmnts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.hanks.passcodeview.PasscodeView;


import cf.homeit.raeted.R;

public class UnlockAppFragment extends Fragment {
    PasscodeView passcodeView;
    NavController navController;

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
        passcodeView.setPasscodeLength(4)
                .setLocalPasscode("1414")
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getActivity().getApplicationContext(), view.findViewById(R.id.wrong_password),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        navController = Navigation.findNavController(requireActivity(), R.id.first_nav_host);
                        navController.navigate(R.id.unlockAppFragment);                    }
                });
        return view;
   }
}
