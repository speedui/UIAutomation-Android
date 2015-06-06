package com.speedui.android.uiautomation.listingautomation.recyclerview.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

import java.lang.ref.WeakReference;

/**
 * Created by pradipvaghasiya on 04/06/15.
 */
public class SPBindingRecyclerAdapter extends RecyclerView.Adapter<SPBindingViewHolder> {
    private SPListingData spListingData;
    private SPBindingViewHolderListener listener;
    private LayoutInflater layoutInflater;


    public SPBindingRecyclerAdapter(SPListingData spListingData, SPBindingViewHolderListener listener){
        super();
        this.setSpListingData(spListingData);
        this.listener = listener;
    }

    public void setSpListingData(SPListingData spListingData) {
        this.spListingData = spListingData;
        this.spListingData.setSpBindingRecyclerAdapter(this);
    }

    public SPListingData getSpListingData() {
        return spListingData;
    }

    @Override
    public int getItemViewType(int position) {
        return this.spListingData.getIndexOfItemGroupFrom(position);
    }

    @Override
    public SPBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SPListingData.ItemGroup itemGroup= spListingData.itemGroupList.get(viewType);

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        try {

            ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater,
                    itemGroup.itemLayoutId,
                    parent,
                    false);


            SPBindingViewHolder bindingViewHolder = (SPBindingViewHolder) itemGroup.bindingViewHolderConstructor.
                    newInstance(binding, this.listener);

            return bindingViewHolder;

        } catch (Exception e) {
            System.out.println("SpeedKit Error: onCreateViewHolder :" + e.toString());
        }

        return null;
}

    @Override
    public void onBindViewHolder(SPBindingViewHolder bindingViewHolder, int position) {
        SPListingData.ItemGroupAndItemModelIndexReturnType itemGroupDetail =
                spListingData.getListingItemGroupWithIndexOfItemModelList(position);

        if (itemGroupDetail.itemGroup.getItemCount() > itemGroupDetail.indexOfItemModelList &&
                itemGroupDetail.indexOfItemModelList >= 0) {
            bindingViewHolder.itemGroupPosition = itemGroupDetail.indexOfItemModelList;

            SPViewModel viewModel = itemGroupDetail.itemGroup.getItemModelList().get(itemGroupDetail.indexOfItemModelList);
            viewModel.weakViewHolder = new WeakReference<SPBindingViewHolder>(bindingViewHolder);

            bindingViewHolder.getViewDataBinding().setVariable(
                    itemGroupDetail.itemGroup.itemBindingVariable,
                    viewModel);
            bindingViewHolder.getViewDataBinding().executePendingBindings();
        }

    }

    @Override
    public int getItemCount() {
        return this.spListingData.getTotalItemCount();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        spListingData.removeObserverCallbacks();
    }

    @Override
    public void onViewRecycled(SPBindingViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewDetachedFromWindow(SPBindingViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public boolean onFailedToRecycleView(SPBindingViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }
}