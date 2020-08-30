package com.github.petrchatrny;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Objects;

public class VerticalStepCounterView extends ListView {
    protected static int badgeNumberColor, badgeColor, lineColor, textColor, textSize;
    protected static Typeface textFont = Typeface.DEFAULT;
    protected static int elementBottomMargin = 40;

    public VerticalStepCounterView(Context context) {
        super(context);
        initialize();
    }

    public VerticalStepCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public VerticalStepCounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    @TargetApi(21)
    public VerticalStepCounterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.VerticalStepCounterView, 0, 0);
        try {
            badgeNumberColor = ta.getColor(
                    R.styleable.VerticalStepCounterView_badgeNumberColor,
                    ContextCompat.getColor(context, R.color.vertical_step_counter_view_badge_number_color));
            badgeColor = ta.getColor(
                    R.styleable.VerticalStepCounterView_badgeColor,
                    ContextCompat.getColor(context, R.color.vertical_step_counter_view_badge_color));

            lineColor = ta.getColor(
                    R.styleable.VerticalStepCounterView_lineColor,
                    ContextCompat.getColor(context, R.color.vertical_step_counter_view_line_color));

            textColor = ta.getColor(
                    R.styleable.VerticalStepCounterView_textColor,
                    ContextCompat.getColor(context, R.color.vertical_step_counter_view_text_color));
            textSize = ta.getInt(R.styleable.VerticalStepCounterView_textSize, 12);
            textFont = ResourcesCompat.getFont(
                    context,
                    ta.getResourceId(R.styleable.VerticalStepCounterView_textFont, R.font.roboto));

            elementBottomMargin = ta.getInt(R.styleable.VerticalStepCounterView_elementBottomMargin, 40);
        } finally {
            ta.recycle();
        }
        setDivider(null);
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

    public void setBadgeNumberColor(int color) {
        badgeNumberColor = color;
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

    public void setTextFont(Typeface typeface) {
        textFont = typeface;
    }

    public void setTextSize(int size) {
        textSize = size;
    }

    public void setElementBottomMargin(int margin) {
        elementBottomMargin = margin;
    }
}
