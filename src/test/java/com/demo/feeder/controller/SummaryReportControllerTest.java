package com.demo.feeder.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.feeder.controller.SummaryReportController;
import com.demo.feeder.executor.InputFeederReader;
import com.demo.feeder.service.SummaryReportService;

@RunWith(SpringRunner.class)
@WebMvcTest({ SummaryReportController.class })
public class SummaryReportControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SummaryReportService reportService;

	@MockBean
	private InputFeederReader feederReader;

	@Test
	public void testGenerateReport() throws Exception {
		// init data
		when(reportService.generateReport()).thenReturn(new File("test"));
		mockMvc.perform(MockMvcRequestBuilders.get("/demo/summaryreport"))
				.andExpect(status().isOk()).andExpect(content().contentType("text/csv"));
	}

}
