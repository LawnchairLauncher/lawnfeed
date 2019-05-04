package amirz.aidlbridge;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class TransactProxy extends Binder {
    private final IBinder mTarget;

    TransactProxy(IBinder target) {
        mTarget = target;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags)
            throws RemoteException {
        return mTarget.transact(code, data, reply, flags);
    }
}
