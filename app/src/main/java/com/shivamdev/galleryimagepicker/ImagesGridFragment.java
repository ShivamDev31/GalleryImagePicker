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
public class ImagesGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView rv;
    private GridLayoutManager glm;
    private GalleryPickerAdapter adapter;
    private ProgressBar pbFrag;
    private static int position;
    private static final int URL_LOADER = 1;


    public static ImagesGridFragment newInstance(int pos) {
        ImagesGridFragment fg = new ImagesGridFragment();
        Bundle args = new Bundle();
        args.putInt("pos", pos);
        fg.setArguments(args);
        return fg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments() != null ? getArguments().getInt("pos") : 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        pbFrag = (ProgressBar) view.findViewById(R.id.pb_main);
        pbFrag.setVisibility(View.VISIBLE);
        getLoaderManager().initLoader(URL_LOADER, null, this);
        Log.d("MyTag", "ImagesGridFragment");
        pbFrag.setVisibility(View.GONE);
        showImages(view);

        return view;
    }

    private void showImages(View view) {
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
        if (id == URL_LOADER) {
            return new CursorLoader(getActivity(),
                    GalleryPickerAdapter.uri,
                    GalleryPickerAdapter.projections,
                    GalleryPickerAdapter.projections[3] + " = \"" + GalleryPickerAdapter.data.get(position).getBucketId() + "\"",
                    null,
                    GalleryPickerAdapter.sortOrder);
        } else {
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.setData(PhotosData.getData(false, cursor));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}