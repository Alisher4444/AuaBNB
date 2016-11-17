package creatingnew.kz.auabnb.weatherService.data;

import org.json.JSONObject;

/**
 * Created by Alisher on 13.09.2016.
 */
public class Item implements JSONPopulator {

    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {

        condition= new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}
