package com.example.jkenny.venues.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.jkenny.venues.R;
import com.example.jkenny.venues.client.foursquare.Venue;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VenueAdapter extends BaseArrayAdapter<Venue, VenueAdapter.ViewHolder> {

    public VenueAdapter(Context context) {
        super(context, R.layout.row_venue, new ArrayList<Venue>());
    }

    @Override
    protected ViewHolder getViewHolder(View v, int position, Context c) {
        return new ViewHolder(v);
    }

    @Override
    protected void bindView(View v, int position, Context context) {
        Venue venue = getItem(position);
        ViewHolder vh = (ViewHolder)v.getTag();
        vh.venueName.setText(venue.name);
    }

    public static class ViewHolder {
        @InjectView(R.id.row_venue_name)
        TextView venueName;

        public ViewHolder(View v) {
            ButterKnife.inject(this, v);
        }
    }
}
