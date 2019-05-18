package com.example.musthafa.fitness;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewTrainers extends AppCompatActivity {

    private List<Trainers> trainerlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private TrainerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trainers);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_trainers);

        mAdapter = new TrainerAdapter(trainerlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        RequestQueue requestQueue=Volley.newRequestQueue(ViewTrainers.this);
        String Url="http://www.slight.fabstudioz.com/fitness/viewtrainers.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int length = jsonArray.length();
                    Context context=getApplicationContext();

                    for(int i = 0 ; i < length ; i++) {
                        JSONObject obj = (JSONObject) jsonArray.get(i);
                        String trainer_name = obj.optString("Name");
                        //Log.v("A", prod_name);
                        String trainer_age = obj.optString("Age");
                        String trainer_phone = obj.optString("Phone");
                        String image = obj.optString("image");

                        Log.v("imagee", image);
                        Trainers trainers = new Trainers(trainer_name, trainer_age, trainer_phone, image, context);
                        trainerlist.add(trainers);
                        mAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
