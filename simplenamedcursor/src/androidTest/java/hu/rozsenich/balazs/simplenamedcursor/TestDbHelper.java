package hu.rozsenich.balazs.simplenamedcursor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Balazs Rozsenich on 2015.06.25.
 * b.rozsenich@gmail.com
 */
public class TestDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "snctest.db";
    public static final int DB_VERSION = 1;

    public static final String CREATE_TABLE = "CREATE TABLE snctest (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "string TEXT, " +
            "int INTEGER, " +
            "blob BLOB, " +
            "short SHORT, " +
            "long LONG, " +
            "float FLOAT, " +
            "double DOUBLE)";

    public TestDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL("INSERT INTO snctest (string, int, blob, short, long, float, double)" +
                " VALUES ('foo bar'," + Integer.MAX_VALUE + ",'" + fooByte() + "'," +
                Short.MAX_VALUE + "," + Long.MAX_VALUE + "," + Float.MAX_VALUE + "," + Double.MAX_VALUE + ")");
        db.execSQL("INSERT INTO snctest (string, int, blob, short, long, float, double)" +
                " VALUES ('foo bar'," + Integer.MAX_VALUE + ",'" + fooByte() + "'," +
                Short.MAX_VALUE + "," + Long.MAX_VALUE + "," + Float.MAX_VALUE + "," + Double.MAX_VALUE + ")");
        db.execSQL("INSERT INTO snctest (int, blob, short, long, float, double)" +
                " VALUES (" + Integer.MAX_VALUE + ",'" + fooByte() + "'," +
                Short.MAX_VALUE + "," + Long.MAX_VALUE + "," + Float.MAX_VALUE + "," + Double.MAX_VALUE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE snctest");
        onCreate(db);
    }

    private String fooByte() {
        char[] longText = new char[65535/2];

        for(int i = 0; i<65535/2; i++) {
            longText[i] = 'x';
        }

        return new String(longText);
    }
}
