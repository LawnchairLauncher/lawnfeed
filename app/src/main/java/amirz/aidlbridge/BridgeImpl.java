package amirz.aidlbridge;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;

import java.util.Objects;

public class BridgeImpl extends Binder implements ServiceConnection {
    private static final String TAG = "BridgeImpl";

    private final Context mContext;
    private final Uri mCaller;

    private boolean mConnected;
    private SparseArray<BridgeCallback> mCallbacks = new SparseArray<>();
    private IBinder mOverlay;

    public BridgeImpl(Context context, Uri caller) {
        mContext = context;
        mCaller = caller;
    }

    // Client connected
    public boolean bind(Intent intent) {
        return mContext.bindService(intent, this,
                Context.BIND_AUTO_CREATE | Context.BIND_ADJUST_WITH_ACTIVITY);
    }

    // Server connected
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.w(TAG, "onServiceConnected " + this.toString());
        mOverlay = service;

        mConnected = true;
        for (int i = 0; i < mCallbacks.size(); i++) {
            try {
                mCallbacks.valueAt(i).onBridgeConnected(this);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Client disconnected
    public boolean unbind(Uri caller) {
        if (Objects.equals(caller, mCaller)) {
            mContext.unbindService(this);
            return true;
        }
        return false;
    }

    // Server disconnected
    @Override
    public void onServiceDisconnected(ComponentName name) {
        if (mConnected) {
            Log.w(TAG, "onServiceDisconnected " + this.toString());

            mOverlay = null;
            mConnected = false;

            for (int i = 0; i < mCallbacks.size(); i++) {
                try {
                    mCallbacks.valueAt(i).onBridgeDisconnected();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Forward from client to server
    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        return mOverlay == null
                ? super.onTransact(code, data, reply, flags)
                : mOverlay.transact(code, data, reply, flags);
    }

    // AIDL Stub that implements the callback
    public IBinder getProxy() {
        return mProxy;
    }

    private final IBinder mProxy = new Bridge.Stub() {
        @Override
        public void setCallback(int index, BridgeCallback cb) throws RemoteException {
            mCallbacks.put(index, cb);
            Log.w(TAG, "callbacks " + mCallbacks);
            if (mConnected) {
                cb.onBridgeConnected(BridgeImpl.this);
            }
        }
    };
}
