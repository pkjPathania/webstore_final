package com.local.domain.comtrollers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.local.domain.Cart;
import com.local.domain.CartItem;
import com.local.domain.Product;
import com.local.domain.service.CartService;
import com.local.domain.service.ProductService;

@Controller
@RequestMapping("/cart/rest")
public class CartRestController {

	@Autowired
	protected CartService cartService;

	@Autowired
	protected ProductService productService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Cart create(@RequestBody Cart cart) {
		return cartService.create(cart);

	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
		return cartService.read(cartId);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value = "cartId") String cartId, @RequestBody Cart cart) {
		cartService.update(cartId, cart);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("cartId") String cartId) {
		cartService.delete(cartId);
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addItem(String productId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();

		Cart cart = cartService.read(sessionId);

		if (cart == null)
			cart = cartService.create(cart);

		Product product = productService.getProductById(productId);
		if (product == null)
			throw new IllegalArgumentException("Product does not exist");

		cart.addCartItem(new CartItem(product));

		cartService.update(sessionId, cart);
	}

	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable("productId") String productId, HttpServletRequest request) {

		String sessionId = request.getSession(true).getId();

		Cart cart = cartService.read(sessionId);
		if (cart == null) {
			cart = cartService.create(cart);
		}

		Product product = productService.getProductById(sessionId);
		if (product == null)
			throw new IllegalArgumentException(new IllegalArgumentException("NOT FOUND"));

		cart.removeCartItem(new CartItem(product));

		cartService.update(sessionId, cart);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "illegal request, Please try again")
	public void handleClientErrors(Exception ex) {
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Please verify your payload")
	public void handleServerErrors(Exception ex) {

	}
}
