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

    public ForecastAdapter(Context context) {
        this.context = context;
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

        Picasso.with(context).load("http://openweathermap.org/img/w/" + weather.getIcon() + ".png").into(viewHolder.imageViewForecast);

        viewHolder.textViewTemperature.setText(weatherInfo.getMain().getTemp() + " grader");
        viewHolder.textViewWhen.setText(weatherInfo.getDt().toString());
        viewHolder.textViewWind.setText(weather.getDescription());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setWeather(List<WeatherInfo> weatherList) {
        Log.i("KLOVN", weatherList.toString());

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
