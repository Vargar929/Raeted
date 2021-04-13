package cf.homeit.rating.Fragmnts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import cf.homeit.rating.R;

import static cf.homeit.rating.Extends.SupportVoids.onNavigateTo;
import static cf.homeit.rating.Extends.SupportVoids.onRotateScreen;
import static cf.homeit.rating.Extends.SupportVoids.showToast;

public class UnlockAppFragment extends Fragment {
    SharedPreferences sharedPreferences;
    Context context,spContext;
    String pinCode,pinLength;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        onRotateScreen(getActivity(),"portrait");
        View view = inflater.inflate(R.layout.unlock_fragment, container, false);
        context = requireActivity().getApplicationContext();
        spContext  = requireActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(spContext);
        pinCode=sharedPreferences.getString(getString(R.string.key_pin_preference),getString(R.string.default_pass));
        pinLength =sharedPreferences.getString(getString(R.string.key_pin_length_preference),getString(R.string.default_pass_length));
        showLockScreenFragment(view);
        return view;
    }


    private void showLockScreenFragment(View view) {
        PinLockView mPinLockView = view.findViewById(R.id.pin_lock_view);
        IndicatorDots mIndicatorDots = view.findViewById(R.id.indicator_dots);
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        mPinLockView.setPinLength(Integer.parseInt(pinLength));
        mPinLockView.setTextColor(ContextCompat.getColor(context, R.color.white));

        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
    }

    private final PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            if(pin.equals(pinCode)){
                showToast(context, getString(R.string.on_complete_message),"s");
                showMainFragment();
            }else{
                showToast(context, getString(R.string.on_in_complete_message),"e");
            }
        }

        @Override
        public void onEmpty()
        {

        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {

        }
    };

    private  void showMainFragment() {
        onNavigateTo(requireActivity(),R.id.main_container,R.id.viewRatingFragment);
    }
}
