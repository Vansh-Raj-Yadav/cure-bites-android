package com.example.curebites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

class WeeklyBarChart extends View {

    private final Paint barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint bgBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // Normalized values (0..1) for weeks w1–w6
    private final float[] values = {0.35f, 0.30f, 0.40f, 0.80f, 0.70f, 0.85f};
    // Highlight weeks 4, 5, 6 as dark green; rest as light green
    private final boolean[] highlighted = {false, false, false, true, true, true};

    private final int colorHighlight = 0xFF2E8B6E;
    private final int colorLight     = 0xFFB2DFDB;
    private final int colorBg        = 0xFFEEEEEE;

    public WeeklyBarChart(Context context) { super(context); }
    public WeeklyBarChart(Context context, AttributeSet attrs) { super(context, attrs); }
    public WeeklyBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        int count = values.length;

        float totalGap = w * 0.4f;
        float barW = (w - totalGap) / count;
        float gap = totalGap / (count + 1);
        float cornerR = barW * 0.35f;

        for (int i = 0; i < count; i++) {
            float left = gap + i * (barW + gap);
            float right = left + barW;

            // Background bar
            bgBarPaint.setColor(colorBg);
            canvas.drawRoundRect(new RectF(left, 0, right, h), cornerR, cornerR, bgBarPaint);

            // Value bar
            float barH = values[i] * h;
            float top = h - barH;
            barPaint.setColor(highlighted[i] ? colorHighlight : colorLight);
            canvas.drawRoundRect(new RectF(left, top, right, h), cornerR, cornerR, barPaint);
        }
    }
}
