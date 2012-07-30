package no.mesan.android.demo.ui;

import java.util.ArrayList;

import no.mesan.android.demo.model.application.Application;
import no.mesan.android.demo.model.dto.TwitterDto;
import no.mesan.android.demo.model.util.TwitterUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	public static final String INTENT_KEYWORD = "keyword";
	public static final String INTENT_GET_FROM_HISTORY = "history";

	private EditText txtKeyword;
	private Button btnSearch;
	private ListView lstKeywords;

	private ArrayList<String> keywords;
	private TwitterUtil twitterUtil;

	private ArrayAdapter<String> arrAdapter;

	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;

		initLayout();
		renderView();
		initListeners();

	}

	private void initLayout() {
		txtKeyword = (EditText) findViewById(R.id.txtKeyword);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		lstKeywords = (ListView) findViewById(R.id.lstKeywords);
		keywords = new ArrayList<String>();
		arrAdapter = new ArrayAdapter<String>(context, R.layout.layout_tweet_keyword_list_item, keywords);
		lstKeywords.setAdapter(arrAdapter);
	}

	private void renderView() {
		twitterUtil = new TwitterUtil(context);
		populateList();
	}

	private void initListeners() {

		btnSearch.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				getKeywordAndSendToActivity();
			}
		});
		txtKeyword.setOnKeyListener(new View.OnKeyListener() {

			public boolean onKey(View v, int keyCode, KeyEvent event) {

				if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
					return getKeywordAndSendToActivity();
				}
				return false;
			}
		});

		lstKeywords.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int pos, long id) {
				goToActivity(view.getContext(), keywords.get(pos), true);
			}
		});
	}

	private boolean getKeywordAndSendToActivity() {
		String keyword = txtKeyword.getText().toString();
		if (!"".equals(keyword)) {
			txtKeyword.setText("");
			Application.hideKeyboard(context, txtKeyword);
			goToActivity(context, keyword, false);
			return true;
		}
		return false;
	}

	private void populateList() {
		keywords.clear();
		ArrayList<TwitterDto> allTweets = twitterUtil.getAllTwitterDTOs();
		for (TwitterDto t : allTweets) {
			keywords.add(t.getKeyword());
		}
		arrAdapter.notifyDataSetChanged();
	}

	private void goToActivity(final Context context, final String keyword, final boolean getFromHistory) {
		Intent myIntent = new Intent();

		myIntent.setClass(context, ResultActivity.class);
		myIntent.putExtra(INTENT_KEYWORD, keyword);
		myIntent.putExtra(INTENT_GET_FROM_HISTORY, getFromHistory);
		startActivity(myIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateList();
	}

}