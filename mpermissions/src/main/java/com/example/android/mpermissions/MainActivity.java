package com.example.android.mpermissions;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.mpermissions.permission.MPermissions;
import com.example.android.mpermissions.permission.PermissionCallback;

/**
 * 简单的动态权限封装
 * 实现PermissionCallback接口
 * 请求权限 MPermissions.requestPermissions
 * 权限回调 MPermissions.onRequestPermissionsResult
 */
public class MainActivity extends AppCompatActivity implements PermissionCallback, View.OnClickListener {

    String[] permissions = {Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS};
    public static final int REQUESTCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MPermissions.requestPermissions(MainActivity.this, REQUESTCODE, MainActivity.this, permissions);
    }

    @Override
    public void permissionGrant() {
        callPhone();
    }

    @Override
    public void permissionDenied() {
        Toast.makeText(this,"请去设置->应用->update->权限,打开权限",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(requestCode, grantResults, this, REQUESTCODE);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }
}
