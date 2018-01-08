package com.example.dell.socialsift.utils.commonUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dell.socialsift.beans.LoginBean;
import com.example.dell.socialsift.beans.masterBean.Category;
import com.example.dell.socialsift.beans.masterBean.City;
import com.example.dell.socialsift.beans.masterBean.Country;
import com.example.dell.socialsift.beans.masterBean.State;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Replete Android on 1/11/2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private SingletonUtil singletonUtilObj = SingletonUtil.getSingletonConfigInstance();

    // Database Name
    private static final String DATABASE_NAME = "SiftDatabase";
    //Table Name
    private final String TABLE_TOKEN = "tokenTable";
    private final String TABLE_COUNTRY = "countryTable";
    private final String TABLE_STATE = "stateTable";
    private final String TABLE_CITY = "cityTable";
    private final String TABLE_CATEGORY = "categoryTable";

    //field of token table
    private final String TOKEN_ID = "tokenId";
    private final String REFRESH_TOKEN_NAME = "refreshTokenName";
    private final String TOKEN_TYPE = "tokenType";
    private final String ACCESS_TOKEN_NAME = "accessTokenName";


    //fields of country table
    private final String COUNTRY_ID = "countryId";
    private final String COUNTRY_NAME = "countryName";
    private final String COUNTRY_UID = "countryUid";

    //fields of state table
    private final String STATE_ID = "stateId";
    private final String STATE_NAME = "stateName";
    private final String STATE_UID = "stateUid";
    private final String STATE_COUNTRY_ID = "stateCountryUid";

    //fields of city table
    private final String CITY_ID = "cityId";
    private final String CITY_NAME = "cityName";
    private final String CITY_UID = "cityUid";
    private final String CITY_STATE_ID = "cityStateUid";


    //fields of category table
    private final String CATEGORY_ID = "categoryId";
    private final String CATEGORY_NAME = "categoryName";
    private final String CATEGORY_UID = "categoryUid";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE_TOKEN = "CREATE TABLE " + TABLE_TOKEN + "("
                + TOKEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                REFRESH_TOKEN_NAME + " TEXT ," +
                TOKEN_TYPE + " TEXT ," +
                ACCESS_TOKEN_NAME + " TEXT ) ";
        Log.d("SQLiteHandler", CREATE_TABLE_TOKEN);
        db.execSQL(CREATE_TABLE_TOKEN);


        String CREATE_TABLE_COUNTRY = "CREATE TABLE " + TABLE_COUNTRY + "("
                + COUNTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COUNTRY_NAME + " TEXT ," +
                COUNTRY_UID + " TEXT ) ";
        Log.d("SQLiteHandler", CREATE_TABLE_COUNTRY);
        db.execSQL(CREATE_TABLE_COUNTRY);


        String CREATE_TABLE_STATE = "CREATE TABLE " + TABLE_STATE + "("
                + STATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                STATE_NAME + " TEXT ," +
                STATE_COUNTRY_ID + " TEXT ," +
                STATE_UID + " TEXT ) ";
        Log.d("SQLiteHandler", CREATE_TABLE_STATE);
        db.execSQL(CREATE_TABLE_STATE);


        String CREATE_TABLE_CITY = "CREATE TABLE " + TABLE_CITY + "("
                + CITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CITY_NAME + " TEXT ," +
                CITY_STATE_ID + " TEXT ," +
                CITY_UID + " TEXT ) ";
        Log.d("SQLiteHandler", CREATE_TABLE_CITY);
        db.execSQL(CREATE_TABLE_CITY);


        String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + "("
                + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CATEGORY_NAME + " TEXT ," +
                CATEGORY_UID + " TEXT ) ";
        Log.d("SQLiteHandler", CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_CATEGORY);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOKEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        // Create tables again
        onCreate(db);
    }


    public void addTokenInfo(LoginBean bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(REFRESH_TOKEN_NAME, bean.getRefreshToken());
        values.put(TOKEN_TYPE, bean.getTokenType());
        values.put(ACCESS_TOKEN_NAME, bean.getAccessToken());
        db.insert(TABLE_TOKEN, null, values);
        db.close(); // Closing database connection
    }

    public boolean updateTokennfo(String tokenType, String accessToken, String refreshToken) {
        boolean status;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        System.out.println("IN UPDATE TABEL");
        values.put(TOKEN_TYPE, tokenType);
        values.put(ACCESS_TOKEN_NAME, accessToken);
        values.put(REFRESH_TOKEN_NAME, refreshToken);

        db.update(TABLE_TOKEN, values, "tokenId=" + 1, null);

        long affectedRow = db.insert(TABLE_TOKEN, null, values);
        db.close(); // Closing database connection
        if (affectedRow > 1) {
            System.out.println("IN TRUE  " + affectedRow);
            return true;
        } else {
            System.out.println("IN FALSE " + affectedRow);
            return false;
        }

    }


    public void addCountryInfo(Country bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COUNTRY_NAME, bean.getName());
        values.put(COUNTRY_UID, bean.getId());

        db.insert(TABLE_COUNTRY, null, values);
        db.close(); // Closing database connection
    }


    public void addStateInfo(State bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(STATE_NAME, bean.getName());
        values.put(STATE_COUNTRY_ID, bean.getCountryDtlId());
        values.put(STATE_UID, bean.getId());

        db.insert(TABLE_STATE, null, values);
        db.close(); // Closing database connection
    }


    public void addCityInfo(City bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CITY_NAME, bean.getName());
        values.put(CITY_STATE_ID, bean.getStateDtlId());
        values.put(CITY_UID, bean.getId());
        db.insert(TABLE_CITY, null, values);
        db.close(); // Closing database connection
    }

    public void addCategoryInfo(Category bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CATEGORY_NAME, bean.getName());
        values.put(CATEGORY_UID, bean.getId());


        db.insert(TABLE_CATEGORY, null, values);
        db.close(); // Closing database connection
    }

    public LoginBean getTokenInfo() {
        LoginBean bean = null;
        String selectQuery = "SELECT  * FROM " + TABLE_TOKEN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                bean = new LoginBean(cursor.getString(3),
                        cursor.getString(2), cursor.getString(1));

            } while (cursor.moveToNext());
        }

        // return contact list
        return bean;
    }


    public boolean updateTokenInfo(String tokenType, String accessToken) {
        boolean status;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        System.out.println("IN UPDATE TABEL");
        values.put(TOKEN_TYPE, tokenType);
        values.put(ACCESS_TOKEN_NAME, accessToken);

        db.update(TABLE_TOKEN, values, "tokenId=" + 1, null);

        long affectedRow = db.insert(TABLE_TOKEN, null, values);
        db.close(); // Closing database connection
        if (affectedRow > 1) {
            System.out.println("IN TRUE  " + affectedRow);
            return true;
        } else {
            System.out.println("IN FALSE " + affectedRow);
            return false;
        }

    }


    public List<Country> getCountryList() {
        List<Country> countryList = new ArrayList<Country>();
        String selectQuery = "SELECT  * FROM " + TABLE_COUNTRY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Country countryMaster = new Country(cursor.getString(0),
                        cursor.getString(1)
                );
                countryList.add(countryMaster);
            } while (cursor.moveToNext());
        }

        // return contact list
        return countryList;
    }

    public String getCountryID(String country) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COUNTRY,
                new String[]{COUNTRY_UID},
                COUNTRY_NAME + "=?",
                new String[]{String.valueOf(country)}, null, null, null, null);
        String id = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getString(0);

                } while (cursor.moveToNext());
            }

        }
        return id;
    }


    public String getCountryName(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COUNTRY,
                new String[]{COUNTRY_NAME},
                COUNTRY_UID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        String name = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(0);

                } while (cursor.moveToNext());
            }

        }
        return name;
    }


    public List<State> getStateByCountry(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STATE,
                new String[]{STATE_NAME, STATE_UID},
                STATE_COUNTRY_ID + "=?",
                new String[]{id}, null, null, null, null);
        ArrayList<State> stateList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    State stateMaster = new State(cursor.getString(0),
                            cursor.getString(1));


                    // Adding contact to list
                    stateList.add(stateMaster);


                } while (cursor.moveToNext());
            }
//            Log.d(TAG, "categoryTemplateList: " + categoryTemplateList);
        }
        return stateList;
    }


    public String getStateID(String state) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STATE,
                new String[]{STATE_UID},
                STATE_NAME + "=?",
                new String[]{String.valueOf(state)}, null, null, null, null);
        String id = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
//                    Log.d(TAG, "getCategoryI: " + cursor.getInt(0) + "  " + template + "  " + cursor.getString(1));
                    id = cursor.getString(0);

                } while (cursor.moveToNext());
            }

        }
        return id;
    }

    public String getStateName(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STATE,
                new String[]{STATE_NAME},
                STATE_UID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        String name = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(0);

                } while (cursor.moveToNext());
            }

        }
        return name;
    }


    public List<City> getCityByState(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CITY,
                new String[]{CITY_NAME, CITY_UID},
                CITY_STATE_ID + "=?",
                new String[]{id}, null, null, null, null);
        ArrayList<City> cityList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    City cityMaster = new City(cursor.getString(0),
                            cursor.getString(1));

                    cityList.add(cityMaster);


                } while (cursor.moveToNext());
            }

        }
        return cityList;
    }

    public String getCityID(String city) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CITY,
                new String[]{CITY_UID},
                CITY_NAME + "=?",
                new String[]{String.valueOf(city)}, null, null, null, null);
        String id = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    id = cursor.getString(0);

                } while (cursor.moveToNext());
            }
        }
        return id;
    }

    public String getCityName(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CITY,
                new String[]{CITY_NAME},
                CITY_UID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        String name = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(0);

                } while (cursor.moveToNext());
            }

        }
        return name;
    }


    public List<String> getCategoryList() {
        List<String> categoryList = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Category categoryMaster = new Category(cursor.getString(0),
                        cursor.getString(1)
                );
                categoryList.add(categoryMaster.getName());
            } while (cursor.moveToNext());
        }

        // return contact list
        return categoryList;
    }


    public String getCategoryID(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CATEGORY,
                new String[]{CATEGORY_UID},
                CATEGORY_NAME + "=?",
                new String[]{String.valueOf(category)}, null, null, null, null);
        String id = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getString(0);

                } while (cursor.moveToNext());
            }

        }
        return id;
    }

    public int checkCountOfCity() {
        String countQuery = "SELECT * FROM " + TABLE_CITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor.getCount();
    }


    public void deleteTokenInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from " + TABLE_TOKEN);
    }

}

