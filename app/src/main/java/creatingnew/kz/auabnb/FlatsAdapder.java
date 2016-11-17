package creatingnew.kz.auabnb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Алишер on 20.06.2016.
 */
public class FlatsAdapder {
/*
    private Context context;
    private Backendless backendless;
    private List<Flats> flatsList;
    private LayoutInflater inflater;
    private List<City> cities;

    public FlatsAdapder(Context context, List<Flats> flatsList) {
        this.context = context;
        this.flatsList = flatsList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    @Override
    public int getCount() {
        return flatsList.size();
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
        ViewHolder viewHolder =null;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.swipe_layout,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cityNameTextView.setText(cities.get(position).getTitle());
        viewHolder.swipeTextView.setText(flatsList.get(position).getPrice() + " тг");

        DrawableRequestBuilder<String> builder = (DrawableRequestBuilder<String>)
                Glide.with(context)
                        .load(flatsList
                                .get(position)
                                .getImages())
                        .dontTransform()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.slideImageView);


        return convertView;
    }



    private class ViewHolder{

        TextView swipeTextView;
        TextView cityNameTextView;
        ImageView slideImageView;

        public ViewHolder(View v){
            swipeTextView = (TextView) v.findViewById(R.id.swipeTextView);
            cityNameTextView = (TextView) v.findViewById(R.id.cityNameTextView);
            slideImageView = (ImageView) v.findViewById(R.id.slideImageView);
        }


    }


*/
}
