package no.mesan.android.demo.model.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import no.mesan.android.demo.model.application.Application;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class Request {

	public HttpResponse sendGetRequestForUrl(String url) {
		
		url = url.replaceAll(" ", "+");
		
		// Execute the request
		HttpResponse response = null;
		HttpGet httpget = null;

		// Prepare a request object
		httpget = new HttpGet(url);

		DefaultHttpClient client = new DefaultHttpClient();

		try {

			response = client.execute(httpget);

		} catch (ClientProtocolException cpex) {
			Log.e(Application.class.getSimpleName(), "", cpex);
		} catch (IOException ioex) {
			Log.e(Application.class.getSimpleName(), "", ioex);
		}
		return response;
	}
	
	public Drawable getImageFromWeb(String pathToImage){
		InputStream is = null;
		try {
			URL url = new URL(pathToImage);
			is = new BufferedInputStream(url.openStream());
			return Drawable.createFromStream(is, "src");

		} catch (MalformedURLException e) {
			Log.d(Application.class.getSimpleName(), e.getMessage(), e);
		} catch (IOException e) {
			Log.d(Application.class.getSimpleName(), e.getMessage(), e);
		} finally{
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					Log.d(Application.class.getSimpleName(), e.getMessage(), e);
				}
			}
		}
		return null;
	}
}
