package io.mns.mediaadapter.media;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.jess.ui.TwoWayAbsListView;

import io.mns.mediaadapter.R;

public class MediaAdapter extends CursorAdapter {

    private static BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
    static int IMAGE_WIDTH = 86;
    static int IMAGE_HEIGHT = 86;
    private ContentResolver contentResolver;
    private static final int MEDIA_ID_COLUMN = 0;
    private FrameLayout.LayoutParams videoIconLayoutParams;
    private TwoWayAbsListView.LayoutParams thumbnailLayoutParams;


    MediaAdapter(Context context, Cursor c, boolean autoReQuery) {
        super(context, c, autoReQuery);
        float scale = context.getResources().getDisplayMetrics().density;
        int mImageWidth = (int) (IMAGE_WIDTH * scale);
        int mImageHeight = (int) (IMAGE_HEIGHT * scale);
        bitmapOptions.inSampleSize = 2;
        contentResolver = context.getContentResolver();
        int videoIconSize = context.getResources().getDimensionPixelSize(R.dimen.video_thumbnail_icon_size);
        videoIconLayoutParams = new FrameLayout.LayoutParams(videoIconSize, videoIconSize);
        videoIconLayoutParams.setMargins(8, 4, 4, 4);
        thumbnailLayoutParams = new TwoWayAbsListView.LayoutParams(mImageWidth, mImageHeight);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        FrameLayout frameLayout = new FrameLayout(context);
        ImageView imageView = new ImageView(context);
        ImageView videoIcon = new ImageView(context);
        frameLayout.setLayoutParams(thumbnailLayoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_video_camera_white));
        frameLayout.addView(imageView);
        frameLayout.addView(videoIcon, videoIconLayoutParams);
        return frameLayout;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int id = cursor.getInt(MEDIA_ID_COLUMN);
        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
        if (bitmap == null) {
            bitmap = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
            ((FrameLayout) view).getChildAt(1).setVisibility(View.VISIBLE);
        } else {
            ((FrameLayout) view).getChildAt(1).setVisibility(View.GONE);
        }
        ((ImageView) ((FrameLayout) view).getChildAt(0)).setImageBitmap(bitmap);

    }

}