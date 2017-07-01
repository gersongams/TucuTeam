package pe.ggarridomuni.tucuteam.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shinji on 29/06/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    // User Table
    private static final String TABLE_USER = "user";
    public static final String TABLE_ROW_ID = "id";
    public static final String TABLE_ROW_NAME = "name";
    public static final String TABLE_ROW_DESCRIPTION = "description";
    public static final String TABLE_ROW_JOB = "job";
    public static final String TABLE_ROW_UID = "uid";

    // User Interests Table
    private static final String TABLE_USER_INTERESTS = "user_interests";
    public static final String USER_ID = "user_id";

    // Interests Table

    private static final String TABLE_INTERESTS = "interests";

    private static final String DB_NAME = "tucuteam";
    private static final int DB_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    // This method only runs the first time the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
// Create a table for photos and all their details
        String newTableQueryString = "create table if not exists"
                + TABLE_USER + " ("
                + TABLE_ROW_ID
                + " integer primary key autoincrement not null,"
                + TABLE_ROW_NAME
                + " text ,"
                + TABLE_ROW_DESCRIPTION
                + " text ,"
                + TABLE_ROW_JOB
                + " text,"
                + TABLE_ROW_UID
                + " text);";
        db.execSQL(newTableQueryString);
    }
    // This method only runs when we increment DB_VERSION
// We will look at this in chapter 26
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
    }

}
