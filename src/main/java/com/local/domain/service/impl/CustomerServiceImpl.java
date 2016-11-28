package com.local.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.domain.Customer;
import com.local.domain.repo.CustomerRepositiry;
import com.local.domain.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepositiry customerRepositiry;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepositiry.getAllCustomers();
	}

}
