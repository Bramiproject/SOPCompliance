package com.forestry.sopcompliance.data.local.hotspot;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by fimansya on 5/10/2017.
 */

public class FrosDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="fros.db";
    private static final int SCHEMA=1;

    // TABLE HOTSPOT VERIFICATION
    public static final String DB_TABLE_HOTSPOT_VERIFICATION="hotspot_verification";
    public static final String DB_HS_VER_FIELD_ID ="_id";
    public static final String DB_HS_VER_FIELD_HS_ID ="ID";
    public static final String DB_HS_VER_FIELD_LONGITUDE ="XCoordinate";
    public static final String DB_HS_VER_FIELD_LATITUDE ="YCoordinate";
    public static final String DB_HS_VER_FIELD_HTDATE ="HTDate";
    public static final String DB_HS_VER_FIELD_SOURCE ="source";
    public static final String DB_HS_VER_FIELD_AGENCY ="Agency";
    public static final String DB_HS_VER_FIELD_ADAAPI ="AdaApi";
    public static final String DB_HS_VER_FIELD_REMARKS ="Remarks";
    public static final String DB_HS_VER_FIELD_COMPANY ="company";
    public static final String DB_HS_VER_FIELD_COMPANY_CODE ="companyCode";
    public static final String DB_HS_VER_FIELD_DISTRICT ="district";
    public static final String DB_HS_VER_FIELD_DISTRICT_CODE ="districtCode";
    public static final String DB_HS_VER_FIELD_KEYID ="KEYID";
    public static final String DB_HS_VER_FIELD_ROWNO ="RowNo";
    public static final String DB_HS_VER_FIELD_VER_DATE ="VerificationDate";
    public static final String DB_HS_VER_FIELD_ISFIRE_EXIST ="isFireExist";
    public static final String DB_HS_VER_FIELD_DESA ="Desa";
    public static final String DB_HS_VER_FIELD_KECAMATAN ="Kecamatan";
    public static final String DB_HS_VER_FIELD_KABUPATEN ="Kabupaten";
    public static final String DB_HS_VER_FIELD_HTTIME ="HTTime";
    public static final String DB_HS_VER_FIELD_HOTSPOT ="Hotspot";
    public static final String DB_HS_VER_FIELD_LOCATION_ID ="LocationId";
    public static final String DB_HS_VER_FIELD_BU ="Bu";
    public static final String DB_HS_VER_FIELD_FIRESOURCE ="FireSource";
    public static final String DB_HS_VER_FIELD_FULLNAME ="Fullname";
    public static final String DB_HS_VER_FIELD_VEGETATION ="Vegetation";
    public static final String DB_HS_VER_FIELD_FIREAREASIZE ="FireAreaSize";
    public static final String DB_HS_VER_FIELD_FIRETREATMENT ="FireTreatment";
    public static final String DB_HS_VER_FIELD_COORD_LATITUDE ="Latitude";
    public static final String DB_HS_VER_FIELD_COORD_LONGITUDE ="Longitude";
    public static final String DB_HS_VER_FIELD_FIRE_DESCSTATUS ="FireDescStatus";
    public static final String DB_HS_VER_FIELD_IMAGES ="imagepaths";
    public static final String DB_HS_VER_FIELD_VERIFICATION ="verification";


    private static FrosDatabaseHelper singltonInstance;



    // Database creation sql statement
    private static final String DATABASE_CREATE_SQL_HOTSPOT_VERIFICATION = "create table "+ DB_TABLE_HOTSPOT_VERIFICATION
            + " ( "+DB_HS_VER_FIELD_ID+" INTEGER PRIMARY KEY, "
            + DB_HS_VER_FIELD_HS_ID +" INTEGER, "
            + DB_HS_VER_FIELD_LONGITUDE +" DOUBLE, "
            + DB_HS_VER_FIELD_LATITUDE +" DOUBLE, "
            + DB_HS_VER_FIELD_HTDATE + " TEXT, "
            + DB_HS_VER_FIELD_SOURCE + " TEXT, "
            + DB_HS_VER_FIELD_AGENCY +" TEXT, "
            + DB_HS_VER_FIELD_ADAAPI +" TEXT, "
            + DB_HS_VER_FIELD_REMARKS +" TEXT, "
            + DB_HS_VER_FIELD_COMPANY_CODE + " TEXT, "
            + DB_HS_VER_FIELD_COMPANY + " TEXT, "
            + DB_HS_VER_FIELD_DISTRICT_CODE + " TEXT, "
            + DB_HS_VER_FIELD_DISTRICT + " TEXT, "
            + DB_HS_VER_FIELD_KEYID +" TEXT, "
            + DB_HS_VER_FIELD_ROWNO +" INTEGER, "
            + DB_HS_VER_FIELD_VER_DATE +" TEXT, "
            + DB_HS_VER_FIELD_ISFIRE_EXIST + " INTEGER, "
            + DB_HS_VER_FIELD_DESA + " TEXT, "
            + DB_HS_VER_FIELD_KECAMATAN +" TEXT, "
            + DB_HS_VER_FIELD_KABUPATEN +" TEXT, "
            + DB_HS_VER_FIELD_HTTIME +" TEXT, "
            + DB_HS_VER_FIELD_HOTSPOT + " TEXT, "
            + DB_HS_VER_FIELD_LOCATION_ID + " TEXT, "
            + DB_HS_VER_FIELD_BU +" TEXT, "
            + DB_HS_VER_FIELD_FIRESOURCE +" TEXT, "
            + DB_HS_VER_FIELD_FULLNAME + " TEXT, "
            + DB_HS_VER_FIELD_VEGETATION + " TEXT, "
            + DB_HS_VER_FIELD_FIREAREASIZE +" TEXT, "
            + DB_HS_VER_FIELD_FIRETREATMENT +" TEXT, "
            + DB_HS_VER_FIELD_FIRE_DESCSTATUS + " TEXT, "
            + DB_HS_VER_FIELD_IMAGES + " TEXT, "
            + DB_HS_VER_FIELD_VERIFICATION + " TEXT);";


    // do not call this anymore
    public FrosDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    public static synchronized FrosDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (singltonInstance == null) {
            singltonInstance = new FrosDatabaseHelper(context.getApplicationContext());
        }
        return singltonInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_SQL_HOTSPOT_VERIFICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(FrosDatabaseHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_HOTSPOT_VERIFICATION);
        onCreate(db);
    }


}
