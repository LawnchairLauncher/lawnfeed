package amirz.aidlbridge;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;

import java.util.HashMap;
import java.util.Map;

public class BridgeService extends Service {
    private Map<Intent, Bridge> mBridges = new HashMap<>();

    @Override
    public IBinder onBind(Intent intent) {
        Intent forwardIntent = (Intent) intent.clone();

        forwardIntent.setPackage("com.google.android.googlequicksearchbox");
        forwardIntent.setData(forwardIntent.getData()
                .buildUpon()
                .encodedAuthority(getPackageName() + ":" + Process.myUid())
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
