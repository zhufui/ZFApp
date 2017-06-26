package com.example.android.autoinputauthcode.authcode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zf on 17-6-23.
 */
public class CodeConfig implements Parcelable {
    private int mCodeLen = 6;//验证码长度
    private String mSmsBodyStart;//短信内容以什么开头
    private String mSmsBodyContains;//短信中包含的内容
    private long mSmsFrom;//发送短信的号码
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mCodeLen);
        dest.writeString(this.mSmsBodyStart);
        dest.writeString(this.mSmsBodyContains);
        dest.writeLong(this.mSmsFrom);
        dest.writeInt(this.mSmsFromStart);
    }

    public CodeConfig() {
    }

    protected CodeConfig(Parcel in) {
        this.mCodeLen = in.readInt();
        this.mSmsBodyStart = in.readString();
        this.mSmsBodyContains = in.readString();
        this.mSmsFrom = in.readLong();
        this.mSmsFromStart = in.readInt();
    }

    public static final Parcelable.Creator<CodeConfig> CREATOR = new Parcelable.Creator<CodeConfig>() {
        @Override
        public CodeConfig createFromParcel(Parcel source) {
            return new CodeConfig(source);
        }

        @Override
        public CodeConfig[] newArray(int size) {
            return new CodeConfig[size];
        }
    };
}
