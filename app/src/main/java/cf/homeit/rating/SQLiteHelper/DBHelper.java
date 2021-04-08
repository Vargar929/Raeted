package cf.homeit.rating.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static cf.homeit.rating.Extends.SupportVoids.debugLog;
import static cf.homeit.rating.Extends.SupportVoids.getTime;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DBHISTORY = "ratingList";
    public static final int DBVERSION = 1;
    private static final String COL1 = "_id ";
    public static final String COL2 = "r_value";
    public static final String COL3 = "r_time";
    public static final String TABLE_NAME = "rating_cassa";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME
            +" ("+COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL2+" TEXT, "
            +COL3+" TEXT);\n";
    Context mContext;

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, DBHISTORY, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBHISTORY);
        onCreate(db);
    }
    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        final String dateTime = getTime();
        contentValues.put(COL2, item);
        contentValues.put(COL3, dateTime);
        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        return result != -1;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        return db.rawQuery(query, null);
    }
}