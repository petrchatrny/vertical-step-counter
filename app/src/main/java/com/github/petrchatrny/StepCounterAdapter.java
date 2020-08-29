package com.github.petrchatrny;

import androidx.annotation.Nullable;
import java.util.ArrayList;

public class StepCounterAdapter extends VerticalStepCounterAdapter {
    private ArrayList<String> myData;
    private int count;

    public StepCounterAdapter(ArrayList<String> data, int size) {
        super();
        myData = data;
        count = size;
    }

    @Nullable
    @Override
    public String getText(int position) {
        return myData.get(position);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Void getItem(int position) {
        return null;
    }
}