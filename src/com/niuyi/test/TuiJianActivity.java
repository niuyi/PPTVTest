package com.niuyi.test;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.Gallery.LayoutParams;

public class TuiJianActivity extends Activity implements
		ViewSwitcher.ViewFactory {

	private ImageSwitcher imageSwitcher;
	private final Integer[] images = { 
								R.drawable.gallery_photo_1,
								R.drawable.gallery_photo_2, 
								R.drawable.gallery_photo_3,
								R.drawable.gallery_photo_4 };
	private Handler handler;
	private int index = 0;
	private Timer timer = new Timer();
	private ImageTask task;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tuijian);

		imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher1);
		imageSwitcher.setFactory(this);
		
		handler = new Handler(){
			public void handleMessage(Message message){
				imageSwitcher.setImageResource(images[getImageIndex()]);
			}
		};
	}

	@Override
	public View makeView() {
		ImageView i = new ImageView(this);
		i.setBackgroundColor(0xFF000000);
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		i.setImageResource(R.drawable.gallery_photo_1);
		return i;
	}
	
	@Override
	public void onStart(){
		super.onStart();
		if(task != null)
			task.cancel();
		
		task = new ImageTask();
		timer.schedule(task, 0, 2000);
	}
	
	public void onPause(){
		super.onPause();
		if(task != null)
			task.cancel();
	}
	
	private int getImageIndex() {
		return index ++ % 4;
	}

	class ImageTask extends TimerTask {
		public void run(){
			Message message = handler.obtainMessage();
			handler.sendMessage(message);
		}
	}
}
