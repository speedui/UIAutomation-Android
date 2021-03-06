package com.happyfall.android.swiftui.reuse.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.happyfall.android.swiftui.R;

import com.happyfall.android.swiftui.listing.ListingData;
import com.happyfall.android.swiftui.listing.adapter.ListingAdapter;
import com.happyfall.android.swiftui.listing.viewholder.ListingViewHolderListener;

/**
 * Created by pradip on 12/13/15.
 */
abstract public class ListingDataFragment extends Fragment implements ListingViewHolderListener {
    protected SPListingDataFragmentListener mListener;

    protected RecyclerView mRecyclerView;
    private void setRecyclerView(RecyclerView recyclerView) {
        if (mRecyclerView != recyclerView){
            mRecyclerView = recyclerView;

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(getLayout(getActivity()));
            recyclerView.setAdapter(new ListingAdapter(getListingData(),this));
        }
    }


    public ListingDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_listing_data, container, false);
        setRecyclerView((RecyclerView) fragmentView.findViewById(R.id.recyclerView));

        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SPListingDataFragmentListener) {
            mListener = (SPListingDataFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SPListingDataFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface SPListingDataFragmentListener {

    }

    abstract public ListingData getListingData();
    abstract public RecyclerView.LayoutManager getLayout(Context context);
}
