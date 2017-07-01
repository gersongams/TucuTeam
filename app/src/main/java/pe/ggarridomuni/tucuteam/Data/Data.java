package pe.ggarridomuni.tucuteam.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by bitzer on 17/06/17.
 */

public class Data {
    private SQLiteDatabase db;
    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_UNIVERSITY = "universidad";
    public static final String TABLE_ROW_CARRERA = "carrera";
    public static final String TABLE_ROW_INTERES = "interes";
    private static final String DB_NAME = "tucuteam";
    private static final int DB_VERSION = 1;
    private static final String TABLE_PROFILE = "profile";

    public Data(Context context) {
        // Create an instance of our internal
        //CustomSQLiteOpenHelper class
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        // Get a writable database
        db = helper.getWritableDatabase();
    }
    // Insert a record
    public void insert(String universidad, String carrera, String interes){
        // Add all the details to the table
        String query = "INSERT INTO " + TABLE_PROFILE + " (" +
                TABLE_ROW_UNIVERSITY + ", " +
                TABLE_ROW_CARRERA + "," +
                TABLE_ROW_INTERES + ") " +
                "VALUES (" +
                "'" + universidad + "'" + ", " + "'" + carrera + "'" + ", "+"'" + interes + "'"+"); ";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }
    public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" +" from " +
                TABLE_PROFILE, null);
        return c;
    }

    public void showData(){
        Cursor query = db.rawQuery("SELECT * FROM " + TABLE_PROFILE,null);

        while(query.moveToNext()){
            int rows = query.getCount();
            String  rowUniversity = query.getString(1);
            Log.i("Universidad : " , rowUniversity);
            Log.i("Rows : ", Integer.toString(rows));
        }
    }



    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        // This method only runs the first time the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
// Create a table for photos and all their details
            String newTableQueryString = "create table "
                    + TABLE_PROFILE + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_UNIVERSITY
                    + " text not null,"
                    + TABLE_ROW_CARRERA
                    + " text not null,"
                    + TABLE_ROW_INTERES
                    + " text not null);";
            db.execSQL(newTableQueryString);
        }
        // This method only runs when we increment DB_VERSION
// We will look at this in chapter 26
        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
        }
    }
}
