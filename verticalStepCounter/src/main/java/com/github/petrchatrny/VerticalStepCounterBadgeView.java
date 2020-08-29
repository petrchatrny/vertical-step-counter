package com.github.petrchatrny;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import java.util.Objects;

class VerticalStepCounterBadgeView extends FrameLayout {
    private TextView number;
    private ImageView icon;

    public VerticalStepCounterBadgeView(Context context) {
        super(context);
        initialize(context);
    }

    public VerticalStepCounterBadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public VerticalStepCounterBadgeView(Context context, AttributeSet attrs, int defStyleAttr ) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VerticalStepCounterBadgeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void initialize(Context context) {
        LayoutInflater.from(context).inflate(R.layout.vertical_step_counter_view_badge, this, true);
        number = findViewById(R.id.vertical_step_counter_view_number);
        icon = findViewById(R.id.vertical_step_counter_view_icon);
    }

    public void setBackground() {
        GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(getContext(), R.drawable.vertical_step_counter_view_badge);
        if (VerticalStepCounterView.badgeColor == 0) {
            Objects.requireNonNull(drawable).setColor(ResourcesCompat.getColor(
                    getResources(),
                    R.color.vertical_step_counter_view_badge_color,
                    null));
        } else {
            Objects.requireNonNull(drawable).setColor(VerticalStepCounterView.badgeColor);
        }
        setBackgroundResource(R.drawable.vertical_step_counter_view_badge);
    }

    public void setNumber(int value) {
        icon.setVisibility(View.GONE);
        number.setVisibility(View.VISIBLE);
        number.setText(String.valueOf(value));
        if (VerticalStepCounterView.numberColor == 0) {
            number.setTextColor(ResourcesCompat.getColor(
                    getResources(),
                    R.color.vertical_step_counter_view_number_color,
                    null));
        } else {
            number.setTextColor(VerticalStepCounterView.numberColor);
        }
    }
}
