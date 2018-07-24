package com.a000webhostapp.httpsquandt.timezone;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationDetailFragment extends Fragment {

    public static final String TIMEZONE_DETAIL_PARAM = "location_detail_param";

    public LocationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_detail, container, false);
    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(getArguments() != null) {
//            TimezoneContent.TimezoneItem timezoneItem = (TimezoneContent.TimezoneItem)
//                    getArguments().get(TIMEZONE_DETAIL_PARAM);
//            if(timezoneItem != null) {
//                updateTimezoneItemView(timezoneItem);
//            }
//        } else {
//            updateTimezoneItemView(TimezoneContent.ITEMS.get(0));
//        }
//    }
//
//    public static LocationDetailFragment getCourseDetailFragment(TimezoneContent.TimezoneItem timezoneItem) {
//        LocationDetailFragment fragment = new LocationDetailFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(TIMEZONE_DETAIL_PARAM, timezoneItem);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    public void updateTimezoneItemView(TimezoneContent.TimezoneItem item) {
//        TextView timezoneIdTextView = (TextView) getActivity().findViewById(R.id.timezone_item_id);
//        timezoneIdTextView.setText(item.id);
//        TextView timezoneTitleTextView = (TextView) getActivity().findViewById(R.id.timezone_item_content);
//        timezoneTitleTextView.setText(item.content);
//        TextView timezoneShortDescTextView = (TextView) getActivity().findViewById(R.id.timezone_item_details);
//        timezoneShortDescTextView.setText(item.details);
//    }

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
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
