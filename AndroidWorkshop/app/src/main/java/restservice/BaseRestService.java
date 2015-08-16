package restservice;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;

public class BaseRestService {
    public static final String ENDPOINT = "http://api.openweathermap.org/data/2.5";
    private static final String LOG_TAG = BaseRestService.class.getSimpleName();

    private static ErrorHandler errorHandler = new ErrorHandler() {
        @Override
        public Throwable handleError(RetrofitError cause) {
            if (cause != null && cause.getResponse() != null) {
                Log.w(LOG_TAG, cause.getUrl() + ", " + cause.getResponse().getReason());
            }

            return cause;
        }
    };

    public static CurrentWeatherRestService getCurrentWeather() {
        return getRestAdapter().create(CurrentWeatherRestService.class);
    }

    private static RestAdapter getRestAdapter() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.i(LOG_TAG, msg);
                    }
                })
                .setErrorHandler(errorHandler)
                .build();
    }

    public static void logRetrofitError(RetrofitError retrofitError) {

        if (retrofitError.getResponse() != null) {
            Log.d(LOG_TAG, retrofitError.getResponse().getReason()); // Proguard: Remove
        }

        RetrofitError.Kind kind = retrofitError.getKind();
        Log.d("HEI", kind.toString());
        switch (kind) {
            case NETWORK:
                break;
            case CONVERSION:
                break;
            case UNEXPECTED:
                break;
            case HTTP:
                break;
        }
    }
}
