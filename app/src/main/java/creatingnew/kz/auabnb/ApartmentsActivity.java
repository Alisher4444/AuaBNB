package creatingnew.kz.auabnb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.List;

import creatingnew.kz.auabnb.redirect.redirectData;
import creatingnew.kz.auabnb.weatherService.data.Channel;
import creatingnew.kz.auabnb.weatherService.data.Item;
import creatingnew.kz.auabnb.weatherService.service.WeatherServiceCallBack;
import creatingnew.kz.auabnb.weatherService.service.YahooWeatherService;

public class ApartmentsActivity extends AppCompatActivity implements WeatherServiceCallBack {

    private static final String TAG = "ApartmentsActivity";
    private String city_id;
    private ListView listView;
    private List<Apartment> apartments;
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    public TextView conditionTextView;
    private TextView locationTextView;
    private YahooWeatherService service;
    private ProgressDialog dialog;
    private redirectData redirectData;
    private CheckBox activeCheckBox;
    private CheckBox passivCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartments);
        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        redirectData=new redirectData();
        passivCheckBox = (CheckBox) findViewById(R.id.passivCheckBox);
        activeCheckBox = (CheckBox) findViewById(R.id.activeCheckbox);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Almaty, KZ");
        listView = (ListView) findViewById(R.id.listView);

        city_id = getIntent().getExtras().getString("city_id");


        downloadApartments(city_id);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openFlats(position);
            }
        });


    }





    private void openFlats(int position) {
        Intent intent = new Intent(this,FlatsActivity.class);
        intent.putExtra("apartment_id",apartments.get(position).getObjectId());
        startActivity(intent);
    }


    private void downloadApartments(String city_id) {
        String whereClause = "city.objectId = " + "'" + city_id + "'";

        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause(whereClause);

        Backendless.Persistence.of(Apartment.class).find(query, new AsyncCallback<BackendlessCollection<Apartment>>() {
            @Override
            public void handleResponse(BackendlessCollection<Apartment> response) {

                    displayApartments(response.getData());

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG,"some error " +fault.getMessage());
            }
        });

    }

    @Override
    public void serviceSuccess(Channel channel) {

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_"+ item.getCondition().getCode(),null,getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        redirectData.setTemp(item.getCondition().getTemperature());

        //ApartmentAdapter adapter = new ApartmentAdapter(apartments,this);
        //adapter.setTemp(item.getCondition().getTemperature());

        temperatureTextView.setText(item.getCondition().getTemperature()+"\u00B0"+channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());

        locationTextView.setText(service.getLocation());

    }

    public void serviceFailure(Exception e) {
        dialog.dismiss();
        Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
    }


    private void displayApartments(List<Apartment> apartments) {
        this.apartments=apartments;
        ApartmentAdapter adapter = new ApartmentAdapter(apartments,this);

        listView.setAdapter(adapter);
    }
}
