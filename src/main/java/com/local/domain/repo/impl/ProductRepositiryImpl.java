package com.local.domain.repo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.local.domain.Product;
import com.local.domain.repo.ProductRepositiry;

@Repository
public class ProductRepositiryImpl implements ProductRepositiry {
	private List<Product> listOfProducts = new ArrayList<Product>();

	public ProductRepositiryImpl() {

		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
		iphone.setCategory("Smart Phone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
		laptop_dell.setDescription("Dell Inspiron 14-inch Laptop (Black) with 3rd Generation Intel Core processors");
		laptop_dell.setCategory("Laptop");
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(1000);
		Product tablet_Nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
		tablet_Nexus.setDescription(
				"Google Nexus 7 is the lightest 7 inch	tablet With a quad-core Qualcomm Snapdragon� S4 Pro processor");
		tablet_Nexus.setCategory("Tablet");

		tablet_Nexus.setManufacturer("Google");
		tablet_Nexus.setUnitsInStock(1000);
		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet_Nexus);

	}

	public List<Product> getAllProducts() {
		return listOfProducts;
	}

	public Product getProductById(String ProductId) {
		return listOfProducts.stream().filter(product -> product.getProductId().equalsIgnoreCase(ProductId)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No product found with id " + ProductId));
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return listOfProducts.stream().filter(product -> product.getCategory().equalsIgnoreCase(category))
				.collect(Collectors.toList());
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		return listOfProducts.stream().filter(product -> product.getManufacturer().equalsIgnoreCase(brand))
				.collect(Collectors.toList());
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		Set<String> criteria = filterParams.keySet();
		Set<Product> productsByCategory = new HashSet<>();
		Set<Product> productByBrand = new HashSet<>();
		if (criteria.contains("category")) {
			for (String category : filterParams.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}

		if (criteria.contains("brand")) {
			for (String brandName : filterParams.get("brand")) {
				productByBrand.addAll(this.getProductsByBrand(brandName));
			}
		}
		productByBrand.addAll(productsByCategory);
		return productByBrand;
	}

	@Override
	public void addProduct(Product product) {
		listOfProducts.add(product);
	}

}
