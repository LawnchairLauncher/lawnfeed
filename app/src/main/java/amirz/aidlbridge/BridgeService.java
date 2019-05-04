package amirz.aidlbridge;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class BridgeService extends Service {
    private static final String TAG = "BridgeService";

    private static String sLastConnection;
    private final Map<Intent, BridgeImpl> mBridges = new HashMap<>();

    @Override
    public IBinder onBind(Intent intent) {
        Uri caller = intent.getData();
        if (caller != null) {
            Log.e(TAG, "Bind from " + caller.toString());
            if (!mBridges.containsKey(intent)) {
                mBridges.put(intent, new BridgeImpl(getApplicationContext(), intent) {
                    @Override
                    public void connect(final BridgeCallback cb, int flags) {
                        sLastConnection = getPackage();
                        for (BridgeImpl bridge : mBridges.values()) {
                            if (bridge != this) {
                                bridge.disconnect();
                            }
                        }
                        super.connect(cb, flags);
                    }
                });
            }
            return mBridges.get(intent);
        }
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Uri caller = intent.getData();
        if (caller != null) {
            Log.e(TAG, "Unbind from " + caller.toString());
            if (mBridges.containsKey(intent)) {
                mBridges.remove(intent).disconnect();
            }
        }
        return super.onUnbind(intent);
    }

    public static String getLastConnection() {
        return sLastConnection;
    }
}
