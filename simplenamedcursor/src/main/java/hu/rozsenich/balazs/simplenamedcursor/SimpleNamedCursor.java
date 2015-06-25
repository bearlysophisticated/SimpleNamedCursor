package hu.rozsenich.balazs.simplenamedcursor;

import android.database.CharArrayBuffer;
import android.database.Cursor;

/**
 * Created by Balazs Rozsenich on 2015.06.25.
 * b.rozsenich@gmail.com
 *
 * Wraps up a Cursor object to provide column getters based on column name.
 * Getter methods are overridden, everything else is delegated to the cursor object.
 *
 */
public class SimpleNamedCursor extends SimpleNamedCursorBase {

    public SimpleNamedCursor() {
        super(null);
    }

    /**
     * Initialize cursor object
     *
     * @param cursor The cursor object to be overridden. Normally a result from a query
     */
    public SimpleNamedCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Initialize cursor object
     *
     * @param cursor The cursor object to be overridden. Normally a result from a query
     */
    @Override
    public void readQueryResult(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public String getString(String columnName) {
        return getString(getColumnIndex(columnName));
    }

    @Override
    public int getInt(String columnName) {
        return getInt(getColumnIndex(columnName));
    }

    @Override
    public byte[] getBlob(String columnName) {
        return getBlob(getColumnIndex(columnName));
    }

    @Override
    public short getShort(String columnName) {
        return getShort(getColumnIndex(columnName));
    }

    @Override
    public long getLong(String columnName) {
        return getLong(getColumnIndex(columnName));
    }

    @Override
    public float getFloat(String columnName) {
        return getFloat(getColumnIndex(columnName));
    }

    @Override
    public double getDouble(String columnName) {
        return getDouble(getColumnIndex(columnName));
    }

    @Override
    public int getType(String columnName) {
        return getType(getColumnIndex(columnName));
    }

    @Override
    public boolean isNull(String columnName) {
        return isNull(getColumnIndex(columnName));
    }

    @Override
    public void copyStringToBuffer(String columnName, CharArrayBuffer buffer) {
        cursor.copyStringToBuffer(getColumnIndex(columnName), buffer);
    }

}
