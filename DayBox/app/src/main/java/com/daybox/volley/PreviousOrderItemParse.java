package com.daybox.volley;

import com.daybox.dto.ItemDTO;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2/29/2016.
 */
public class PreviousOrderItemParse {

    public static String[] id;
    public static String[] name;
    public static String[] qty;

    public static final String JSON_ARRAY = "response";
    public static final String KEY_ID = "id";
    public static final String KEY_QTY = "qty";
    public static final String KEY_NAME = "name";

    private JSONArray items = null;

    private String json;
    private Gson gson;
    private List<ItemDTO> list;

    public PreviousOrderItemParse(String json){
        this.json = json;
    }

    public void parseJSON(){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(json);
            items = jsonObject.getJSONArray(JSON_ARRAY);

            id = new String[items.length()];
            name = new String[items.length()];
            qty = new String [items.length()];

            for (int i = 0; i < items.length(); i++){
                JSONObject jsonObj = items.getJSONObject(i);
                id[i] = jsonObj.getString(KEY_ID);
                name[i] = jsonObj.getString(KEY_NAME);
                qty[i] = jsonObj.getString(KEY_QTY);
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

