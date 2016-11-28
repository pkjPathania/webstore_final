package com.local.domain.repo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.local.domain.Product;

public interface ProductRepositiry {
	List<Product> getAllProducts();

	Product getProductById(String ProductId);

	List<Product> getProductsByCategory(String category);

	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	List<Product> getProductsByBrand(String brand);

	void addProduct(Product product);
}
