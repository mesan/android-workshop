package no.mesan.android.demo.view;

import java.util.ArrayList;

import no.mesan.android.demo.R;
import no.mesan.android.demo.model.application.Application;
import no.mesan.android.demo.model.dto.TweetDto;
import no.mesan.android.demo.task.DownloadImageTask;
import no.mesan.android.demo.task.TaskResult;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetsAdapter extends BaseAdapter {
	ArrayList<TweetDto> tweetDtoList;
	Context context;
	private LayoutInflater layoutInflater;
	private TweetDto tweetDto;

	public TweetsAdapter(Context context, ArrayList<TweetDto> listOfTweetDTO) {
		this.context = context;
		this.tweetDtoList = listOfTweetDTO;
		layoutInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return tweetDtoList.size();
	}

	public Object getItem(int position) {
		return tweetDtoList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int index, View tweetListItemView, ViewGroup parent) {
		ViewHolder holder;

		if (tweetListItemView == null) {
			tweetListItemView = layoutInflater.inflate(R.layout.layout_tweets_list_item, parent, false);

			holder = new ViewHolder();
			holder.txtProfileName = (TextView) tweetListItemView.findViewById(R.id.txtProfileName);
			holder.txtTweetText = (TextView) tweetListItemView.findViewById(R.id.txtTweetText);
			holder.txtTweetDate = (TextView) tweetListItemView.findViewById(R.id.txtTweetDate);
			holder.imgProfileImage = (ImageView) tweetListItemView.findViewById(R.id.imgProfileImage);

			tweetListItemView.setTag(holder);
		} else {
			holder = (ViewHolder) tweetListItemView.getTag();
		}

		tweetDto = tweetDtoList.get(index);

		// la stå
		if (index % 2 != 0) {
			tweetListItemView.setBackgroundResource(R.drawable.tweets_gradient_list_element_darker);
		} else {
			tweetListItemView.setBackgroundResource(R.drawable.tweets_gradient_list_element);
		}

		// fjernes. Teksten skal settes i oppgaven
		holder.txtProfileName.setText(tweetDto.getProfileName());
		holder.txtTweetText.setText(tweetDto.getContent());
		holder.txtTweetDate.setText(Application.formatDateToTimeDiff(tweetDto.getDate()));

		// la stå
		if (!tweetDto.hasImage() && Application.isNetworkAvailable(context)) {
			tweetDto.setImgProfile(context.getResources().getDrawable(R.drawable.twitter_01));			
			loadImage(tweetDto);
		}

		holder.imgProfileImage.setImageDrawable(tweetDto.getImgProfile());

		return tweetListItemView;
	}
	
	private void loadImage(final TweetDto tweetDto) {
		new DownloadImageTask().executeWithCallback(new TaskResult<Drawable>() {

			@Override
			public void handleResult(Drawable result) {
				if (result != null) {
					tweetDto.setImgProfile(result);
					
					// Update rows
					notifyDataSetChanged();
				}
			}
		}, tweetDto.getProfileUrl());
	}

	static class ViewHolder {
		TextView txtProfileName, txtTweetText, txtTweetDate;
		ImageView imgProfileImage;
	}
}
