package creatingnew.kz.auabnb.redirect;

import android.text.Editable;

/**
 * Created by Alisher on 17.10.2016.
 */

public class redirectData {
    private String weatherType;
    int temp;

    public redirectData() {

    }

    public void setTemp(int temp){
        this.temp=temp;
    }
    public int getTemp(){
        return temp;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getWeatherType() {
        return weatherType;
    }
}
