package com.a000webhostapp.httpsquandt.timezone;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import location.Location;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LocationAddListener} interface
 * to handle interaction events.
 * Use the {@link LocationAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationAddFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LOCATION_FIND_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
    private static final String LOCATION_ADD_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
    private static final String GOOGLE_API_KEY = "AIzaSyCTMYAa5cUjAODP12X_U9tuFsri588cRpc";
    private static final String LOCATION_ADD_DEBUG = "LocationAddFragment: ";


    private EditText mLocationCityEditText;
    private EditText mLocationSurroundLocality;

    private Location mLocation;
    private LocationAddListener mListener;

    public LocationAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationAddFragment newInstance(String param1, String param2) {
        LocationAddFragment fragment = new LocationAddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_location_add, container, false);

        mLocationCityEditText = (EditText) v.findViewById(R.id.text_field_add_city);
        mLocationSurroundLocality = (EditText) v.findViewById(R.id.text_field_add_surrounding_locality);
        Button addLocationButton = (Button) v.findViewById(R.id.btn_find_location_details);
        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = buildFindLocationURL(v);
                FindLocationAsyncTask task = new FindLocationAsyncTask();
                task.execute(new String[]{url.toString()});

                url = buildAddLocationURL(v);
                AddLocationAsyncTask addTask = new AddLocationAsyncTask();
                addTask.execute(new String[]{url.toString()});

                mListener.addLocation(mLocation);
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LocationAddListener) {
            mListener = (LocationAddListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LocationAddListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private String buildFindLocationURL(View v) {

        StringBuilder sb = new StringBuilder(LOCATION_FIND_URL);

        try {

            String courseId = mLocationCityEditText.getText().toString();
            sb.append("address=");
            sb.append(URLEncoder.encode(courseId, "UTF-8"));


            String courseShortDesc = mLocationSurroundLocality.getText().toString();
            sb.append(",&");
            sb.append(URLEncoder.encode(courseShortDesc, "UTF-8"));

            sb.append("&key=");
            sb.append(GOOGLE_API_KEY);


            Log.i(TAG, sb.toString());

        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        Log.v(LOCATION_ADD_DEBUG, sb.toString());
        return sb.toString();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface LocationAddListener {
        void addLocation(Location location);
    }


    private class FindLocationAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to add Location, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }

            }
            return response;
        }

        /**
         * It checks to see if there was a problem with the URL(Network) which is when an
         * exception is caught. It tries to call the parse Method and checks to see if it was successful.
         * If not, it displays the exception.
         *
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            try {
                Log.i("WEBSERVRESULT--------", result);
                mLocation = Location.parseLocationJSON(result);
            }
            catch (JSONException e) {
                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        }
    }


    private String buildAddLocationURL(View v) {

        StringBuilder sb = new StringBuilder(LOCATION_ADD_URL);

        try {

            String courseId = mLocationCityEditText.getText().toString();
            sb.append("address=");
            sb.append(URLEncoder.encode(courseId, "UTF-8"));


            String courseShortDesc = mLocationSurroundLocality.getText().toString();
            sb.append(",&");
            sb.append(URLEncoder.encode(courseShortDesc, "UTF-8"));

            sb.append("&key=");
            sb.append(GOOGLE_API_KEY);


            Log.i(TAG, sb.toString());

        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        Log.v(LOCATION_ADD_DEBUG, sb.toString());
        return sb.toString();
    }

    private class AddLocationAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to add Location, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }

            }
            return response;
        }

        /**
         * It checks to see if there was a problem with the URL(Network) which is when an
         * exception is caught. It tries to call the parse Method and checks to see if it was successful.
         * If not, it displays the exception.
         *
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            try {
                Log.i("WEBSERVRESULT--------", result);
                mLocation = Location.parseLocationJSON(result);
            }
            catch (JSONException e) {
                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        }
    }
}
