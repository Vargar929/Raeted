package cf.homeit.rating.Extends;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.format.DateUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cf.homeit.rating.R;
import es.dmoral.toasty.Toasty;
import static android.graphics.Typeface.BOLD_ITALIC;


public class SupportVoids {

    private static SharedPreferences sharedPreferences;
    public static final Boolean debugMode = false;

    /**
     *
     * @param context Context to use
     * @param message Message to display
     */
    public static void showToast(Context context, String message,String type) {
        switch (type) {
            case "e":
                Toasty.error(context, message, Toast.LENGTH_SHORT, true).show();
                break;
            case "s":
                Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();
                break;
            case "i":
                Toasty.info(context, message, Toast.LENGTH_SHORT, true).show();
                break;
            case "w":
                Toasty.warning(context, message, Toast.LENGTH_SHORT, true).show();
                break;
            default:
                Toasty.normal(context, message, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     *
     * @return Date format
     */

    public static String getTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("ddMyyyy HH:mm:ss");
        Date curDate = new Date();
        return format.format(curDate);
    }

    /**
     *
     * @param context Context
     * @param key Preference key
     * @param value Preference value
     */
    public static void setStringPref(Context context, String key, String value) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.apply();
    }
    /**
     *
     * @param context Context
     * @param key Preference key
     * @param value Preference value
     */
    public static void setIntPref(Context context, String key, int value) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }
    /**
     *
     * @param context Context
     * @param key Preference key
     */
    public static String getStringPref(Context context, String key) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    /** Log  for testing on non android host.
     *
     * @param targetClass <i>Object</i> Used to identify the source of a log message.  It usually identifies
     *              the class or activity where the log call occurs.<br>
     * @param title <i>String</i> Used to identify the event of a log message.  It usually identifies
     *              the your event or data where the log call occurs.<br>
     * @param msg <i>String</i> The message you would like logged.<br>
     *            Here are some more examples of how strings can be used:
     *            <blockquote><pre>
     *                debugLog(this, "Activity create", "succes");
     *            </blockquote></pre>
     */
    public static void debugLog(Object targetClass, String title, String msg){
       String target = targetClass.getClass().getSimpleName();
        Log.d("DEBUG: "+target, title + " : <=> : " + msg);
    }
    /** Log  for testing on non android host.
     *
     * @param targetClass <i>Object</i> Used to identify the source of a log message.  It usually identifies
     *              the class or activity where the log call occurs.<br>
     * @param title <i>String</i> Used to identify the event of a log message.  It usually identifies
     *              the your event or data where the log call occurs.<br>
     * @param msg <i>String</i> The message you would like logged.<br>
     *            Here are some more examples of how strings can be used:
     *            <blockquote><pre>
     *                debugLog(this, "Activity create", "succes");
     *            </blockquote></pre>
     */
    public static void errorLog(Object targetClass, String title, String msg){
        String target = targetClass.getClass().getSimpleName();
        Log.e("ERROR: "+target, title + " : <=> : " + msg);
    }

    /**
     *
     * @param a Is activity instance
     * @param rotate Is string value (<i>landscape, portrait, onSensor,universal</i>)
     */
    public static void onRotateScreen(Activity a, String rotate){
        if(rotate==null){
            rotate = "onSensor";
        }
            switch (rotate){
                case "landscape":
                    if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                case "portrait":
                    if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case "onSensor":
                    if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                    break;
                case "universal":
                    if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    break;
            }
    }

    public static CharSequence getFormattedMessage() {
        final String prefix = "Formatted ";
        final String highlight = "bold italic";
        final String suffix = " text";
        SpannableStringBuilder ssb = new SpannableStringBuilder(prefix).append(highlight).append(suffix);
        int prefixLen = prefix.length();
        ssb.setSpan(new StyleSpan(BOLD_ITALIC),
                prefixLen, prefixLen + highlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

    public static Locale getLocale(Context context,String key,String defValue){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        String lang = sharedPreferences.getString(key, defValue);

        switch (lang) {
            case "ru":
                lang = "ru";
                break;
            case "kk":
                lang = "kk";
                break;
            case "en":
                lang = "en";
                break;
            case "hi":
                lang = "hi";
                break;
        }
        return new Locale(lang);
    }

    /**
     *
     * @param activity Activity
     * @param viewID int R.id.container
     * @param resID int R.id.Navigation_Item
     */
    public static void onNavigateTo(Activity activity,   int viewID, int resID){
        NavController navController;
        navController = Navigation.findNavController(activity, viewID);
        navController.navigate(resID);
    }

    /**
     *
     * @param graphView GraphView
     * @param series BarGraphSeries<DataPoint>
     * @param legendText String
     * @param align LegendRenderer.LegendAlign
     */
    public static void drawLegend(GraphView graphView, BarGraphSeries<DataPoint> series, String legendText, LegendRenderer.LegendAlign align){
        series.setTitle(legendText);
        graphView.getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(align);

    }




}
