package com.example.caolan.followr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActionBar extends Fragment {


    private static ActionBar instance = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);

        View fragView =  inflater.inflate(R.layout.fragment_action_bar, container, false);

        return fragView;
    }

    public static ActionBar getInstance() {
        if (instance == null) {
            instance = new ActionBar();
        }
        return instance;
    }
    public static ActionBar getNewInstance() {
        instance = new ActionBar();
        return instance;
    }


}
