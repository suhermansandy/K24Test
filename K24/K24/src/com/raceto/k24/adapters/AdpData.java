package com.raceto.k24.adapters;

import java.util.List;

import com.raceto.k24.R;
import com.raceto.k24.items.ItemData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdpData extends ArrayAdapter<ItemData>{

	public AdpData(Context context, int resource,
			List<ItemData> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout v = (LinearLayout)convertView;
		
		if(v == null)
		{
			LayoutInflater inflater = LayoutInflater.from(getContext());
			v = (LinearLayout)inflater.inflate(R.layout.list , null);
		}
		
		ItemData id = getItem(position);
		
		if(v != null)
		{
			TextView txtId = (TextView)v.findViewById(R.id.txtId);
			TextView txtNama = (TextView)v.findViewById(R.id.txtNama);
			TextView txtAsal = (TextView)v.findViewById(R.id.txtAsal);
			TextView txtJoin = (TextView)v.findViewById(R.id.txtJoin);
			
			if(txtId != null)
				txtId.setText(id.getId());
			
			if(txtNama != null)
				txtNama.setText(id.getNama());
			
			if(txtAsal != null)
				txtAsal.setText(id.getAsal());
			
			if(txtJoin != null)
				txtJoin.setText(id.getJoin());
			
		}
		
		
		return v;
	}

}
