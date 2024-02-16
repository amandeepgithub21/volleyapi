package com.example.volleyapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.onitemclicklistner {
    public  static  final String EXTRA_URL="imagrurl";
    public  static  final String EXTRA_CREATOR="creatorname";
    public  static  final String EXTRA_LIKES="likecount";
private RecyclerView mrecyclerview;
private ExampleAdapter mexampleadapter;

private ArrayList<ExampleItem> mexamplelist;
private RequestQueue mrequestqueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mrecyclerview=findViewById(R.id.recyclerview);
mrecyclerview.setHasFixedSize(true);
mrecyclerview.setLayoutManager(new LinearLayoutManager(this));

mexamplelist=new ArrayList<>();
mrequestqueue= Volley.newRequestQueue(this);

parseJSON();

    }
private void parseJSON(){
      String url="https://pixabay.com/api/?key=42366819-4b1ddae3beb6814fcd8452d86&q=yellow+flowers&image_type=photo";
    JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONArray jsonArray=response.getJSONArray("hits");
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject hit=jsonArray.getJSONObject(i);
                    String creatorname=hit.getString("user");
                    String imageurl=hit.getString("webformatURL");
                    String likecount=hit.getString("likes");
                    mexamplelist.add(new ExampleItem(imageurl,creatorname,likecount));
                }
                mexampleadapter=new ExampleAdapter(MainActivity.this,mexamplelist);
                mrecyclerview.setAdapter(mexampleadapter);
                mexampleadapter.setonitemclicklistner(MainActivity.this);
                Log.d("TAG", "onResponse: "+response);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            Log.d("TAG", "onErrorResponse: "+ error.getMessage());
        }
    });

mrequestqueue.add(request);
    }

    @Override
    public void onitemclick(int posistion) {
        Intent intent=new Intent(MainActivity.this,DetailActivity.class);
        ExampleItem clickitem=mexamplelist.get(posistion);
        intent.putExtra(EXTRA_URL,clickitem.getImageurl());
        intent.putExtra(EXTRA_CREATOR,clickitem.getcreator());
        intent.putExtra(EXTRA_LIKES,clickitem.getlikes());
        startActivity(intent);
    }


}
