package hu.rozsenich.balazs.simplenamedcursor;

import android.app.Application;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class SimpleNamedCursorTest extends ApplicationTestCase<Application> {
    public SimpleNamedCursorTest() {
        super(Application.class);
    }

    public void testString() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT string FROM snctest", null));
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String string = cursor.getString("string");

            if(!cursor.isLast()) {
                assertNotNull(string);
                assertEquals("foo bar", string);
            }

            cursor.moveToNext();
        }

        db.close();
    }

    public void testCopyToCharArray() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT string FROM snctest", null));
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            if(!cursor.isLast()) {
                CharArrayBuffer charray = new CharArrayBuffer(7);
                cursor.copyStringToBuffer("string", charray);
                assertNotNull(charray);

                CharArrayBuffer charrayExpected = new CharArrayBuffer(7);
                charrayExpected.data = new char[]{'f','o','o', ' ', 'b', 'a', 'r'};

                for(int i = 0; i<7; i++) {
                    assertEquals((char) charrayExpected.data[i], (char) charray.data[i]);
                }
            }

            cursor.moveToNext();
        }

        db.close();
    }

    public void testInteger() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT int FROM snctest", null));
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int integer = cursor.getInt("int");

            assertNotNull(integer);
            assertEquals(Integer.MAX_VALUE, integer);

            cursor.moveToNext();
        }

        db.close();
    }

    public void testBlob() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT blob FROM snctest", null));
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            byte[] blob = cursor.getBlob("blob");
            char[] blobText = new String(blob).toCharArray();

            assertNotNull(blob);
            for(int i = 0; i<65535/2; i++) {
                assertEquals('x', blobText[i]);
            }

            cursor.moveToNext();
        }

        db.close();
    }

    public void testShort() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT short FROM snctest", null));

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            short shorT = cursor.getShort("short");

            assertNotNull(shorT);
            assertEquals(Short.MAX_VALUE, shorT);

            cursor.moveToNext();
        }

        db.close();
    }

    public void testLong() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT long FROM snctest", null));

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            long lonG = cursor.getLong("long");

            assertNotNull(lonG);
            assertEquals(Long.MAX_VALUE, lonG);

            cursor.moveToNext();
        }

        db.close();
    }

    public void testFloat() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT float FROM snctest", null));

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            float floaT = cursor.getFloat("float");

            assertNotNull(floaT);
            assertEquals(Float.MAX_VALUE, floaT);

            cursor.moveToNext();
        }

        db.close();
    }

    public void testDouble() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT double FROM snctest", null));

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            double doublE = cursor.getDouble("double");

            assertNotNull(doublE);
            assertEquals(Double.POSITIVE_INFINITY, doublE);

            cursor.moveToNext();
        }

        db.close();
    }

    public void testType() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT string FROM snctest", null));

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int type = cursor.getType("string");

            assertNotNull(type);
            if(cursor.isLast()) {
                assertEquals(Cursor.FIELD_TYPE_NULL, type);
            } else {
                assertEquals(Cursor.FIELD_TYPE_STRING, type);
            }

            cursor.moveToNext();
        }

        db.close();
    }

    public void testIsNull() {
        SQLiteDatabase db = new TestDbHelper(getContext()).getReadableDatabase();

        SimpleNamedCursorBase cursor = new SimpleNamedCursor();
        cursor.readQueryResult(db.rawQuery("SELECT string FROM snctest", null));

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            boolean isNull = cursor.isNull("string");

            if(cursor.isLast()) {
                assertTrue(isNull);
            } else {
                assertFalse(isNull);
            }

            cursor.moveToNext();
        }

        db.close();
    }
}