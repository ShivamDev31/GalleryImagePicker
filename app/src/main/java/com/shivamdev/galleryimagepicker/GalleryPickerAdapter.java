package com.shivamdev.galleryimagepicker;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shivamchopra on 14/08/15.
 */
public class GalleryPickerAdapter extends RecyclerView.Adapter<GalleryPickerAdapter.MyViewHolder> {


    //define source of MediaStore.Images.Media, internal or external storage
    final Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    final String[] projections = {MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DISPLAY_NAME};
    Context context;
    LayoutInflater inflater;
    Cursor cursor = null;
    ArrayList<String> myList = new ArrayList<>();
    String imageName;
    String imagePath;
    String imageBucket;
    String bucketId;
    String selection = "1) GROUP BY 1,(2";
    String[] selectionArgs = {"MAX(datetaken) DESC"};
    String sortOrder = MediaStore.Images.Media.DATE_ADDED + " ASC";

    HashMap<String, ArrayList<String>> folderMap = new HashMap<>();

    int count;


    public GalleryPickerAdapter(Context context, ArrayList<String> list) {
        this.context = context;

        // Taking the list size from previous Activity as Cursor.getCount() is not working.
        this.myList = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = inflater.inflate(R.layout.grid_item, parent, false);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        try {

            CursorLoader cl = new CursorLoader(context, uri, projections, null, null, sortOrder);
            cursor = cl.loadInBackground();

            while(cursor.moveToNext()){
                    imageName = cursor.getString(cursor.getColumnIndex(projections[4]));
                    Log.d("Image Name ", imageName);
                    imageBucket = cursor.getString(cursor.getColumnIndex(projections[2]));
                    Log.d("Image Bucket ", imageBucket);
                    bucketId = cursor.getString(cursor.getColumnIndex(projections[3]));
                    Log.d("Bucket Id ", bucketId);
                    imagePath = cursor.getString(cursor.getColumnIndex(projections[1]));
                    Log.d("Image Path ", imagePath);
                    myList.add(imagePath);
                    folderMap.put(imageBucket, myList);


            }
            holder.iv_grid.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            holder.tv_grid.setText(imageBucket);
            count = cursor.getCount();
            Log.d("Last Index : ", " " + myList.size());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public int getItemCount() {
        // cursor.getCount() not working so for making it work right now using myList.size() which returns total images
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View row;
        ImageView iv_grid;
        TextView tv_grid;

        public MyViewHolder(View itemView) {
            super(itemView);
            row = itemView;
            iv_grid = (ImageView) row.findViewById(R.id.gv_image);
            tv_grid = (TextView) row.findViewById(R.id.gv_title);
        }
    }
}