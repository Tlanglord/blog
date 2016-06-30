package com.qiang.blog.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver  
{  
  
    @Override  
    public void onReceive(Context context, Intent intent)  
    {  
        Log.d("MyTag", "onclock......................");  
        String msg = intent.getStringExtra("msg");  
        Toast.makeText(context,"EEDDEE",Toast.LENGTH_SHORT).show();  
    }  
  
}  
