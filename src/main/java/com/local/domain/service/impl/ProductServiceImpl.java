package com.local.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.domain.Product;
import com.local.domain.repo.ProductRepositiry;
import com.local.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepositiry productRepositiry;

	public List<Product> getAllProducts() {
		return productRepositiry.getAllProducts();
	}

	@Override
	public Product getProductById(String ProductId) {
		return productRepositiry.getProductById(ProductId);
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepositiry.getProductsByCategory(category);
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productRepositiry.getProductsByFilter(filterParams);
	}

	@Override
	public void addProduct(Product product) {
		productRepositiry.addProduct(product);

	}

}
