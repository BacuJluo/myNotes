package com.example.notes;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {

    private int index;

    protected Notes(Parcel in) {
        index = in.readInt();
    }

    public Notes(int finalI) {
        this.index=finalI;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }






}



