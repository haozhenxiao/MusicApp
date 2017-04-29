package com.tidal.hao.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tidal.hao.model.Track;
import com.tidal.hao.tidal.R;

import java.util.List;

/**
 * Created by hao on 4/29/2017.
 */

public class TrackAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<Track> tracks;

    public TrackAdapter(Activity activity, List<Track> tracks){
        this.activity = activity;
        this.tracks = tracks;
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Track getItem(int position) {
        return tracks.get(position);
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
            convertView = inflater.inflate(R.layout.track_list_row, null);

        TextView trackIndex = (TextView)convertView.findViewById(R.id.track_index);
        TextView trackName = (TextView)convertView.findViewById(R.id.track_name);
        TextView trackArtist = (TextView)convertView.findViewById(R.id.track_artist_name);
        TextView trackDuration = (TextView)convertView.findViewById(R.id.track_duration);

        Track track = tracks.get(position);
        trackIndex.setText(String.valueOf(position + 1));
        trackName.setText(track.getTitle());
        trackArtist.setText(track.getArtist());
        trackDuration.setText(track.getDuration());

        return convertView;
    }
}
