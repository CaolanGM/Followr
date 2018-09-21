package com.example.caolan.followr;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class BottomNav extends Fragment implements View.OnClickListener {
    private ArrayList<ImageButton> buttonList;
    private ArrayList<TextView> tvList;
    private View view;
    private LinearLayout l1;
    private LinearLayout l2;
    private LinearLayout l3;
    private LinearLayout l4;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottom_nav, container, false);
        ImageButton ib = view.findViewById(R.id.home);
        ImageButton ib2 = view.findViewById(R.id.add);
        ImageButton ib3 = view.findViewById(R.id.profile);
        ImageButton ib4 = view.findViewById(R.id.burger_menu);
        //TextView tv2 = view.findViewById(R.id.libraryTV);

        l1 = view.findViewById(R.id.homeButton);
        l2 = view.findViewById(R.id.addButton);
        l3 = view.findViewById(R.id.profileButton);
        l4 = view.findViewById(R.id.menuButton);
        ib.setColorFilter(getActivity().getResources().getColor(R.color.blue,null));

        buttonList = new ArrayList<>();
        tvList = new ArrayList<>();
        buttonList.add(ib);
        buttonList.add(ib2);
        buttonList.add(ib3);
        buttonList.add(ib4);
        //tvList.add(tv2);

        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);

        return view;
    }

    private void onIconClick(View bView) {
        ImageButton ib = view.findViewWithTag("" + bView.getTag() + "IB".trim());

        setColors(ib);

        ((MainActivity) getActivity()).changeFragment("" + bView.getTag(), null);
    }

    public void setColors( ImageButton ib) {
        for (ImageButton image : buttonList) {
            image.setColorFilter(getActivity().getResources().getColor(R.color.gray, null));
        }
        ib.setColorFilter(getActivity().getResources().getColor(R.color.blue, null));
    }

    public void setHomeActive() {
        ImageButton ib = view.findViewWithTag("" + l1.getTag() + "IB".trim());
        setColors(ib);
    }
    public void setAddActive() {
        ImageButton ib = view.findViewWithTag("" + l2.getTag() + "IB".trim());
        setColors(ib);
    }

    public void setProfileActive() {
        ImageButton ib = view.findViewWithTag("" + l3.getTag() + "IB".trim());
        setColors(ib);
    }

    public void setMenuActive() {
        ImageButton ib = view.findViewWithTag("" + l4.getTag() + "IB".trim());
        setColors(ib);
    }

    @Override
    public void onClick(View v) {
        onIconClick(v);
    }

    public void onBackPressed() {
        onIconClick(l1);
    }
}
