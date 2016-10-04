package com.angkotan.dot.angkotan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 17/08/16.
 */
public class TutParseJSON {
    public static String[] ids;
    public static String[] angkot;
    public static String[] emails;

    public static final String JSON_ARRAY = "list_event";
    public static final String KEY_ID = "id";
    public static final String KEY_ANGKOT = "angkot";
    //public static final String KEY_EMAIL = "email";

    private JSONArray users = null;

    private String json;

    public TutParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[users.length()];
            angkot = new String[users.length()];
            //emails = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                angkot[i] = jo.getString(KEY_ANGKOT);
                //emails[i] = jo.getString(KEY_EMAIL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
