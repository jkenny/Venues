package com.example.jkenny.venues.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jkenny.venues.R;
import com.example.jkenny.venues.client.foursquare.ExploreGroup;
import com.example.jkenny.venues.client.foursquare.ExploreItem;
import com.example.jkenny.venues.client.foursquare.ExploreResponse;
import com.example.jkenny.venues.client.foursquare.FoursquareApi;
import com.example.jkenny.venues.client.foursquare.FoursquareApiConstants;
import com.example.jkenny.venues.client.foursquare.FoursquareResponseWrapper;
import com.example.jkenny.venues.client.foursquare.Venue;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class VenueListActivity extends Activity implements ListView.OnItemClickListener {

    @InjectView(R.id.master_list)
    ListView venuesListView;

    VenueAdapter venueAdapter;
    FoursquareApi foursquareApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_list);
        ButterKnife.inject(this);
        venueAdapter = new VenueAdapter(this);
        venuesListView.setAdapter(venueAdapter);
        venuesListView.setOnItemClickListener(this);
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

        foursquareApi.getVenues(FoursquareApiConstants.CLIENT_ID, FoursquareApiConstants.CLIENT_SECRET, FoursquareApiConstants.VERSION_DATE, "Dublin,Ireland", "", new Callback<FoursquareResponseWrapper<ExploreResponse>>() {
            @Override
            public void success(FoursquareResponseWrapper<ExploreResponse> foursquareResponseWrapper, Response response) {
                List<Venue> venues = new ArrayList<>();
                for (ExploreGroup group : foursquareResponseWrapper.response.groups) {
                    for (ExploreItem item : group.items) {
                        venues.add(item.venue);
                    }
                }

                venueAdapter.addAll(venues);
            }

            @Override
            public void failure(RetrofitError error) {

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

    private void initRestAdapter() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://api.foursquare.com/v2/")
                .build();

        foursquareApi = adapter.create(FoursquareApi.class);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Venue venue = venueAdapter.getItem(position);
        Intent intent = VenueDetailActivity.makeIntent(this, venue);
        startActivity(intent);
    }
}
