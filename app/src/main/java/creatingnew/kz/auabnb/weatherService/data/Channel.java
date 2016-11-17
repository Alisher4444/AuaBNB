package creatingnew.kz.auabnb.weatherService.data;

import org.json.JSONObject;

/**
 * Created by Alisher on 13.09.2016.
 */
public class Channel implements JSONPopulator{

    private Units units;
    private Item item;
    private String location;

    public String getLocation() {
        return location;
    }

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));



    }
}
