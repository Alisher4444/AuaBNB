package creatingnew.kz.auabnb.weatherService.service;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import creatingnew.kz.auabnb.weatherService.data.Channel;



/**
 * Created by Alisher on 13.09.2016.
 */
public class YahooWeatherService {

    private WeatherServiceCallBack weatherServiceCallBack;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallBack weatherServiceCallBack) {
        this.weatherServiceCallBack = weatherServiceCallBack;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(String l){
        this.location = l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {

                String YQl = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'",strings[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQl));

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        result.append(line);
                    }
                    return  result.toString();

                } catch (Exception e) {
                    error=e;

                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if (s ==null && error!=null){
                    weatherServiceCallBack.serviceFailure(error);
                    return;
                }

                try{
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResults =data.optJSONObject("query");
                    int count = queryResults.optInt("count");
                    if (count==0){
                        weatherServiceCallBack.serviceFailure(new LocationWeatherException("No weather information for"+ location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));

                    weatherServiceCallBack.serviceSuccess(channel);

                } catch (JSONException e) {
                    weatherServiceCallBack.serviceFailure(e);
                }


            }
        }.execute(location);
    }

    public class LocationWeatherException extends Exception {
        public LocationWeatherException(String detailMessage){
            super(detailMessage);
        }
    }


}
