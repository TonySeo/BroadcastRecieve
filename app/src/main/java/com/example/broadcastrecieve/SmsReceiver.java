package com.example.broadcastrecieve;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive 호출됨.");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);
        if (messages.length > 0){
            String sender = messages[0].getOriginatingAddress();    //발신번호
            Log.d(TAG, "sender : "+sender);

            String contents = messages[0].getMessageBody().toString();
            Log.d(TAG, "contents : "+contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis()); // 발신 시각
            Log.d(TAG, "received date : "+receivedDate);
        }
    }//onReceive end

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus"); // pdus : SMS 국제 표준 프로토콜 SMPP 안에 데이터가 들어가있는 이름. 안드로이드 내부 모듈이 처리 후 데이터를 던져줌.
        SmsMessage[] messages = new SmsMessage[objs.length];

        for(int i=0; i<objs.length; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){  // 마쉬멜로우 버전보다 같거나 크면
                String format = bundle.getString("format"); // 인텐트에 저장된 정보 format
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]); // 마쉬멜로우 이전버전
            }
        }
        // 번들객체 안의 데이터를 이용해 smsMessages라는 객체로 변환 후 리턴.
        // 이 안에는 데이터가 있음.
        return messages;
    }//SmsMessage end
}
