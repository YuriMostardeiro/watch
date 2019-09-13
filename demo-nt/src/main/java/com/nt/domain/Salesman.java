package com.nt.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Salesman {
	public String cpf;
	public String name;
	public double salary;
	public List<Salesman> salesmans = new ArrayList<Salesman>();
    
	public Salesman(String cpf, String name, double salary) {
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	public Salesman() {
		
	}
	
	public void getSalesmansFromList(List<String> listFormated) {
		for (String salesman : listFormated) {
			if (salesman.startsWith("001;")) {
				String aux[] = salesman.split(";");
				salesmans.add(new Salesman(aux[1], aux[2], Double.parseDouble(aux[3])));
			}
		}
	}

	public int amountOfSalesman() {
		return salesmans.size();
	}

	public List<Salesman> getSalesmans() {
		return salesmans;
	}
}