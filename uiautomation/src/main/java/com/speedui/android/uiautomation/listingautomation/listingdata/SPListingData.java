package com.speedui.android.uiautomation.listingautomation.listingdata;

import java.util.List;

/**
 * Created by Pradip on 5/12/2015.
 */
final public class SPListingData {
    public List<SPListingCellGroup> spCellGroupList;

    // Create Listing Data with given section array.
    public SPListingData(List<SPListingCellGroup> spCellGroupList){
        this.spCellGroupList = spCellGroupList;
    }

    public int getTotalCellCount(){
        int cellCount = 0;
        for (SPListingCellGroup cellGroup : spCellGroupList){
            cellCount += cellGroup.getCellCount();
        }
        return cellCount;
    }

    public class CellGroupAndCellModelIndexReturnType{
        public SPListingCellGroup spListingCellGroup;
        public int indexOfCellModelList;
    }

    public CellGroupAndCellModelIndexReturnType getListingCellGroupWithIndexOfCellModelList(int index){
        CellGroupAndCellModelIndexReturnType returnType = new CellGroupAndCellModelIndexReturnType();

        int startIndexOfCellFound  = 0;
        int totalCellCountParsedFromCellGroupArray  = 0;
        for(SPListingCellGroup cellGroup : this.spCellGroupList){
            totalCellCountParsedFromCellGroupArray += cellGroup.getCellCount();
            if (index < totalCellCountParsedFromCellGroupArray){
                returnType.spListingCellGroup = cellGroup;
                returnType.indexOfCellModelList = index - startIndexOfCellFound;
                break;
            }

            startIndexOfCellFound += startIndexOfCellFound + cellGroup.getCellCount();
        }
        return returnType;
    }

    public int getIndexOfCellGroupFrom(int indexOfListView){
        int startIndexOfCellGroup  = 0;
        int totalIndexConvered = 0;
        for(SPListingCellGroup cellGroup : this.spCellGroupList){
            totalIndexConvered += cellGroup.getCellCount();
            if (indexOfListView < totalIndexConvered){
                return startIndexOfCellGroup;
            }

            startIndexOfCellGroup ++;
        }
        return 0;
    }
}
