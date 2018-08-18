package amirz.aidlbridge;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BridgeBinder extends Binder {
    private final IBinder mBinder;

    public BridgeBinder(IBinder binder) {
        mBinder = binder;
    }

    @Override
    public boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply,
                                 int flags) throws RemoteException {
        return mBinder.transact(code, data, reply, flags);
    }

    /*
    @Override
    public void attachInterface(@Nullable IInterface owner, @Nullable String descriptor) {
        mBinder.attachInterface(owner, descriptor);
    }

    @Override
    public @Nullable String getInterfaceDescriptor() {
        return mBinder.getInterfaceDescriptor();
    }
    */

    @Override
    public boolean pingBinder() {
        return mBinder.pingBinder();
    }

    @Override
    public boolean isBinderAlive() {
        return mBinder.isBinderAlive();
    }

    @Override
    public @Nullable IInterface queryLocalInterface(@NonNull String descriptor) {
        return mBinder.queryLocalInterface(descriptor);
    }

    /*
    @Override
    public void linkToDeath(@NonNull DeathRecipient recipient, int flags) {
        mBinder.linkToDeath(recipient, flags);
    }

    @Override
    public boolean unlinkToDeath(@NonNull DeathRecipient recipient, int flags) {
        return mBinder.unlinkToDeath(recipient, flags);
    }
    */
}
