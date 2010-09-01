package no.mesan.android.demo.controller;

import java.util.ArrayList;

import no.mesan.android.demo.controller.R;
import no.mesan.android.demo.model.application.Application;
import no.mesan.android.demo.model.dto.TwitterDTO;
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

public class DefaultController extends Activity {

	private EditText txtKeyword;
	private Button btnSearch;
	private ListView lstKeywords;

	private ArrayList<String> keywords;
	private TwitterUtil twitterUtil;

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
				if (!"".equals(keyword)) {
					txtKeyword.setText("");
					Application.hideKeyboard(context, txtKeyword);
					goToActivity(keyword, context);
				}
			}
		});
		txtKeyword.setOnKeyListener(new View.OnKeyListener() {

			public boolean onKey(View v, int keyCode, KeyEvent event) {

				if (keyCode == KeyEvent.KEYCODE_ENTER
						&& event.getAction() == KeyEvent.ACTION_DOWN) {
					String keyword = txtKeyword.getText().toString();
					if (!"".equals(keyword)) {
						txtKeyword.setText("");
						Application.hideKeyboard(context, txtKeyword);
						goToActivity(keyword, context);
						return true;
					}
				}
				return false;
			}
		});

		lstKeywords
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View view,
							int pos, long id) {
						goToActivity(keywords.get(pos), view.getContext());
					}
				});
	}

	private void populateList() {
		keywords.clear();
		ArrayList<TwitterDTO> allTweets = twitterUtil.getAllTwitterDTOs();
		for (TwitterDTO t : allTweets) {
			keywords.add(t.getKeyword());
		}
		lstKeywords.setAdapter(new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, keywords));
	}

	private void goToActivity(String keyword, Context context) {
		Intent myIntent = new Intent();

		myIntent.setClass(context, TweetsController.class);
		myIntent.putExtra("keyword", keyword);
		startActivity(myIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateList();
	}

}