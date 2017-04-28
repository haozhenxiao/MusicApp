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
import com.tidal.hao.model.Album;
import com.tidal.hao.tidal.R;

import java.util.List;

/**
 * Created by hao on 4/28/2017.
 */

public class AlbumAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Album> albums;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public AlbumAdapter(Activity activity, List<Album> albums){
        this.activity = activity;
        this.albums = albums;
    }

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public Album getItem(int position) {
        return albums.get(position);
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
            convertView = inflater.inflate(R.layout.album_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView albumPicture = (NetworkImageView)convertView.findViewById(R.id.albumPicture);
        TextView albumName = (TextView)convertView.findViewById(R.id.albumName);

        Album album = albums.get(position);
        albumPicture.setImageUrl(album.getAlbumImage(), imageLoader);
        albumName.setText(album.getAlbumName());

        return convertView;
    }
}
