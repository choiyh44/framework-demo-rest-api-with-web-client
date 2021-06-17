package kr.co.ensmart.frameworkdemo.common.rest;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
//	@Bean
//	public ReactorResourceFactory resourceFactory() {
//	    return new ReactorResourceFactory();
//	}
	
	@Bean
	public WebClient webClient() {

		HttpClient httpClient = HttpClient.create()
		        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000) // connection timeout
		        .responseTimeout(Duration.ofSeconds(10)); // response timeout

		WebClient webClient = WebClient.builder()
				.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();

		return webClient;
		
//		Function<HttpClient, HttpClient> mapper = client -> {
//	        return client;
//	    };
//
//	    ClientHttpConnector connector = new ReactorClientHttpConnector(resourceFactory(), mapper); 
//
//	    return WebClient.builder().clientConnector(connector).build(); 
	}
}
