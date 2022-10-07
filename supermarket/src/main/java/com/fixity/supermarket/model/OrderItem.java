package com.fixity.supermarket.model;

public class OrderItem extends CartItem{
	
	
	int orderId;
	public OrderItem()
	{}
	public OrderItem(CartItem cart)
	{
		this.itemId=cart.getItemId();
		this.name=cart.getName();
		this.price=cart.getPrice();
		this.quantity=cart.getQuantity();	
		 setFile(cart.getFile());
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
}
