package pe.ggarridomuni.tucuteam.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by shinji on 29/06/2017.
 */

public class Profile {
    private SQLiteDatabase db;
    private static final String TABLE_USER = "user";
    public static final String TABLE_ROW_ID = "id";
    public static final String TABLE_ROW_NAME = "name";
    public static final String TABLE_ROW_DESCRIPTION = "description";
    public static final String TABLE_ROW_JOB = "job";
    public static final String TABLE_ROW_UID = "uid";
    public Profile (Context context) {
        // Create an instance of our internal
        //CustomSQLiteOpenHelper class
        SQLiteHelper helper = new SQLiteHelper(context);

        db = helper.getWritableDatabase();
    }

    public void insert(String name, String description, String job, String UID){
        // Add all the details to the table
        String query = "INSERT INTO " + TABLE_USER + " (" +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_DESCRIPTION + "," +
                TABLE_ROW_JOB + "," + TABLE_ROW_UID + ") " +
                "VALUES (" +
                "'" + name + "'" + ", " + "'" + description + "'" + ", "+"'" + job + "'"+ "," +
                "'" + UID + "'" + "); ";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }

    public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" +" from " +
                TABLE_USER, null);
        return c;
    }

    public void showData() {
        Cursor query = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        while (query.moveToNext()) {
            int rows = query.getCount();
            String rowUniversity = query.getString(1);
            Log.i("Universidad : ", rowUniversity);
            Log.i("Rows : ", Integer.toString(rows));
        }
    }

}
