package com.demo.feeder.extract.util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.feeder.entity.DailyTransaction;

/**
 * Utility class to convert transaction lines to DailyTransaction obj
 *
 */
public class ConvertLineToObject {

	public static List<DailyTransaction> convertLinesToObject(List<String> lines) {
		return lines.parallelStream().map(u -> substringLine(u)).filter(t -> t != null)
				.collect(Collectors.toList());
	}

	private static DailyTransaction substringLine(String line) {
		if (line.length() == 176) {
			String clientType = line.substring(3, 7);
			String clientNo = line.substring(7, 11);
			String accountNo = line.substring(11, 15);
			String subAccountNo = line.substring(15, 19);
			String exchangeCode = line.substring(27, 31);
			String productGrpCode = line.substring(25, 27);
			String symbol = line.substring(31, 37);
			String expiration = line.substring(37, 45);
			Long qtyLong = Long.valueOf(line.substring(52, 62));
			Long qtyShort = Long.valueOf(line.substring(63, 73));
			DailyTransaction summaryReport = new DailyTransaction(null, clientType,
					clientNo, accountNo, subAccountNo, exchangeCode, productGrpCode,
					symbol, expiration, qtyLong, qtyShort, LocalDate.now());
			return summaryReport;
		}
		return null;
	}

}
