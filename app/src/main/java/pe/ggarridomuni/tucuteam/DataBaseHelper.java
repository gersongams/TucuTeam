package pe.ggarridomuni.tucuteam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gerson on 05/06/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLiteExample.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "person";
    public static final String TABLE_COLUMN_ID = "_id";
    public static final String TABLE_COLUMN_NAME = "name";
    public static final String TABLE_COLUMN_EMAIL = "email";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        "(" + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        TABLE_COLUMN_NAME + " TEXT, " +
                        TABLE_COLUMN_EMAIL + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact(String name, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COLUMN_NAME, name);
        contentValues.put(TABLE_COLUMN_EMAIL, title);

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(Integer id, String name, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COLUMN_NAME, name);
        contentValues.put(TABLE_COLUMN_EMAIL, title);

        db.update(TABLE_NAME, contentValues, TABLE_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );

        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                TABLE_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                TABLE_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + TABLE_NAME, null );
        return res;
    }


}
