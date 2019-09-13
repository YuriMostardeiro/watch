package com.nt.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {
	public String cnpj;
	public String name;
	public String businessArea;
	public List<com.nt.domain.Customer> customers = new ArrayList<com.nt.domain.Customer>();

	public Customer(String cnpj, String name, String businessarea) {
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessarea;
	}

	public Customer() {
		
	}

	public void getCustomersFromList(List<String> listFormated) {
		for (String customer : listFormated) {
			if (customer.startsWith("002;")) {
				String aux[] = customer.split(";");
				customers.add(new Customer(aux[1], aux[2], aux[3]));
			}
		}
	}

	public int amountOfClients() {
		return customers.size();
	}

	public List<Customer> getCustomers() {
		return customers;
	}
}