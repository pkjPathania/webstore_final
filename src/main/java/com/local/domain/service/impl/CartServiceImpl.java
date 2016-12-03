package com.local.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.domain.Cart;
import com.local.domain.repo.CartRepositiry;
import com.local.domain.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	protected CartRepositiry cartRepositiry;

	@Override
	public Cart create(Cart cart) {
		return cartRepositiry.create(cart);
	}

	@Override
	public Cart read(String cartId) {
		return cartRepositiry.read(cartId);
	}

	@Override
	public void update(String cartId, Cart cart) {
		cartRepositiry.update(cartId, cart);
	}

	@Override
	public void delete(String cartId) {
		cartRepositiry.delete(cartId);
	}

}
