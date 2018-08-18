package amirz.aidlbridge;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class Bridge extends Binder implements ServiceConnection {
    private final Context mContext;
    private IBinder mOverlay;

    public Bridge(Context context, Intent intent) {
        mContext = context;
        mContext.bindService(intent, this,
                Context.BIND_AUTO_CREATE | Context.BIND_ADJUST_WITH_ACTIVITY);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mOverlay = service;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply,
                                 int flags) throws RemoteException {
        return mOverlay == null
                ? super.onTransact(code, data, reply, flags)
                : mOverlay.transact(code, data, reply, flags);
    }

    public void onUnbind() {
        mContext.unbindService(this);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mOverlay = null;
    }
}
