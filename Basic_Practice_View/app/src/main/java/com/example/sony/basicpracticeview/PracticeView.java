package com.example.sony.basicpracticeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class PracticeView extends View {
    public
    PracticeView(Context context, AttributeSet attribute_set)
    {
        super(context, attribute_set);
    }

    /*
     * This method draws some shapes and text on the view.
     */
    @Override
    protected void
    onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawARGB(255, 150, 60, 90); //alpha, red, green, blue

        Paint aqua = new Paint();
        aqua.setARGB(255, 0, 80, 220);

        canvas.drawRect(new RectF(10, 30, 300, 600), aqua);
        canvas.drawOval(new RectF(400, 50, getWidth(), getHeight()), aqua);

        Paint font = new Paint();
        font.setARGB(255, 0, 0, 0);
        font.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC));
        font.setTextSize(40);
        canvas.drawText("Hello World", 80, 200, font);
    }
}
