package amirz.aidlbridge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.util.Log;

public abstract class GoogleSourceForwarder extends BroadcastReceiver {
    private static final String TAG = "GoogleSourceForwarder";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = (Intent) intent.clone();
        i.setPackage(null);
        i.setComponent(null);

        for (ResolveInfo ri : context.getPackageManager().queryBroadcastReceivers(i, 0)) {
            ActivityInfo ai = ri.activityInfo;
            if (ai != null) {
                String packageName = ai.packageName;
                if (packageName != null && !BuildConfig.APPLICATION_ID.equals(packageName)) {
                    Log.w(TAG, "Sending " + i.getAction() + " to " + packageName);
                    i.setPackage(packageName);
                    context.sendBroadcast(i);
                }
            }
        }
    }
}
