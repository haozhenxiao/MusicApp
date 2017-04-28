package com.tidal.hao.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tidal.hao.app.AppController;
import com.tidal.hao.model.Artist;
import com.tidal.hao.tidal.R;

import java.util.List;

/**
 * Created by hao on 4/26/2017.
 */

public class ArtistAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<Artist> artists;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ArtistAdapter(Activity activity, List<Artist> artists){
        this.activity = activity;
        this.artists = artists;
    }

    @Override
    public int getCount() {
        return artists.size();
    }

    @Override
    public Artist getItem(int position) {
        return artists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.artist_list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView artistPicture = (NetworkImageView)convertView.findViewById(R.id.artistPicture);
        TextView artistName = (TextView)convertView.findViewById(R.id.artistName);

        Artist artist = artists.get(position);
        artistPicture.setImageUrl(artist.getProfilePicture(), imageLoader);
        artistName.setText(artist.getName());

        return convertView;
    }
}
