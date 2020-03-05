package com.example.recursive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

public class MyDraw extends SurfaceView implements SurfaceHolder.Callback {

    Paint paint;
    int w, h;

    public MyDraw(Context context) {
        super(context);
        getHolder().addCallback(this);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawCircles(canvas,canvas.getWidth()/2,canvas.getHeight()/2,1000);
        canvas.drawColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.w = w;
        this.h = h;

    }

    public void drawCircles(Canvas canvas, int x, int y, int r) {
        Random z = new Random();
        int color = Color.rgb(z.nextInt(),z.nextInt(),z.nextInt());
        paint.setColor(color);
        canvas.drawCircle(x, y, r, paint);


        if (r > 20) {
            drawCircles(canvas, x, y - r, r / 2);
            drawCircles(canvas, x + r, y, r / 2);
            drawCircles(canvas, x, y + r, r / 2);
            drawCircles(canvas, x - r, y, r / 2);
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        new Thread() {
            @Override
            public void run() {

                while (true) {
                    Canvas canvas = getHolder().lockCanvas();
                    if (canvas != null) {
                        Random r = new Random();
                        int color = Color.rgb(r.nextInt(),r.nextInt(),r.nextInt());
                        paint.setColor(color);
                        drawCircles(canvas, canvas.getWidth() / 2, canvas.getHeight() / 2, 700);
                        getHolder().unlockCanvasAndPost(canvas);
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }
}

