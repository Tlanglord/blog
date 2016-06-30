package com.qiang.blog.activity;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.qiang.blog.R;
import com.qiang.blog.requestbase.BaseActivity;

public class MemoTaskShowActivity extends BaseActivity implements
	OnClickListener {

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private AlarmManager mAlarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    protected int onContentView() {
	return R.layout.activity_memo_task_add;
    }

    @Override
    protected void onInitView() {
	mDatePicker = (DatePicker) findViewById(R.id.memo_task_datepicker);
	mTimePicker = (TimePicker) findViewById(R.id.memo_task_timepicker);

    }

    @Override
    protected void onInitEvent() {
	Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DAY_OF_MONTH);
	int hour = c.get(Calendar.HOUR);
	int minute = c.get(Calendar.MINUTE);
	mDatePicker.init(year, month, day, new OnDateChangedListener() {

	    @Override
	    public void onDateChanged(DatePicker view, int year,
		    int monthOfYear, int dayOfMonth) {

	    }
	});

	mTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

	    @Override
	    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

	    }
	});
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void back() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this)
		.setTitle("添加Task");

	builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

	    @Override
	    public void onClick(DialogInterface dialog, int which) {
		finish();
	    }
	});
	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

	    @Override
	    public void onClick(DialogInterface dialog, int which) {
		Intent intent = new Intent(); 
		PendingIntent pIntent = PendingIntent.getActivity(
			MemoTaskShowActivity.this, 1, intent, 0);
	    }
	});
	builder.create().show();
    }

}
