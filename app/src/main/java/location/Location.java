package location;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Location implements Serializable{

    public static final String RESULTS = "results";
    public static final String ADDRESS_COMPONENTS = "address_components";
    public static final String FORMATTED_ADDRESS = "formatted_address";

    public static final String LONG_NAME = "long_name";

    public static final String GEOMETRY = "geometry";
    public static final String ID = "id";
    public static final String LONCG_NAME = "long_name";
    public static final String LOCATION = "location";
    public static final String LAT = "lat";
    public static final String LNG = "lng";

    private String mFormattedAddress;
    private String mLat;
    private String mLng;
    private int mLocationID = 0;


    public Location(String mFormattedAddress, String mLat, String mLng) {
        this.mLocationID = mLocationID++;
        this.mFormattedAddress = mFormattedAddress;
        this.mLat = mLat;
        this.mLng = mLng;
    }



    public static Location parseLocationJSON(String locationJSON) throws JSONException {
        Location location = null;
        if (locationJSON != null) {

            JSONObject jsonObject = new JSONObject(locationJSON);
            JSONArray results = jsonObject.getJSONArray(RESULTS);
            JSONObject outerObj = results.getJSONObject(0);



            JSONObject geometry = outerObj.getJSONObject(GEOMETRY);
            JSONObject locationObj = geometry.getJSONObject(LOCATION);

            location = new Location(outerObj.getString(FORMATTED_ADDRESS), locationObj.getString(LAT), locationObj.getString(LNG));
            Log.i("PARSING_____", outerObj.getString(FORMATTED_ADDRESS));
            Log.i("PARSING_____",  locationObj.getString(LAT));
            Log.i("PARSING_____", locationObj.getString(LNG));
        }

        return location;
    }

//    public static List<Location> parseLocationJSON(String locationJSON) throws JSONException {
//        List<Location> locationList = new ArrayList<Location>();
//        if (locationJSON != null) {
//
//            JSONObject jsonObject = new JSONObject(locationJSON);
//            JSONArray results = jsonObject.getJSONArray(RESULTS);
//            JSONObject outerObj = results.getJSONObject(0);
//
//
//
//            JSONObject geometry = outerObj.getJSONObject(GEOMETRY);
//            JSONObject locationObj = geometry.getJSONObject(LOCATION);
//
//            Location location = new Location(outerObj.getString(FORMATTED_ADDRESS), locationObj.getString(LAT), locationObj.getString(LNG));
//            Log.i("PARSING_____", outerObj.getString(FORMATTED_ADDRESS));
//            Log.i("PARSING_____",  locationObj.getString(LAT));
//            Log.i("PARSING_____", locationObj.getString(LNG));
//            locationList.add(location);
//        }
//
//        return locationList;
//    }

//    public static List<Location> parseLocationJSON(String locationJSON) throws JSONException {
//        List<Location> locationList = new ArrayList<Location>();
//        if (locationJSON != null) {
//            JSONObject jsonObject = new JSONObject(locationJSON);
//            JSONArray results = jsonObject.getJSONArray(RESULTS);
//
//            for (int i = 0; i < results.length(); i++) {
//                JSONObject outerObj = results.getJSONObject(i);
//                JSONArray geometry = outerObj.getJSONArray(GEOMETRY);
//                for (int j = 0; j < geometry.length(); j++) {
//                    JSONObject obj = geometry.getJSONObject(j);
//                    Location location = new Location(obj.getString(Location.ID), obj.getString(Location.LOCALITY),
//                            obj.getDouble(Location.LAT), obj.getDouble(Location.LNG));
//                    locationList.add(location);
//                }
//
//            }
//
//        }
//
//        return locationList;
//    }



    public int getmLocationID() {
        return mLocationID;
    }

    public void setmLocationID(int mId) {
        this.mLocationID = mId;
    }

    public String getmFormattedAddress() {
        return mFormattedAddress;
    }

    public void setmFormattedAddress(String mFormattedAddress) {
        this.mFormattedAddress = mFormattedAddress;
    }

    public String getmLat() {
        return mLat;
    }

    public void setmLat(String mLat) {
        this.mLat = mLat;
    }

    public String getmLng() {
        return mLng;
    }

    public void setmLng(String mLng) {
        this.mLng = mLng;
    }
}
