package com.example.jkenny.venues.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jkenny.venues.R;
import com.example.jkenny.venues.client.foursquare.ExploreGroup;
import com.example.jkenny.venues.client.foursquare.ExploreItem;
import com.example.jkenny.venues.client.foursquare.ExploreResponse;
import com.example.jkenny.venues.client.foursquare.FoursquareApiConstants;
import com.example.jkenny.venues.client.foursquare.FoursquareClient;
import com.example.jkenny.venues.client.foursquare.FoursquareResponseWrapper;
import com.example.jkenny.venues.client.foursquare.Venue;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class VenueListActivity extends Activity implements ListView.OnItemClickListener, View.OnClickListener {

    @InjectView(R.id.master_list)
    ListView venuesListView;

    @InjectView(R.id.list_search)
    Button searchButton;

    @InjectView(R.id.list_near)
    TextView nearInput;

    @InjectView(R.id.list_query)
    TextView queryInput;

    VenueAdapter venueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_list);
        ButterKnife.inject(this);
        venueAdapter = new VenueAdapter(this);
        venuesListView.setAdapter(venueAdapter);
        venuesListView.setOnItemClickListener(this);
        searchButton.setOnClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Venue venue = venueAdapter.getItem(position);
        Intent intent = VenueDetailActivity.makeIntent(this, venue);
        startActivity(intent);
    }

    @DebugLog
    @Override
    public void onClick(View v) {
        String near = nearInput.getText().toString();
        String query = queryInput.getText().toString();

        FoursquareClient.getInstance().getVenues(FoursquareApiConstants.CLIENT_ID, FoursquareApiConstants.CLIENT_SECRET, FoursquareApiConstants.VERSION_DATE,
                near, query,
                new Callback<FoursquareResponseWrapper<ExploreResponse>>() {
                    @Override
                    public void success(FoursquareResponseWrapper<ExploreResponse> foursquareResponseWrapper, Response response) {
                        List<Venue> venues = new ArrayList<>();
                        for (ExploreGroup group : foursquareResponseWrapper.response.groups) {
                            for (ExploreItem item : group.items) {
                                venues.add(item.venue);
                            }
                        }

                        venueAdapter.clear();
                        venueAdapter.addAll(venues);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }
}
