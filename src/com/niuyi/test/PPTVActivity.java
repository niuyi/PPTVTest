package com.niuyi.test;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

public class PPTVActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Resources resources = this.getResources();
		TabHost tabHost = this.getTabHost();

		tabHost.addTab(tabHost
				.newTabSpec("TuiJian")
				.setIndicator("tuijian", resources.getDrawable(R.drawable.tabs))
				.setContent(new Intent(this, TuiJianActivity.class)));

		tabHost.addTab(tabHost.newTabSpec("PinDao")
				.setIndicator("PinDao", resources.getDrawable(R.drawable.tabs))
				.setContent(new Intent(this, PinDaoActivity.class)));

		tabHost.addTab(tabHost.newTabSpec("ZhiBo")
				.setIndicator("ZhiBo", resources.getDrawable(R.drawable.tabs))
				.setContent(new Intent(this, ZhiBoActivity.class)));
		
		tabHost.addTab(tabHost.newTabSpec("Search")
				.setIndicator("Search", resources.getDrawable(R.drawable.tabs))
				.setContent(new Intent(this, SearchActivity.class)));

		tabHost.addTab(tabHost.newTabSpec("Personal")
				.setIndicator("Personal", resources.getDrawable(R.drawable.tabs))
				.setContent(new Intent(this, PersonalActivity.class)));
		
		tabHost.setCurrentTab(0);

	}
}