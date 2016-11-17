package creatingnew.kz.auabnb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Алишер on 14.06.2016.
 */
public class CityAdapter extends BaseAdapter {

    private List<City> cities;
    private Context context;
    private LayoutInflater inflater;

    public CityAdapter(List<City> cities, Context context) {
        this.cities = cities;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_city_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.cityTitleTextView.setText(cities.get(position).getTitle());
        Glide.with(context).load(cities.get(position).getImage()).centerCrop().into(viewHolder.cityImageView);

        return convertView;
    }

    private class ViewHolder{
        ImageView cityImageView;
        TextView cityTitleTextView;

        public ViewHolder(View v){

            cityImageView = (ImageView) v.findViewById(R.id.cityImageView);
            cityTitleTextView = (TextView) v.findViewById(R.id.cityTitleTextView);


        }

    }

}
