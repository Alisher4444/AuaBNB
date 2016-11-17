package creatingnew.kz.auabnb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import creatingnew.kz.auabnb.weatherService.data.Channel;
import creatingnew.kz.auabnb.weatherService.data.Item;
import creatingnew.kz.auabnb.weatherService.service.WeatherServiceCallBack;
import creatingnew.kz.auabnb.weatherService.service.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallBack {

    private Button signOutButton;
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;


    private YahooWeatherService service;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Almaty, KZ");


/*
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null){
            finish();
            //startActivity(new Intent(this,LoginActivity.class));
        }
        FirebaseUser user = auth.getCurrentUser();
        storage = FirebaseStorage.getInstance();
        

*/
    }

    private void signOut() {
  //      auth.signOut();
        finish();
        //startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }


    @Override
    public void serviceSuccess(Channel channel) {

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_"+ item.getCondition().getCode(),null,getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        temperatureTextView.setText(item.getCondition().getTemperature()+"\u00B0"+channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());

        locationTextView.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception e) {
        dialog.hide();
        Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
