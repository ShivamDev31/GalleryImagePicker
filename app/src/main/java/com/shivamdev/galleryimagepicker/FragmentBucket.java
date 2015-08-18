package com.shivamdev.galleryimagepicker;

import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class FragmentBucket extends Fragment {

    RecyclerView rv;
    GridLayoutManager glm;
    GalleryPickerAdapter adapter;
    ProgressBar pbMain;
    Cursor cursor;

    public static FragmentBucket newInstance() {
        FragmentBucket fb = new FragmentBucket();

        return fb;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (adapter == null) {
            adapter = new GalleryPickerAdapter(getActivity());
        }
        glm = new GridLayoutManager(getActivity(), 2);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        loadBucket(view);
        return view;

    }

    private void loadBucket(View view) {
        pbMain = (ProgressBar) view.findViewById(R.id.pb_main);
        rv = (RecyclerView) view.findViewById(R.id.rv_main_grid);

        rv.setLayoutManager(glm);
        rv.setAdapter(adapter);
        pbMain.setVisibility(View.VISIBLE);
        Log.d("Progress", "" + pbMain.isShown());

        getImageBucket();
        pbMain.setVisibility(View.GONE);
    }

    private void getImageBucket() {
        try {
            CursorLoader cl = new CursorLoader(getActivity(), GalleryPickerAdapter.uri, GalleryPickerAdapter.projections, null, null, GalleryPickerAdapter.sortOrder);
            cursor = cl.loadInBackground();
            adapter.setData(PhotosData.getData(true, cursor));

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}