package com.example.android.autoinputauthcode;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.autoinputauthcode.authcode.AuthCode;
import com.example.android.autoinputauthcode.permission.MPermissions;
import com.example.android.autoinputauthcode.permission.PermissionCallback;

public class MainActivity extends AppCompatActivity implements PermissionCallback, View.OnClickListener {

    String[] permissions = {Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS};
    public static final int REQUESTCODE = 1;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.bt);
        tv = (TextView) findViewById(R.id.tv);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MPermissions.requestPermissions(MainActivity.this, REQUESTCODE, MainActivity.this, permissions);
    }

    @Override
    public void permissionGrant(int requestCode) {
        AuthCode.getInstance().with(this).setCodeLen(6).setSmsFromStart(185).into(tv);
    }

    @Override
    public void permissionDenied(int requestCode) {
        Toast.makeText(this, "请去设置->应用->" + getString(R.string.app_name) + "->权限,打开权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e("AutoInputAuthCode", "onRequestPermissionsResult");
        MPermissions.onRequestPermissionsResult(requestCode, grantResults, this, REQUESTCODE);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
