package kr.co.ensmart.frameworkdemo.common.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;

import kr.co.ensmart.frameworkdemo.common.dto.sample.Sample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest()
class RestApiTest {

	public static final String localApiServer = "http://localhost:8081";
	
	@Test
	void get() {
		RestResponse<Sample> response = RestApi.client(localApiServer+ "/api/samples/{id}")
				.uriVariable("id", "2")
				.get(Sample.class);
		
		assertEquals(false, response.hasError());
		assertEquals(false, response.hasResponseError());
		assertEquals(false, response.hasUnkownError());
		
		Sample result = response.getBody();
		
		log.debug("ID: {}", result.getId());
	}

	@Test
	void search() {
		RestResponse<List<Sample>> response = RestApi.client(localApiServer+ "/api/samples/search")
				.queryParam("description", "설명입니다.")
				.queryParam("name", "국화")
				.get(new ParameterizedTypeReference<List<Sample>>() {});
		
		assertEquals(false, response.hasError());
		assertEquals(false, response.hasResponseError());
		assertEquals(false, response.hasUnkownError());
		
		List<Sample> result = response.getBody();
		
		log.debug("result: {}", result);
	}

}
