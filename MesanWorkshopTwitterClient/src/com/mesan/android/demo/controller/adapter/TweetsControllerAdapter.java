package com.mesan.android.demo.controller.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mesan.android.demo.model.dto.TweetDTO;

public class TweetsControllerAdapter extends BaseAdapter{
	private ArrayList<TweetDTO> listOfTweetDTO;
	private Context context;
	
	public TweetsControllerAdapter(Context context, ArrayList<TweetDTO> listOfTweetDTO){
		this.context = context;
		this.listOfTweetDTO = listOfTweetDTO;		
	}

	public int getCount() {
		return listOfTweetDTO.size();
	}

	public Object getItem(int position) {
		return listOfTweetDTO.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int pos, View view, ViewGroup viewGroup) {
		return new TweetsControllerAdapterView(context, listOfTweetDTO.get(pos));
	}

}
