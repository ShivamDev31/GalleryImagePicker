package com.shivamdev.galleryimagepicker;

import android.app.Activity;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import static com.shivamdev.galleryimagepicker.R.id.my_grid_view;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    GridLayoutManager glm;
    GalleryPickerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = getAllShownImagesPath(this);

        for(int i = 0; i <= list.size() - 1; i++) {
            //Log.d("My List", "MyList : " + String.valueOf(list.get(i)));
        }

        Log.d("MyTag", " My List size() : " +  list.size() + "");
        
        rv = (RecyclerView) findViewById(my_grid_view);
        adapter = new GalleryPickerAdapter(this, list);
        glm = new GridLayoutManager(this, 2);
        rv.setLayoutManager(glm);
        rv.setAdapter(adapter);
    }

    public static ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor = null;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.Images.Thumbnails.DATA}; //, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
        try {


            CursorLoader cursorLoader = new CursorLoader(activity, uri, projection, null, null, null);
            cursor = cursorLoader.loadInBackground();

            column_index_data = cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA);
            //column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA));

                listOfAllImages.add(absolutePathOfImage);
            }
        } finally {
            if(cursor != null) {
                cursor.close();
            }
        }
        return listOfAllImages;
    }
}
