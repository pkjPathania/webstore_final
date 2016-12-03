package com.local.domain.repo.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.local.domain.Cart;
import com.local.domain.repo.CartRepositiry;

@Repository
public class CartRepositiryImpl implements CartRepositiry {

	Map<String, Cart> listOfCarts;

	public CartRepositiryImpl() {
		listOfCarts = new HashMap<>();
	}

	@Override
	public Cart create(Cart cart) {
		if (listOfCarts.containsKey(cart.getCartId()))
			throw new IllegalArgumentException(String.format("Cart with id : (%s) already exists", cart.getCartId()));

		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	@Override
	public Cart read(String cartId) {
		return listOfCarts.get(cartId);
	}

	@Override
	public void update(String cartId, Cart cart) {
		if (!listOfCarts.containsKey(cartId))
			throw new IllegalArgumentException(String.format("you cannot update this as no product found in cart"));
		listOfCarts.put(cartId, cart);
	}

	@Override
	public void delete(String cartId) {
		if (!listOfCarts.containsKey(cartId))
			throw new IllegalArgumentException(
					String.format("Cart with id : (%s) does not exists. Please create one ", cartId));

		listOfCarts.remove(cartId);
	}

}
