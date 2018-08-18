package amirz.aidlbridge;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

import com.google.android.libraries.launcherclient.ILauncherOverlay;

import java.util.HashMap;
import java.util.Map;

public class BridgeService extends Service {
    private static final String TAG = "BridgeService";
    private Map<Intent, Bridge> mBridges = new HashMap<>();

    public BridgeService() {
    }

    @Override
    public IBinder onBind(final Intent intent) {
        Intent forwardIntent;
        //Intent forwardIntent = (Intent) intent.clone();
        //forwardIntent.setPackage("com.google.android.googlequicksearchbox");

        String pkg = getPackageName();
        forwardIntent = new Intent("com.android.launcher3.WINDOW_OVERLAY")
                .setPackage("com.google.android.googlequicksearchbox")
                .setData(Uri.parse(new StringBuilder(pkg.length() + 18)
                        .append("app://")
                        .append(pkg)
                        .append(":")
                        .append(Process.myUid())
                        .toString())
                        .buildUpon()
                        .appendQueryParameter("v", Integer.toString(7))
                        .appendQueryParameter("cv", Integer.toString(9))
                        .build());

        Bridge bridge = new Bridge(getApplicationContext(), forwardIntent);
        mBridges.put(intent, bridge);
        return bridge;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mBridges.get(intent).onUnbind();
        mBridges.remove(intent);
        return false;
    }
}
