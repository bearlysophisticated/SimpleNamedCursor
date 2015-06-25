package hu.rozsenich.balazs.simplenamedcursor;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by Balazs Rozsenich on 2015.06.25.
 * b.rozsenich@gmail.com
 *
 * This interface describes the methods which has to be implemented
 * to provide a functional column name based cursor object.
 *
 */
public abstract class SimpleNamedCursorBase implements Cursor {

    /** The cursor object which is overridden */
    protected Cursor cursor;

    public SimpleNamedCursorBase(Cursor cursor) {
        this.cursor = cursor;
    }

    /**
     * Returns the value of the requested column as a String.
     *
     * <p>The result and whether this method throws an exception when the
     * column value is null or the column type is not a string type is
     * implementation-defined.
     *
     * @param columnName the name of the target column.
     * @return the value of that column as a String.
     */
    public abstract String getString(String columnName);

    /**
     * Returns the value of the requested column as an int.
     *
     * <p>The result and whether this method throws an exception when the
     * column value is null, the column type is not an integral type, or the
     * integer value is outside the range [<code>Integer.MIN_VALUE</code>,
     * <code>Integer.MAX_VALUE</code>] is implementation-defined.
     *
     * @param columnName the name of the target column.
     * @return the value of that column as an int.
     */
    public abstract int getInt(String columnName);

    /**
     * Returns the value of the requested column as a byte array.
     *
     * <p>The result and whether this method throws an exception when the
     * column value is null or the column type is not a blob type is
     * implementation-defined.
     *
     * @param columnName the name of the target column.
     * @return the value of that column as a byte array.
     */
    public abstract byte[] getBlob(String columnName);

    /**
     * Returns the value of the requested column as a short.
     *
     * <p>The result and whether this method throws an exception when the
     * column value is null, the column type is not an integral type, or the
     * integer value is outside the range [<code>Short.MIN_VALUE</code>,
     * <code>Short.MAX_VALUE</code>] is implementation-defined.
     *
     * @param columnName the name of the target column.
     * @return the value of that column as a short.
     */
    public abstract short getShort(String columnName);

    /**
     * Returns the value of the requested column as a long.
     *
     * <p>The result and whether this method throws an exception when the
     * column value is null, the column type is not an integral type, or the
     * integer value is outside the range [<code>Long.MIN_VALUE</code>,
     * <code>Long.MAX_VALUE</code>] is implementation-defined.
     *
     * @param columnName the name of the target column.
     * @return the value of that column as a long.
     */
    public abstract long getLong(String columnName);

    /**
     * Returns the value of the requested column as a float.
     *
     * <p>The result and whether this method throws an exception when the
     * column value is null, the column type is not a floating-point type, or the
     * floating-point value is not representable as a <code>float</code> value is
     * implementation-defined.
     *
     * @param columnName the name of the target column.
     * @return the value of that column as a float.
     */
    public abstract float getFloat(String columnName);

    /**
     * Returns the value of the requested column as a double.
     *
     * <p>The result and whether this method throws an exception when the
     * column value is null, the column type is not a floating-point type, or the
     * floating-point value is not representable as a <code>double</code> value is
     * implementation-defined.
     *
     * @param columnName the name of the target column.
     * @return the value of that column as a double.
     */
    public abstract double getDouble(String columnName);

    /**
     * Returns data type of the given column's value.
     * The preferred type of the column is returned but the data may be converted to other types
     * as documented in the get-type methods such as {@link #getInt(int)}, {@link #getFloat(int)}
     * etc.
     *<p>
     * Returned column types are
     * <ul>
     *   <li>{@link #FIELD_TYPE_NULL}</li>
     *   <li>{@link #FIELD_TYPE_INTEGER}</li>
     *   <li>{@link #FIELD_TYPE_FLOAT}</li>
     *   <li>{@link #FIELD_TYPE_STRING}</li>
     *   <li>{@link #FIELD_TYPE_BLOB}</li>
     *</ul>
     *</p>
     *
     * @param columnName the name of the target column.
     * @return column value type
     */
    public abstract int getType(String columnName);

    /**
     * Returns <code>true</code> if the value in the indicated column is null.
     *
     * @param columnName the name of the target column.
     * @return whether the column value is null.
     */
    public abstract boolean isNull(String columnName);

    /**
     * Retrieves the requested column text and stores it in the buffer provided.
     * If the buffer size is not sufficient, a new char buffer will be allocated
     * and assigned to CharArrayBuffer.data
     * @param columnName the name of the target column.
     *        if the target column is null, return buffer
     * @param buffer the buffer to copy the text into.
     */
    public abstract void copyStringToBuffer(String columnName, CharArrayBuffer buffer);

    /**
     * Initialize cursor object
     *
     * @param cursor The cursor object to be overridden. Normally a result from a query
     */
    public abstract void readQueryResult(Cursor cursor);


    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public int getPosition() {
        return cursor.getPosition();
    }

    @Override
    public boolean move(int offset) {
        return cursor.move(offset);
    }

    @Override
    public boolean moveToPosition(int position) {
        return cursor.moveToPosition(position);
    }

    @Override
    public boolean moveToFirst() {
        return cursor.moveToFirst();
    }

    @Override
    public boolean moveToLast() {
        return cursor.moveToLast();
    }

    @Override
    public boolean moveToNext() {
        return cursor.moveToNext();
    }

    @Override
    public boolean moveToPrevious() {
        return cursor.moveToPrevious();
    }

    @Override
    public boolean isFirst() {
        return cursor.isFirst();
    }

    @Override
    public boolean isLast() {
        return cursor.isLast();
    }

    @Override
    public boolean isBeforeFirst() {
        return cursor.isBeforeFirst();
    }

    @Override
    public boolean isAfterLast() {
        return cursor.isAfterLast();
    }

    @Override
    public int getColumnIndex(String columnName) {
        return cursor.getColumnIndex(columnName);
    }

    @Override
    public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
        return cursor.getColumnIndexOrThrow(columnName);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return cursor.getColumnName(columnIndex);
    }

    @Override
    public String[] getColumnNames() {
        return cursor.getColumnNames();
    }

    @Override
    public int getColumnCount() {
        return cursor.getColumnCount();
    }

    @Override
    public byte[] getBlob(int columnIndex) {
        return cursor.getBlob(columnIndex);
    }

    @Override
    public String getString(int columnIndex) {
        return cursor.getString(columnIndex);
    }

    @Override
    public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {
        cursor.copyStringToBuffer(columnIndex, buffer);
    }

    @Override
    public short getShort(int columnIndex) {
        return cursor.getShort(columnIndex);
    }

    @Override
    public int getInt(int columnIndex) {
        return cursor.getInt(columnIndex);
    }

    @Override
    public long getLong(int columnIndex) {
        return cursor.getLong(columnIndex);
    }

    @Override
    public float getFloat(int columnIndex) {
        return cursor.getFloat(columnIndex);
    }

    @Override
    public double getDouble(int columnIndex) {
        return cursor.getDouble(columnIndex);
    }

    @Override
    public int getType(int columnIndex) {
        return cursor.getType(columnIndex);
    }

    @Override
    public boolean isNull(int columnIndex) {
        return cursor.isNull(columnIndex);
    }

    @Override
    public void deactivate() {
        cursor.deactivate();
    }

    @Override
    public boolean requery() {
        return cursor.requery();
    }

    @Override
    public void close() {
        cursor.close();
    }

    @Override
    public boolean isClosed() {
        return cursor.isClosed();
    }

    @Override
    public void registerContentObserver(ContentObserver observer) {
        cursor.registerContentObserver(observer);
    }

    @Override
    public void unregisterContentObserver(ContentObserver observer) {
        cursor.unregisterContentObserver(observer);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        cursor.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        cursor.unregisterDataSetObserver(observer);
    }

    @Override
    public void setNotificationUri(ContentResolver cr, Uri uri) {
        cursor.setNotificationUri(cr, uri);
    }

    @Override
    public Uri getNotificationUri() {
        return null;
    }

    @Override
    public boolean getWantsAllOnMoveCalls() {
        return cursor.getWantsAllOnMoveCalls();
    }

    @Override
    public Bundle getExtras() {
        return cursor.getExtras();
    }

    @Override
    public Bundle respond(Bundle extras) {
        return cursor.respond(extras);
    }
}
