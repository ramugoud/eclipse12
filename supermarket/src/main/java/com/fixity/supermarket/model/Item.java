package com.fixity.supermarket.model;

import java.util.Base64;

public class Item {
	
	String itemId;
	String name;
	double price;
	byte[] file;
	String base64Image;
	//Item nextNode;
	
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
		setBase64Image(Base64.getEncoder().encodeToString(file));
	}
	
	
	public Item() {
		
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemNo) {
		this.itemId = itemNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
