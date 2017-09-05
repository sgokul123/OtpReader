package com.otpreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.otpreader.interfaces.SmsListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity  {

    private String TAG="fgdgg";
    private Matcher matcher;
    private String val="";

    protected void onCreate(Bundle savedInstanceState)
    {
         final AppCompatTextView textView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (AppCompatTextView) findViewById(R.id.textview_otp);
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                Log.d("Text",messageText);

                Pattern pattern = Pattern.compile("(\\d{4})");
                matcher=  pattern.matcher(messageText);
                if (matcher.find()) {
                    val = matcher.group(1);
                    textView.setText("OTP :"+val);
                  //  Toast.makeText(MainActivity.this,"OTP : "+val,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
