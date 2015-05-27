package com.example.jkenny.venues;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jkenny.venues.foursquare.FoursquareApi;
import com.example.jkenny.venues.foursquare.FoursquareApiConstants;
import com.example.jkenny.venues.foursquare.FoursquareResponseWrapper;
import com.example.jkenny.venues.foursquare.Venue;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class VenueDetailActivity extends Activity {

    @InjectView(R.id.detail_image)
    ImageView image;
    @InjectView(R.id.detail_title)
    TextView title;
    @InjectView(R.id.detail_open_in_browser)
    Button openInBrowser;

    FoursquareApi foursquareApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        ButterKnife.inject(this);
        initRestAdapter();
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

        foursquareApi.getVenue(FoursquareApiConstants.CLIENT_ID, FoursquareApiConstants.CLIENT_SECRET, FoursquareApiConstants.VERSION_DATE, "4c2b5abe355cef3bdd3fcd56", makeApiCallbackHandler());
    }

    private Callback<FoursquareResponseWrapper> makeApiCallbackHandler() {
        return new Callback<FoursquareResponseWrapper>() {
            @Override
            public void success(FoursquareResponseWrapper venue, Response response) {
                initDetails(venue.response.venue);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        };
    }

    @DebugLog
    private void initDetails(final Venue venue) {
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

    @DebugLog
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private void initRestAdapter() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://api.foursquare.com/v2/")
                .build();

        foursquareApi = adapter.create(FoursquareApi.class);
    }
}
