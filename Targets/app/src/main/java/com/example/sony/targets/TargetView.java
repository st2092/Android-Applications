package com.example.sony.targets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/*
 * This class a graphical view of a drawing of a red/white target.
 */
public class TargetView extends View {
    public
    TargetView(Context context, AttributeSet attribute_set)
    {
        super(context, attribute_set);
    }

    /*
     * This method draws the target figure on the view.
     */
    @Override
    protected void
    onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int num_of_ovals = 5;

        for (int i = 0; i < num_of_ovals; i++)
        {
            canvas.drawOval(new RectF(width*i/10, height*i/10, width*(10-i)/10, height*(10-i)/10),
                            getOvalColor(i));
        }
    }

    /*
     *  This method determines which color an oval should be base on a counter.
     *  The target figure always start with a red oval then white. Thus, when
     *  the counter is even the oval is red and white when odd.
     */
    private Paint
    getOvalColor(int counter)
    {
        Paint oval_color = new Paint();
        if (counter % 2 == 0) //red
        {
            oval_color.setARGB(255, 255, 0, 0); //alpha, red, green, blue
        }
        else //white
        {
            oval_color.setARGB(255, 255, 255, 255);
        }
        return oval_color;
    }
}
