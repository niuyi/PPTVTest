package com.niuyi.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TuiJianActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		/*LinearLayout view = (LinearLayout)this.findViewById(R.layout.tuijian);
		Log.d("view", "layout: " + (view == null));*/
		setContentView(R.layout.tuijian);
	}
}
