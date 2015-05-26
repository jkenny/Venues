package com.example.jkenny.venues;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class VenueDetailActivity extends Activity {

    @InjectView(R.id.detail_image)
    ImageView image;
    @InjectView(R.id.detail_title)
    TextView title;
    @InjectView(R.id.detail_phrases)
    TextView phrases;
    @InjectView(R.id.detail_open_in_browser)
    Button openInBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        ButterKnife.inject(this);
        initButtons();
    }

    private void initButtons() {
        openInBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.gilt.com"));
                startActivity(intent);
            }
        });
    }
}
