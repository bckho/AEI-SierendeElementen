package com.bckho.sierendeelementen.presentation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bckho.sierendeelementen.models.Element;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private static final String KEY = "Element";

    TextView mTVTitle;
    TextView mTVGeolocation;
    ImageView mIVElementImage;
    TextView mTVArtist;
    TextView mTVDescription;
    TextView mTVMaterial;
    TextView mTVUnderlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent getIntent = getIntent();
        Log.d(TAG, "onCreate: getIntent" + getIntent);

        // GET OBJECT
        initializeDetailObjects();

        Bundle receivedExtras = getIntent.getExtras();

        assert receivedExtras != null;
        Element element = (Element) receivedExtras.getSerializable(KEY);

        mTVTitle.setText(element.getTitle());
        mTVGeolocation.setText(element.getGeographicalLocation());
        Picasso.get().load(Uri.parse(element.getImageUri())).into(mIVElementImage);
        mTVArtist.setText(element.getArtist());
        mTVDescription.setText(element.getDescription());
        mTVMaterial.setText(element.getMaterial());
        mTVUnderlayer.setText(element.getUnderLayer());
    }

    private void initializeDetailObjects() {
        mTVTitle = findViewById(R.id.tv_detail_title);
        mTVGeolocation = findViewById(R.id.tv_detail_geo);
        mIVElementImage = findViewById(R.id.iv_detail_elementImage);
        mTVArtist = findViewById(R.id.tv_detail_artist);
        mTVDescription = findViewById(R.id.tv_detail_description);
        mTVMaterial = findViewById(R.id.tv_detail_material);
        mTVUnderlayer = findViewById(R.id.tv_detail_underlayer);
    }
}
