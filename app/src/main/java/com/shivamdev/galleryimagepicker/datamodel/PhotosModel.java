package com.shivamdev.galleryimagepicker.datamodel;

import android.util.Log;

/**
 * Created by shivamchopra on 17/08/15.
 */
public class PhotosModel {

    String bucketId, imageBucket, imagePath, imageName;

    public PhotosModel(String bucketId, String imageBucket, String imagePath, String imageName) {
        this.bucketId = bucketId;
        this.imageBucket = imageBucket;
        this.imageName = imageName;
        this.imagePath = imagePath;
        Log.d("MyTag", "PhotoModel");
    }

    public String getBucketId() {
        return bucketId;
    }

    public String getImageBucket() {
        return imageBucket;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImagePath() {
        return imagePath;
    }


}