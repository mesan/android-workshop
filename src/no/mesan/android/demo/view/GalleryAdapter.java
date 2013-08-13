package no.mesan.android.demo.view;

import java.util.List;

import no.mesan.android.demo.R;
import no.mesan.android.demo.model.dto.FlickrDto;
import no.mesan.android.demo.model.service.Request;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GalleryAdapter extends BaseAdapter {
	private List<FlickrDto> flickrImageList;
	private LayoutInflater layoutInflater;

	public GalleryAdapter(Context context, List<FlickrDto> images) {
		flickrImageList = images;
		layoutInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return flickrImageList.size();
	}

	public Object getItem(int position) {
		return flickrImageList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View galleryItemView, final ViewGroup parent) {
		final ViewHolder holder;

		if (galleryItemView == null) {
			galleryItemView = layoutInflater.inflate(R.layout.layout_gallery_item, parent, false);

			holder = new ViewHolder();
			holder.imageViewFlickr = (ImageView) galleryItemView.findViewById(R.id.imageViewFlickr);
			holder.progressBarImage = (ProgressBar) galleryItemView.findViewById(R.id.progressBarImage);
			holder.textViewImageTitle = (TextView) galleryItemView.findViewById(R.id.textViewImageTitle);

			galleryItemView.setTag(holder);
		} else {
			holder = (ViewHolder) galleryItemView.getTag();
		}

		final FlickrDto flickrDto = flickrImageList.get(position);

		if (!flickrDto.hasImage() && !flickrDto.isLoading()) {
			flickrDto.setLoading(true);
			holder.imageViewFlickr.setImageDrawable(null);
			loadImage(flickrDto);	
			
		} else if (!flickrDto.hasImage() && flickrDto.isLoading()) {
			holder.progressBarImage.setVisibility(View.VISIBLE);
			holder.imageViewFlickr.setVisibility(View.GONE);
			
		} else {
			holder.imageViewFlickr.setImageDrawable(flickrDto.getImage());
			holder.progressBarImage.setVisibility(View.GONE);
			holder.imageViewFlickr.setVisibility(View.VISIBLE);
		}

		holder.textViewImageTitle.setText(flickrImageList.get(position).getTitle());

		return galleryItemView;
	}
		
	static class ViewHolder {
		ImageView imageViewFlickr;
		ProgressBar progressBarImage;
		TextView textViewImageTitle;
	}
	
	private void loadImage(final FlickrDto flickrDto) {
		new DownloadImageTask(flickrDto).execute(flickrDto.getImageUrl());
		
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Drawable> {

		private FlickrDto flickrDto;
		
		public DownloadImageTask(FlickrDto flickrDto) {
			this.flickrDto = flickrDto;
		}
		
		@Override
		protected Drawable doInBackground(String... params) {
			return new Request().getImageFromWeb(params[0]);
		}

		@Override
		protected void onPostExecute(Drawable result) {
			flickrDto.setImage(result);
			notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}
