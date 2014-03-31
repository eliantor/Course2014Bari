package me.aktor.systemapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by eto on 25/03/14.
 */
public class SystemAppChangerFragment extends Fragment {

    private CompoundButton mBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_sysapp,container,false);
        mBtn = (Switch)v.findViewById(R.id.wifi_switch);
        return v;
    }

}
