package no.mesan.mobil.androidworkshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import no.mesan.mobil.androidworkshop.restservice.DateTimeDeserializer;

public class WeatherInfo implements Parcelable {
    private DateTime dt;
    private WeatherMain main;
    private Temp temp;
    private List<Weather> weather;
    private String name;

    public DateTime getDt() {
        return dt;
    }

    public void setDt(DateTime dt) {
        this.dt = dt;
    }

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected WeatherInfo(Parcel in) {
        dt = (DateTime) in.readValue(DateTime.class.getClassLoader());
        main = (WeatherMain) in.readValue(WeatherMain.class.getClassLoader());
        temp = (Temp) in.readValue(Temp.class.getClassLoader());
        if (in.readByte() == 0x01) {
            weather = new ArrayList<Weather>();
            in.readList(weather, Weather.class.getClassLoader());
        } else {
            weather = null;
        }
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dt);
        dest.writeValue(main);
        dest.writeValue(temp);
        if (weather == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(weather);
        }
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WeatherInfo> CREATOR = new Parcelable.Creator<WeatherInfo>() {
        @Override
        public WeatherInfo createFromParcel(Parcel in) {
            return new WeatherInfo(in);
        }

        @Override
        public WeatherInfo[] newArray(int size) {
            return new WeatherInfo[size];
        }
    };
}
