package io.mns.mediaadapter.media;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.loader.content.CursorLoader;

public class RecentMedia {
    public static final String DATE_TAKEN = "datetaken";
    public static final String DESCENDING = " DESC";
    public MediaAdapter getAdapter(Context context) {
        String[] projection = {
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.TITLE
        };

// Return only video and image metadata.
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

        Uri queryUri = MediaStore.Files.getContentUri("external");

        CursorLoader cursorLoader = new CursorLoader(
                context,
                queryUri,
                projection,
                selection,
                null, // Selection args (none).
                MediaStore.Files.FileColumns.DATE_ADDED + DESCENDING // Sort order.
        );

        Cursor cursor = cursorLoader.loadInBackground();

        return new MediaAdapter(context, cursor, true);
    }

    public void setHeight(int height) {
        MediaAdapter.IMAGE_HEIGHT = height;
    }

    public void setWidth(int width) {
        MediaAdapter.IMAGE_WIDTH = width;
    }
}