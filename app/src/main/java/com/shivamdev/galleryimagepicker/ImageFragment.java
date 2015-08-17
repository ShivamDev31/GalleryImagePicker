package com.shivamdev.galleryimagepicker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by shivamchopra on 16/08/15.
 */
public class ImageFragment extends Fragment {
    Bitmap bitmap;
    static String imagePath;
    static ImageFragment newInstance(String path) {
        ImageFragment imgf = new ImageFragment();

        Bundle args = new Bundle();
        args.putString("IMAGE_PATH", path);
        return imgf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagePath = getArguments() != null ? getArguments().getString("IMAGE_PATH") : null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        ImageView iv = (ImageView) view.findViewById(R.id.iv_fragment);

        iv.setImageBitmap(BitmapFactory.decodeFile(imagePath));

        return view;
    }
}
