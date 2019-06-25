package com.bckho.sierendeelementen.presentation;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bckho.sierendeelementen.api.ElementAPIConnector;
import com.bckho.sierendeelementen.models.Element;
import com.bckho.sierendeelementen.adapters.ElementAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        ElementAPIConnector.ElementTaskListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = MainActivity.class.getSimpleName();

    // Arraylist for the loaded Elements
    private ArrayList<Element> mElementList = new ArrayList<>();

    // RecyclerView
    RecyclerView mRvElementRecyclerView;

    // Adapter for the RecyclerView
    private ElementAdapter mElementAdapter;

    // AsyncTask JSON parser
    ElementAPIConnector mAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mElementAdapter = new ElementAdapter(mElementList);
        mRvElementRecyclerView = findViewById(R.id.rv_list_items);

        // New layout for RecyclerView and set the adapter
        mRvElementRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRvElementRecyclerView.setAdapter(mElementAdapter);

        // New AsyncTask and set callback
        mAPI = new ElementAPIConnector();
        mAPI.setOnElementAvailableListener(this);

        // Executes AsyncTask
        mAPI.execute();

//        if (savedInstanceState == null) {
//            mAPI = new ElementAPIConnector();
//            mAPI.setOnElementAvailableListener(this);
//            fragment = new NonUITaskFragment();
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(fragment, "TaskFragment").commit();
//            mAPI.execute();
//        } else {
//            fragment = getSupportFragmentManager()
//                    .findFragmentByTag("TaskFragment");
//            mAPI.execute();
//        }

        if (savedInstanceState != null) {
            Bundle bundle = new Bundle();

        }

    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }


    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG, "onLoadFinished: ");
    }


    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset: ");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    public void OnElementInfoAvailable(ArrayList<Element> elementArrayList) {
        Log.d(TAG, "OnElementInfoAvailable: Called");
        Log.d(TAG, "OnElementInfoAvailable: ListSize_ " + elementArrayList.size());

        // Clears List for a new List
        this.mElementList.clear();
        this.mElementList.addAll(elementArrayList);

        Log.d(TAG, "OnElementInfoAvailable: AddAll_ size:" + this.mElementList.size());

        // Shows how many Elements have been stored in the ArrayList
        Toast.makeText(this,
                mElementList.size() + " items geladen",
                Toast.LENGTH_SHORT).show();

        this.mElementAdapter.notifyDataSetChanged();
    }
}
