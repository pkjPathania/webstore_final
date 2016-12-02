package com.local.domain.repo;

import com.local.domain.Cart;

public interface CartRepositiry {
	Cart create(Cart cart);

	Cart read(String cartId);

	void update(String cartId, Cart cart);

	void delete(String cartId);
}
