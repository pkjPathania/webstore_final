package com.local.domain;

import java.math.BigDecimal;

public class CartItem {

	protected Product product;
	protected int quantity;
	protected BigDecimal totalPrice;

	public CartItem() {
	}

	public CartItem(Product product) {
		this.product = product;
		this.quantity = 1;
		this.totalPrice = product.getUnitPrice();
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.updateGrandTotal();
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateGrandTotal();
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
		this.updateGrandTotal();
	}

	public void updateGrandTotal() {
		this.totalPrice = this.product.getUnitPrice().multiply(new BigDecimal(this.quantity));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}

}
