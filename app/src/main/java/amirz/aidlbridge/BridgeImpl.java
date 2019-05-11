package amirz.aidlbridge;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

class BridgeImpl extends IBridge.Stub {
    private static final String TAG = "BridgeImpl";

    private final Context mContext;
    private final String mPackage;
    private final Intent mIntent;
    private final Set<ServiceConnection> mConnections = new HashSet<>();

    BridgeImpl(Context context, Intent intent) {
        mContext = context;

        Uri caller = intent.getData();
        String authority = caller.getEncodedAuthority();
        mPackage = TextUtils.isEmpty(authority) ? "" : authority.split(":")[0];

        mIntent = intent.cloneFilter();
        mIntent.setPackage("com.google.android.googlequicksearchbox");
        String auth = context.getPackageName() + ":" + Process.myUid();
        mIntent.setData(caller.buildUpon().encodedAuthority(auth).build());
    }

    String getPackage() {
        return mPackage;
    }

    @Override
    public void bindService(final IBridgeCallback cb, int flags) {
        Log.e(TAG, "Connect request from " + getPackage());
        ServiceConnection connection = new ServiceConnection() {
            private boolean mConnected;

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                if (!mConnected) {
                    mConnected = true;
                    Log.e(TAG, "Connected for " + mPackage);
                    try {
                        cb.onServiceConnected(name, new TransactProxy(service));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                if (mConnected) {
                    mConnected = false;
                    Log.e(TAG, "Disconnected for " + mPackage);
                    try {
                        cb.onServiceDisconnected(name);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        if (mContext.bindService(mIntent, connection, flags)) {
            mConnections.add(connection);
        }
    }

    void disconnect() {
        Log.e(TAG, "Disconnect " + mPackage + " with " + mConnections.size() + " connections");
        for (ServiceConnection connection : mConnections) {
            mContext.unbindService(connection);
            connection.onServiceDisconnected(null);
        }
        mConnections.clear();
    }
}
