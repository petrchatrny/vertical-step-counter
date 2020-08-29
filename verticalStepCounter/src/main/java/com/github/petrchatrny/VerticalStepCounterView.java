package com.github.petrchatrny;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;

public class VerticalStepCounterView extends ListView {
    protected static int numberColor, textColor, lineColor, badgeColor, textSize;
    protected static Typeface font = Typeface.DEFAULT;
    protected static int elementBottomMargin = 40;

    public VerticalStepCounterView(Context context) {
        super(context);
        initialize();
    }

    public VerticalStepCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public VerticalStepCounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @TargetApi(21)
    public VerticalStepCounterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {
        setDivider(null);
    }

    @Override
    public VerticalStepCounterAdapter getAdapter() {
        return (VerticalStepCounterAdapter) super.getAdapter();
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (!(adapter instanceof VerticalStepCounterAdapter)) {
            throw new IllegalArgumentException("Must be a VerticalStepperAdapter");
        }
        super.setAdapter(adapter);
    }

    public void setStepperAdapter(VerticalStepCounterAdapter adapter) {
        setAdapter(adapter);
    }

    @Nullable
    @Override
    public Parcelable onSaveInstanceState() {
        SparseArray<View> contentViews = getAdapter().getContentViews();
        ArrayList<Bundle> containers = new ArrayList<>(contentViews.size());

        for (int i = 0; i < contentViews.size(); i++) {
            int id = contentViews.keyAt(i);
            View contentView = contentViews.valueAt(i);
            SparseArray<Parcelable> container = new SparseArray<>(1);
            contentView.saveHierarchyState(container);

            Bundle bundle = new Bundle(2);
            bundle.putInt("id", id);
            bundle.putSparseParcelableArray("container", container);

            containers.add(bundle);
        }

        Bundle bundle = new Bundle(3);
        bundle.putParcelable("super", super.onSaveInstanceState());
        bundle.putParcelableArrayList("containers", containers);

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable("super"));

            ArrayList<Bundle> containers = bundle.getParcelableArrayList("containers");
            SparseArray<View> contentViews = getAdapter().getContentViews();

            for (Bundle contentViewBundle : Objects.requireNonNull(containers)) {
                int id = contentViewBundle.getInt("id");
                SparseArray<Parcelable> container = contentViewBundle.getSparseParcelableArray("container");

                View contentView = contentViews.get(id);
                if (contentView != null) {
                    contentView.restoreHierarchyState(container);
                }
            }

        } else {
            super.onRestoreInstanceState(state);
        }
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    // scroll solution
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public void setNumberColor(int color) {
        numberColor = color;
    }

    public void setTextColor(int color) {
        textColor = color;
    }

    public void setLineColor(int color) {
        lineColor = color;
    }

    public void setBadgeColor(int color) {
        badgeColor = color;
    }

    public void setTextFont(Typeface typeface){
        font = typeface;
    }

    public void setTextSize(int size){
        textSize = size;
    }

    public void setElementBottomMargin(int margin){
        elementBottomMargin = margin;
    }
}
