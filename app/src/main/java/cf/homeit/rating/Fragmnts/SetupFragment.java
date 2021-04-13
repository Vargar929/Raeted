package cf.homeit.rating.Fragmnts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import cf.homeit.rating.Activity.ContainerActivity;
import cf.homeit.rating.Extends.SupportVoids;
import cf.homeit.rating.R;

import static cf.homeit.rating.Extends.SupportVoids.onRotateScreen;
import static cf.homeit.rating.Extends.SupportVoids.showToast;

public class SetupFragment extends Fragment{
    int selectedValue;
    Spinner spinner;
    TextInputEditText tePinLength,tePin;
    Button setupComplete;
    String pinLengthStr,pinStr,selectedValueStr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        onRotateScreen(getActivity(),"portrait");
        return inflater.inflate(R.layout.fragment_setup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewBuId(view);

        Context context = requireActivity().getApplicationContext();
        Context spContext = requireActivity();
        Resources res = getResources();
        @SuppressLint("Recycle") final TypedArray selectedValues = res
                .obtainTypedArray(R.array.msg_time_values_list_preference);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selectedValue = selectedValues.getInt(position, -1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        setupComplete.setOnClickListener(v -> {
//            new DBHelper(requireActivity().getApplicationContext());

            pinLengthStr = Objects.requireNonNull(tePinLength.getText()).toString();
            pinStr = Objects.requireNonNull(tePin.getText()).toString();
            if (TextUtils.isEmpty(pinLengthStr)) {
                tePinLength.setError(getString(R.string.required));
                showToast(context,getString(R.string.required),"w");
            }
            if (TextUtils.isEmpty(pinStr)) {
                tePin.setError(getString(R.string.required));
                showToast(context,getString(R.string.required),"w");
            }

            selectedValueStr = String.valueOf(selectedValue);
            SupportVoids.setStringPref(spContext,getString(R.string.key_pin_length_preference), pinLengthStr);
            SupportVoids.setStringPref(spContext,getString(R.string.key_pin_preference), pinStr);
            SupportVoids.setStringPref(spContext,getString(R.string.key_time_preference),selectedValueStr);

            requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            requireActivity().finish();
            startActivity(new Intent(requireActivity().getApplicationContext(), ContainerActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });


    }

    private void initViewBuId(View view) {
        spinner = view.findViewById(R.id.sSelect);
        tePinLength = view.findViewById(R.id.fieldPinLength);
        tePin =  view.findViewById(R.id.fieldPin);
        setupComplete =  view.findViewById(R.id.setupComplete);
    }

}