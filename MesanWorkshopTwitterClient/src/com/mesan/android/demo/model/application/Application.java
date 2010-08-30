package com.mesan.android.demo.model.application;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
	
	public static void hideKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}
