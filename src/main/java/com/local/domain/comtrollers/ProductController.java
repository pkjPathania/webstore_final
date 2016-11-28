package com.local.domain.comtrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.local.domain.Product;
import com.local.domain.service.ProductService;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/all")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping(value = "/{category}")
	public String getProductByCatetory(@PathVariable(value = "category") String category, Model model) {
		model.addAttribute("products", productService.getProductsByCategory(category));
		return "products";
	}

	@RequestMapping("/filter/{byCriteria}")
	public String getProductByFilter(@MatrixVariable(pathVar = "byCriteria") Map<String, List<String>> filterParams,
			Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}

	@RequestMapping(value = "/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct_GET(@ModelAttribute("newProduct") Product newProduct) {
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct_POST(@ModelAttribute(name = "newProduct") Product product, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attemption to bind disallowed fields");
		}
		productService.addProduct(product);
		return "redirect:/products/all";
	}

	@InitBinder
	public void initilizeBinder(WebDataBinder binder) {
		binder.setDisallowedFields("unitsInOrder", "discounted");
	}

}
