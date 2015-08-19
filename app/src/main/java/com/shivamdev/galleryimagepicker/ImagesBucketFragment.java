package com.shivamdev.galleryimagepicker;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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
public class ImagesBucketFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView rv;
    private GridLayoutManager glm;
    private GalleryPickerAdapter adapter;
    private ProgressBar pbMain;
    private static final int URL_LOADER = 0;

    public static ImagesBucketFragment newInstance() {
        ImagesBucketFragment fb = new ImagesBucketFragment();

        return fb;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        pbMain = (ProgressBar) view.findViewById(R.id.pb_main);
        pbMain.setVisibility(View.VISIBLE);

        getLoaderManager().initLoader(URL_LOADER, null, this);

        Log.d("MyTag", "ImagesBucketFragment");
        pbMain.setVisibility(View.GONE);
        loadBucket(view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MyTag", "Bucket onResume()");
        getLoaderManager().initLoader(URL_LOADER, null, this);
    }

    private void loadBucket(View view) {

        rv = (RecyclerView) view.findViewById(R.id.rv_main_grid);
        if (adapter == null) {
            adapter = new GalleryPickerAdapter(getActivity());
        }
        glm = new GridLayoutManager(getActivity(), 2);
        rv.setLayoutManager(glm);
        rv.setAdapter(adapter);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(getActivity(),
                GalleryPickerAdapter.uri,
                GalleryPickerAdapter.projections,
                null,
                null,
                GalleryPickerAdapter.sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.setData(PhotosData.getData(true, cursor));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

}