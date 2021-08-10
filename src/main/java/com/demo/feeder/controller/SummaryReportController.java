package com.demo.feeder.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.feeder.service.SummaryReportService;

/**
 * Controller REST point to generate daily summary report
 *
 */
@RestController
@RequestMapping("/demo")
public class SummaryReportController {

	@Autowired
	private SummaryReportService reportService;

	@RequestMapping(value = "/summaryreport", produces = "text/csv", method = RequestMethod.GET)
	public ResponseEntity generateReport() {
		try {
			File file = reportService.generateReport();
			return ResponseEntity.ok()
					.header("Content-Disposition",
							"attachment; filename=" + file.getName())
					.contentLength(file.length())
					.contentType(MediaType.parseMediaType("text/csv"))
					.body(new FileSystemResource(file));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Unable to generate report: " + "", ex);
		}
	}

}
