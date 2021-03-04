package cf.homeit.raeted;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import cf.homeit.raeted.Activity.AppraisalsActivity;

public class LauncherActivity extends AppCompatActivity {

        @Override
        public void onCreate( Bundle savedInstanceState ) {
            setTheme(R.style.AppTheme_Launcher);
            super.onCreate( savedInstanceState );
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            finish();
            startActivity(new Intent(getApplicationContext(), AppraisalsActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
}
