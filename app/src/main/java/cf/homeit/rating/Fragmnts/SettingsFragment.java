package cf.homeit.rating.Fragmnts;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import cf.homeit.rating.BuildConfig;
import cf.homeit.rating.R;

import static cf.homeit.rating.Extends.SupportVoids.onRotateScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    SharedPreferences sharedPreferences;


    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings_fragment);
        onRotateScreen(getActivity(),"portrait");

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity());

        onSharedPreferenceChanged(sharedPreferences, getString(R.string.name_ime_preference));
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.name_pin_preference));
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.name_pin_length_preference));

        Preference version = findPreference(getString(R.string.key_app_version));
        assert version != null;
        version.setSummary(BuildConfig.VERSION_NAME);
    }

    @Override
    public void onResume() {
        super.onResume();
        //unregister the preferenceChange listener
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(sharedPreferences.getString(key, ""));
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            if (preference != null) {
                preference.setSummary(sharedPreferences.getString(key, ""));
            }

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //unregister the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}
