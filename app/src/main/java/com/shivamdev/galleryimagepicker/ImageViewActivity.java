package com.shivamdev.galleryimagepicker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by shivamchopra on 16/08/15.
 */
public class ImageViewActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageViewAdapter adapter;
    private ArrayList<String> paths = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_view_pager);

        Log.d("MyTag", "ImageViewActivity");


        paths = getIntent().getStringArrayListExtra("paths");
        if (paths != null) {
            for (int i = 0; i < paths.size() - 1; i++) {
                Log.d("Activity paths ", paths.get(i));
            }
        }
        viewPager = (ViewPager) findViewById(R.id.image_view_pager);
        adapter = new ImageViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }


    /*static ImageViewActivity newInstance(Context c, String path) {
        ImageViewActivity imf = new ImageViewActivity();
        imagePath = path;
        return imf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.images_view_pager, container, false);
        Log.d("ImageViewActivity", "clicked on image");
        Toast.makeText(getActivity(), "ImageViewActivity", Toast.LENGTH_LONG).show();

        fm = ((MainActivity) context).getSupportFragmentManager();

        viewPager = (ViewPager) view.findViewById(R.id.image_view_pager);
        adapter = new ImageViewAdapter(fm);
        viewPager.setAdapter(adapter);

        ImageView iv = (ImageView) view.findViewById(R.id.iv_fragment);

        iv.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        Log.d("Path in Fragment : ", imagePath);
        return view;
    }*/


    private class ImageViewAdapter extends FragmentPagerAdapter {

        public ImageViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageViewFragment.newInstance(paths.get(position));
        }

        @Override
        public int getCount() {
            return paths.size();
        }
    }
}
