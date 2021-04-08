package cf.homeit.rating;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cf.homeit.rating.Activity.ContainerActivity;

import static cf.homeit.rating.Extends.SupportVoids.onRotateScreen;

public class LauncherActivity extends AppCompatActivity {

        @Override
        public void onCreate( Bundle savedInstanceState ) {
            setTheme(R.style.AppTheme_Launcher);
            super.onCreate( savedInstanceState );
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            finish();
            startActivity(new Intent(getApplicationContext(), ContainerActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
}
