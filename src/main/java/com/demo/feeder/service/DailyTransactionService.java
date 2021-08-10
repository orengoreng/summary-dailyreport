package com.demo.feeder.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.feeder.entity.DailyTransaction;
import com.demo.feeder.repository.DailyTransactionRepository;

@Service
public class DailyTransactionService {

	@Autowired
	private DailyTransactionRepository dailyTxnRepository;

	public void save(List<DailyTransaction> dailyTransactions) {
		dailyTxnRepository.saveAll(dailyTransactions);
	}

	public List<List<String>> getDailySummaryReport() {
		String[][] report = dailyTxnRepository.getDailySummaryReport();
		List<List<String>> summaryReport = Arrays.stream(report).map(Arrays::asList)
				.collect(Collectors.toList());
		return summaryReport;
	}

}
