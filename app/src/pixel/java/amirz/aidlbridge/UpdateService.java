package amirz.aidlbridge;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseIntArray;

import com.android.launcher3.plugin.activity.IActivityPlugin;

public class UpdateService extends Service {
    private static final String TAG = "UpdateService";

    // From IActivityPlugin
    private static final int STATE_CREATED = 1;

    private final SparseIntArray mPidState = new SparseIntArray();
    private IBinder mSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mSession = new IActivityPlugin.Stub() {
            @Override
            public void clearState() {
                mPidState.delete(Binder.getCallingPid());
            }

            @Override
            public void setState(int state) {
                int pid = Binder.getCallingPid();
                int oldState = mPidState.get(pid, 0);
                if ((state & STATE_CREATED) != 0 && (oldState & STATE_CREATED) == 0) {
                    Log.d(TAG, "Activity created");
                    UpdateChecker.getInstance(UpdateService.this).checkForUpdates();
                }
                mPidState.put(pid, state);
            }
        };
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mSession;
    }
}
