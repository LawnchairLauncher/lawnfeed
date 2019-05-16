package amirz.aidlbridge;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseIntArray;

import com.android.launcher3.plugin.activity.IActivityPlugin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class UpdateService extends Service {
    private static final String TAG = "UpdateService";

    // From IActivityPlugin
    private static final int STATE_CREATED = 1;

    private final Requests mRequests;
    private final SparseIntArray mPidState = new SparseIntArray();
    private PendingIntent mUpdatePi;
    private IBinder mSession;

    public UpdateService() {
        super();
        mRequests = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Requests.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.bridge_url)));
        mUpdatePi = PendingIntent.getActivity(this, 0, intent, 0);

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
                    checkForUpdates();
                }
                mPidState.put(pid, state);
            }
        };
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mSession;
    }

    private void checkForUpdates() {
        Log.d(TAG, "Checking for updates");

        Call<List<Release>> call = mRequests.getReleases(
                getString(R.string.bridge_owner), getString(R.string.bridge_repo));
        call.enqueue(new Callback<List<Release>>() {
            @Override
            public void onResponse(Call<List<Release>> call, Response<List<Release>> response) {
                List<Release> repos = response.body();
                if (!repos.isEmpty()) {
                    String tag = repos.get(0).getTag();
                    if (tag.startsWith("v")) {
                        tag = tag.substring(1);
                        try {
                            int version = Integer.parseInt(tag);
                            Log.d(TAG, "Latest version: " + version);
                            if (version > BuildConfig.VERSION_CODE) {
                                showNotification(version);
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Release>> call, Throwable t) {
            }
        });
    }

    private void showNotification(int version) {
        Log.d(TAG, "Showing update notification");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager == null) return;

        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("all", "All",
                    NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            builder = new Notification.Builder(this, channel.getId());
        } else {
            builder = new Notification.Builder(this);
        }
        builder.setSmallIcon(R.drawable.ic_update);
        builder.setContentTitle(getString(R.string.update_title, version));
        builder.setContentText(getString(R.string.update_desc));
        builder.setContentIntent(mUpdatePi);
        builder.setPriority(Notification.PRIORITY_MAX);
        manager.notify(0, builder.build());
    }

    public interface Requests {
        @GET("/repos/{user}/{repo}/releases")
        Call<List<Release>> getReleases(@Path("user") String user, @Path("repo") String repo);
    }

    private static class Release {
        @SuppressWarnings("unused")
        private String tag_name;

        private String getTag() {
            return tag_name;
        }
    }
}
