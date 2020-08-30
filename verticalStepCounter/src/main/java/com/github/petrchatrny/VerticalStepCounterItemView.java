package com.github.petrchatrny;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

class VerticalStepCounterItemView extends FrameLayout {
    private boolean showConnectorLine = true;
    private VerticalStepCounterBadgeView badge;
    private TextView text;
    private LinearLayout wrapper;
    private ConnectorLineDrawer connector;

    // CONSTRUCTORS
    public VerticalStepCounterItemView(Context context) {
        super(context);
        initialize(context);
    }

    public VerticalStepCounterItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public VerticalStepCounterItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VerticalStepCounterItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    // GUI METHODS
    private void initialize(Context context) {
        setWillNotDraw(false);
        setClipChildren(false);
        setClipToPadding(false);

        int padding = (int) Util.dpToPx(context, 8);
        setPadding(padding, padding, padding, 0);

        LayoutInflater.from(context).inflate(R.layout.item_stepper_line, this, true);

        badge = findViewById(R.id.vertical_step_counter_badge);
        wrapper = findViewById(R.id.vertical_stepper_view_item_wrapper);
        text = findViewById(R.id.vertical_step_counter_text);

        connector = new ConnectorLineDrawer(context);
    }

    public void setShowConnectorLine(boolean show) {
        showConnectorLine = show;
        setMarginBottom();
    }

    public boolean getShowConnectorLine() {
        return showConnectorLine;
    }

    // DATA METHODS
    public void setCircleNumber(int number) {
        badge.setNumber(number);
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    private void displayDataTogether() {
        setMarginBottom();
        badge.setBackground();

        // text color
        if (VerticalStepCounterView.textColor == 0) {
            text.setTextColor(ResourcesCompat.getColor(
                    getResources(),
                    R.color.vertical_step_counter_view_text_color,
                    null));
        } else {
            text.setTextColor(VerticalStepCounterView.textColor);
        }

        // text font
        text.setTypeface(VerticalStepCounterView.textFont);

        // text size
        if (VerticalStepCounterView.textSize != 0) {
            text.setTextSize(VerticalStepCounterView.textSize);
        }
    }

    private void setMarginBottom() {
        MarginLayoutParams params = (MarginLayoutParams) wrapper.getLayoutParams();

        if (!getShowConnectorLine())
            params.bottomMargin = 0;
        else
            params.bottomMargin = (int) Util.dpToPx(getContext(), VerticalStepCounterView.elementBottomMargin);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (showConnectorLine) {
            connector.draw(canvas);
        }
        displayDataTogether();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        connector.adjust(getContext(), height);
    }
}