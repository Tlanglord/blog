package com.qiang.blog.activity;

import java.util.Calendar;
import java.util.TimeZone;

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
import android.widget.Toast;

import com.qiang.blog.R;
import com.qiang.blog.requestbase.BaseActivity;
import com.qiang.blog.ui.RichEditText2;

public class MemoTaskAddActivity extends BaseActivity implements
	OnClickListener {

    private DatePicker mMemoDatePicker;
    private TimePicker mMemoTimePicker;
    private RichEditText2 mMemoTaskContent;
    private AlarmManager mAlarmManager;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

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
	mMemoDatePicker = (DatePicker) findViewById(R.id.memo_task_datepicker);
	mMemoTimePicker = (TimePicker) findViewById(R.id.memo_task_timepicker);
	mMemoTaskContent = (RichEditText2) findViewById(R.id.memo_task_content);
    }

    @Override
    protected void onInitEvent() {
	Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DAY_OF_MONTH);
	int hour = c.get(Calendar.HOUR);
	int minute = c.get(Calendar.MINUTE);
	mMemoDatePicker.init(year, month, day, new OnDateChangedListener() {

	    @Override
	    public void onDateChanged(DatePicker view, int year,
		    int monthOfYear, int dayOfMonth) {
		mYear = year;
		mMonth = monthOfYear;
		mDay = dayOfMonth;
	    }
	});

	mMemoTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

	    @Override
	    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		mHour = hourOfDay;
		mMinute = minute;
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
		intent.setAction("ELITOR_CLOCK");

		intent.putExtra("memo_content", mMemoTaskContent.getText()
			.toString());
		PendingIntent pIntent = PendingIntent.getBroadcast(
			MemoTaskAddActivity.this, 0, intent,
			PendingIntent.FLAG_CANCEL_CURRENT);

		Calendar calendar = Calendar.getInstance(TimeZone
			.getTimeZone("GMT+8"));
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, mHour);
		calendar.set(Calendar.MINUTE, mMinute);

		// mAlarmManager.setExact(AlarmManager.RTC_WAKEUP, 60000,
		// pIntent);
		long curTime =  System.currentTimeMillis();
		long time = calendar.getTimeInMillis() - curTime;
		mAlarmManager.setWindow(AlarmManager.RTC_WAKEUP,
			curTime, time, pIntent);

		// mAlarmManager.set(AlarmManager.RTC_WAKEUP,
		// 60000,
		// pIntent);

		Toast.makeText(MemoTaskAddActivity.this, "设置任务成功!",
			Toast.LENGTH_SHORT).show();
		finish();
	    }
	});
	builder.create().show();
	// super.back();

    }
}
