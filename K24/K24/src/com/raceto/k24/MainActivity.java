package com.raceto.k24;

import java.util.ArrayList;
import java.util.List;
import com.raceto.k24.adapters.AdpData;
import com.raceto.k24.data.DataManager;
import com.raceto.k24.data.Parser;
import com.raceto.k24.interfaces.IReadDataComplited;
import com.raceto.k24.items.ItemData;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ListView lsv;
	List<ItemData> lItem;
	AdpData adpData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lsv = (ListView)findViewById(R.id.lsv);
        
        lItem = new ArrayList<ItemData>();
        
        adpData = new AdpData(this, R.layout.list, lItem);
        
        lsv.setAdapter(adpData);
        
        ambilData();
    }
    
    public void ambilData()
    {
    	DataManager.Read(this, new IReadDataComplited() {
			
			@Override
			public void onReadDataSuccess(String result) {
				// TODO Auto-generated method stub
				lItem.clear();
				
				lItem.addAll(Parser.parse(result));
				
				adpData.notifyDataSetChanged();
			}
			
			@Override
			public void onReadDataFailed(String message) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
			}
		});
    }
}
