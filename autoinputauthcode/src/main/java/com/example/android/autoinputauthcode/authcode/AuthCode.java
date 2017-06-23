package com.example.android.autoinputauthcode.authcode;

import android.content.Context;

/**
 * Created by zf on 17-6-23.
 */
public class AuthCode {
    private Context mContext;
    private static CodeConfig mCodeConfig;
    private static AuthCode sInstance;

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

}
