package com.shivamdev.galleryimagepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shivamdev.galleryimagepicker.datamodel.PhotosData;
import com.shivamdev.galleryimagepicker.datamodel.PhotosModel;

import java.util.List;

/**
 * Created by shivamchopra on 14/08/15.
 */
public class GalleryPickerAdapter extends RecyclerView.Adapter<GalleryPickerAdapter.MyViewHolder> {


    //define source of MediaStore.Images.Media, internal or external storage
    public static final Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    public static final String[] projections = {MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DISPLAY_NAME};
    private Context context;
    LayoutInflater inflater;
    static String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";

    static List<PhotosModel> data;


    public GalleryPickerAdapter(Context context) {
        this.context = context;

        inflater = LayoutInflater.from(context);
    }


    public void setData(List<PhotosModel> data) {
        GalleryPickerAdapter.data = data;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = inflater.inflate(R.layout.grid_item, parent, false);
        return new MyViewHolder(myView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final PhotosModel model = data.get(position);

        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {

                Bitmap thumb = BitmapDecoder.decodeBitmapFromFile(model.getImagePath(), 200, 200);
                //return Bitmap.createScaledBitmap(thumb, 400, 400, false);
                return thumb;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                holder.iv_grid.setImageBitmap(bitmap);
            }
        }.execute();
        if (PhotosData.dir) {
            holder.tv_grid.setText(model.getImageName() == null ? model.getImageBucket() : model.getImageName());
        } else {
            holder.tv_grid.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PhotosData.dir) {
                        openBucketFragment();
                    } else {
                        openImageFragment();
                    }
                }
            });
        }

        private void openBucketFragment() {
            Toast.makeText(context, "Clicked on the bucket at :" + getLayoutPosition(), Toast.LENGTH_LONG).show();
            Log.d("MyTag", "Bucket clicked");
            // Do something when clicked on a bucket
            FragmentGrid fg = FragmentGrid.newInstance(getLayoutPosition());
            Bundle args = new Bundle();
            
            FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.view_holder, fg);
            //ft.add(R.id.view_holder, fg);
            ft.addToBackStack("images");
            ft.commit();
        }

        private void openImageFragment() {
            Toast.makeText(context, "Clicked on the image at :" + getLayoutPosition(), Toast.LENGTH_LONG).show();
            Log.d("MyTag", "Image clicked");
        }
    }
}