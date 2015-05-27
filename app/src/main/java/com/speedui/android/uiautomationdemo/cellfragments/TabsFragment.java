package com.speedui.android.uiautomationdemo.cellfragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.view.View;

import com.example.android.common.view.SlidingTabLayout;
import com.speedui.android.uiautomation.slidingtabs.SPSlidingTabsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pradipvaghasiya on 26/05/15.
 */
public class TabsFragment extends SPSlidingTabsFragment{

    private List<String> tabTitles = Arrays.asList("Title Cell","Check List Cell","Grid Titles");
    private TitleCellFragment titleCellFragment;
    private ChecklistCellFragment checklistCellFragment;
    private GridFragment gridFragment;

    public TabsFragment(){
        this.isActionBarOverLay = true;
        this.titleCellFragment = new TitleCellFragment();
        this.checklistCellFragment = new ChecklistCellFragment();
        this.gridFragment = new GridFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Customise the colors of indicator.
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.RED;
            }

            @Override
            public int getDividerColor(int position) {
                return Color.GRAY;
            }
        });

        slidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                showActionBarAndMoveSlidingTabsBelowIt();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public List<String> getTabTitles() {
        return tabTitles;
    }

    @Override
    public Fragment getV4FragmentAt(int position) {
        switch (position){
            case 0:
                return this.titleCellFragment;
            case 1:
                return this.checklistCellFragment;
            case 2:
                return this.gridFragment;
            default:
                return null;
        }
    }
}
