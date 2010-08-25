package com.mesan.android.demo.model.application;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class Application {

	public synchronized static HttpResponse sendGetRequestForUrl(String url){
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
}
