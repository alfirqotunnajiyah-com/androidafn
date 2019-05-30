package com.afn.afnapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.afn.afnapp.model.AyahModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AyatDataHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "db_alquran.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_TABLE_NAME = "AyahTable";
    private static final String KEY_DATABASEID = "DatabaseID";
    private static final String KEY_SURAHID = "SurahID";
    private static final String KEY_AYAHID = "AyahID";
    private static final String KEY_AYAHTEXT = "AyahText";
    private static final String KEY_ARTITEXT = "ArtiText";

    public AyatDataHelper(Context context1) {
        super(context1, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    public boolean apakahNull() {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + KEY_TABLE_NAME;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        db.close();
        if (mcursor.getInt(0) > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void tambahAyat(AyahModel am) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SURAHID, am.getSurahId());
        values.put(KEY_AYAHID, am.getNoAyah());
        values.put(KEY_AYAHTEXT, am.getAyah());
        values.put(KEY_ARTITEXT, am.getAyahTranslate());
        db.insert(KEY_TABLE_NAME, null, values);
        db.close();
    }

    public List<AyahModel> getAyahList() {
        List<AyahModel> data = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + KEY_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                AyahModel u = new AyahModel();

                u.setSurahId(cursor.getInt(1));
                u.setNoAyah(cursor.getInt(2));
                u.setAyah(cursor.getString(3));
                u.setAyahTranslate(cursor.getString(4));

                data.add(u);
                cursor.moveToNext();
            }
        }

        db.close();
        return data;
    }

    public List<AyahModel> getAyahListFromSurah(int surahId) {
        List<AyahModel> data = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + KEY_TABLE_NAME + " WHERE SurahID =" + surahId, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                AyahModel u = new AyahModel();

                u.setSurahId(cursor.getInt(1));
                u.setNoAyah(cursor.getInt(2));
                u.setAyah(cursor.getString(3));
                u.setAyahTranslate(cursor.getString(4));

                data.add(u);
                cursor.moveToNext();
            }
        }

        db.close();
        return data;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS " + KEY_TABLE_NAME);
        onCreate(arg0);
    }
}
