package com.example.malik.bengkel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.malik.bengkel.domain.Bengkel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malik on 29/10/15.
 */
public class DBAdapter {
    public class DBAdapter extends SQLiteOpenHelper {
        private static final String DB_NAME = "Bengkel";
        private static final String TABLE_NAME = "bengkel";
        private static final String COL_ID = "id";
        private static final String COL_NAMA = "nama";
        private static final String COL_LAHIR = "lahir";
        private static final String COL_ALAMAT = "jabatan";
        private static final String COL_JENISKELAMIN = "jenisKelamin";
        private static final String COL_KEAHLIAN = "keahlian";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME + ";";

        private SQLiteDatabase sqliteDatabase = null;

        public DBAdapter(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTable(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            db.execSQL(DROP_TABLE);
        }

        public void openDB() {
            if (sqliteDatabase == null) {
                sqliteDatabase = getWritableDatabase();
            }
        }

        public void closeDB() {
            if (sqliteDatabase != null) {
                if (sqliteDatabase.isOpen()) {
                    sqliteDatabase.close();
                }
            }
        }

        public void createTable(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + COL_NAMA + " TEXT," + COL_LAHIR + " TEXT,"+ COL_ALAMAT + " TEXT,"
                    + COL_JENISKELAMIN + " TEXT,"+ COL_KEAHLIAN + " TEXT);");
        }

        public void updateBengkel(Bengkel bengkel) {
            sqliteDatabase = getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(COL_NAMA, bengkel.getNama());
            cv.put(COL_LAHIR, bengkel.getLahir());
            cv.put(COL_ALAMAT, bengkel.getAlamat());
            cv.put(COL_JENISKELAMIN, bengkel.getJenisKelamin());
            cv.put(COL_KEAHLIAN, bengkel.getKeahlian());
            String whereClause = COL_ID + "==?";
            String whereArgs[] = new String[] { bengkel.getId() };
            sqliteDatabase.update(TABLE_NAME, cv, whereClause, whereArgs);
            sqliteDatabase.close();
        }

        public void save(Bengkel bengkel) {
            sqliteDatabase = getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_NAMA, bengkel.getNama());
            contentValues.put(COL_LAHIR, bengkel.getLahir());
            contentValues.put(COL_ALAMAT, bengkel.getAlamat());
            contentValues.put(COL_JENISKELAMIN, bengkel.getJenisKelamin());
            contentValues.put(COL_KEAHLIAN, bengkel.getKeahlian());

            sqliteDatabase.insertWithOnConflict(TABLE_NAME, null,
                    contentValues, SQLiteDatabase.CONFLICT_IGNORE);

            sqliteDatabase.close();
        }

        public void delete(Bengkel bengkel) {
            sqliteDatabase = getWritableDatabase();
            String whereClause = COL_ID + "==?";
            String[] whereArgs = new String[] { String.valueOf(bengkel.getId()) };
            sqliteDatabase.delete(TABLE_NAME, whereClause, whereArgs);
            sqliteDatabase.close();
        }

        public void deleteAll() {
            sqliteDatabase = getWritableDatabase();
            sqliteDatabase.delete(TABLE_NAME, null, null);
            sqliteDatabase.close();
        }

        public List<Bengkel> getAllBengkel() {
            sqliteDatabase = getWritableDatabase();

            Cursor cursor = this.sqliteDatabase.query(TABLE_NAME, new String[] {
                    COL_ID, COL_NAMA, COL_LAHIR, COL_ALAMAT, COL_JENISKELAMIN, COL_KEAHLIAN }, null, null, null, null, null);
            List<Bengkel> bengkels = new ArrayList<Bengkel>();

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Bengkel bengkel = new Bengkel();
                    bengkel.setId(cursor.getString(cursor.getColumnIndex(COL_ID)));
                    bengkel.setNama(cursor.getString(cursor
                            .getColumnIndex(COL_NAMA)));
                    bengkel.setLahir(cursor.getString(cursor
                            .getColumnIndex(COL_LAHIR)));
                    bengkel.setAlamat(cursor.getString(cursor
                            .getColumnIndex(COL_ALAMAT)));
                    bengkel.setJenisKelamin(cursor.getString(cursor
                            .getColumnIndex(COL_JENISKELAMIN)));
                    bengkel.setKeahlian(cursor.getString(cursor
                            .getColumnIndex(COL_KEAHLIAN)));
                    bengkels.add(bengkel);
                }
                sqliteDatabase.close();
                return bengkels;
            } else {
                sqliteDatabase.close();
                return new ArrayList<Bengkel>();
            }
        }
    }

}
