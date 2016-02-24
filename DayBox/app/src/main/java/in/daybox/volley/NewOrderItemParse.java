package in.daybox.volley;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import in.daybox.dto.ItemDTO;

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
    private Gson gson;
    private List<ItemDTO> list;

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
                gson = new Gson();
                ItemDTO itemDTO = gson.fromJson(jsonObj.toString(), ItemDTO.class);
                list = new ArrayList<ItemDTO>();
                list.add(itemDTO);
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
