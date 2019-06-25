package com.bckho.sierendeelementen.adapters;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bckho.sierendeelementen.models.Element;
import com.bckho.sierendeelementen.presentation.DetailActivity;
import com.bckho.sierendeelementen.presentation.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ElementViewHolder> {

    private static final String TAG = ElementAdapter.class.getSimpleName();

    // Key for serializing Element for the Intent
    private static final String KEY = "Element";

    // ArrayList for all loaded elements
    private ArrayList<Element> mElementList;

    public ElementAdapter(ArrayList<Element> mElements) {
        this.mElementList = mElements;
    }


    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Log.d(TAG, "onCreateViewHolder: " + i);

        // Get context from ViewGroup
        Context context = viewGroup.getContext();

        // Set layout inflater from context
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemlist = inflater.inflate(R.layout.layout_listitem, viewGroup, false);
        return new ElementViewHolder(itemlist);
    }


    @Override
    public void onBindViewHolder(@NonNull ElementViewHolder holder, int position) {

        // Element on the position of the parameter i
        Element element = this.mElementList.get(position);

        // Search all components from R.layout.layout.listitem for pairing data
        Picasso.get().load(element.getImageUri()).into(holder.image);
        holder.title.setText(element.getTitle());
        holder.geo.setText(element.getGeographicalLocation());
        holder.identification.setText(element.getIdentification());
    }


    @Override
    public int getItemCount() {
        return this.mElementList.size();
    }

    // Each item in the recyclerView
    protected class ElementViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        // Components for the list
        ImageView image;
        TextView title;
        TextView geo;
        TextView identification;


        public ElementViewHolder(@NonNull View itemView) {
            super(itemView);

            // Get all components from itemView
            image = itemView.findViewById(R.id.iv_list_icon);
            title = itemView.findViewById(R.id.tv_list_title);
            geo = itemView.findViewById(R.id.tv_list_geo);
            identification = itemView.findViewById(R.id.tv_list_identification);

            // Set onClickListener for the item
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: " + getAdapterPosition());

            // Detail screen
            Intent intent = new Intent(v.getContext(), DetailActivity.class);

            // Put content in intent
            intent.putExtra(KEY, mElementList.get(getAdapterPosition()));
            v.getContext().startActivity(intent);
        }
    }
}
