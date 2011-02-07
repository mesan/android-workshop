package no.mesan.android.demo.controller;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabController extends TabActivity {
	private TabHost tabHost;
	private TabHost.TabSpec spec;
	private Intent intent;
	private String keyword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		tabHost = getTabHost();		
		
		keyword = getIntent().getStringExtra("keyword");
		initTabs();
		
		tabHost.setCurrentTab(0);
	}
	
	void initTabs(){
		intent = new Intent().setClass(this, TweetsController.class);
		intent.putExtra("keyword", keyword);
	    spec = tabHost.newTabSpec("tweets").setIndicator("Tweets", getResources().getDrawable(R.drawable.twitter_birds_icon)).setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, GalleryController.class);
	    intent.putExtra("keyword", keyword);
	    spec = tabHost.newTabSpec("gallery").setIndicator("Gallery", getResources().getDrawable(R.drawable.gallery_icon)).setContent(intent);
	    tabHost.addTab(spec);
	}
}
