package com.example.jkenny.venues.client.foursquare;

public class Photo {
    public String prefix;
    public String suffix;
    public int width;
    public int height;

    public String url() {
        return prefix + "width" + width + suffix;
    }

    Photo() {
    }
}
