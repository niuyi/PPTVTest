package com.niuyi.test;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class TuiJianActivity extends Activity implements
		ViewSwitcher.ViewFactory {

	private ImageSwitcher imageSwitcher;
	private final Integer[] images = { 
								R.drawable.image1,
								R.drawable.image2, 
								R.drawable.image3,
								R.drawable.image4 };
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
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));
		
		handler = new Handler(){
			public void handleMessage(Message message){
				imageSwitcher.setImageResource(images[getImageIndex()]);
			}
		};
		
		GridView gridView = (GridView)this.findViewById(R.id.gridview1);
		gridView.setAdapter(new ImageAdapter(this));
	}

	@Override
	public View makeView() {
		ImageView view = new ImageView(this);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		view.setScaleType(ImageView.ScaleType.FIT_START);
		view.setImageResource(R.drawable.image1);
		return view;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		setTask();
	}

	private void setTask() {
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
