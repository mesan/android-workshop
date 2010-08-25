package com.mesan.android.demo.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mesan.android.demo.model.dto.TwitterDTO;
import com.mesan.android.demo.model.util.TwitterUtil;

public class DefaultController extends Activity {
	
	private EditText txtKeyword;
	private Button btnSearch;
	private ListView lstKeywords;
	private ProgressDialog progress;
	
	private ArrayList<String> keywords;
	private TwitterUtil twitterUtil;
	
	private static final int SEARCH_FAILED = 0;
	private static final int SEARCH_SUCCESS = 1;
	
	private Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);               
        
        context = this;
        
        initLayout();
        renderView();
        initListeners();
        
    }

	private void initLayout() {
		txtKeyword = (EditText) findViewById(R.id.txtKeyword);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		lstKeywords = (ListView) findViewById(R.id.lstKeywords);
	}


	private void renderView() {
		keywords = new ArrayList<String>();
		twitterUtil = new TwitterUtil(context);	
		populateList();
	}

	private void initListeners() {
		
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {

				String keyword = txtKeyword.getText().toString();
				if(!"".equals(keyword)) {
					
					searchForTweets(txtKeyword.getText().toString());
					
				}
			}
		});
		
		lstKeywords.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int pos, long id) {
				Intent myIntent = new Intent();
				
				myIntent.setClass(view.getContext(), TweetsController.class);
				myIntent.putExtra("keyword", keywords.get(pos));
				startActivity(myIntent);
			}
		});
	}
	
	private void searchForTweets(final String keyword){
		progress = ProgressDialog.show(context, "Kontakter Twitter", "søker etter tweets med nøkkelord " + keyword, true, true);
		new Thread(new Runnable() {
						
			public void run() {
				Message msg = new Message();
				
				TwitterDTO twitterDTO = twitterUtil.getTwitterDTO(keyword, true);
				
				if(twitterDTO != null){					
					
					msg.what = SEARCH_SUCCESS;
				}else{
					msg.what = SEARCH_FAILED;
				}				
				tweetHandler.sendMessage(msg);
			}
		}).start();
	}
	
	private void populateList(){
		keywords.clear();
		ArrayList<TwitterDTO> allTweets = twitterUtil.getAllTwitterDTOs();
		for (TwitterDTO t : allTweets){
			keywords.add(t.getKeyword());
		}
		lstKeywords.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, keywords));
	}
	
	private Handler tweetHandler = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == SEARCH_SUCCESS){
				txtKeyword.setText("");
				populateList();
				progress.dismiss();
			}
			super.handleMessage(msg);
		}
	};
}