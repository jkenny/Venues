package com.example.jkenny.venues.foursquare;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface FoursquareApi {

    @GET("/venues/{id}")
    void getVenue(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("v") String version,
            @Path("id") String venueId,
            Callback<FoursquareResponseWrapper> callback);
}
