package com.demo.feeder.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

import lombok.extern.slf4j.Slf4j;

/**
 * Service to generate csv report
 */
@Slf4j
@Service
public class SummaryReportService {

	@Autowired
	private DailyTransactionService dailyTxnService;

	@Value("${feeder.report.folder}")
	private String reportFolder;

	public File generateReport() throws IOException {

		List<String[]> csvData = createCsvDataSpecial(
				dailyTxnService.getDailySummaryReport());
		String reportName = String.format("%s-%s", "summaryreport",
				LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		String fileName = reportFolder + "/" + reportName + ".csv";
		try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
			writer.writeAll(csvData);
		}
		log.info("Report generated: {} ", fileName);
		return new File(fileName);
	}

	private List<String[]> createCsvDataSpecial(List<List<String>> data) {

		String[] header = { "Client Type", "Client Number", "Account No",
				"Account SubNumber", "Exchange Code", "Product GroupCode", "Symbol",
				"Expiration Date", "Transaction Amount" };

		List<String[]> list = new ArrayList<>();
		list.add(header);
		if (CollectionUtils.isNotEmpty(data)) {
			data.forEach(u -> list.add((String[]) u.toArray()));
		}
		return list;
	}

}
