package com.tidal.hao.tidal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tidal.hao.adapter.ArtistAdapter;
import com.tidal.hao.app.AppController;
import com.tidal.hao.model.Artist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ArtistListActivity extends Activity {

    private ProgressDialog pDialog;
    private List<Artist>  artists = new ArrayList<Artist>();
    private ListView listView;
    private ArtistAdapter adapter;
    private AutoCompleteTextView artistSearch;

    static String[] artistsHint = {"adele","beyonce","prince","taylor swift"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_list);

        listView = (ListView) findViewById(R.id.list);
        artistSearch = (AutoCompleteTextView) findViewById(R.id.artistSearch);
        adapter = new ArtistAdapter(this, artists);
        listView.setAdapter(adapter);

        ArrayAdapter<String> artistHintAdapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, artistsHint);
        artistSearch.setAdapter(artistHintAdapter);
        artistSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, getUrl(artistSearch.getText().toString()), null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                artists.clear();
                                try {
                                    JSONArray artistsArray = response.getJSONArray("data");
                                    for(int i = 0; i < artistsArray.length(); ++i){
                                        try{
                                            JSONObject obj = artistsArray.getJSONObject(i);
                                            JSONObject artistObj = obj.getJSONObject("artist");
                                            Artist artist = new Artist(artistObj.getString("picture_small"), artistObj.getString("name"), artistObj.getInt("id"));
                                            artists.add(artist);
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
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent albumListActivity = new Intent(getApplicationContext(), AlbumListActivity.class);
                albumListActivity.putExtra("artist", artists.get(position).getId());
                startActivity(albumListActivity);
            }
        });

    }

    static String getUrl(String key){
        try {
            return "http://api.deezer.com/search?q=artist:" + "\"" + URLEncoder.encode(key, "UTF-8") + "\"";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
