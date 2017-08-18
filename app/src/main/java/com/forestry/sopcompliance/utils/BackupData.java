package com.forestry.sopcompliance.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.forestry.sopcompliance.data.local.dao.HotspotVerificationTableDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by fimansya on 5/23/2017.
 */

public class BackupData {

    public static String DB_FILEPATH = "/data/data/{package_name}/databases/database.db";


    public static void writeToSD(Context context){
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            String packageName = context.getPackageName();

            if (sd.canWrite()) {

                //String currentDBPath = "\\data\\your.package.name\\databases\\dabase_name";
                //String backupDBPath = "database_name";


                String currentDBPath = "//data//"+ packageName +"//databases//"+"//fros.db//";
                String backupDBPath = "//fros.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(context, backupDB.toString(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Copies the database file at the specified location over the current
     * internal application database.
     * */
    public boolean importDatabase(String dbPath, HotspotVerificationTableDAO database) throws IOException {

        // Close the SQLiteOpenHelper so it will commit the created empty
        // database to internal storage.
        database.close();
        File newDb = new File(dbPath);
        File oldDb = new File(DB_FILEPATH);
        if (newDb.exists()) {
            copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
            // Access the copied database so SQLiteHelper will cache it and mark
            // it as created.
            //getWritableDatabase().close();
            database.close();
            return true;
        }
        return false;
    }

        /**
         * Creates the specified <code>toFile</code> as a byte for byte copy of the
         * <code>fromFile</code>. If <code>toFile</code> already exists, then it
         * will be replaced with a copy of <code>fromFile</code>. The name and path
         * of <code>toFile</code> will be that of <code>toFile</code>.<br/>
         * <br/>
         * <i> Note: <code>fromFile</code> and <code>toFile</code> will be closed by
         * this function.</i>
         *
         * @param fromFile
         *            - FileInputStream for the file to copy from.
         * @param toFile
         *            - FileInputStream for the file to copy to.
         */
        public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
            FileChannel fromChannel = null;
            FileChannel toChannel = null;
            try {
                fromChannel = fromFile.getChannel();
                toChannel = toFile.getChannel();
                fromChannel.transferTo(0, fromChannel.size(), toChannel);
            } finally {
                try {
                    if (fromChannel != null) {
                        fromChannel.close();
                    }
                } finally {
                    if (toChannel != null) {
                        toChannel.close();
                    }
                }
            }
        }


    public static void copyAppDbToDownloadFolder(Context context) throws IOException {
        try {
            File backupDB = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "toDatabaseName"); // for example "my_data_backup.db"
            File currentDB = context.getDatabasePath("databaseName"); //databaseName=your current application database name, for example "my_data.db"
            if (currentDB.exists()) {
                FileInputStream fis = new FileInputStream(currentDB);
                FileOutputStream fos = new FileOutputStream(backupDB);
                fos.getChannel().transferFrom(fis.getChannel(), 0, fis.getChannel().size());
                // or fis.getChannel().transferTo(0, fis.getChannel().size(), fos.getChannel());
                fis.close();
                fos.close();
                Log.i("Database successfully", " copied to download folder");
                //return true;
            } else Log.i("Copying Database", " fail, database not found");
        } catch (IOException e) {
            Log.d("Copying Database", "fail, reason:", e);
        }
    }

    public static void saveToSDCards(final Activity activity, final Context context){
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5);
            }
            else{
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},5);
            }
        }
        else{
            final File backupDb=new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"<Folder Name>"+File.separator+"<Backup Database Name>");
            final File currentDB = new File(String.valueOf(context.getDatabasePath("<Your Database Name>")));
            if(backupDb.exists()){
                AlertDialog.Builder builder=new AlertDialog.Builder(activity)
                        .setTitle("Alert")
                        .setMessage("File already exists.Do you want to replace it")
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(!new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"<Folder Name>").canWrite()) {
                                    Toast.makeText(activity, "Unable to write into external storage", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    if(!currentDB.exists()){
                                        Toast.makeText(activity, "Database doesn't exists", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        try {
                                            FileChannel src = new FileInputStream(currentDB).getChannel();
                                            FileChannel dst = new FileOutputStream(backupDb).getChannel();
                                            dst.transferFrom(src, 0, src.size());
                                            src.close();
                                            dst.close();
                                            MediaScannerConnection.scanFile(context, new String[]{backupDb.toString()}, null, null);
                                            Toast.makeText(activity, "Database successfully copied to external storage", Toast.LENGTH_SHORT).show();
                                        }catch(Exception e){
                                            Toast.makeText(activity, "Got exception" + e, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(activity, "Please enter another name", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"<Folder Name>").mkdir();
                if(!new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"<Folder Name>").canWrite()) {
                    Toast.makeText(activity, "Unable to write into external storage", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!currentDB.exists()){
                        Toast.makeText(activity, "Database doesn't exists", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        try {
                            FileChannel src = new FileInputStream(currentDB).getChannel();
                            FileChannel dst = new FileOutputStream(backupDb).getChannel();
                            dst.transferFrom(src, 0, src.size());
                            src.close();
                            dst.close();
                            MediaScannerConnection.scanFile(context, new String[]{backupDb.toString()}, null, null);
                            Toast.makeText(activity, "Database successfully copied to external storage", Toast.LENGTH_SHORT).show();
                        }catch(Exception e){
                            Toast.makeText(activity, "Got exception" + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }



    public static void exportDBtoSD(Context context){
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "com.gkm.frosmobile" +"/databases/fros.db";
        String backupDBPath = "fros.db";
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(context, "DB Exported!", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}
