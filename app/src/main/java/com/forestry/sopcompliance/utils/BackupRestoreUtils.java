package com.forestry.sopcompliance.utils;

/**
 * Created by fimansya on 6/20/2017.
 */

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fimansya on 6/13/2017.
 */

public class BackupRestoreUtils {

    public static boolean checkSrcFile(Context context) throws IOException {

        File dbFile = context.getDatabasePath("fros.db");
        String pathDatabaseSource = dbFile.toString();
        File inFile = new File(pathDatabaseSource);
        if (inFile.exists()){
            return true;
        }
        return false;
    }

    public static boolean checkDstFile(Context context) throws IOException {
        String inFileName = File.separator + "fros.db"; //TODO Use folder/filename
        File backupPathFile = new File(pathExtCard() + File.separator + "Android" + File.separator +"data" + File.separator + context.getApplicationContext().getPackageName() + File.separator +"BACKUP");
        File outFile = new File(backupPathFile + inFileName);
        if (outFile.exists()){
            return true;
        }
        return false;
    }

    public static void backupFile(Context context, BackupStatus callback) throws IOException {

        String storagestate = Environment.getExternalStorageState();

        File dbFile = context.getDatabasePath("fros.db");
        String path = dbFile.getAbsolutePath();

        File[] mountDirs = context.getExternalFilesDirs( File.separator + "fros.db");
        File from = Environment.getExternalStorageDirectory();
        if (from.canRead() && from.canWrite()) {
            if (storagestate.equals(Environment.MEDIA_MOUNTED)) {
                File inFile = new File(path);

                FileInputStream inputStream = new FileInputStream(inFile);
                FileChannel inChannel = inputStream.getChannel();

                //String timeStamp = new SimpleDateFormat("(dd-MM-yyyy__HH-mm)").format(new Date());
                String outFileName = File.separator + "fros.db" + ".backup";
                File backupPathFile = new File(pathExtCard() + File.separator + "Android" + File.separator +"data" + File.separator + context.getApplicationContext().getPackageName() + File.separator +"BACKUP");

                if (!backupPathFile.exists())
                {
                    backupPathFile.mkdir();
                    Toast.makeText(context, "Directory Created!!", Toast.LENGTH_SHORT).show();
                }

                try {
                    if (backupPathFile.canRead() && backupPathFile.canWrite()) {

                        File  outFile = new File(backupPathFile + outFileName);
                        FileOutputStream outputStream = new FileOutputStream(outFile);
                        FileChannel outChannel = outputStream.getChannel();

                        inChannel.transferTo(0, inFile.length(), outChannel);

                        inputStream.close();
                        outputStream.close();
                        //String timeStamp = new SimpleDateFormat("(dd-MM-yyyy HH:mm)").format(new Date());
                        callback.Success();
                        //Toast.makeText(context, "BACKUP File PHI Mobile at :\n" + timeStamp + "\nSuccess!" , Toast.LENGTH_LONG).show();

                    } else {
                        callback.Failed("Directory Cant Read Wrire!!");
                        //Toast.makeText(context, "Directory Cant Read Wrire!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException error) {
                    callback.Failed(String.valueOf(error));
                    //Toast.makeText(context, "Directory Cant Read Wrire, Catch Error File not found!!", Toast.LENGTH_SHORT).show();
                }

            } else {
                callback.Failed("Storage not mounted!!");
                //Log.i("Gagal", "Ulang");
                //Toast.makeText(context, "Storage not mounted!!", Toast.LENGTH_SHORT).show();
            }

        } else {
            //requestPermission();
        }

    }

    public interface BackupStatus {
        void Success();
        void Failed(String error);
    }

    public static void restoreFile(Context context, RestoreStatus callback) throws IOException {

        String storagestate = Environment.getExternalStorageState();

        File dbFile = context.getDatabasePath("fros.db");
        String path = dbFile.getAbsolutePath();


        File[] mountDirs = context.getExternalFilesDirs( File.separator + "fros.db");
        File from = new File(pathExtCard() + File.separator + "Android" + File.separator +"data" + File.separator + context.getApplicationContext().getPackageName() + File.separator +"BACKUP");
        if (from.canRead() && from.canWrite()) {
            if (storagestate.equals(Environment.MEDIA_MOUNTED)) {
                String inFileName = from + File.separator + "fros.db.backup"; //TODO Use folder/filename
                File inFile = new File(inFileName);

                FileInputStream inputStream = new FileInputStream(inFile);
                FileChannel inChannel = inputStream.getChannel();

                //String timeStamp = new SimpleDateFormat("(dd-MM-yyyy__HH-mm)").format(new Date());
                String outFileName = File.separator + "fros.db";
                File backupPathFile = new File(path);

                try {
                    if (backupPathFile.canRead() && backupPathFile.canWrite()) {

                        File  outFile = new File(backupPathFile.toString());
                        FileOutputStream outputStream = new FileOutputStream(outFile);
                        FileChannel outChannel = outputStream.getChannel();

                        inChannel.transferTo(0, inFile.length(), outChannel);

                        inputStream.close();
                        outputStream.close();
                        //String timeStamp = new SimpleDateFormat("(dd-MM-yyyy HH:mm)").format(new Date());
                        callback.Success();
                        //Toast.makeText(context, "RESTORE File PHI Mobile at : " + timeStamp , Toast.LENGTH_LONG).show();

                    } else {
                        callback.Failed("SDCard cant read");
                    }
                } catch (IOException error) {
                    callback.Failed(String.valueOf(error));
                }

            } else {
                callback.Failed("Mohon diulang kembali!");
            }

        } else {
            //requestPermission();
        }
    }

    public interface RestoreStatus {
        void Success();
        void Failed(String error);
    }


    private static String pathExtCard(){
        String removableStoragePath = null;
        File fileList[] = new File("/storage/").listFiles();
        for (File file : fileList) {
            if(!file.getAbsolutePath().equalsIgnoreCase(Environment.getExternalStorageDirectory().getAbsolutePath()) && file.isDirectory() && file.canRead())
                removableStoragePath = file.getAbsolutePath();
        }
        return removableStoragePath;
    }

}

