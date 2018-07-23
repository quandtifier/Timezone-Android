package com.a000webhostapp.httpsquandt.timezone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a000webhostapp.httpsquandt.timezone.Timezone.TimezoneContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimezoneDetailFragment extends Fragment {

    public static final String TIMEZONE_DETAIL_PARAM = "timezone_detail_param";

    public TimezoneDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timezone_detail, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getArguments() != null) {
            TimezoneContent.TimezoneItem timezoneItem = (TimezoneContent.TimezoneItem)
                    getArguments().get(TIMEZONE_DETAIL_PARAM);
            if(timezoneItem != null) {
                updateTimezoneItemView(timezoneItem);
            }
        } else {
            updateTimezoneItemView(TimezoneContent.ITEMS.get(0));
        }
    }

    public static TimezoneDetailFragment getCourseDetailFragment(TimezoneContent.TimezoneItem timezoneItem) {
        TimezoneDetailFragment fragment = new TimezoneDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(TIMEZONE_DETAIL_PARAM, timezoneItem);
        fragment.setArguments(args);
        return fragment;
    }

    public void updateTimezoneItemView(TimezoneContent.TimezoneItem item) {
        TextView timezoneIdTextView = (TextView) getActivity().findViewById(R.id.timezone_item_id);
        timezoneIdTextView.setText(item.id);
        TextView timezoneTitleTextView = (TextView) getActivity().findViewById(R.id.timezone_item_content);
        timezoneTitleTextView.setText(item.content);
        TextView timezoneShortDescTextView = (TextView) getActivity().findViewById(R.id.timezone_item_details);
        timezoneShortDescTextView.setText(item.details);
    }

}
