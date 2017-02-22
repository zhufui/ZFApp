package com.example.encryptsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.encryptsample.util.AESUtil;
import com.example.encryptsample.util.DESUtil;
import com.example.encryptsample.util.MD5Util;
import com.example.encryptsample.util.SHAUtil;
import com.example.encryptsample.util.TripleDESUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.aesEncrypt_bt)
    Button aesEncryptBt;
    @BindView(R.id.aesDecrypt_bt)
    Button aesDecryptBt;
    @BindView(R.id.desEncrypt_bt)
    Button desEncryptBt;
    @BindView(R.id.desDecrypt_bt)
    Button desDecryptBt;
    @BindView(R.id.tripleDesEncrypt_bt)
    Button tripleDesEncryptBt;
    @BindView(R.id.tripleDesDecrypt_bt)
    Button tripleDesDecryptBt;
    @BindView(R.id.md5Encrypt_bt)
    Button md5EncryptBt;
    @BindView(R.id.shaEncrypt_bt)
    Button shaEncryptBt;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.data_et)
    EditText dataEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.aesEncrypt_bt, R.id.aesDecrypt_bt, R.id.desEncrypt_bt, R.id.desDecrypt_bt, R.id.tripleDesEncrypt_bt, R.id.tripleDesDecrypt_bt, R.id.md5Encrypt_bt, R.id.shaEncrypt_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aesEncrypt_bt:
                String encryptData = AESUtil.encrypt(dataEt.getText().toString());
                dataEt.setText(encryptData);
                Log.d("MainActivity", "encryptData = " + encryptData);
                break;
            case R.id.aesDecrypt_bt:
                String decryptData = AESUtil.decrypt(dataEt.getText().toString());
                dataEt.setText(decryptData);
                Log.d("MainActivity", "decryptData = " + decryptData);
                break;
            case R.id.desEncrypt_bt:
                encryptData = DESUtil.encrypt(dataEt.getText().toString());
                dataEt.setText(encryptData);
                Log.d("MainActivity", "encryptData = " + encryptData);
                break;
            case R.id.desDecrypt_bt:
                decryptData = DESUtil.decrypt(dataEt.getText().toString());
                dataEt.setText(decryptData);
                Log.d("MainActivity", "decryptData = " + decryptData);
                break;
            case R.id.tripleDesEncrypt_bt:
                encryptData = TripleDESUtils.encrypt(dataEt.getText().toString());
                dataEt.setText(encryptData);
                Log.d("MainActivity", "encryptData = " + encryptData);
                break;
            case R.id.tripleDesDecrypt_bt:
                decryptData = TripleDESUtils.decrypt(dataEt.getText().toString());
                dataEt.setText(decryptData);
                Log.d("MainActivity", "decryptData = " + decryptData);
                break;
            case R.id.md5Encrypt_bt:
                encryptData = MD5Util.encryptMD5(dataEt.getText().toString());
                dataEt.setText(encryptData);
                Log.d("MainActivity", "encryptData = " + encryptData);
                break;
            case R.id.shaEncrypt_bt:
                encryptData = SHAUtil.encryptSHA(dataEt.getText().toString());
                dataEt.setText(encryptData);
                Log.d("MainActivity", "encryptData = " + encryptData);
                break;
        }
    }
}
