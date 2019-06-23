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

public class DetailActivity extends AppCompatActivity{

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
        Log.d(TAG, "onCreate: Bundle_:" + receivedExtras);

        assert receivedExtras != null;
        Element element = (Element) receivedExtras.getSerializable(KEY);
        Log.d(TAG, "onCreate: Element from Bundle_: " + element);

        // Set all components in the DetailActivity
        setComponents(element);

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

    private void setComponents(Element element) {
        Log.d(TAG, "setComponents: " + element);
        Picasso.get().load(Uri.parse(element.getImageUri())).into(mIVElementImage);

        if (element.getTitle().equals("null") || element.getTitle().equals(""))
            mTVTitle.setText(R.string.nl_unknownTitle);
        else mTVTitle.setText(element.getTitle());

        if (element.getGeographicalLocation().equals("null") || element.getGeographicalLocation().equals(""))
            mTVGeolocation.setText(R.string.nl_unknownLocation);
        else mTVGeolocation.setText(element.getGeographicalLocation());

        if (element.getArtist().equals("null") || element.getArtist().equals(""))
            mTVArtist.setText(R.string.nl_unknownArtist);
        else mTVArtist.setText(element.getArtist());

        if (element.getDescription().equals("null") || element.getDescription().equals(""))
            mTVDescription.setText(R.string.nl_unknownDescription);
        else mTVDescription.setText(element.getDescription());

        if (element.getMaterial().equals("null") || element.getMaterial().equals(""))
            mTVMaterial.setText(R.string.nl_unknownMaterial);
        else mTVMaterial.setText(element.getMaterial());

        if (element.getUnderLayer().equals("null") || element.getUnderLayer().equals(""))
            mTVUnderlayer.setText(R.string.nl_unknownUnderlayer);
        else mTVUnderlayer.setText(element.getUnderLayer());
    }
}
