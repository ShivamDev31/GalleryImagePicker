package com.shivamdev.galleryimagepicker;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by shivamchopra on 19/08/15.
 */
public class ImageViewFragment extends Fragment {

    private String imagePath;
    private ImageView iv;

    public static ImageViewFragment newInstance(String path) {
        ImageViewFragment ivf = new ImageViewFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("path", path);
        ivf.setArguments(args);

        return ivf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagePath = getArguments() != null ? getArguments().getString("path") : null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, container, false);

        iv = (ImageView) view.findViewById(R.id.iv_fragment);
        iv.setImageBitmap(BitmapFactory.decodeFile(imagePath));


        return view;
    }
}
