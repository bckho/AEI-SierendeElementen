package com.bckho.sierendeelementen.presentation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bckho.sierendeelementen.models.Element;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = DetailActivity.class.getSimpleName();

    // Key for the getIntent bundle
    private static final String KEY = "Element";

    // Key for the Map component
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    // Received element
    private Element mElement;

    // Map component
    private MapView mMapView;

    // Screen components
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
        this.mElement = (Element) receivedExtras.getSerializable(KEY);
        Log.d(TAG, "onCreate: Element from Bundle_: " + mElement);

        // Set all components in the DetailActivity
        setComponents(mElement);

        // Mapview components
        initGoogleMaps(savedInstanceState);
    }


    // Searches for all components and initializes all of them
    private void initializeDetailObjects() {
        mTVTitle = findViewById(R.id.tv_detail_title);
        mTVGeolocation = findViewById(R.id.tv_detail_geo);
        mIVElementImage = findViewById(R.id.iv_detail_elementImage);
        mTVArtist = findViewById(R.id.tv_detail_artist);
        mTVDescription = findViewById(R.id.tv_detail_description);
        mTVMaterial = findViewById(R.id.tv_detail_material);
        mTVUnderlayer = findViewById(R.id.tv_detail_underlayer);
    }


    // Set all info of the received Element in the components
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


    // Initialized the map component
    private void initGoogleMaps(Bundle savedInstanceState) {
        Bundle mapviewBundle = null;
        if (savedInstanceState != null) {
            mapviewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mMapView = findViewById(R.id.mv_detail_mapView);
        mMapView.onCreate(mapviewBundle);

        mMapView.getMapAsync(this);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        Log.d(TAG, "onResume: ");
    }


    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
        Log.d(TAG, "onStart: ");
    }


    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
        Log.d(TAG, "onStop: ");
    }


    @Override
    public void onMapReady(GoogleMap map) {

        // creates a new location
        LatLng elementLocation = new LatLng(mElement.getLatitude(), mElement.getLongitude());

        // Shows a marker on the map of the location
        map.addMarker(new MarkerOptions()
                .position(elementLocation)
                .title(mElement.getTitle()));

        // Adds zoom in capabilities
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(elementLocation, 13.0f));

    }


    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
        Log.d(TAG, "onPause: ");
    }


    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
        Log.d(TAG, "onLowMemory: ");
    }
}
