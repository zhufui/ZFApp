package cn.com.easypermissions;

/**
 * Created by zhufu on 2017/1/12.
 */

public interface PermissionCallBackM {
    void onPermissionGrantedM(int requestCode, String... perms);

    void onPermissionDeniedM(int requestCode, String... perms);
}
