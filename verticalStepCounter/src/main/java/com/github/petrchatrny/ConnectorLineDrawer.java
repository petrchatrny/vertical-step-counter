package com.github.petrchatrny;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.core.content.res.ResourcesCompat;

class ConnectorLineDrawer {
    private final Paint paint = new Paint();
    private final RectF line = new RectF();

    ConnectorLineDrawer(Context context) {
        int color;
        if (VerticalStepCounterView.lineColor == 0) {
            color = ResourcesCompat.getColor(context.getResources(), R.color.vertical_step_counter_view_line_color, null);

        } else {
            color = VerticalStepCounterView.lineColor;
        }
        paint.setColor(color);
    }

    void adjust(Context context, int height) {
        line.left = Util.dpToPx(context, 19.5f);
        line.right = Util.dpToPx(context, 20.5f);
        line.top = Util.dpToPx(context, 40);
        line.bottom = height;
    }

    void draw(Canvas canvas) {
        canvas.drawRect(line, paint);
    }
}