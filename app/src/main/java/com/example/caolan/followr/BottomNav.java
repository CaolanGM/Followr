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
    private LinearLayout l3;
    private LinearLayout l4;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottom_nav, container, false);
        ImageButton ib = view.findViewById(R.id.timetable);
        //ImageButton ib2 = view.findViewById(R.id.library);
        ImageButton ib3 = view.findViewById(R.id.finder);
        ImageButton ib4 = view.findViewById(R.id.burger_menu);
        TextView tv = view.findViewById(R.id.timetableTV);
        //TextView tv2 = view.findViewById(R.id.libraryTV);
        TextView tv3 = view.findViewById(R.id.finderTv);
        TextView tv4 = view.findViewById(R.id.burger_menuTv);

        l1 = view.findViewById(R.id.timetableButton);
        // LinearLayout l2 = view.findViewById(R.id.libraryButton);

        l3 = view.findViewById(R.id.finderButton);
        l4 = view.findViewById(R.id.menuButton);
        ib.setColorFilter(getActivity().getResources().getColor(R.color.blue,null));
        tv.setTextColor(getActivity().getResources().getColor(R.color.blue,null));

        buttonList = new ArrayList<>();
        tvList = new ArrayList<>();
        buttonList.add(ib);
        //buttonList.add(ib2);
        buttonList.add(ib3);
        buttonList.add(ib4);
        tvList.add(tv);
        //tvList.add(tv2);
        tvList.add(tv3);
        tvList.add(tv4);

        l1.setOnClickListener(this);
        //l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);

        return view;
    }

    private void onIconClick(View bView) {
        TextView label = view.findViewWithTag("" + bView.getTag() + "TV".trim());
        ImageButton ib = view.findViewWithTag("" + bView.getTag() + "IB".trim());

        setColors(label, ib);

        ((MainActivity) getActivity()).changeFragment("" + bView.getTag(), null);
    }

    public void setColors(TextView label, ImageButton ib) {
        for (ImageButton image : buttonList) {
            image.setColorFilter(getActivity().getResources().getColor(R.color.gray, null));
        }
        for (TextView tv : tvList) {
            tv.setTextColor(getActivity().getResources().getColor(R.color.gray, null));
        }
        ib.setColorFilter(getActivity().getResources().getColor(R.color.blue, null));
        label.setTextColor(getActivity().getResources().getColor(R.color.blue, null));
    }

    public void setTimetableActive() {
        TextView label = view.findViewWithTag("" + l1.getTag() + "TV".trim());
        ImageButton ib = view.findViewWithTag("" + l1.getTag() + "IB".trim());
        setColors(label, ib);
    }

    public void setFinderActive() {
        TextView label = view.findViewWithTag("" + l3.getTag() + "TV".trim());
        ImageButton ib = view.findViewWithTag("" + l3.getTag() + "IB".trim());
        setColors(label, ib);
    }

    public void setMenuActive() {
        TextView label = view.findViewWithTag("" + l4.getTag() + "TV".trim());
        ImageButton ib = view.findViewWithTag("" + l4.getTag() + "IB".trim());
        setColors(label, ib);
    }

    @Override
    public void onClick(View v) {
        onIconClick(v);
    }

    public void onBackPressed() {
        onIconClick(l1);
    }
}
