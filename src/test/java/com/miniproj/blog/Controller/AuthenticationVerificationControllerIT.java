package com.miniproj.blog.Controller;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.miniproj.blog.BlogApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationVerificationControllerIT {

	@LocalServerPort
	private int port; 
	
	@Test
	public void testtheurltest() throws JSONException {
		
		String URL = "http://localhost:"+port+"/test?page=0&size=1";
		
		TestRestTemplate restTemplate = new TestRestTemplate();	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		
		ResponseEntity<String> response = restTemplate.exchange(URL,HttpMethod.GET,entity,String.class);
		
		JSONAssert.assertEquals("{content:[{id:11}]}", response.getBody(), false);
	}

}
