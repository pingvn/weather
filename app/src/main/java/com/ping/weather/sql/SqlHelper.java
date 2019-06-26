package com.ping.weather.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.FileObserver;

import com.ping.weather.datamodel.City;
import com.ping.weather.datamodel.CitySqlData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SqlHelper extends SQLiteOpenHelper implements SqlInterface {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "city.db";
    private static final String TABLE_CITY = "city";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COUNTRU = "country";

    private static String mDataBasePatch = "";
    private Context mContext;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_CITY + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_COUNTRU + " TEXT" + ")";


    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (Build.VERSION.SDK_INT >= 17) {
            mDataBasePatch = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            mDataBasePatch = "/data/data/" + context.getPackageName() + "/databases";
        }
        mContext = context;

        copyDataBase();
    }

    private boolean checkDataBase() {
        File mDbFile = new File(mDataBasePatch + DATABASE_NAME);
        return mDbFile.exists();
    }

    private void copyDbFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DATABASE_NAME);
        OutputStream mOutStream = new FileOutputStream(mDataBasePatch + DATABASE_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutStream.write(mBuffer,0,mLength);
        }
        mOutStream.flush();
        mOutStream.close();
        mInput.close();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            getReadableDatabase();
            close();
            try {
                copyDbFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
        onCreate(db);

    }

    @Override
    public void addCity(City mCity) {
        SQLiteDatabase mDataBase = this.getWritableDatabase();
        ContentValues mCityContent = new ContentValues();
        mCityContent.put(KEY_NAME, mCity.getName());
        mCityContent.put(KEY_COUNTRU, mCity.getCountry());

        mDataBase.insert(TABLE_CITY, null, mCityContent);
        mDataBase.close();
    }

    @Override
    public List<CitySqlData> getCity(String mNameCity) {
        SQLiteDatabase mDataBAse = this.getReadableDatabase();
        List<CitySqlData> mCityList = new ArrayList<>();
        String mSelectQuery = "SELECT " + KEY_NAME + " , " + KEY_COUNTRU + " FROM " + TABLE_CITY + " WHERE " + KEY_NAME + " LIKE '" + mNameCity + "%'";
        Cursor mResponeSelect = mDataBAse.rawQuery(mSelectQuery, null);
        if (mResponeSelect.moveToFirst()) {
            do {
                CitySqlData mCity = new CitySqlData();
                mCity.setmName(mResponeSelect.getString(0));
                mCity.setmCountry(mResponeSelect.getString(1));
                mCityList.add(mCity);
            } while (mResponeSelect.moveToNext());
        }
        return mCityList;
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase mDataBase = this.getWritableDatabase();
        mDataBase.delete(TABLE_CITY, null, null);
        mDataBase.close();
    }
}
