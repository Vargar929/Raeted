package cf.homeit.rating;

import android.app.Application;
import android.content.Context;

import cf.homeit.rating.SQLiteHelper.DBHelper;

public class RatingApplication extends Application {
    DBHelper mDbwrapper;

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        mDbwrapper = new DBHelper(context);
    }
}
