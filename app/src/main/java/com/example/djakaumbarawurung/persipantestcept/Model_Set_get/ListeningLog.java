package com.example.djakaumbarawurung.persipantestcept.Model_Set_get;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by linuxluv on 12/25/16.
 */

public class ListeningLog implements Parcelable {
    String pertanyaan;
    String kunci;
    String jawabanUser;
    String penjelasan;

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    public String getJawabanUser() {
        return jawabanUser;
    }

    public void setJawabanUser(String jawabanUser) {
        this.jawabanUser = jawabanUser;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }

    public ListeningLog(){}

    protected ListeningLog(Parcel in) {
        pertanyaan = in.readString();
        kunci = in.readString();
        jawabanUser = in.readString();
        penjelasan = in.readString();
    }

    public static final Creator<ListeningLog> CREATOR = new Creator<ListeningLog>() {
        @Override
        public ListeningLog createFromParcel(Parcel in) {
            return new ListeningLog(in);
        }

        @Override
        public ListeningLog[] newArray(int size) {
            return new ListeningLog[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pertanyaan);
        dest.writeString(kunci);
        dest.writeString(jawabanUser);
        dest.writeString(penjelasan);
    }
}
