package cf.homeit.raeted.Sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RatingReader.db";
    public static final String TABLE_NAME = "rating";
    public static final String COLUMN_NAME_RATING = "value";
    public static final String COLUMN_NAME_TIMESTAMP = "rate_time";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY," +
                    COLUMN_NAME_RATING + " INTEGER," + COLUMN_NAME_TIMESTAMP + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public Cursor getDataAVGByTime(String stTime, String enTime) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "SELECT AVG("+ COLUMN_NAME_RATING +")from "+ TABLE_NAME +" WHERE "+ COLUMN_NAME_TIMESTAMP+"="+stTime+"AND"+enTime+"", null );
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }
    public boolean insertRating (String val,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_RATING, val);
        contentValues.put(COLUMN_NAME_TIMESTAMP, time);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }
    public ArrayList<String> getAllRatings() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+"", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_RATING)));
            res.moveToNext();
        }
        return array_list;
    }
}