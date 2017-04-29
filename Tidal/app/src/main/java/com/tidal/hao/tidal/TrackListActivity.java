package com.tidal.hao.tidal;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.tidal.hao.adapter.TrackAdapter;
import com.tidal.hao.app.AppController;
import com.tidal.hao.model.Track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hao on 4/29/2017.
 */

public class TrackListActivity extends Activity{
    private List<Track> tracks = new ArrayList<Track>();
    private ListView listView;
    private TrackAdapter adapter;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    NetworkImageView albumPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_list);

        listView = (ListView)findViewById(R.id.trackList);
        adapter = new TrackAdapter(this, tracks);
        listView.setAdapter(adapter);

        String albumPictureUrl = getIntent().getStringExtra("album_pic");
        albumPicture = (NetworkImageView)findViewById(R.id.trackAlbumPicture);
        albumPicture.setImageUrl(albumPictureUrl, imageLoader);
        String albumId = getIntent().getStringExtra("album_id");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, getUrl(albumId), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray artistsArray = response.getJSONArray("data");
                            for(int i = 0; i < artistsArray.length(); ++i){
                                try{
                                    JSONObject obj = artistsArray.getJSONObject(i);
                                    JSONObject artistObj = obj.getJSONObject("artist");
                                    String artist = artistObj.getString("name");
                                    Track track = new Track(obj.getString("title"), artist, obj.getInt("duration"));
                                    tracks.add(track);
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

    static String getUrl(String key) {
        return "http://api.deezer.com/album/" + key + "/tracks";
    }
}
