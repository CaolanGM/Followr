package com.example.caolan.followr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    private static ActionBar abFrag;
    private static BottomNav bottomNavFragment;
    private static ConstraintLayout bottomNavFrame;
    private String currentNavPos;
    private String prevNavPos;
    String INITIAL_FRAGMENT = "yourChannels";
    BroadcastReceiver offlineReceiver;
    BroadcastReceiver onlineReceiver;
    NetworkChangeReceiver networkChangeReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        abFrag = ActionBar.getNewInstance();
        replaceFragment(abFrag, R.id.custom_action_bar_container);


       // bottomNavFrame = findViewById(R.id.bottom_nav_bar);
       // bottomNavFragment = (BottomNav) getSupportFragmentManager().findFragmentById(R.id.bottom_nav_bar);


        currentNavPos = INITIAL_FRAGMENT;
        prevNavPos = INITIAL_FRAGMENT;


        offlineReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //showNotification(R.layout.offline_notifcation);
                registerReceiver(onlineReceiver, new IntentFilter("INTERNET_GAINED"));
            }
        };

        onlineReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //showNotification(R.layout.online_notifcation);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
                ft.detach(currentFragment).attach(currentFragment).commit();
            }
        };
        networkChangeReceiver = new NetworkChangeReceiver();
        initFirstFragment();
        registerReceiver(offlineReceiver, new IntentFilter("INTERNET_LOST"));

        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(networkChangeReceiver, intentFilter);

    }

    public static ActionBar getAbFrag() {
        return abFrag;
    }

    public static BottomNav getBotNav() {
        return bottomNavFragment;
    }



    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    private void initFirstFragment() {

            changeFragment("yourChannels", null);

    }


    private void replaceFragment(Fragment fragment, int container) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment, fragment.toString());
        fragmentTransaction.commit();
    }

    public static void closeNav() {
        bottomNavFrame.setVisibility(View.GONE);
    }

    public static void openNav() {
        bottomNavFrame.setVisibility(View.VISIBLE);
    }

    /**
     * @param navigationKey Fragment to change to (Timetable,Menu, etc.) or navigation keywords (Back,Cancel, etc.)
     * @param bundle        Optional parameters passed to new fragment
     */
    public void changeFragment(String navigationKey, @Nullable Bundle bundle) {
        Fragment newFrag = null;
        if (!navigationKey.equals("Cancel") && !navigationKey.equals("Back")) {
            prevNavPos = currentNavPos;
            currentNavPos = navigationKey;
        }
        boolean animate = false;
        if (prevNavPos.equals("Cancel")) {
            animate = true;
        }

        switch (navigationKey) {
            case "yourChannels":
                newFrag = new yourChannels();
                break;
            case "yourEvents":
                newFrag = new yourEvents();
                break;
            case "yourStore":
                newFrag = new yourStore();
                break;
            case "Menu":
                newFrag = new yourEvents();
                break;
            case "Cancel":
                openNav();
                onBackPressed();
                break;
//            case "Settings":
//                prevNavPos = "Menu";
//                newFrag = new SettingsFragment();
//                break;
//            case "Emergency Contacts":
//                prevNavPos = "Settings";
//                newFrag = new EmergencyContactsFragment();

            case "Back":
                onBackPressed();
                break;

        }
        if (!navigationKey.equals("Cancel") && !navigationKey.equals("Back") && !navigationKey.equals("ID")&& !navigationKey.equals("Map")) {
            replaceFragment(newFrag, R.id.fragment_container);
        }
    }


    @Override
    public void onBackPressed() {

//        openNav();

        if (prevNavPos == null || (currentNavPos.equals("yourChannels"))) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        } else if (currentNavPos.equals("Library") || currentNavPos.equals("Finder") || currentNavPos.equals("Menu")) {
            bottomNavFragment.onBackPressed();
            changeFragment("Timetable", null);
        } else {
            changeFragment(prevNavPos, null);
        }
    }
}
