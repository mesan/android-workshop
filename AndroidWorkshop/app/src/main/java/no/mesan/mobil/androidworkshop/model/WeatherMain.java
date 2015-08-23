package no.mesan.mobil.androidworkshop.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherMain implements Parcelable {
    private double temp;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "WeatherMain{" +
                "temp=" + temp +
                '}';
    }

    protected WeatherMain(Parcel in) {
        temp = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(temp);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WeatherMain> CREATOR = new Parcelable.Creator<WeatherMain>() {
        @Override
        public WeatherMain createFromParcel(Parcel in) {
            return new WeatherMain(in);
        }

        @Override
        public WeatherMain[] newArray(int size) {
            return new WeatherMain[size];
        }
    };
}