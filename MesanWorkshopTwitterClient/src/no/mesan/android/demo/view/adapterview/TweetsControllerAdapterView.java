package no.mesan.android.demo.view.adapterview;

import no.mesan.android.demo.controller.R;
import no.mesan.android.demo.model.application.Application;
import no.mesan.android.demo.model.dto.TweetDTO;
import no.mesan.android.demo.model.service.Request;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TweetsControllerAdapterView extends RelativeLayout {

	private RelativeLayout listItemLayout;
	private TextView txtProfileName, txtTweetText, txtTweetDate;
	private ImageView imgProfileImage;

	private Context context;

	public TweetsControllerAdapterView(Context context) {
		super(context);
		this.context = context;
		initLayout();
	}

	public void initLayout() {
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		listItemLayout = (RelativeLayout) layoutInflater.inflate(R.layout.tweets_controller_list_item, this);
		// TextViewene fjernes, skal lages i oppgave fire
		txtProfileName = (TextView) listItemLayout.findViewById(R.id.txtProfileName);
		txtTweetText = (TextView) listItemLayout.findViewById(R.id.txtTweetText);
		txtTweetDate = (TextView) listItemLayout.findViewById(R.id.txtTweetDate);
		imgProfileImage = (ImageView) listItemLayout.findViewById(R.id.imgProfileImage);
	}

	public void renderItem(Boolean isNew, TweetDTO tweetDTO, int position) {
		// la stå
		if (position % 2 != 0) {
			listItemLayout.setBackgroundResource(R.drawable.tweets_gradient_list_element_darker);
		} else {
			listItemLayout.setBackgroundResource(R.drawable.tweets_gradient_list_element);
		}
		// fjernes. Teksten skal settes i oppgaven
		txtProfileName.setText(tweetDTO.getProfileName());
		txtTweetText.setText(tweetDTO.getContent());
		txtTweetDate.setText(Application.formatDateToTimeDiff(tweetDTO.getDate()));

		// la stå
		if (isNew && Application.isNetworkAvailable(context)) {
			
			new ImageFromWebTask().execute(tweetDTO.getProfileUrl());
		}
	}

	private class ImageFromWebTask extends AsyncTask<String, Void, Drawable> {

		@Override
		protected Drawable doInBackground(String... params) {
			Request request = new Request();
			return request.getImageFromWeb(params[0]);
		}

		@Override
		protected void onPostExecute(Drawable image) {
			if (image != null) {
				imgProfileImage.setImageDrawable(image);
			}else{
				imgProfileImage.setImageResource(R.drawable.twiter_01);
			}
		}
	}

}
