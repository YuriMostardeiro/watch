package com.nt.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Sale {
	public String saleId;
	public double salePrice;
	public String salesman;
	public List<Sale> sales = new ArrayList<Sale>();

	public Sale(String saleid, double saleprice, String saleman) {
		this.saleId = saleid;
		this.salePrice = saleprice;
		this.salesman = saleman;
	}

	public Sale() {
		
	}

	public void getSalesFromList(List<String> listFormated) {
		for (String sale : listFormated) {
			if (sale.startsWith("003;")) {
				String aux[] = sale.split(";");
				sales.add(new Sale(aux[1], getSalesPrice(aux[2]), aux[3]));
			}
		}
	}

	public int amoutOfSales() {
		return sales.size();
	}

	public List<Sale> getSales() {
		return sales;
	}

	public double getSalesPrice(String sale) {
		double price = 0;
		String splitincoma[] = sale.split(",");
		for (int i = 0; i < splitincoma.length; i++) {
			String splitinindent[] = splitincoma[i].split("-");
			if (splitinindent.length > 1){
				price = price + Double.parseDouble(splitinindent[1].replace("[", ""))
						* Double.parseDouble(splitinindent[2].replace("]", ""));
			}
		}
		return price;
	}

	public String getIdOfTheMostExpensiveSale() {
		sales.sort(Comparator.comparing(Sale::getSalePrice).reversed());

		if (sales.stream().count() > 0){
			return sales.get(0).getSaleId();
		}
		else{
			return "no sales";
		}
	}

	public String getWorstSalesmanEver() {
		sales.sort(Comparator.comparing(Sale::getSalePrice));

		if (sales.stream().count() > 0){
			return sales.get(0).getSalesman();
		}
		else{
			return "no salesman";
		}
	}
}