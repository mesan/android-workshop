package no.mesan.android.demo.ui;

import java.util.ArrayList;
import java.util.List;

import no.mesan.android.demo.R;
import no.mesan.android.demo.model.persistence.KeywordDao;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

	public static final String INTENT_KEYWORD = "keyword";
	public static final String INTENT_GET_FROM_HISTORY = "history";

	private ArrayList<String> keywords;
	private KeywordDao keywordDao;

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
		
		// Oppgave 1.4
		
		// Oppgave 2.2
		
		keywords = new ArrayList<String>();
		arrAdapter = new ArrayAdapter<String>(context, R.layout.layout_tweet_keyword_list_item, keywords);
	}

	private void renderView() {		
		// Oppgave 2.4a
	}

	private void initListeners() {

		// Oppgave 1.5
		
		// Oppgave 2.3 og 2.3a

		// Oppgave 2.4b
	}
	

	private boolean getKeywordAndSendToActivity() {
		
		// Oppgave 3.3
		
		return false;
	}

	private void populateList() {
		keywords.clear();
		if (keywordDao != null) {
			List<String> allKeywords = keywordDao.getAllKeywords();
			for (String keyword : allKeywords) {
				keywords.add(keyword);
			}
			arrAdapter.notifyDataSetChanged();
		}
		
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