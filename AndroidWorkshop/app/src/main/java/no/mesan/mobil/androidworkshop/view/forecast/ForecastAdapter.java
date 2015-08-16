package no.mesan.mobil.androidworkshop.view.forecast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.Weather;
import no.mesan.mobil.androidworkshop.model.WeatherInfo;

/**
 * Created by Thomas on 16.08.2015.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder>  {

    private final Context context;
    private List<WeatherInfo> weatherList = new ArrayList<>();
    private int dayOfMonth;

    private String[] months;

    public ForecastAdapter(Context context) {
        this.context = context;
        months = context.getResources().getStringArray(R.array.months);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.forecast_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        WeatherInfo weatherInfo = weatherList.get(i);
        Weather weather = weatherInfo.getWeather().get(0);

        DateTime dateTime = weatherInfo.getDt();
        boolean shouldShowDate = false;

        if (i == 0 || dateTime.getDayOfMonth() != weatherList.get(i-1).getDt().getDayOfMonth()) {
            shouldShowDate = true;
        }

        double temp = 0;

        if (weatherInfo.getMain() != null) {
            temp = weatherInfo.getMain().getTemp();
        } else if (weatherInfo.getTemp() != null) {
            temp = weatherInfo.getTemp().getDay();
        }

        Picasso.with(context).load("http://openweathermap.org/img/w/" + weather.getIcon() + ".png").into(viewHolder.imageViewForecast);
        viewHolder.textViewTemperature.setText((Math.round(temp*10.0) / 10.0) + "" + ((char) 0x00B0) + "C");
        viewHolder.textViewWhen.setText("Kl " + dateTime.getHourOfDay() + (shouldShowDate ? " - " + dateTime.getDayOfMonth() + ". " + months[dateTime.getMonthOfYear()-1] : ""));
        viewHolder.textViewWind.setText(weather.getDescription());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setWeather(List<WeatherInfo> weatherList) {
        this.weatherList.clear();
        this.weatherList.addAll(weatherList);

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewForecast;
        public TextView textViewWhen;
        public TextView textViewTemperature;
        public TextView textViewWind;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewForecast = (ImageView) itemView.findViewById(R.id.imageViewForecast);
            textViewWhen = (TextView) itemView.findViewById(R.id.textViewWhen);
            textViewTemperature = (TextView) itemView.findViewById(R.id.textViewTemperature);
            textViewWind = (TextView) itemView.findViewById(R.id.textViewWind);
        }
    }
}
