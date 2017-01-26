package com.example.furkanmumcu.wawrecorder;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Recorder recorder = new Recorder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonHandlers();
        enableButtons(false);


        int bufferSize = AudioRecord.getMinBufferSize(8000,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        recorder.setBufferSize(bufferSize);


    }

    private void setButtonHandlers() {
        ((Button)findViewById(R.id.btnStart)).setOnClickListener(btnClick);
        ((Button)findViewById(R.id.btnStop)).setOnClickListener(btnClick);
    }

    private void enableButton(int id,boolean isEnable){
        ((Button)findViewById(id)).setEnabled(isEnable);
    }

    private void enableButtons(boolean isRecording) {
        enableButton(R.id.btnStart,!isRecording);
        enableButton(R.id.btnStop,isRecording);
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnStart:{
                    if (!isEmpty((EditText) findViewById(R.id.tw1))) {
                        AppLog.logString("Start Recording");
                        enableButtons(true);
                        EditText text = (EditText)findViewById(R.id.tw1);
                        String str = text.getText().toString();
                        recorder.setWavname(str);
                        recorder.startRecording();
                        text.setEnabled(false);

                        /*
                        Example to threading and waiting
                        Thread t = new Thread()
                        {
                            public void run() {
                                recorder.startRecording();
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                AppLog.logString("selam");
                            }
                        };
                        t.start(); */

                        break;
                    }
                        else {
                        Toast.makeText(MainActivity.this, "Please enter a name!", Toast.LENGTH_SHORT).show();
                        break; /* instead of this, might be used following
                                  if (!isEmpty((EditText) findViewById(R.id.tw1)))
                                  in the next case */
                    }
                }
                case R.id.btnStop:{
                    AppLog.logString("Stop Recording");
                    enableButtons(false);
                    recorder.stopRecording();
                    EditText text = (EditText)findViewById(R.id.tw1);
                    text.setEnabled(true);
                    break;

                }
            }
        }
    };


}
