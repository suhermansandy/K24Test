package com.raceto.k24.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.raceto.k24.items.ItemData;

public class Parser {
	
	public static List<ItemData> parse(String data) 
	{
		List<ItemData> iData = new ArrayList<ItemData>();;
		
		try {
			
			JSONArray ja = new JSONArray(data);
			
			for (int i = 0; i < ja.length(); i++) 
			{
				JSONObject jobj = ja.getJSONObject(i);
				
				String id = jobj.getString("ID");
				String nama = jobj.getString("nama");
				String asal = jobj.getString("asal");
				String join = jobj.getString("join");
				
				ItemData iD = new ItemData(id, nama, asal, join);
				
				iData.add(iD);
			}
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return iData;
	}
}
