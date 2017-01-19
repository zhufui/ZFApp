package cn.com.permissiongen.internal;

import android.content.pm.PackageManager;

/**
 * Created by zhufu on 2017/1/12.
 */
public class ValidateUtil {
    public static boolean verifyGrants(int... grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
