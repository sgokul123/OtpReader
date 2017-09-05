package com.otpreader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.otpreader.interfaces.SmsListener;

public class SmsReceiver extends BroadcastReceiver {

    private static SmsListener mListener;
    private String TAG="SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for(int i=0;i<pdus.length;i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String sender = smsMessage.getDisplayOriginatingAddress();
            //You must check here if the sender is your provider and not another one with same text.
            Log.i(TAG, "onReceive: "+sender);
            String messageBody = smsMessage.getMessageBody();

            //Pass on the text to our listener.
            if(sender.equals("+917977948588")) {
                mListener.messageReceived(messageBody);
            }
        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}
