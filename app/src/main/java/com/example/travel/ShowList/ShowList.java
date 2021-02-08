package com.example.travel.ShowList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.travel.BookData.BookDetails;
import com.example.travel.HomeList.Home;
import com.example.travel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity implements ShowListAdapter.onlesner {
    public static  ArrayList<ShowListModel> data = new ArrayList<>();

    public static  String url;

    private        RequestQueue requestQueue;

    public static   ShowListModel curnt_Trip;



    ShowListAdapter adapter;

    public SearchView searchView;

    public static void setUrl(String url) {
        ShowList.url = url;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        searchView=findViewById(R.id.search);
        requestQueue = Volley.newRequestQueue(this);
        curnt_Trip = new ShowListModel();


        RecyclerView rv;
        rv = findViewById(R.id.rv);
        adapter = new ShowListAdapter(data, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        if (!Home.fave) {
            load();
        }
    }

    @Override
    public void onlesnerclik(int position) {
        curnt_Trip = data.get(position);
        Intent intent = new Intent(this, BookDetails.class);
        startActivity(intent);
    }

    private void load() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray JA = response.getJSONArray("items");
                            for (int i = 0; i < JA.length(); i++) {
                                JSONObject item = JA.getJSONObject(i);
                                JSONObject volum = item.getJSONObject("volumeInfo");
                                String name = volum.getString("title");
                                String outher_name = volum.getString("publisher");
                                String img_url = volum.getJSONObject("imageLinks").getString("thumbnail");
                                String id = item.getString("id");
                                ShowListModel d = new ShowListModel(name, outher_name, img_url);
                                d.setBook_id(id);
                                try{
                                    d.setDiscretion(volum.getString("description"));
                                }catch (Exception e){

                                    d.setDiscretion("no discretion");
                                }
                                data.add(d);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }
}