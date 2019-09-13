package com.nt;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.List;

import com.nt.domain.Customer;
import com.nt.domain.Sale;
import com.nt.domain.Salesman;
import com.nt.service.FileService;
import com.nt.util.FileUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class FileServiceTest {

	private Sale sale = new Sale();
	private Salesman salesman = new Salesman();
	private Customer customer = new Customer();
    
    private String sampleStr1 = "001ç1234567891234çPedroç50000";
	private String sampleStr2 = "002ç2345675434544345çJoseçRural";
	private String sampleStr3 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";	
	
 
	@Before
	public void setup() {
		sale = new Sale();
	}

	@Test
	public void salesmanTest() {		
		
		List<String> fileSimulation = new ArrayList<String>();
		fileSimulation.add(FileUtil.replaceAscIIDelimiter(sampleStr1));

		salesman.getSalesmansFromList(fileSimulation);
		
		assertEquals(salesman.salesmans.get(0).getCpf() , "1234567891234");
		assertEquals(salesman.salesmans.get(0).getName() , "Pedro");
		assertEquals(salesman.salesmans.get(0).getSalary(), 50000, 0);        
	}

	@Test
	public void customerTest() {		
		List<String> fileSimulation = new ArrayList<String>();
		fileSimulation.add(FileUtil.replaceAscIIDelimiter(sampleStr2));

		customer.getCustomersFromList(fileSimulation);
		
		assertEquals(customer.customers.get(0).getCnpj() , "2345675434544345");
		assertEquals(customer.customers.get(0).getName() , "Jose");
		assertEquals(customer.customers.get(0).getBusinessArea(), "Rural");        
	}

	@Test
	public void salesTest() {		
		
		List<String> fileSimulation = new ArrayList<String>();
		fileSimulation.add(FileUtil.replaceAscIIDelimiter(sampleStr3));

		sale.getSalesFromList(fileSimulation);
		
		assertEquals(sale.sales.get(0).getSaleId() , "10");		
		assertEquals(sale.sales.get(0).getSalesman(), "Diego");        
	}
}
