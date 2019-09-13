package com.nt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nt.domain.Customer;
import com.nt.domain.Sale;
import com.nt.domain.Salesman;
import com.nt.util.FileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileService {

	private final Logger logger = LoggerFactory.getLogger(FileService.class);
	private static final String folderIn = System.getProperty("user.home") + "\\data\\in\\";
	private static final String folderOut = System.getProperty("user.home") + "\\data\\out\\";	

	public void watchFile() {
		try {
			List<String> listFormated = new ArrayList<String>();
			FileUtil.createDirectory(folderIn);
			this.logger.info("Watcher Started");
			WatchService watcher = FileSystems.getDefault().newWatchService();

			Path directory = Paths.get(folderIn);
			directory.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

			while (true) {
				WatchKey key = watcher.take();
				Optional<WatchEvent<?>> watchEvent = key.pollEvents().stream().findFirst();
				Path path = (Path) watchEvent.get().context();
				if (path.toString().endsWith(".txt")) {
					this.logger.info("ReadFile Started");


					List<String> listFileRow = new ArrayList<String>();
					BufferedReader buffered = new BufferedReader(new FileReader(folderIn + "\\" + path.toString()));
					String row = "";
					
					while ((row = buffered.readLine()) != null) {
						listFileRow.add(FileUtil.replaceAscIIDelimiter(row));
					}
					buffered.close();

					createFileWithResult(listFileRow);
					this.logger.info("OutputFile Updated");
				}
				boolean valid = key.reset();
				if (!valid) {
					this.logger.info("Watcher Stoped");
					break;
				}
			}
			watcher.close();
		} catch (IOException | InterruptedException e) {
			this.logger.error("Watcher error", e);
			e.printStackTrace();
		}
	}

	private void createFileWithResult(List<String> listFormated) {

		Customer customer = new Customer();
	    Sale sale = new Sale();
	    Salesman salesman = new Salesman();
		customer.getCustomersFromList(listFormated);
		sale.getSalesFromList(listFormated);
		salesman.getSalesmansFromList(listFormated);

		FileWriter arquivo;
		FileUtil.createDirectory(folderOut);
		try {
			arquivo = new FileWriter(new File(folderOut + "OutputFile.done.txt"));
			arquivo.write("Amount of clients in the input file: " + customer.amountOfClients() + "\r\n"
					    + "Amount of salesman in the input file: " + salesman.amountOfSalesman() + "\r\n"
					    + "Id of the most expensive sale: " + sale.getIdOfTheMostExpensiveSale() + "\r\n"
					    + "Worst salesman ever: " + sale.getWorstSalesmanEver());
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}