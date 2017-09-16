package com.fastr.fadni.fast_r;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.fastr.fadni.fast_r.R;


public class Display extends View {
    String question, ans_a,ans_b,ans_c, ans_d, timer, correct;
    Paint paint;
    public Display(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground(ContextCompat.getDrawable(context, R.drawable.bb));
        question = "";
        ans_a = "";
        ans_b = "";
        ans_c = "";
        ans_d = "";
        timer = "";
        correct = "";
        paint = new Paint();
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
    }



    private PointF getPoints() {
        return new PointF(getWidth()/2f,getHeight()/4f);
    }

    public Position getPosition(float x, float y){
        if(x < getPoints().x && y > getPoints().y*2 && y < getPoints().y*3){
            return Position.ANS_A;
        }else if (x > getPoints().x && y > getPoints().y*2 && y < getPoints().y*3){
            return  Position.ANS_B;
        }else if (x < getPoints().x && y > getPoints().y*3 && y < getPoints().y*4){
            return Position.ANS_C;
        }else if (x > getPoints().x && y > getPoints().y*3 && y < getPoints().y*4){
            return  Position.ANS_D;
        }
        else {
            return Position.NULL;
        }

    }
    public void setQuestion(String question){
        this.question = question;
        invalidate();
    }
    public void setAns_a(String ans_a){
        this.ans_a = ans_a;
        invalidate();
    }
    public void setAns_b(String ans_b){
        this.ans_b = ans_b;
        invalidate();
    }
    public void setAns_c(String ans_c){
        this.ans_c = ans_c;
        invalidate();
    }
    public void setAns_d(String ans_d){
        this.ans_d = ans_d;
        invalidate();
    }
    public void setTimer(String timer){
        this.timer = timer;
        invalidate();
    }

    public void setCorrect(String correct){
        this.correct = correct;
        invalidate();
    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        float x = getWidth()/2;
        float y = getHeight()/4;
        float xa = getWidth()/4;
        float ya = getHeight()*5/8;
        float xb = getWidth()*3/4;
        float yb =getHeight()*5/8;
        float xc =getWidth()/4;
        float yc =getHeight()*7/8;
        float xd =getWidth()*3/4;
        float yd =getHeight()*7/8;
        float xt = getWidth()/20;
        float yt = getHeight()/20;
        float xr = getWidth()/20*19;
        float yr = getHeight()/20;
        paint.setColor(Color.WHITE);
        canvas.drawText(question, x, y, paint);
        canvas.drawText(ans_a,xa,ya,paint);
        canvas.drawText(ans_b,xb,yb,paint);
        canvas.drawText(ans_c,xc,yc,paint);
        canvas.drawText(ans_d,xd,yd,paint);
        canvas.drawText(timer, xt, yt,paint);
        canvas.drawText(correct,xr,yr,paint);
    }



    }

