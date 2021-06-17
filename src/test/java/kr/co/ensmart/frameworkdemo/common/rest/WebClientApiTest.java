package kr.co.ensmart.frameworkdemo.common.rest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import kr.co.ensmart.frameworkdemo.common.dto.sample.Sample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest()
class WebClientApiTest {
	@Autowired
	WebClient webClient;

	@Test
	void get() {

		int id = 2;
		Sample result = webClient.get().uri("http://localhost:8080/api/samples/{id}", id).retrieve().bodyToMono(Sample.class).block();

		assertNotNull(result);
		
		log.debug("ID: {}", result.getId());

	}

	@Test
	void getResponseEntity() {

		int id = 2;
		ResponseEntity<Sample> result = webClient.get().uri("http://localhost:8080/api/samples/{id}", id).retrieve().toEntity(Sample.class).block();

		assertNotNull(result);
		
		log.debug("ID: {}", result.getBody().getId());

	}

	@Test
	void getList() {
		List<Sample> result = webClient.get().uri("http://localhost:8080/api/samples").retrieve().bodyToFlux(Sample.class).collectList().block();

		assertNotNull(result);
		assertTrue(result.size() > 0);
		
		log.debug("ID: {}", result);
	}

}
