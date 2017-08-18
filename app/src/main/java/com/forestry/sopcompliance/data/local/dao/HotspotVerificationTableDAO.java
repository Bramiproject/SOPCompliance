package com.forestry.sopcompliance.data.local.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.forestry.sopcompliance.data.local.hotspot.FrosDatabaseHelper;
import com.forestry.sopcompliance.data.model.Hotspot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fimansya on 5/24/2017.
 */

public class HotspotVerificationTableDAO {
    // Database fields
    private SQLiteDatabase database;
    private FrosDatabaseHelper dbHelper;

    /**
     * This construction creates the database help object that creates the planet database and table
     * @param context - The activity calling this.
     */
    public HotspotVerificationTableDAO(Context context) {
        dbHelper = FrosDatabaseHelper.getInstance(context);
    }

    /**
     * Opens up the planet table on the database and gets a link to the SQLite database
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Closes the SQLite database
     */
    public void close() {
        dbHelper.close();
    }


    public Hotspot createHotspot(Hotspot hotspot) {
        // store new values into a ContentValues object
        ContentValues values = new ContentValues();
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_HS_ID, hotspot.getID());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_LONGITUDE, hotspot.getLongitude());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_LATITUDE, hotspot.getLatitude());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_HTDATE, hotspot.getHTDate());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_SOURCE, hotspot.getSource());

        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_AGENCY, hotspot.getAgency());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_ADAAPI, hotspot.getAdaApi());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_REMARKS, "");
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_COMPANY_CODE, hotspot.getCompanyCode());

        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_COMPANY, hotspot.getCompany());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_DISTRICT_CODE, hotspot.getDistrictCode());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_DISTRICT, hotspot.getDistrict());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_KEYID, hotspot.getKEYID());

        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_ROWNO, hotspot.getRowNo());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_VER_DATE, hotspot.getVerificationDate());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_ISFIRE_EXIST, hotspot.getIsFireExist());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_DESA, hotspot.getDesa());

        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_KECAMATAN, hotspot.getKecamatan());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_KABUPATEN, hotspot.getKabupaten());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_HTTIME, hotspot.getHTTime());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_HOTSPOT, hotspot.getHotspot());

        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_LOCATION_ID, hotspot.getLocationId());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_BU, hotspot.getBu());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_FIRESOURCE, hotspot.getFireSource());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_FULLNAME, hotspot.getFullname());

        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_VEGETATION, hotspot.getVegetation());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_FIREAREASIZE, hotspot.getFireAreaSize());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_FIRETREATMENT, hotspot.getFireTreatment());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_FIRE_DESCSTATUS, hotspot.getFireDescStatus());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_IMAGES, hotspot.getImages());
        values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_VERIFICATION, "false");

        //insert(String table, String nullColumnHack, ContentValues values)
        long insertId = database.insert(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION, null, values);
        Hotspot newHotspot = new Hotspot((int) insertId, hotspot.getID(), hotspot.getLongitude(), hotspot.getLatitude(), hotspot.getHTDate(),
                hotspot.getSource(), hotspot.getAgency(), hotspot.getAdaApi(), hotspot.getRemarks(), hotspot.getCompanyCode(), hotspot.getCompany(),
                hotspot.getDistrictCode(), hotspot.getDistrict(), hotspot.getKEYID(), hotspot.getRowNo(), hotspot.getVerificationDate(), hotspot.getIsFireExist(),
                hotspot.getDesa(), hotspot.getKecamatan(), hotspot.getKabupaten(), hotspot.getHTTime(), hotspot.getHotspot(), hotspot.getLocationId(), hotspot.getBu(),
                hotspot.getFireSource(), hotspot.getFullname(), hotspot.getVegetation(), hotspot.getFireAreaSize(), hotspot.getFireTreatment(),
                hotspot.getFireDescStatus(), hotspot.getImages(), "false");
        return newHotspot;
    }

    /**
     * Get an list of all the planets in the database
     * The R in CRUD for Reading database records
     * @return the list of planet objects
     */
    public List<Hotspot> getAllHotspots() {
        List<Hotspot> hotspots = new ArrayList<Hotspot>();
        // query the database for all the fields of all the records in the planet table
        // query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        Cursor cursor = database.query(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION,
                null, null, null, null, null, null);
        // loop through the cursor converting each row into a planet object
        while (cursor.moveToNext()) {
            Hotspot hotspot = cursorToHotspot(cursor);
            hotspots.add(hotspot);
        }
        // make sure to close the cursor
        cursor.close();
        return hotspots;
    }

    public List<Hotspot> getAllVerifiedHotspots() {
        List<Hotspot> hotspots = new ArrayList<Hotspot>();
        // query the database for all the fields of all the records in the planet table
        // query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        Cursor cursor = database.query(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION,
                null,
                FrosDatabaseHelper.DB_HS_VER_FIELD_VERIFICATION + " = ?",
                new String[] { "true" } ,
                null, null, null);
        // loop through the cursor converting each row into a planet object
        while (cursor.moveToNext()) {
            Hotspot hotspot = cursorToHotspot(cursor);
            hotspots.add(hotspot);
        }
        // make sure to close the cursor
        cursor.close();
        return hotspots;
    }

    public List<Hotspot> getAllUnVerifiedHotspots() {
        List<Hotspot> hotspots = new ArrayList<Hotspot>();
        // query the database for all the fields of all the records in the planet table
        // query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        Cursor cursor = database.query(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION,
                null,
                FrosDatabaseHelper.DB_HS_VER_FIELD_VERIFICATION + " = ?",
                new String[] { "false" } ,
                null, null, null);
        // loop through the cursor converting each row into a planet object
        while (cursor.moveToNext()) {
            Hotspot hotspot = cursorToHotspot(cursor);
            hotspots.add(hotspot);
        }
        // make sure to close the cursor
        cursor.close();
        return hotspots;
    }


    public Hotspot getOneHotspot(String hotspot_id) {
        // create a null planet to return if one is not found
        Hotspot hotspot = new Hotspot(1,"kosong","", "", -0.972, 101.897, "false");
        // query the database for all the fields of all the records in the planet table
        // query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        Cursor cursor = database.query(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION,
                null,                                           /* all collumns */
                FrosDatabaseHelper.DB_HS_VER_FIELD_ID + " = ?",    /* where clause "title = ?" */
                new String[] { hotspot_id } ,                         /* where parameters is an array of Strings including only the name */
                null, null, null);
        // check if we found a record
        if (cursor.getCount() >0) {
            cursor.moveToFirst();
            hotspot = cursorToHotspot(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        return hotspot;
    }

    /**
     * Convert the current record the cursor points to into a planet object
     * @param cursor - points to a record in the planet table of the SQLite database
     * @return  a planet object
     */

    private Hotspot cursorToHotspot(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_ID));
        int ID = cursor.getInt(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_HS_ID));
        double lng = cursor.getDouble(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_LONGITUDE));
        double lat = cursor.getDouble(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_LATITUDE));
        String htDate = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_HTDATE));
        String source = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_SOURCE));
        String agency = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_AGENCY));
        String adaApi = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_ADAAPI));
        String remarks = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_REMARKS));
        String companyCode = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_COMPANY_CODE));
        String company = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_COMPANY));
        String districtCode = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_DISTRICT_CODE));
        String district = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_DISTRICT));
        String keyID = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_KEYID));
        int rowNo = cursor.getInt(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_ROWNO));
        String verificationDate = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_VER_DATE));
        int isFireExist = cursor.getInt(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_ISFIRE_EXIST));
        String ds = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_DESA));
        String kec = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_KECAMATAN));
        String kab = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_KABUPATEN));
        String htTime = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_HTTIME));
        String hs = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_HOTSPOT));
        String locId = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_LOCATION_ID));
        String bu = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_BU));
        String fireSource = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_FIRESOURCE));
        String fullName = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_FULLNAME));
        String vegetation = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_VEGETATION));
        String fireAreaSize = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_FIREAREASIZE));
        String fireTreatment = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_FIRETREATMENT));
        String fireDescStatus = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_FIRE_DESCSTATUS));
        String images = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_IMAGES));
        String status = cursor.getString(cursor.getColumnIndex(FrosDatabaseHelper.DB_HS_VER_FIELD_VERIFICATION));
        Hotspot hotspot = new Hotspot(id,ID, lng, lat, htDate, source, agency, adaApi, remarks, companyCode, company,
                districtCode, district, keyID, rowNo, verificationDate, isFireExist, ds, kec, kab, htTime, hs, locId,
                bu, fireSource, fullName, vegetation, fireAreaSize, fireTreatment, fireDescStatus, images, status);
        return hotspot;
    }


    public int updateHotspot(Hotspot hotspot, String fullname, String remarks, int isFireExist, String imagePaths, statusInfo callback) {

        if (hotspot != null) {

            // store new values into a ContentValues object
            ContentValues values = new ContentValues();
            values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_REMARKS, remarks);
            values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_ISFIRE_EXIST, isFireExist);
            values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_FULLNAME, fullname);

            /*for (int i = 0; i < imagePaths.size() ; i++) {
                int j = i + 1;
                values.put("imagepaths" + j, imagePaths.get(i));
            }*/
            values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_IMAGES, imagePaths);
            values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_VERIFICATION, "true");

            // need an String array with the planet id in it
            String[] strId = new String[] { String.valueOf(hotspot.get_id()) };
            callback.Success();
            // update(String table, ContentValues values, String whereClause, String[] whereArgs)
            return database.update(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION, values, FrosDatabaseHelper.DB_HS_VER_FIELD_ID + " = ?", strId);

        }
        callback.Failed();
        return 0;
    }

    public interface statusInfo{
        void Success();
        void Failed();
    }

    public int saveImages(Hotspot hotspot, String imagePaths) {

        if (hotspot != null){
            // store new values into a ContentValues object
            ContentValues values = new ContentValues();
            values.put(FrosDatabaseHelper.DB_HS_VER_FIELD_IMAGES, imagePaths);

            // need an String array with the planet id in it
            String[] strId = new String[] { String.valueOf(hotspot.get_id()) };
            // update(String table, ContentValues values, String whereClause, String[] whereArgs)
            return database.update(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION, values, FrosDatabaseHelper.DB_HS_VER_FIELD_ID + " = ?", strId);
        }
        return 0;
    }


    public int deleteHotspot(Hotspot hotspot) {
        // need an String array with the planet id in it
        String[] strId = new String[] { String.valueOf(hotspot.get_id()) };
        //delete(String table, String whereClause, String[] whereArgs)
        return database.delete(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION, FrosDatabaseHelper.DB_HS_VER_FIELD_ID + " = ?", strId);
    }

  /*  public int deleteHotspot(Hotspot hotspot) {
        // need an String array with the planet id in it
        //String[] strId = new String[] { hotspot.get_id().toString() };
        //delete(String table, String whereClause, String[] whereArgs)
        return database.delete(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION, FrosDatabaseHelper.DB_HS_VER_FIELD_HS_ID + "=?" ,  new String[] { String.valueOf(hotspot.getID()) });
    }*/


    public void deleteAllHotspots() {
        database.delete(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION, null, null);
    }


    public boolean hasObject(String hotspot_id) {
        // query the database for all the fields of all the records in the planet table
        // query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        Cursor cursor = database.query(FrosDatabaseHelper.DB_TABLE_HOTSPOT_VERIFICATION,
                null,                                           /* all collumns */
                FrosDatabaseHelper.DB_HS_VER_FIELD_ID + " = ?",    /* where clause "title = ?" */
                new String[] { hotspot_id } ,                         /* where parameters is an array of Strings including only the name */
                null, null, null);
        boolean hasObject = false;

        if(cursor.moveToFirst()){
            hasObject = true;
            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
        }
        // make sure to close the cursor
        cursor.close();
        return hasObject;
    }

}