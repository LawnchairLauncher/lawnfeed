package amirz.aidlbridge;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.content.Context.NOTIFICATION_SERVICE;

class UpdateChecker {
    private static final String TAG = "UpdateChecker";

    private static UpdateChecker sInstance;

    static synchronized UpdateChecker getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UpdateChecker(context.getApplicationContext());
        }
        return sInstance;
    }

    private final Context mContext;
    private final UpdateChecker.Requests mRequests;
    private final NotificationManager mManager;
    private final Notification.Builder mBuilder;

    private UpdateChecker(Context context) {
        mContext = context;
        mRequests = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UpdateChecker.Requests.class);

        mManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(
                    "all", "All", NotificationManager.IMPORTANCE_HIGH);
            mManager.createNotificationChannel(channel);
            mBuilder = new Notification.Builder(mContext, channel.getId());
        } else {
            mBuilder = new Notification.Builder(mContext);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(context.getString(R.string.bridge_url)));
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

        mBuilder.setSmallIcon(R.drawable.ic_update);
        mBuilder.setContentIntent(pi);
        mBuilder.setPriority(Notification.PRIORITY_MAX);
    }

    void checkForUpdates() {
        Log.d(TAG, "Checking for updates");
        Call<List<Release>> call = mRequests.getReleases(
                mContext.getString(R.string.bridge_owner),
                mContext.getString(R.string.bridge_repo)
        );

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
        mBuilder.setContentTitle(mContext.getString(R.string.update_title, version));
        mBuilder.setContentText(mContext.getString(R.string.update_desc));
        mManager.notify(0, mBuilder.build());
    }

    public interface Requests {
        @GET("/repos/{user}/{repo}/releases")
        Call<List<UpdateChecker.Release>> getReleases(@Path("user") String user, @Path("repo") String repo);
    }

    private static class Release {
        @SuppressWarnings("unused")
        private String tag_name;

        private String getTag() {
            return tag_name;
        }
    }
}
