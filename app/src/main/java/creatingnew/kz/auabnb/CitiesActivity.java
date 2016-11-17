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

import creatingnew.kz.auabnb.weatherService.data.Channel;
import creatingnew.kz.auabnb.weatherService.data.Item;
import creatingnew.kz.auabnb.weatherService.service.WeatherServiceCallBack;
import creatingnew.kz.auabnb.weatherService.service.YahooWeatherService;

public class CitiesActivity extends AppCompatActivity {

    private static final String TAG = "CitiesActivity";
    private ListView listView;
    private List<City> cities;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        listView = (ListView) findViewById(R.id.listView);
      //  btn = (Button) findViewById(R.id.cityWeatherButton);
        Backendless.initApp(this,Const.APP_ID, Const.ANDROID_KEY, "v1");
        downloadCities();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openCity(position);
            }
        });
/*        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CitiesActivity.this,MainActivity.class));
            }
        });
*/
    }

    private void openCity(int position) {
        Intent intent = new Intent(this,ApartmentsActivity.class);
        intent.putExtra("city_id",cities.get(position).getObjectId());
        startActivity(intent);

    }
    private void downloadCities(){



        Backendless.Persistence.of(City.class).find(new AsyncCallback<BackendlessCollection<City>>() {
            @Override
            public void handleResponse(BackendlessCollection<City> response) {
                Log.d(TAG,response.getData().toString());

                displayCities(response.getData());

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG,"some error"+ fault.getMessage());

            }
        });

    }

    private void displayCities(List<City> cities) {
        this.cities=cities;
        CityAdapter adapter  = new CityAdapter(cities,this);
        listView.setAdapter(adapter);

    }
}
