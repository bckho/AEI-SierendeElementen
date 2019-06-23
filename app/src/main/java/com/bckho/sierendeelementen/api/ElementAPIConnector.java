package com.bckho.sierendeelementen.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bckho.sierendeelementen.models.Element;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class ElementAPIConnector extends AsyncTask<Void, Void, String> {

    private static final String TAG = ElementAPIConnector.class.getSimpleName();

    private static final String BASE_URL = "https://services7.arcgis.com/21GdwfcLrnTpiju8/arcgis/rest/services/Sierende_elementen/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json";

    // All JSON attributes
    private final static String IDENTIFICATION = "IDENTIFICATIE";
    private final static String TITLE = "AANDUIDINGOBJECT";
    private final static String GEO = "GEOGRAFISCHELIGGING";
    private final static String ARTIST = "KUNSTENAAR";
    private final static String MATERIAL = "MATERIAAL";
    private final static String DESCRIPTION = "OMSCHRIJVING";
    private final static String UNDERLAYER = "ONDERGROND";
    private final static String IMAGEURL = "URL";

    private ElementTaskListener listener;


    public ElementAPIConnector() {
    }

    public void setOnElementAvailableListener(ElementTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: called");
        String jsonResponse = null;

        try {
            URL url = new URL(BASE_URL);
            URLConnection urlConnection = url.openConnection();

            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect();

            // check response code
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Received status 200: OK
                Log.d(TAG, "doInBackground: status_" + httpURLConnection.getResponseCode());

                InputStream in = httpURLConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    jsonResponse = scanner.next();
                }
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "Error: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Error: " + e.getMessage());
        }

        Log.d(TAG, "doInBackground: Response=" + jsonResponse);

        return jsonResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: ");
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        Log.d(TAG, "onPostExecute: " + response);

        ArrayList<Element> elementArrayList = new ArrayList<>();

        if (response == null || response.equals("")) {
            Log.e(TAG, "onPostExecute: empty response!");
            return;
        }

        //Parsing JSON input to Element object
        try {
            JSONObject object = new JSONObject(response);
            JSONArray results = object.getJSONArray("features");
            Log.d(TAG, "onPostExecute: " + results);

            for (int i = 0; i < results.length(); i++) {
                JSONObject jsonElement = results.getJSONObject(i);

                // Retrieve all Element info
                JSONObject attributes = jsonElement.getJSONObject("attributes");
                String identification = attributes.getString(IDENTIFICATION);
                String title = attributes.getString(TITLE);
                String geo = attributes.getString(GEO);
                String artist = attributes.getString(ARTIST);
                String description = attributes.getString(DESCRIPTION);
                String material = attributes.getString(MATERIAL);
                String underLayer = attributes.getString(UNDERLAYER);
                String imageUri = attributes.getString(IMAGEURL);

                // Retrieve geo location lat lon
                JSONObject geometry = jsonElement.getJSONObject("geometry");
                double lat = geometry.getDouble("x");
                double lon = geometry.getDouble("y");

                // New Element of the retrieved JSON
                Element element = new Element(identification,
                        title,
                        geo,
                        artist,
                        material,
                        underLayer,
                        description,
                        imageUri,
                        lat,
                        lon);

                // Add the new Element to the ArrayList
                elementArrayList.add(element);
            }

            // Callback to indicate new ArrayList with Elements has been created
            listener.OnElementInfoAvailable(elementArrayList);

        } catch (JSONException e) {
            Log.e(TAG, "onPostExecute: " + e);
            e.printStackTrace();
        }
    }

    // OnElementInfoAvailable() functions as a callback for the MainActivity class
    public interface ElementTaskListener {

        void OnElementInfoAvailable(ArrayList<Element> elementArrayList);

    }
}
