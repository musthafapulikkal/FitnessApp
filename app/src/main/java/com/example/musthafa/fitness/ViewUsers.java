package com.example.musthafa.fitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewUsers extends AppCompatActivity {
    ListView listView;

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);


        RequestQueue requestQueue=Volley.newRequestQueue(ViewUsers.this);
        String Url="http://www.slight.fabstudioz.com/fitness/viewusers.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    JSONObject obj=new JSONObject(response);
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> arrayList=new ArrayList<>();
                    int length = jsonArray.length();
                    for(int i = 0 ; i < length ; i++){
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String username=jsonObject.optString("user name");
                        arrayList.add(username);
//                        arrayList.notify();
//                        complaintlist.add(jsonArray.getJSONObject(i).getString("complaint"));
                    }
                    listView=(ListView)findViewById(R.id.listview_users_id);
                    arrayAdapter=new ArrayAdapter<String>(ViewUsers.this,android.R.layout.simple_list_item_1,arrayList);
                    listView.setAdapter(arrayAdapter);

//                    arrayList.add(response);
//                    obj.getString("user name");

//                    while (obj.length()>0)
//                    {


                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                arrayList.notify();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }
}
