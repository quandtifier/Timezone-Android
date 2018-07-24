package location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Location implements Serializable{

    public static final String RESULTS = "results";
    public static final String GEOMETRY = "geometry";
    public static final String ID = "id";
    public static final String LOCALITY = "locality";
    public static final String LAT = "lat";
    public static final String LNG = "lng";

    private String mLocationID;
    private String mLocality;
    private double mLat;
    private double mLng;


    public Location(String mLocationID, String mLocality, double mLat, double mLng) {
        this.mLocationID = mLocationID;
        this.mLocality = mLocality;
        this.mLat = mLat;
        this.mLng = mLng;
    }

    public static List<Location> parseLocationJSON(String locationJSON) throws JSONException {
        List<Location> locationList = new ArrayList<Location>();
        if (locationJSON != null) {
            JSONObject jsonObject = new JSONObject(locationJSON);
            JSONArray results = jsonObject.getJSONArray(RESULTS);

            for (int i = 0; i < results.length(); i++) {
                JSONObject outerObj = results.getJSONObject(i);
                JSONArray geometry = outerObj.getJSONArray(GEOMETRY);
                for (int j = 0; j < geometry.length(); j++) {
                    JSONObject obj = geometry.getJSONObject(j);
                    Location location = new Location(obj.getString(Location.ID), obj.getString(Location.LOCALITY),
                            obj.getDouble(Location.LAT), obj.getDouble(Location.LNG));
                    locationList.add(location);
                }

            }

        }

        return locationList;
    }

    public String getmLocationID() {
        return mLocationID;
    }

    public void setmLocationID(String mLocationID) {
        this.mLocationID = mLocationID;
    }

    public String getmLocality() {
        return mLocality;
    }

    public void setmLocality(String mLocality) {
        this.mLocality = mLocality;
    }

    public double getmLat() {
        return mLat;
    }

    public void setmLat(double mLat) {
        this.mLat = mLat;
    }

    public double getmLng() {
        return mLng;
    }

    public void setmLng(double mLng) {
        this.mLng = mLng;
    }
}
