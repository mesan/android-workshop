package no.mesan.mobil.androidworkshop.view.forecast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import no.mesan.mobil.androidworkshop.util.DateFormatter;
import no.mesan.mobil.androidworkshop.util.TemperatureFormatter;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private final Context context;
    private ForecastClickListener forecastClickListener;
    private List<WeatherInfo> weatherList = new ArrayList<>();
    private int dayOfMonth;

    private String[] months;

    public ForecastAdapter(Context context, ForecastClickListener forecastClickListener) {
        this.context = context;
        this.forecastClickListener = forecastClickListener;
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

        if (i == 0 || dateTime.getDayOfMonth() != weatherList.get(i - 1).getDt().getDayOfMonth()) {
            shouldShowDate = true;
        }

        double temp = 0;

        if (weatherInfo.getMain() != null) {
            temp = weatherInfo.getMain().getTemp();
        } else if (weatherInfo.getTemp() != null) {
            temp = weatherInfo.getTemp().getDay();
        }

        String iconUrl = "http://openweathermap.org/img/w/" + weather.getIcon() + ".png";
        String description = weather.getDescription();

        /* Oppgave 4c
         * Koden over henter ut en del informasjon fra weatherInfo-objektet.
         * Vis frem denne informasjonen i gui.
         * Bruk klassene DateFormatter og TemperatureFormatter til å formattere teksten for dateTime og temp.
         */
        Picasso.with(context).load(iconUrl).into(viewHolder.imageViewForecast);
        viewHolder.textViewTemperature.setText(TemperatureFormatter.format(temp));
        viewHolder.textViewWhen.setText("Kl " + dateTime.getHourOfDay() + (shouldShowDate ? " - " + DateFormatter.formatDate(dateTime) : ""));
        viewHolder.textViewDescription.setText(description);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    // Brukes i oppgave 4e
    public void setWeather(List<WeatherInfo> weatherList) {
        this.weatherList.clear();
        this.weatherList.addAll(weatherList);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageViewForecast;
        public TextView textViewWhen;
        public TextView textViewTemperature;
        public TextView textViewDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewForecast = (ImageView) itemView.findViewById(R.id.imageViewForecast);
            textViewWhen = (TextView) itemView.findViewById(R.id.textViewWhen);
            textViewTemperature = (TextView) itemView.findViewById(R.id.textViewTemperature);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            // Oppgave 5c
            int position = getAdapterPosition();
            forecastClickListener.onClick(weatherList.get(position));
        }
    }
}