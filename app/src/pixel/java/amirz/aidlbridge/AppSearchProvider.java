package amirz.aidlbridge;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class AppSearchProvider extends ContentProvider {
    private ContentResolver mResolver;

    private static Uri forwardUri(Uri uri) {
        String forwardTo = BridgeService.getLastConnection();
        if (forwardTo == null) {
            forwardTo = "";
        } else {
            forwardTo = uri.getEncodedAuthority()
                    .replace("com.google.android.apps.nexuslauncher", forwardTo);
        }

        return uri.buildUpon()
                .encodedAuthority(forwardTo)
                .build();
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        if (context != null) {
            mResolver = context.getContentResolver();
            return mResolver != null;
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mResolver.query(forwardUri(uri), projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        return mResolver.getType(forwardUri(uri));
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return mResolver.insert(forwardUri(uri), values);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mResolver.delete(forwardUri(uri), selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return mResolver.update(forwardUri(uri), values, selection, selectionArgs);
    }
}
