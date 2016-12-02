package com.local.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	protected String cartId;
	protected Map<String, CartItem> cartItems;
	private BigDecimal grandTotal;

	public Cart() {
		cartItems = new HashMap<>();
		grandTotal = new BigDecimal(0);
	}

	public Cart(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public void addCartItem(CartItem item) {
		String productId = item.getProduct().getProductId();
		if (cartItems.containsKey(productId)) {
			CartItem existingCartItem = cartItems.get(productId);
			existingCartItem.setQuantity(item.getQuantity() + existingCartItem.getQuantity());
			cartItems.put(productId, existingCartItem);
		} else
			cartItems.put(productId, item);

		this.updateGrandTotal();
	}

	public void removeCartItem(CartItem cartItem) {
		this.cartItems.remove(cartItem.getProduct().getProductId());
	}

	public void updateGrandTotal() {
		grandTotal = new BigDecimal(0);
		for (CartItem item : cartItems.values()) {
			grandTotal.add(item.getTotalPrice());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + ((cartItems == null) ? 0 : cartItems.hashCode());
		result = prime * result + ((grandTotal == null) ? 0 : grandTotal.hashCode());
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
		Cart other = (Cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (cartItems == null) {
			if (other.cartItems != null)
				return false;
		} else if (!cartItems.equals(other.cartItems))
			return false;
		if (grandTotal == null) {
			if (other.grandTotal != null)
				return false;
		} else if (!grandTotal.equals(other.grandTotal))
			return false;
		return true;
	}

}
