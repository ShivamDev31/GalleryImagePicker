package com.shivamdev.galleryimagepicker;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivamchopra on 17/08/15.
 */
public class PhotosData {
    public static List<PhotosModel> getData(boolean home, Cursor cursor) {

        List<PhotosModel> photos = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        String[] projections = GalleryPickerAdapter.projections;

        while (cursor.moveToNext()) {
            String imageName = cursor.getString(cursor.getColumnIndex(projections[4]));
            String imageBucket = cursor.getString(cursor.getColumnIndex(projections[2]));
            String bucketId = cursor.getString(cursor.getColumnIndex(projections[3]));
            String imagePath = cursor.getString(cursor.getColumnIndex(projections[1]));
            PhotosModel model;

            if (home) {
                model = new PhotosModel(bucketId, imageBucket, imagePath, null);
                if (!ids.contains(bucketId)) {
                    photos.add(model);
                    ids.add(bucketId);
                }
            } else {
                model = new PhotosModel(bucketId, imageBucket, imagePath, imageName);
                photos.add(model);
            }
        }
        return photos;
    }
}