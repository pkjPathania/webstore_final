package com.local.domain.repo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.local.domain.Customer;
import com.local.domain.repo.CustomerRepositiry;

@Repository
public class CustomerRepositiryImpl implements CustomerRepositiry {

	private List<Customer> allCustomersList = new ArrayList<>();

	public CustomerRepositiryImpl() {
		Customer pankaj = new Customer();
		pankaj.setCustomerId("C1234");
		pankaj.setName("Pankaj Pathania");
		pankaj.setAddress("Mohali");
		pankaj.setNoOfOrders(3);

		Customer sveta = new Customer();
		sveta.setCustomerId("C1235");
		sveta.setName("Shveta Palial");
		sveta.setAddress("Mohali");
		sveta.setNoOfOrders(4);

		allCustomersList.add(pankaj);
		allCustomersList.add(sveta);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return allCustomersList;
	}

}
