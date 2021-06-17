package kr.co.ensmart.frameworkdemo.common.rest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import kr.co.ensmart.frameworkdemo.common.dto.sample.Sample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest()
class RestClientPerfTest {
	public static final String localApiServer = "http://localhost:8080";
	
	@Autowired
	WebClient webClient;
	
	@Test
	void get() {
		int loopCount = 100;
		int id = 2;
		LocalDateTime startDtime;
		LocalDateTime endDtime;
		
		startDtime = LocalDateTime.now();
		for (int i = 0; i < loopCount; i++) {
			RestResponse<Sample> response = RestApi.client(localApiServer+ "/api/samples/{id}")
					.uriVariable("id", id)
					.get(Sample.class);
			Sample result = response.getBody();
			if (i != 0 && (i % 10) == 0) {
				endDtime = LocalDateTime.now();
				log.info("(1)RestApi {} run time: {}", i, ChronoUnit.MILLIS.between(startDtime, endDtime));
				startDtime = LocalDateTime.now();
			}
		}
		
		for (int i = 0; i < loopCount; i++) {
			Sample result = webClient.get().uri("http://localhost:8080/api/samples/{id}", id).retrieve().bodyToMono(Sample.class).block();
			if (i != 0 && (i % 10) == 0) {
				endDtime = LocalDateTime.now();
				log.info("(2)webClient {} run time: {}", i, ChronoUnit.MILLIS.between(startDtime, endDtime));
				startDtime = LocalDateTime.now();
			}
		}
		
		startDtime = LocalDateTime.now();
		for (int i = 0; i < loopCount; i++) {
			RestResponse<Sample> response = RestApi.client(localApiServer+ "/api/samples/{id}")
					.uriVariable("id", id)
					.get(Sample.class);
			Sample result = response.getBody();
			if (i != 0 && (i % 10) == 0) {
				endDtime = LocalDateTime.now();
				log.info("(3)RestApi {} run time: {}", i, ChronoUnit.MILLIS.between(startDtime, endDtime));
				startDtime = LocalDateTime.now();
			}
		}
		
		for (int i = 0; i < loopCount; i++) {
			Sample result = webClient.get().uri("http://localhost:8080/api/samples/{id}", id).retrieve().bodyToMono(Sample.class).block();
			if (i != 0 && (i % 10) == 0) {
				endDtime = LocalDateTime.now();
				log.info("(4)webClient {} run time: {}", i, ChronoUnit.MILLIS.between(startDtime, endDtime));
				startDtime = LocalDateTime.now();
			}
		}
		
	}

}
