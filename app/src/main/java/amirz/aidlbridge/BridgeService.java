package amirz.aidlbridge;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BridgeService extends Service {
    private static final String TAG = "BridgeService";
    private static String sLastConnection;
    private final Set<BridgeImpl> mBridges = new HashSet<>();

    @Override
    public IBinder onBind(Intent intent) {
        Uri caller = intent.getData();
        if (caller != null) {
            BridgeImpl bridge = new BridgeImpl(getApplicationContext(), caller);

            // Replace caller package name and UID
            Intent forwardIntent = intent.cloneFilter();
            forwardIntent.setPackage("com.google.android.googlequicksearchbox");
            forwardIntent.setData(caller.buildUpon().encodedAuthority(getPackageName() + ":" + Process.myUid()).build());

            Log.e(TAG, "Attempting bind");
            if (bridge.bind(forwardIntent)) {
                Log.e(TAG, "Bind success");

                // Save package name
                String authority = caller.getEncodedAuthority();
                if (authority != null) {
                    sLastConnection = authority.split(":")[0];
                }

                mBridges.add(bridge);
                return bridge.getProxy();
            }
        }
        return null;
    }

    public static String getLastConnection() {
        return sLastConnection;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Uri caller = intent.getData();
        if (caller != null) {
            for (BridgeImpl bridge : new ArrayList<>(mBridges)) {
                Log.e(TAG, "Attempting unbind");
                if (bridge.unbind(caller)) {
                    Log.e(TAG, "Unbind success");
                    mBridges.remove(bridge);
                }
            }
        }
        return super.onUnbind(intent);
    }
}
