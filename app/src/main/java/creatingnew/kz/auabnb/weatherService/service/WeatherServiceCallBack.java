package creatingnew.kz.auabnb.weatherService.service;

import creatingnew.kz.auabnb.weatherService.data.Channel;


/**
 * Created by Alisher on 13.09.2016.
 */
public interface WeatherServiceCallBack {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception e);


}
