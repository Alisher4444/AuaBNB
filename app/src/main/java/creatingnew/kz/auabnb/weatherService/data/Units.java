package creatingnew.kz.auabnb.weatherService.data;

import org.json.JSONObject;

/**
 * Created by Alisher on 13.09.2016.
 */
public class Units implements JSONPopulator {

    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");

    }
}
