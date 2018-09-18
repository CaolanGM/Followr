package com.example.caolan.followr;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

public class yourStore extends Fragment {

    private TextView yourEvents;
    private TextView yourStore;
    private TextView yourChannels;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View fragView = inflater.inflate(R.layout.fragment_your_store, container, false);

        ActionBar abFrag = ActionBar.getInstance();

        yourChannels =  fragView.findViewById(R.id.channels);
        yourEvents =  fragView.findViewById(R.id.events);

        yourChannels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick("yourChannels");
            }
        });
        yourEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick("yourEvents");
            }
        });


        return fragView;
    }
    private void menuClick(String str) {
        ((MainActivity) Objects.requireNonNull(getActivity())).changeFragment(str, null);
    }
}