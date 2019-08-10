
# android-media-adapter
An android library for indexing photos and videos in attachment selections

[![HitCount](http://hits.dwyl.com/{mehdinosrati}/{android-media-adapter}.svg)](http://hits.dwyl.com/{mehdinosrati}/{android-media-adapter})

**Sample usage:**
* xml:
```
 <com.jess.ui.TwoWayGridView
            android:id="@+id/recent_media"
            android:layout_width="match_parent"
            android:layout_height="72dp"
            android:background="@color/white"
            app:gravity="center"
            app:horizontalSpacing="8dp"
            app:scrollDirectionLandscape="horizontal"
            app:scrollDirectionPortrait="horizontal"
            app:stretchMode="spacingWidth"
            app:verticalSpacing="0dp" />
```

* java:
```
TwoWayGridView mediaView = findViewById(R.id.media_view);
final RecentMedia recentMedia = new RecentMedia();
recentMedia.setWidth(width);  
recentMedia.setHeight(height);
final MediaAdapter mediaAdapter = recentImages.getAdapter(context);
mediaView.setAdapter(mediaAdapter);
mediaView.setOnItemClickListener((parent, view, position, id) -> {
		//for video
	activity.onMediaPicked(ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id));
		//for image
	activity.onMediaPicked(ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id));
		//your code...
	});
```

* credit: [AmirArcane](https://github.com/amirarcane/recent-images) & [JessAnders](https://github.com/jess-anders/two-way-gridview)
