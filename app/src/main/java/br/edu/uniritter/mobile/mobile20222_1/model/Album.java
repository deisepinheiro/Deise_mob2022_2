package br.edu.uniritter.mobile.mobile20222_1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Album implements Parcelable{
    private int userId;
    private int id;
    private String title;

    public Album(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    protected Album(Parcel in) {
        userId = in.readInt();
        id = in.readInt();
        title = in.readString();
    }

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeInt(id);
        parcel.writeString(title);
    }
}
