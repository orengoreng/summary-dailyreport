package com.demo.feeder;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "feeder.rate=5000")
public class ApplicationTest {

	@SpyBean
	private Application application;

	@Test
	public void testScheduledTask() {
		await().atMost(10, TimeUnit.SECONDS)
				.untilAsserted(() -> verify(application, atLeast(1)).feeder());

	}

}
