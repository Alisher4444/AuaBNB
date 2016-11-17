package creatingnew.kz.auabnb;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import creatingnew.kz.auabnb.redirect.redirectData;
import creatingnew.kz.auabnb.weatherService.data.Channel;
import creatingnew.kz.auabnb.weatherService.data.Item;
import creatingnew.kz.auabnb.weatherService.service.WeatherServiceCallBack;

/**
 * Created by Алишер on 14.06.2016.
 */
public class ApartmentAdapter extends BaseAdapter implements WeatherServiceCallBack {
    private LayoutInflater inflater;
    private List<Apartment> apartments;
    private Context context;
    private redirectData redirect;
    private String weatherType;
    private int temp;


    public ApartmentAdapter(List<Apartment> apartments, Context context) {
        this.apartments = apartments;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public void setTemp(int temp){
        this.temp=temp;
    }
    public int getTemp(){
        return temp;
    }


    @Override
    public int getCount() {
            return apartments.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void serviceSuccess(Channel channel) {
        Item item = channel.getItem();
        setTemp(item.getCondition().getTemperature());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        redirect=new redirectData();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.row_apartment_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }



               viewHolder.apartmentTitleTextView.setText(apartments.get(position).getTitle());
               viewHolder.apartmentPriceTextView.setText(apartments.get(position).getGradus() + " " + getTemp());

               Glide.with(context).load(apartments.get(position).getImage())
                       .centerCrop().into(viewHolder.apartmentImageView);
               

        return convertView;

    }


    @Override
    public void serviceFailure(Exception e) {

    }


    private class ViewHolder{
        TextView apartmentPriceTextView;
        ImageView apartmentImageView;
        TextView apartmentTitleTextView;
        CheckBox activeCheckBox;
        CheckBox passivCheckBox;


        public ViewHolder(View v){
            apartmentImageView = (ImageView) v.findViewById(R.id.apartmentImageView);
            apartmentPriceTextView = (TextView) v.findViewById(R.id.apartmentPriceTextView);
            apartmentTitleTextView = (TextView) v.findViewById(R.id.apartmentTitleTextView);
            activeCheckBox= (CheckBox) v.findViewById(R.id.activeCheckbox);
            passivCheckBox = (CheckBox) v.findViewById(R.id.passivCheckBox);
        }

    }
}
    