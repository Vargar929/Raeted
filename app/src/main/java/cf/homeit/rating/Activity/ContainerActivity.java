package cf.homeit.rating.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import cf.homeit.rating.Interfaces.IOnBackPressed;
import cf.homeit.rating.R;

import static cf.homeit.rating.Extends.SupportVoids.onRotateScreen;

public class ContainerActivity extends AppCompatActivity {
    NavController navController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        onRotateScreen(this,"landscape");
        setContentView(R.layout.container_activity);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!isPreferencesSet(this)) {
            navController = Navigation.findNavController(this, R.id.main_container);
            navController.navigate(R.id.setupFragment);
        }
    }

    public boolean isPreferencesSet(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int exactNumberOfPreferences = 0;
        return sharedPreferences.getAll().size() != exactNumberOfPreferences;
    }


}
