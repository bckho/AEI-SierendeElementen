package com.bckho.sierendeelementen.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bckho.sierendeelementen.api.ElementAPIConnector;
import com.bckho.sierendeelementen.models.Element;
import com.bckho.sierendeelementen.models.ElementAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ElementAPIConnector.ElementTaskListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Element> mElementList = new ArrayList<>();
    RecyclerView mRvElementRecyclerView;
    private ElementAdapter mElementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mElementAdapter = new ElementAdapter(mElementList);
        mRvElementRecyclerView = findViewById(R.id.rv_list_items);
        mRvElementRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRvElementRecyclerView.setAdapter(mElementAdapter);

        // New AsyncTask
        ElementAPIConnector connector = new ElementAPIConnector();
        connector.setOnElementAvailableListener(this);

        // Executes AsyncTask
        connector.execute();
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
