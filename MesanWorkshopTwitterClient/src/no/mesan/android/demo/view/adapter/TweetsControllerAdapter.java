package no.mesan.android.demo.view.adapter;

import java.util.ArrayList;

import no.mesan.android.demo.model.dto.TweetDTO;
import no.mesan.android.demo.view.adapterview.TweetsControllerAdapterView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


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
		TweetsControllerAdapterView tcav;
		
		if(view == null){
			tcav = new TweetsControllerAdapterView(context);
			tcav.renderItem(true, listOfTweetDTO.get(pos), pos);
		}else{
			tcav = (TweetsControllerAdapterView) view;
			tcav.renderItem(false, listOfTweetDTO.get(pos), pos);
		}		
		return tcav;
	}

}