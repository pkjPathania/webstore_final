package com.local.domain.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.local.domain.Product;

public interface ProductService {
	List<Product> getAllProducts();

	Product getProductById(String ProductId);

	List<Product> getProductsByCategory(String category);

	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	void addProduct(Product product);

}
