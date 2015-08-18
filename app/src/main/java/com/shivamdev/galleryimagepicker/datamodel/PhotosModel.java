package com.shivamdev.galleryimagepicker.datamodel;

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
    }

    public String getImageName() {
        return imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getBucketId() {
        return bucketId;
    }

    public String getImageBucket() {
        return imageBucket;
    }
}