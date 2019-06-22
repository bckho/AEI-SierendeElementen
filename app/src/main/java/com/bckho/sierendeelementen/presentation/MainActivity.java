package com.bckho.sierendeelementen.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bckho.sierendeelementen.api.ElementAPIConnector;
import com.bckho.sierendeelementen.models.Element;
import com.bckho.sierendeelementen.models.ElementAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ElementAPIConnector.ElementTaskListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Element> mElementList;
    private RecyclerView rvElementRecyclerView;
    private ElementAdapter mElementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mElementAdapter = new ElementAdapter(mElementList);
        rvElementRecyclerView = findViewById(R.id.rv_list_items);
        rvElementRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvElementRecyclerView.setAdapter(mElementAdapter);

        ElementAPIConnector connector = new ElementAPIConnector();
        connector.setOnElementAvailableListener(this);

        connector.execute();
    }

    @Override
    public void OnElementInfoAvailable(ArrayList<Element> elementArrayList) {
        Log.d(TAG, "OnElementInfoAvailable: Called");
        Log.d(TAG, "OnElementInfoAvailable: ListSize_ " + elementArrayList.size());

        this.mElementList.clear();
        this.mElementList.addAll(elementArrayList);

        this.mElementAdapter.notifyDataSetChanged();

    }
}
