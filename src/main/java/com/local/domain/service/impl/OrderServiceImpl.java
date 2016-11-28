package com.local.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.domain.Product;
import com.local.domain.repo.ProductRepositiry;
import com.local.domain.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductRepositiry productRepositiry;

	@Override
	public void processOrder(String productId, Long quantity) {
		Product productById = productRepositiry.getProductById(productId);

		if (productById.getUnitsInStock() < quantity) {
			throw new IllegalArgumentException("Out of Stock " + productId);
		}

		productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
	}

}
