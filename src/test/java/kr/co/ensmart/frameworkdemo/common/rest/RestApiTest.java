package kr.co.ensmart.frameworkdemo.common.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

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

	@Test
	void register() {
		RestResponse<Map<String,String>> response = RestApi.client(localApiServer+ "/api/samples")
				.post(new Sample(4, "test4", "description4"), new ParameterizedTypeReference<Map<String,String>>() {});
		
		assertEquals(false, response.hasError());
		assertEquals(false, response.hasResponseError());
		assertEquals(false, response.hasUnkownError());
		
		Map<String,String> result = response.getBody();
		
		log.debug("result: {}", result);
	}

	@Test
	void save() {
		RestResponse<Map<String,String>> response = RestApi.client(localApiServer+ "/api/samples/{id}")
				.uriVariable("id", "2")
				.put(Sample.builder().name("test4").description("description4").build(), new ParameterizedTypeReference<Map<String,String>>() {});
		
		assertEquals(false, response.hasError());
		assertEquals(false, response.hasResponseError());
		assertEquals(false, response.hasUnkownError());
		
		Map<String,String> result = response.getBody();
		
		log.debug("result: {}", result);
	}

	@Test
	void modify() {
		RestResponse<Map<String,String>> response = RestApi.client(localApiServer+ "/api/samples/{id}")
				.uriVariable("id", "2")
				.put(Sample.builder().description("description4").build(), new ParameterizedTypeReference<Map<String,String>>() {});
		
		assertEquals(false, response.hasError());
		assertEquals(false, response.hasResponseError());
		assertEquals(false, response.hasUnkownError());
		
		Map<String,String> result = response.getBody();
		
		log.debug("result: {}", result);
	}

	@Test
	void remove() {
		RestResponse<Map<String,String>> response = RestApi.client(localApiServer+ "/api/samples/{id}")
				.uriVariable("id", "2")
				.delete(new ParameterizedTypeReference<Map<String,String>>() {});
		
		assertEquals(false, response.hasError());
		assertEquals(false, response.hasResponseError());
		assertEquals(false, response.hasUnkownError());
		
		Map<String,String> result = response.getBody();
		
		log.debug("result: {}", result);
	}

	@Test
	void getWithoutAuth() {
		RestResponse<Sample> response = RestApi.client(localApiServer+ "/api/samples/{id}", false)
				.uriVariable("id", "2")
				.get(Sample.class);
		
		assertEquals(false, response.hasError());
		assertEquals(false, response.hasResponseError());
		assertEquals(false, response.hasUnkownError());
		
		Sample result = response.getBody();
		
		log.debug("ID: {}", result.getId());
	}

	@Test
	void getWithAddtionalHeader() {
		RestResponse<Sample> response = RestApi.client(localApiServer+ "/api/samples/{id}")
				.addHeader("test-header-key1", "testHeaderValue1")
				.addHeader("test-header-key2", "testHeaderValue2")
				.uriVariable("id", "2")
				.get(Sample.class);
		
		assertEquals(false, response.hasError());
		assertEquals(false, response.hasResponseError());
		assertEquals(false, response.hasUnkownError());
		
		Sample result = response.getBody();
		
		log.debug("ID: {}", result.getId());
	}

}
