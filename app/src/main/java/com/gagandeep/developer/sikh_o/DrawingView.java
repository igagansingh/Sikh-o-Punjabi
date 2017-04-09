package com.gagandeep.developer.sikh_o;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by gagandeep on 10/15/2016.
 */
public class DrawingView extends View
{   private Paint paint;
    private ArrayList<Path> line;
    private Path path;
    private int width,height;
    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(Math.min(width,height) * 0.05f);
        line=new ArrayList<>();
        path=new Path();
        this.setDrawingCacheEnabled(true);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#FF0000"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Math.min(width,height) * 0.045f);
        for(Path P:line)
        {   canvas.drawPath(P, paint);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch(event.getAction())
        {   case MotionEvent.ACTION_MOVE:   path.lineTo(x, y);
            break;
            case MotionEvent.ACTION_DOWN:   path=new Path();
                path.moveTo(x, y);
                line.add(path);
                break;
        }
        invalidate();
        return true;
    }
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        this.width=getWidth();
        this.height=getHeight();
        invalidate();
    }
    public Bitmap getBitmap()
    {   return this.getDrawingCache();
    }
    public void clear()
    {   line=new ArrayList<>();
        invalidate();
    }
    @Override
    public void invalidate() {
        super.invalidate();
        this.buildDrawingCache(true);
    }
}

