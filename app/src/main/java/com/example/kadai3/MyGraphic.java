package com.example.kadai3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyGraphic extends View {

    private ImageView img;
    private boolean visible_flag;
    int frag = 0;
    int cw2 = 0;
    int ch2 = 0;

    public MyGraphic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageView(ImageView imageView){
        this.img = imageView;
    }

    public void setVisible_flag(boolean flag){
        this.visible_flag = flag;
    }

    public void drawbitmap(Canvas canvas, Bitmap bitmap, int factor, Paint paint) {         //画像描画

        int cw = canvas.getWidth();
        int ch = canvas.getHeight();
        int left = 16 + ((factor-1) * 8);
        int top = 8;
        int right = 23 + ((factor-1) * 8);
        int bottom = 22;


        Rect srcrect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());     //画像の描画範囲を取得
        RectF rect = new RectF(cw * left / 80, ch * top / 80, cw * right / 80, ch * bottom / 80);//画像をどこに描画するかを取得

        canvas.save();
        canvas.drawBitmap(bitmap, srcrect, rect, paint);                                  //描画する
        canvas.restore();
    }   //画像描画

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        cw2 = w;
//        ch2 = h;
//    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

//        int alpha = 255;
//        int wallpaper[] = {50, 50, 50};
        int viewparam[] = {230, 150, 180};

        int cw = canvas.getWidth();
        int ch = canvas.getHeight();

        cw2 = img.getWidth();
        ch2 = img.getHeight();

//        img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                cw2 = img.getWidth();
//                ch2 = img.getHeight();
//
//                img.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });


//        RectF hokkaido = new RectF(cw2*4/50,ch2*13/50,cw2*21/50,ch2*21/50);    //50,250,300,452
        RectF hokkaido = new RectF(cw2*13/80,ch2*24/80,cw2*37/80,ch2*35/80);    //50,250,300,452
        RectF tohoku = new RectF(cw2*65/80,ch2*23/80,cw2*80/80,ch2*30/80);
        RectF chubu = new RectF(cw2*50/80,ch2*32/80,cw2*67/80,ch2*38/80);
        RectF kanto = new RectF(cw2*59/80,ch2*40/80,cw2*74/80,ch2*45/80);
        RectF kinki= new RectF(cw2*39/80,ch2*43/80,cw2*52/80,ch2*48/80);
        RectF chugoku = new RectF(cw2*13/80,ch2*39/80,cw2*32/80,ch2*45/80);
        RectF shikoku = new RectF(cw2*24/80,ch2*48/80,cw2*37/80,ch2*55/80);
        RectF qushu = new RectF(cw2*3/80,ch2*51/80,cw2*18/80,ch2*59/80);
        RectF okinawa = new RectF(cw2*55/80,ch2*48/80,cw2*69/80,ch2*55/80);





        Paint paint = new Paint();

        if(visible_flag) {
            paint.setColor(Color.argb(190, viewparam[0], viewparam[1], viewparam[2]));

            canvas.drawRect(hokkaido, paint);
            canvas.drawRect(tohoku, paint);
            canvas.drawRect(kanto, paint);
            canvas.drawRect(chubu, paint);
            canvas.drawRect(kinki, paint);
            canvas.drawRect(shikoku, paint);
            canvas.drawRect(chugoku, paint);
            canvas.drawRect(qushu, paint);
            canvas.drawRect(okinawa, paint);
        }
        if(frag == 1){

        }

    }
}
