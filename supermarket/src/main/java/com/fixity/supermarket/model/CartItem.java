package com.fixity.supermarket.model;

public class CartItem extends Item{

	int quantity;
 
	public CartItem()
	{}
	public CartItem(Item it) {
		// TODO Auto-generated constructor stub
		this.itemId=it.getItemId();
		this.name=it.getName();
		this.price=it.getPrice();
	    setFile(it.getFile());
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
