package com.raceto.k24.items;

public class ItemData {
	
	String id;
	String nama;
	String asal;
	String join;
	
	public ItemData(String[] s)
	{
		this(s[0], s[1], s[2], s[3]);
	}

	public ItemData(String id, String nama, String asal,
			String join) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nama = nama;
		this.asal = asal;
		this.join = join;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAsal() {
		return asal;
	}

	public void setAsal(String asal) {
		this.asal = asal;
	}

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}
}
