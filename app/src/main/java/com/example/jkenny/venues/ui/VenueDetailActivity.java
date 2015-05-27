package com.example.jkenny.venues.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jkenny.venues.R;
import com.example.jkenny.venues.client.foursquare.FoursquareApiConstants;
import com.example.jkenny.venues.client.foursquare.FoursquareClient;
import com.example.jkenny.venues.client.foursquare.FoursquareResponseWrapper;
import com.example.jkenny.venues.client.foursquare.Venue;
import com.example.jkenny.venues.client.foursquare.VenueResponse;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class VenueDetailActivity extends Activity {

    @InjectView(R.id.detail_image)
    ImageView image;
    @InjectView(R.id.detail_title)
    TextView title;
    @InjectView(R.id.detail_open_in_browser)
    Button openInBrowser;

    private static final String EXTRA_VENUE_ID = "com.example.jkenny.venues.venue.id";

    public static Intent makeIntent(Context context, Venue venue) {
        Intent intent = new Intent(context, VenueDetailActivity.class);
        intent.putExtra(EXTRA_VENUE_ID, venue.id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        ButterKnife.inject(this);
    }

    @DebugLog
    @Override
    protected void onStart() {
        super.onStart();
    }

    @DebugLog
    @Override
    protected void onResume() {
        super.onResume();

        FoursquareClient.getInstance().getVenue(FoursquareApiConstants.CLIENT_ID, FoursquareApiConstants.CLIENT_SECRET, FoursquareApiConstants.VERSION_DATE, getIntent().getStringExtra(EXTRA_VENUE_ID), new Callback<FoursquareResponseWrapper<VenueResponse>>() {
            @Override
            public void success(FoursquareResponseWrapper<VenueResponse> venue, Response response) {
                initDetails(venue.response.venue);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @DebugLog
    private void initDetails(final Venue venue) {
        if (title != null) {
            title.setText(venue.name);
            Picasso.with(this).load(venue.bestPhoto.url()).into(image);
            openInBrowser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(venue.canonicalUrl));
                    startActivity(intent);
                }
            });
        }
    }

    @DebugLog
    @Override
    protected void onPause() {
        super.onPause();
    }

    @DebugLog
    @Override
    protected void onStop() {
        super.onStop();
    }

    @DebugLog
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
