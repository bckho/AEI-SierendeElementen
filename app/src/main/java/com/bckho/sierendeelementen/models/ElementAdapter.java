package com.bckho.sierendeelementen.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bckho.sierendeelementen.presentation.DetailActivity;
import com.bckho.sierendeelementen.presentation.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ElementViewHolder> {

    private static final String TAG = ElementAdapter.class.getSimpleName();

    private static final String KEY = "Element";

    private ArrayList<Element> mElementList;

    public ElementAdapter(ArrayList<Element> mElements) {
        this.mElementList = mElements;
    }

    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Log.d(TAG, "onCreateViewHolder: " + i);

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemlist = inflater.inflate(R.layout.layout_listitem, viewGroup, false);
        return new ElementViewHolder(itemlist);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementViewHolder holder, int position) {

        // Element on the position of the parameter i
        Element element = this.mElementList.get(position);

        // Search all components from R.layout.layout.listitem for pairing data
        holder.title.setText(element.getTitle());
        holder.geo.setText(element.getIdentification());
        holder.identification.setText(element.getGeographicalLocation());

        Picasso.get().load(Uri.parse(element.getImageUri()));
    }

    @Override
    public int getItemCount() {
        return this.mElementList.size();
    }

    // Each item in the recyclerView
    protected class ElementViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ImageView image;
        TextView title;
        TextView geo;
        TextView identification;

        public ElementViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.iv_list_icon);
            title = itemView.findViewById(R.id.tv_list_title);
            geo = itemView.findViewById(R.id.tv_list_geo);
            identification = itemView.findViewById(R.id.tv_list_identification);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: " + getAdapterPosition());

            // Detail screen
            Intent intent = new Intent(v.getContext(), DetailActivity.class);

            // Put content in intent
            intent.putExtra(KEY, (Serializable) mElementList.get(getAdapterPosition()));
            v.getContext().startActivity(intent);
        }
    }
}
