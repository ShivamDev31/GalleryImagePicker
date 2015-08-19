package com.shivamdev.galleryimagepicker.datamodel;

import android.database.Cursor;
import android.util.Log;

import com.shivamdev.galleryimagepicker.GalleryPickerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivamchopra on 17/08/15.
 */
public class PhotosData {

    public static boolean dir;

    public static List<PhotosModel> getData(boolean home, Cursor cursor) {
        dir = home;
        List<PhotosModel> photos = new ArrayList<>();
        List<String> bucketIds = new ArrayList<>();
        List<String> imagePaths = new ArrayList<>();

        String[] projections = GalleryPickerAdapter.projections;
        Log.d("MyTag", "PhotosData");
        while (cursor.moveToNext()) {
            String imageName = cursor.getString(cursor.getColumnIndex(projections[4]));
            String imageBucket = cursor.getString(cursor.getColumnIndex(projections[2]));
            String bucketId = cursor.getString(cursor.getColumnIndex(projections[3]));
            String imagePath = cursor.getString(cursor.getColumnIndex(projections[1]));
            PhotosModel model;

            if (home) {
                model = new PhotosModel(bucketId, imageBucket, imagePath, null);
                if (!bucketIds.contains(bucketId)) {
                    photos.add(model);
                    bucketIds.add(bucketId);
                }
            } else {
                model = new PhotosModel(bucketId, imageBucket, imagePath, imageName);

                if (!imagePaths.contains(imagePath)) {
                    photos.add(model);
                    //imagePaths.add(imagePath);
                }
            }
        }
        return photos;
    }
}