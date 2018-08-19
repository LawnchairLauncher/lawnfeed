package amirz.aidlbridge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;

public abstract class Forwarder extends BroadcastReceiver {
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
                    i.setPackage(packageName);
                    context.sendBroadcast(i);
                }
            }
        }
    }
}
