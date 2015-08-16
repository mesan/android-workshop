package no.mesan.mobil.androidworkshop.view.forecast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.Weather;

/**
 * Created by Thomas on 16.08.2015.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder>  {

    private List<Weather> weatherList = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setWeather(List<Weather> weatherList) {
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
