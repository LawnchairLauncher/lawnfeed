package amirz.aidlbridge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class GoogleDestForwarder extends BroadcastReceiver {
    private static final String TAG = "GoogleDestForwarder";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String broadcast = intent.getStringExtra("broadcast");
            if (broadcast != null) {
                Intent i = Intent.parseUri(broadcast, Intent.URI_INTENT_SCHEME);
                context.sendBroadcast(i);
            }

            String launch = intent.getStringExtra("launch");
            if (launch != null) {
                Intent i = Intent.parseUri(launch, Intent.URI_INTENT_SCHEME);
                Bundle b = intent.getBundleExtra("launchOptions");
                context.startActivity(i, b);
            }
        } catch (Exception e) {
            Log.d(TAG, "Forward error", e);
        }
    }
}
