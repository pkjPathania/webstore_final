package com.local.domain.repo.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.local.domain.Cart;
import com.local.domain.repo.CartRepositiry;

@Repository
public class CartRepositiryImpl implements CartRepositiry {

	private Map<String, Cart> listOfCarts;

	public CartRepositiryImpl() {
		listOfCarts = new HashMap<>();
	}

	@Override
	public Cart create(Cart cart) {
		if (listOfCarts.keySet().contains(cart.getCartId()))
			throw new IllegalArgumentException(
					String.format("Cart id (%s) alredy exists, and therefore cannot be created", cart.getCartId()));
		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	@Override
	public Cart read(String cartId) {
		if (!listOfCarts.keySet().contains(cartId))
			throw new IllegalArgumentException(String.format("No cart exists with id : %s", cartId));
		return listOfCarts.get(cartId);
	}

	@Override
	public void update(String cartId, Cart cart) {
		if (!cart.getCartItems().containsKey(cartId))
			throw new IllegalStateException(String.format("No cart with id : (%s) exists", cartId));
		listOfCarts.put(cartId, cart);
	}

	@Override
	public void delete(String cartId) {
		if (!listOfCarts.keySet().contains(cartId))
			throw new IllegalArgumentException(String.format("Cart is already empty!"));
		listOfCarts.remove(cartId);
	}

}
