package com.raceto.k24.data;

import org.apache.http.message.BasicNameValuePair;

import com.raceto.k24.interfaces.ICallBack;

import android.os.AsyncTask;

public class DataRead {
	
	public static void read(final ICallBack resp)
	{
		AsyncTask<Void, Void, String> t = new AsyncTask<Void, Void, String>()
		{
			@Override
			protected String doInBackground(Void... params) 
			{
				WebClient client = new WebClient("http://test.k24.co.id/api.php");
				client.addParam(new BasicNameValuePair("id", "rc2017"));
				client.addParam(new BasicNameValuePair("name", "sandy"));
				
				return client.getResponse();
			}
			
			@Override
			protected void onPostExecute(String result) 
			{
				resp.onResponseReceived(result);
			}
		};
		
		t.execute();
	}

}
