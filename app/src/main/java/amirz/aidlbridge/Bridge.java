package amirz.aidlbridge;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.WindowManager;

import com.google.android.libraries.launcherclient.ILauncherOverlay;

public class Bridge extends ILauncherOverlay.Stub implements ServiceConnection {
    private static final String TAG = "Bridge";
    private final Context mContext;

    private ILauncherOverlay mOverlay;

    public Bridge(Context context, Intent intent) {
        Log.e(TAG, "New Bridge");
        mContext = context;
        mContext.bindService(intent, this,
                Context.BIND_AUTO_CREATE | Context.BIND_WAIVE_PRIORITY);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.e(TAG, "onServiceConnected");
        mOverlay = ILauncherOverlay.Stub.asInterface(service);
    }

    public void onUnbind() {
        Log.e(TAG, "onUnbind");
        mContext.unbindService(this);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.e(TAG, "onServiceDisconnected");
        mOverlay = null;
    }

    @Override
    public void startScroll() throws RemoteException {
        Log.e(TAG, "startScroll");
        mOverlay.startScroll();
    }

    @Override
    public void onScroll(float progress) throws RemoteException {
        Log.e(TAG, "onScroll");
        mOverlay.onScroll(progress);
    }

    @Override
    public void endScroll() throws RemoteException {
        Log.e(TAG, "endScroll");
        mOverlay.endScroll();
    }

    @Override
    public void windowAttached(WindowManager.LayoutParams lp, IBinder cb, int flags) throws RemoteException {
        Log.e(TAG, "windowAttached");
        mOverlay.windowAttached(lp, cb, flags);
    }

    @Override
    public void windowDetached(boolean isChangingConfigurations) throws RemoteException {
        Log.e(TAG, "windowDetached");
        mOverlay.windowDetached(isChangingConfigurations);
    }

    @Override
    public void closeOverlay(int flags) throws RemoteException {
        Log.e(TAG, "closeOverlay");
        mOverlay.closeOverlay(flags);
    }

    @Override
    public void onPause() throws RemoteException {
        Log.e(TAG, "onPause");
        mOverlay.onPause();
    }

    @Override
    public void onResume() throws RemoteException {
        Log.e(TAG, "onResume");
        mOverlay.onResume();
    }

    @Override
    public void openOverlay(int flags) throws RemoteException {
        Log.e(TAG, "openOverlay");
        mOverlay.openOverlay(flags);
    }

    @Override
    public void requestVoiceDetection(boolean start) throws RemoteException {
        Log.e(TAG, "requestVoiceDetection");
        mOverlay.requestVoiceDetection(start);
    }

    @Override
    public String getVoiceSearchLanguage() throws RemoteException {
        Log.e(TAG, "getVoiceSearchLanguage");
        return mOverlay.getVoiceSearchLanguage();
    }

    @Override
    public boolean isVoiceDetectionRunning() throws RemoteException {
        Log.e(TAG, "isVoiceDetectionRunning");
        return mOverlay.isVoiceDetectionRunning();
    }

    @Override
    public boolean hasOverlayContent() throws RemoteException {
        Log.e(TAG, "hasOverlayContent");
        return mOverlay.hasOverlayContent();
    }

    @Override
    public void windowAttached2(Bundle bundle, IBinder cb) throws RemoteException {
        Log.e(TAG, "windowAttached2");
        mOverlay.windowAttached2(bundle, cb);
    }

    @Override
    public void unusedMethod() throws RemoteException {
        Log.e(TAG, "unusedMethod");
        mOverlay.unusedMethod();
    }

    @Override
    public void setActivityState(int flags) throws RemoteException {
        Log.e(TAG, "setActivityState");
        mOverlay.setActivityState(flags);
    }

    @Override
    public boolean startSearch(byte[] data, Bundle bundle) throws RemoteException {
        Log.e(TAG, "startSearch");
        return mOverlay.startSearch(data, bundle);
    }
}
