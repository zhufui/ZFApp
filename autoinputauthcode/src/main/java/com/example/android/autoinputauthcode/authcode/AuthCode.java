package com.example.android.autoinputauthcode.authcode;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * Created by zf on 17-6-23.
 */
public class AuthCode {
    private Context mContext;
    private static CodeConfig mCodeConfig;
    private static AuthCode sInstance;

    private Handler mHandler;

    static class AuthCodeHandler extends Handler {
        private Reference<View> mTextViewRef;

        public AuthCodeHandler(View codeView) {
            this.mTextViewRef = new SoftReference<>(codeView);
        }

        @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            View mAuthCode = mTextViewRef.get();
            TextView authCodeTv = null;
            EditText authCodeEt = null;
            if (mAuthCode == null) {
                return;
            }

            if(mAuthCode instanceof TextView){
                authCodeTv = (TextView)mAuthCode;
            }

            if(mAuthCode instanceof EditText){
                authCodeEt = (EditText)mAuthCode;
            }

            if(authCodeTv == null && authCodeEt == null){
                return;
            }

            switch (msg.what) {
                case ReadSmsService.OBSERVER_SMS_CODE_MSG:
                    if(authCodeTv != null){
                        authCodeTv.setText((String) msg.obj);
                    }
                    if(authCodeEt != null){
                        authCodeEt.setText((String) msg.obj);
                    }
                    sInstance = null;
                    mCodeConfig = null;
                    break;
                case ReadSmsService.RECEIVER_SMS_CODE_MSG:
                    if(authCodeTv != null){
                        authCodeTv.setText((String) msg.obj);
                    }
                    if(authCodeEt != null){
                        authCodeEt.setText((String) msg.obj);
                    }
                    sInstance = null;
                    mCodeConfig = null;
                    break;
                default:
                    break;
            }
        }
    }

    private AuthCode() {
    }

    public static synchronized AuthCode getInstance() {
        if (sInstance == null) {
            sInstance = new AuthCode();
            mCodeConfig = new CodeConfig();
        }
        return sInstance;
    }

    public AuthCode with(Context context) {
        mContext = context;
        return this;
    }

    public AuthCode setCodeLen(int codeLen) {
        mCodeConfig.setCodeLen(codeLen);
        return this;
    }

    public AuthCode setSmsBodyStart(String smsStart) {
        mCodeConfig.setSmsBodyStart(smsStart);
        return this;
    }

    public AuthCode setSmsBodyContains(String smsContains) {
        mCodeConfig.setSmsBodyContains(smsContains);
        return this;
    }

    public AuthCode setSmsFrom(long smsFrom) {
        mCodeConfig.setSmsFrom(smsFrom);
        return this;
    }

    public AuthCode setSmsFromStart(int smsFromStart) {
        mCodeConfig.setSmsFromStart(smsFromStart);
        return this;
    }

    public void into(View codeView) {
        if (mCodeConfig == null) {
            throw new NullPointerException("mCodeConfig is null.Please call config(CodeConfig) before this.");
        }
        mHandler = new AuthCodeHandler(codeView);
        startReadSmsService();
    }

    /**
     * 开启短信验证码获取服务
     */
    private void startReadSmsService() {
        Intent mAuthcodeIntent = new Intent(mContext, ReadSmsService.class);
        mAuthcodeIntent.putExtra(ReadSmsService.EXTRAS_MESSAGER, new Messenger(mHandler));
        mAuthcodeIntent.putExtra(ReadSmsService.EXTRAS_COFIG, mCodeConfig);
        mContext.startService(mAuthcodeIntent);
    }
}
