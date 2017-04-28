package com.tidal.hao.tidal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tidal.hao.adapter.AlbumAdapter;
import com.tidal.hao.app.AppController;
import com.tidal.hao.model.Album;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.tidal.hao.tidal.R.id.artistSearch;

/**
 * Created by hao on 4/28/2017.
 */

public class AlbumListActivity extends Activity {

    private List<Album> albums = new ArrayList<Album>();
    private GridView gridView;
    private AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_list);

        gridView = (GridView)findViewById(R.id.albumList);
        adapter = new AlbumAdapter(this, albums);
        gridView.setAdapter(adapter);

        int artist = getIntent().getIntExtra("artist", 0);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, getUrl(artist), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray artistsArray = response.getJSONArray("data");
                            for(int i = 0; i < artistsArray.length(); ++i){
                                try{
                                    JSONObject obj = artistsArray.getJSONObject(i);
                                    Album album = new Album(obj.getString("cover_medium"), obj.getString("title"));
                                    albums.add(album);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Error", "Error: " + error.getMessage());
                    }
                });
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }

    static String getUrl(int key) {
        return "http://api.deezer.com/artist/" + key + "/albums";
    }
}
