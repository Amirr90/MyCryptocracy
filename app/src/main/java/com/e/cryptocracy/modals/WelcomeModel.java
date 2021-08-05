package com.e.cryptocracy.modals;

import android.os.Parcel;
import android.os.Parcelable;

public class WelcomeModel implements Parcelable {
    public static final Creator<WelcomeModel> CREATOR = new Creator<WelcomeModel>() {
        @Override
        public WelcomeModel createFromParcel(Parcel in) {
            return new WelcomeModel(in);
        }

        @Override
        public WelcomeModel[] newArray(int size) {
            return new WelcomeModel[size];
        }
    };
    String text1;
    String text2;
    String text3;

    public WelcomeModel(String text1, String text2, String text3) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }

    protected WelcomeModel(Parcel in) {
        text1 = in.readString();
        text2 = in.readString();
        text3 = in.readString();
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text1);
        dest.writeString(text2);
        dest.writeString(text3);
    }
}
