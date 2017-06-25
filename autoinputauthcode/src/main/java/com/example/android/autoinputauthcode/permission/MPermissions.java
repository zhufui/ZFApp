package com.example.android.autoinputauthcode.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by zf on 17-5-26.
 */
public class MPermissions {
    public static void requestPermissions(Activity activity, int requestCode, PermissionCallback pc, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        boolean checkResult = true;
        for (int i = 0; i < permissions.length; i++) {
            if (!checkPermission(activity, permissions[i])) {
                checkResult = false;
                break;
            }
        }

        if (checkResult) {
            pc.permissionGrant(requestCode);
        } else {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }

    private static boolean checkPermission(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    public static void onRequestPermissionsResult(int requestCode, int[] grantResults,
                                                  PermissionCallback pc,int... requestCodes){
        for (int i = 0; i < requestCodes.length; i++) {
            if(requestCodes[i] != requestCode){
                return;
            }
        }

        boolean grantResult = true;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                grantResult = false;
            }
        }

        if(grantResult){
            pc.permissionGrant(requestCode);
            return;
        }

        pc.permissionDenied(requestCode);
    }
}
