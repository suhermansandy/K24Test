package com.raceto.k24.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class WebClient {
	
	private String url;
	private List<BasicNameValuePair> params;
	
	int connectionTimeout = 3000;
	
	public WebClient(String url)
	{
		this.url = url;
		this.params = new ArrayList<BasicNameValuePair>();
	}
	
	public void addParam(BasicNameValuePair param)
	{
		this.params.add(param);
	}
	
	public void setConnectionTimeout(int timeout)
	{
		this.connectionTimeout = timeout;
	}
	
	public String getResponse()
	{
		String res = null;
		String status= null;
		
		try 
			{
				//jika ping gagal
				if (!pingHost(this.url, 80, 5000))
				{
					return "3Host tidak ditemukan";
				}
			
				HttpParams httpParam = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParam, this.connectionTimeout);
				HttpConnectionParams.setSoTimeout(httpParam, this.connectionTimeout + 2000);
			
				HttpClient httpClient = new DefaultHttpClient(httpParam);
				HttpPost post = new HttpPost(this.url);
				
				post.setEntity(new UrlEncodedFormEntity(this.params));
				
				HttpResponse response = httpClient.execute(post);
				InputStream is = response.getEntity().getContent();
				
				BufferedReader bis = new BufferedReader(new InputStreamReader(is), 8);
				String line = bis.readLine();
				
				StringBuilder sb = new StringBuilder();
				
				while (line != null)
				{
					sb.append(line);
					
					line = bis.readLine();
					
					if (line != null)
						sb.append("\n");
				}
				
				is.close();
				
				res = sb.toString();
			} 
			//nilai masih null
			catch (NullPointerException e)
			{
				res = e.getMessage();
				status = "3";
				
				e.printStackTrace();
			}
			catch (ClientProtocolException e) 
			{
				res = e.getMessage();
				status = "3";
				
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				res = e.getMessage();
				status = "3";
				
				e.printStackTrace();
			}
		
		if (status == null)
			return res;
		else
			return status + res;
	}
	
	//tes ping
	public static boolean pingHost(String fullUrl, int port, int timeout) 
	{
	    try
	    {
	    	URL url = new URL(fullUrl);
	    	String host = url.getHost();
	    	
	    	@SuppressWarnings("resource")
			Socket socket = new Socket();
	        socket.connect(new InetSocketAddress(host, port), timeout);
	        return true;
	    } 
	    catch (IOException e) 
	    {
	        return false; // Either timeout or unreachable or failed DNS lookup.
	    }
	}

}
