package in.daybox.volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dell on 2/20/2016.
 */
public class NewOrderItemParse {

    public static String[] id;
    public static String[] name;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";

    private JSONArray items = null;

    private String json;

    public NewOrderItemParse(String json){
        this.json = json;
    }

    public void parseJSON(){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(json);
            items = jsonObject.getJSONArray(JSON_ARRAY);

            id = new String[items.length()];
            name = new String[items.length()];

            for (int i = 0; i < items.length(); i++){
                JSONObject jsonObj = items.getJSONObject(i);
                id[i] = jsonObj.getString(KEY_ID);
                name[i] = jsonObj.getString(KEY_NAME);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
