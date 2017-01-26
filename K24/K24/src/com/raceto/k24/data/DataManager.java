package com.raceto.k24.data;
import com.raceto.k24.interfaces.ICallBack;
import com.raceto.k24.interfaces.IReadDataComplited;
import android.app.ProgressDialog;
import android.content.Context;

public class DataManager {
	
	public static void Read(final Context context, final IReadDataComplited comp)
	{
		
		final ProgressDialog dialog = new ProgressDialog(context);
		dialog.setMessage("Reading data...");
		dialog.show();
		
		DataRead.read(new ICallBack() {
			
			@Override
			public void onResponseReceived(String response) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
				if (comp == null)
					return;
				
				if (isResponseSuccess(response))
				{
					comp.onReadDataSuccess(response);
				}
				else
				{
					comp.onReadDataFailed(response);
				}
			}
		});
	}
	
	private static boolean isResponseSuccess(String response)
	{
		return getResponseStatus(response) == ResponseConst.RESPONSE_SUCCESS;
	}
	
	private static int getResponseStatus(String response)
	{
		String status = response.substring(0, 1);
		
		if (status.equals("1"))
			return ResponseConst.RESPONSE_SUCCESS;
		else if (status.equals("2"))
			return ResponseConst.RESPONSE_QUERY_FAILED;
		else if (status.equals("3"))
			return ResponseConst.RESPONSE_CONNECTION_FAILED;
		else 
			return ResponseConst.RESPONSE_SUCCESS;
	}

}
