package codist.garmin.uploader.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
public class RestClientConfig {
	
	@Value("${strava.connection.timeoutInMilliSeconds}")
	private int connectTimeoutMillis;
	
	@Value("${strava.connection.readTimeoutInMilliSeconds}")
	private int readTimeoutMillis;
	
	@Bean
	public HttpClient httpClient() {
		final HttpClientBuilder httpClient = HttpClientBuilder.create();
		httpClient.setConnectionManager(connectionManager());
		return httpClient.build();
	}
	
	@Bean
	public Hibernate4Module hibernate4Module() {
		Hibernate4Module hibernate4Module = new Hibernate4Module();
		hibernate4Module.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
		// hibernate4Module.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
		return hibernate4Module;
	}

	@Bean
	public HttpClientConnectionManager connectionManager() {
		final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		return connectionManager;
	}
	
	@Bean
	public ClientHttpRequestFactory requestFactory() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
		requestFactory.setConnectTimeout(connectTimeoutMillis);
		requestFactory.setReadTimeout(readTimeoutMillis);
		return requestFactory;
	}
	
	@Bean
	public HttpMessageConverter<?> jacksonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper());
		return converter;
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		objectMapper.registerModule(hibernate4Module());
		return objectMapper;
	}
		
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(requestFactory());
		
		HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
		//HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
		
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(formHttpMessageConverter);
		converters.add(jacksonMessageConverter());
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}


}
