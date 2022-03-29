package com.example.samplethread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int value = 0;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackGroundThread thread = new BackGroundThread(); //사용자정의 스레드 객체 생성
                thread.start(); //start메소드로 간접적으로 스레드 호출
            }
        });
    }

    //사용자 정의 스레드
    class BackGroundThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                //스레드 처리시 반드시 예외처리도 해야함
                try {
                    Thread.sleep(1000); //1초 딜레이
                } catch (Exception e) {
                }

                value += 1;
                Log.d("Thread", "value : " + value);

                //스레드 안에서 텍스트뷰의 setText메서드 호출
                textView.setText("value값 : " + value);
                //textView는 메인 스레드의 객체이므로
                //사용자정의 스레드(즉, 서브스레드)에서 메인스레드의 객체의 변경 불가능능
            }

        }
    }
}