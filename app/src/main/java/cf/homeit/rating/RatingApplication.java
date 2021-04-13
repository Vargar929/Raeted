package cf.homeit.rating;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

import cf.homeit.rating.SQLiteHelper.DBHelper;

import static cf.homeit.rating.Extends.SupportVoids.getLocale;

public class RatingApplication extends Application {
    DBHelper mDbwrapper;

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        mDbwrapper = new DBHelper(context);
//        setLocale();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        setLocale();
    }

    private void setLocale() {
        final Resources resources = getResources();
        final Configuration configuration = resources.getConfiguration();
        final Locale locale = getLocale(this,getString(R.string.key_lang_preference),"ru");
        if (!configuration.locale.equals(locale)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale);
            }
            resources.updateConfiguration(configuration, null);
        }
    }
}
