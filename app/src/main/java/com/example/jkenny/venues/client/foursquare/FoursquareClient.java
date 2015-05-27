package com.example.jkenny.venues.client.foursquare;

import retrofit.RestAdapter;

public class FoursquareClient {
    private static FoursquareApi instance = createApi();

    public static FoursquareApi getInstance() {
        return instance;
    }

    private static FoursquareApi createApi() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://api.foursquare.com/v2/")
                .build();

        return adapter.create(FoursquareApi.class);
    }
}
