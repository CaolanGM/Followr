package com.example.caolan.followr;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        String action = intent.getAction();
        String CONN_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
        if (action != null && (action.equals(CONN_CHANGE))) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()) {
                    context.sendBroadcast(new Intent("INTERNET_GAINED"));
                } else {
                    context.sendBroadcast(new Intent("INTERNET_LOST"));
                }
            }
        }
    }
}