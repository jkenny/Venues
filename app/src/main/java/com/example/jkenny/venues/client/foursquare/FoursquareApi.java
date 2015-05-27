package com.example.jkenny.venues.client.foursquare;

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
            Callback<FoursquareResponseWrapper<VenueResponse>> callback);

    @GET("/venues/explore")
    void getVenues(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("v") String version,
            @Query("near") String near,
            @Query("query") String query,
            Callback<FoursquareResponseWrapper<ExploreResponse>> callback);
}
