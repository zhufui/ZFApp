package com.example.android.autoinputauthcode.authcode;

/**
 * Created by zf on 17-6-23.
 */
public class CodeConfig {
    private int mCodeLen = 4;
    private String mSmsBodyStart;
    private String mSmsBodyContains;
    private long mSmsFrom;
    private int mSmsFromStart = 1069;

    public int getCodeLen() {
        return mCodeLen;
    }

    public void setCodeLen(int codeLen) {
        mCodeLen = codeLen;
    }

    public String getSmsBodyStart() {
        return mSmsBodyStart;
    }

    public void setSmsBodyStart(String smsStart) {
        mSmsBodyStart = smsStart;
    }

    public String getSmsBodyContains() {
        return mSmsBodyContains;
    }

    public void setSmsBodyContains(String smsContains) {
        mSmsBodyContains = smsContains;
    }

    public long getSmsFrom() {
        return mSmsFrom;
    }

    public void setSmsFrom(long smsFrom) {
        mSmsFrom = smsFrom;
    }

    public int getSmsFromStart() {
        return mSmsFromStart;
    }

    public void setSmsFromStart(int smsFromStart) {
        mSmsFromStart = smsFromStart;
    }
}
