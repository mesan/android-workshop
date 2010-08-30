package com.mesan.android.demo.controller.adapter;

import java.io.IOException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mesan.android.demo.controller.R;
import com.mesan.android.demo.model.dto.TweetDTO;

public class TweetsControllerAdapterView extends RelativeLayout{
	
	private RelativeLayout listItemLayout;
	private TextView txtProfileName, txtTweetText, txtTweetDate;
	private ImageView imgProfileImage;
	
	
	private TweetDTO tweetDTO;
	private Context context;

	public TweetsControllerAdapterView(Context context, TweetDTO tweetDTO) {
		super(context);
		this.context = context;
		this.tweetDTO = tweetDTO;
		initLayout();
		renderItem();
	}

	public void initLayout(){
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		listItemLayout = (RelativeLayout) layoutInflater.inflate(R.layout.tweets_controller_list_item, this);
		txtProfileName = (TextView) listItemLayout.findViewById(R.id.txtProfileName);
		txtTweetText = (TextView) listItemLayout.findViewById(R.id.txtTweetText);
		txtTweetDate = (TextView) listItemLayout.findViewById(R.id.txtTweetDate);
	}
	public void renderItem(){
		txtProfileName.setText(tweetDTO.getProfileName());
		txtTweetText.setText(tweetDTO.getText());
	/*	Bitmap bm;
		try {
			bm = BitmapFactory.decodeStream(tweetDTO.getProfileUrl().openConnection().getInputStream());
			imgProfileImage.setImageBitmap(bm);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		//txtTweetDate.setText(tweetDate);
	}
}
