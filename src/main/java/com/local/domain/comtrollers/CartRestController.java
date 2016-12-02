package com.local.domain.comtrollers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.local.domain.Cart;
import com.local.domain.CartItem;
import com.local.domain.Product;
import com.local.domain.service.CartService;
import com.local.domain.service.ProductService;
import com.local.exception.ProductNotFoundException;

@RestController
@RequestMapping(value = "rest/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Cart create(Cart cart) {
		return cartService.create(cart);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
		return cartService.read(cartId);
	}

	@RequestMapping(value = "/{cartId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable("cartId") String cartId, @RequestBody Cart cart) {
		cartService.update(cartId, cart);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(String cartId) {
		cartService.delete(cartId);
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable("productId") String productId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);

		if (cart == null) {
			cart = cartService.create(cart);
		}

		Product product = productService.getProductById(productId);
		if (product == null) {
			throw new IllegalArgumentException(String.format("NOT FOUND PRODUCT"));
		}

		cart.addCartItem(new CartItem(product));
		cartService.update(sessionId, cart);
	}

	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable(value = "productId") String productId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if (cart == null)
			cart = cartService.create(new Cart(sessionId));
		Product product = productService.getProductById(sessionId);
		if (product == null)
			throw new ProductNotFoundException(productId);
		cart.removeCartItem(new CartItem(product));
		cartService.update(sessionId, cart);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Illegal Request, please verify your payload")
	public void handleClientErrors(Exception ex) {

	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
	public void handleServerErrors(Exception ex) {

	}
}
