package com.shivamdev.galleryimagepicker;

import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.shivamdev.galleryimagepicker.datamodel.PhotosData;

/**
 * Created by shivamchopra on 18/08/15.
 */
public class FragmentImages extends Fragment {

    RecyclerView rv;
    GridLayoutManager glm;
    GalleryPickerAdapter adapter;
    ProgressBar pbFrag;
    //Context context = ;

    //Cursor variables
    CursorLoader cl;
    Cursor cursor;
    String path;
    static int position;

    public static FragmentImages newInstance(int pos) {
        FragmentImages fg = new FragmentImages();
        position = pos;

        return fg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.fragment_grid, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv_main_grid);
        if (adapter == null) {
            adapter = new GalleryPickerAdapter(getActivity());
        }
        glm = new GridLayoutManager(getActivity(), 2);


        rv.setLayoutManager(glm);

        rv.setAdapter(adapter);
        pbFrag = (ProgressBar) view.findViewById(R.id.pb_main);
        pbFrag.setVisibility(View.VISIBLE);
        loadImages();
        pbFrag.setVisibility(View.GONE);
        return view;
    }

    void loadImages() {
        cl = new CursorLoader(getActivity(), GalleryPickerAdapter.uri, GalleryPickerAdapter.projections, GalleryPickerAdapter.projections[3] + " = \"" + GalleryPickerAdapter.data.get(position).getBucketId() + "\"", null, GalleryPickerAdapter.sortOrder);
        Log.d("ImageBucket Cursor : ", GalleryPickerAdapter.projections[3] + " = \"" + GalleryPickerAdapter.data.get(position).getBucketId());
        Log.d("ImageBucket Cursor : ", GalleryPickerAdapter.projections[3] + " = " + GalleryPickerAdapter.data.get(position).getBucketId());

        try {
            cursor = cl.loadInBackground();
            adapter.setData(PhotosData.getData(false, cursor));
            //path = GalleryPickerAdapter.data.get(position).getImagePath();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}