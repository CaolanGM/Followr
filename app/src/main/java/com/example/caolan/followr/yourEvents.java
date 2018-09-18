package com.example.caolan.followr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;


public class yourEvents extends Fragment {

    private TextView yourEvents;
    private TextView yourStore;
    private TextView yourChannels;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View fragView = inflater.inflate(R.layout.fragment_your_events, container, false);

        ActionBar abFrag = ActionBar.getInstance();

        yourChannels =  fragView.findViewById(R.id.channels);
        yourStore =  fragView.findViewById(R.id.store);

        yourChannels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick("yourChannels");
            }
        });
        yourStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick("yourStore");
            }
        });


        return fragView;
    }
    private void menuClick(String str) {
        ((MainActivity) Objects.requireNonNull(getActivity())).changeFragment(str, null);
    }

}
