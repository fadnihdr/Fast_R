package com.fastr.fadni.fast_r;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {
    Display display;
    int firstNum,secondNum,answer;
    int counter = 0;
    Toast toast;
    private ScoresDAOHelper scores;
    int correct, wrong;
    SoundManager soundManager;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        display = (Display) findViewById(R.id.display);
        scores = new ScoresDAOHelper(this);
        soundManager = new SoundManager(this);
        correct = soundManager.addSound(R.raw.correct);
        wrong = soundManager.addSound(R.raw.wrong);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.counter = 0;
        display.setQuestion(generateQ());
        generateA();
        timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                display.setTimer(String.valueOf(millisUntilFinished/1000));
                display.setCorrect(String.valueOf(counter));
            }

            @Override
            public void onFinish() {
                gameEnd();
            }
        };
        timer.start();
    }

    public String generateQ(){
        //format is (a operator b equal c)
        Random random = new Random();
        int a = random.nextInt(15);
        int b = random.nextInt(15);
        int c;
        this.firstNum = a;
        this.secondNum = b;
        switch (random.nextInt(4)+1){
            case 1:
                c = a * b;
                this.answer = c;
                //multiply
                return firstNum + " x " + secondNum + " = ";
            case 2:
                c = a / b;
                this.answer = c;
                //division
                return firstNum + " : " + secondNum + " = ";
            case 3:
                c = a + b;
                this.answer = c;
                //subtraction
                return firstNum + " + " + secondNum + " = ";
            case 4:
                c = a - b;
                this.answer = c;
                //addition
                return firstNum + " - " + secondNum + " = ";
        }
        return null;

    }

    public void generateA(){
        Random random = new Random();
        switch (random.nextInt(4)+1){
            case 1:
                display.setAns_a(String.valueOf(answer));
                display.setAns_b(String.valueOf(random.nextInt(100)));
                display.setAns_c(String.valueOf(random.nextInt(100)));
                display.setAns_d(String.valueOf(random.nextInt(100)));
                break;
            case 2:
                display.setAns_b(String.valueOf(answer));
                display.setAns_a(String.valueOf(random.nextInt(100)));
                display.setAns_c(String.valueOf(random.nextInt(100)));
                display.setAns_d(String.valueOf(random.nextInt(100)));
                break;
            case 3:
                display.setAns_c(String.valueOf(answer));
                display.setAns_b(String.valueOf(random.nextInt(100)));
                display.setAns_a(String.valueOf(random.nextInt(100)));
                display.setAns_d(String.valueOf(random.nextInt(100)));
                break;
            case 4:
                display.setAns_d(String.valueOf(answer));
                display.setAns_b(String.valueOf(random.nextInt(100)));
                display.setAns_c(String.valueOf(random.nextInt(100)));
                display.setAns_a(String.valueOf(random.nextInt(100)));
                break;
        }


    }

    public boolean onTouchEvent(MotionEvent event) {
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);

        if(event.getAction() != MotionEvent.ACTION_DOWN){
            return super.onTouchEvent(event);
        }
        Position position = display.getPosition(event.getX(), event.getY());

        switch (position){
            case ANS_A:
                if (display.ans_a.equals(String.valueOf(answer))){
                    display.setQuestion(generateQ());
                    generateA();
                    this.counter += 1;
                    soundManager.play(correct);
                    break;
                }
                else {
                    display.startAnimation(animShake);
                    soundManager.play(wrong);
                    break;

                }
            case ANS_B:
                if (display.ans_b.equals(String.valueOf(answer))){
                    display.setQuestion(generateQ());
                    generateA();
                    this.counter += 1;
                    soundManager.play(correct);
                    break;
                }
                else {
                    display.startAnimation(animShake);
                    soundManager.play(wrong);
                    break;

                }
            case ANS_C:
                if (display.ans_c.equals(String.valueOf(answer))){
                    display.setQuestion(generateQ());
                    generateA();
                    this.counter += 1;
                    soundManager.play(correct);
                    break;
                }
                else {
                    display.startAnimation(animShake);
                    soundManager.play(wrong);
                    break;

                }
            case ANS_D:
                if (display.ans_d.equals(String.valueOf(answer))){
                    display.setQuestion(generateQ());
                    generateA();
                    this.counter += 1;
                    soundManager.play(correct);
                    break;
                }
                else {
                    display.startAnimation(animShake);
                    soundManager.play(wrong);
                    break;

                }

        }
        return super.onTouchEvent(event);
    }

    public void gameEnd(){
        updateScore();
        Toast.makeText(this,"Time's Up!, You answered " + counter + " questions!", Toast.LENGTH_LONG).show();
        addScore(String.valueOf(counter));
        this.finish();
    }

    private void updateScore() {
        Cursor cursor = scores.getReadableDatabase()
                .rawQuery("select * from scores", null);
        StringBuilder builder = new StringBuilder();
        builder.append("scores: ");
        while (cursor.moveToNext()) {
            System.out.println("id: " + cursor.getString(0)
                    + " value: " + cursor.getString(1));
            builder.append(cursor.getString(1)).append(" ");
        }
        cursor.close();
    }

    public void addScore(String score) {
        SQLiteDatabase db = scores.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", score);
        db.insert("scores", null, contentValues);

        updateScore();
    }

    @Override
    public void onStop(){
        super.onStop();
        timer.cancel();
    }

}
