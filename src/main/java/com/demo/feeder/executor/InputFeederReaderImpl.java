package com.demo.feeder.executor;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.demo.feeder.entity.DailyTransaction;
import com.demo.feeder.extract.util.ConvertLineToObject;
import com.demo.feeder.extract.util.FileExtractor;
import com.demo.feeder.service.DailyTransactionService;

/**
 * Task reading feeder input files, save to DB and move process folder
 *
 */
@Component
public class InputFeederReaderImpl implements InputFeederReader {

	@Autowired
	private DailyTransactionService dailyTxnService;

	@Value("${feeder.input.folder}")
	private String inputFolder;

	@Value("${feeder.process.folder}")
	private String processedFolder;

	@Override
	public void execute() throws IOException {
		List<DailyTransaction> reports = ConvertLineToObject
				.convertLinesToObject(FileExtractor.extractAllFiles(inputFolder));
		dailyTxnService.save(reports);
		FileExtractor.moveFiles(inputFolder, processedFolder);
	}

}
