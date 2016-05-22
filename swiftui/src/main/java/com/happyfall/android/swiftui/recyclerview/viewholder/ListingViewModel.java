package com.happyfall.android.swiftui.recyclerview.viewholder;

import android.databinding.BaseObservable;

/**
 * Created by pradipvaghasiya on 06/06/15.
 */
abstract public class ListingViewModel extends BaseObservable {
    public ListingViewHolder mViewHolder;
    public int mLayoutId;
    public int mBindingVariable = 0;  // By default 0 if in case no Binding used.
}