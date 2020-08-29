package com.github.petrchatrny;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;

public abstract class VerticalStepCounterAdapter extends BaseAdapter {

    private SparseArray<View> contentViews = new SparseArray<>(getCount());

    public VerticalStepCounterAdapter() {}

    public SparseArray<View> getContentViews() {
        return contentViews;
    }

    @Nullable
    public abstract String getText(int position);

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        VerticalStepCounterItemView itemView;

        if (convertView == null) {
            itemView = new VerticalStepCounterItemView(context);
        } else {
            itemView = (VerticalStepCounterItemView) convertView;
        }

        applyData(itemView, position);

        return itemView;
    }

    private int getCircleNumber(int position) {
        return position + 1;
    }

    private boolean showConnectorLine(int position) {
        return position < getCount() - 1;
    }

    private void applyData(VerticalStepCounterItemView itemView, int position){
        itemView.setCircleNumber(getCircleNumber(position));
        itemView.setText(getText(position));
        itemView.setShowConnectorLine(showConnectorLine(position));
    }

}
