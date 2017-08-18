package com.forestry.sopcompliance.utils;

import android.app.Activity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fimansya on 5/9/2017.
 */

public class PermissionUtils {
    public interface CheckPermissionCallback {
        void onResult(boolean result);
    }

    private static void checkPermission(Activity activity, List<String> permissions, final CheckPermissionCallback callback) {
        Dexter.withActivity(activity)
                .withPermissions(permissions)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        callback.onResult(report.areAllPermissionsGranted());
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    public static void checkAllPermissions(Activity activity, CheckPermissionCallback callback) {
        checkPermission(activity, new ArrayList<>(
                Arrays.asList(
                        "android.permission.INTERNET",
                        "android.permission.WRITE_EXTERNAL_STORAGE",
                        "android.permission.READ_EXTERNAL_STORAGE",
                        "android.permission.ACCESS_COARSE_LOCATION",
                        "android.permission.ACCESS_FINE_LOCATION")
        ), callback);
    }

    public static void checkCameraPermission(Activity activity, CheckPermissionCallback callback) {
        checkPermission(activity, new ArrayList<>(Arrays.asList("android.permission.CAMERA")), callback);
    }

    public static void checkLocationPermission(Activity activity, CheckPermissionCallback callback) {
        checkPermission(activity, new ArrayList<>(
                Arrays.asList(
                        "android.permission.ACCESS_COARSE_LOCATION",
                        "android.permission.ACCESS_FINE_LOCATION")
        ), callback);
    }

    public static void checkWriteExternalDataPermission(Activity activity, CheckPermissionCallback callback) {
        checkPermission(activity, new ArrayList<>(
                Arrays.asList(
                        "android.permission.WRITE_EXTERNAL_STORAGE",
                        "android.permission.READ_EXTERNAL_STORAGE")
        ), callback);
    }

    public static void checkVibratePermission(Activity activity, CheckPermissionCallback callback) {
        checkPermission(activity, new ArrayList<>(Arrays.asList("android.permission.VIBRATE")), callback);
    }
}
