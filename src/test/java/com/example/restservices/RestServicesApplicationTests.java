package com.example.restservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import com.example.restservices.Calc;

@SpringBootTest
@AutoConfigureMockMvc
class RestServicesApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void IntegrationTest() throws Exception {
		// example case
		this.mockMvc.perform(get("/calc?data=5,4,6,1")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("{\"output\":8.77}")));
		
		// edge case no data
		this.mockMvc.perform(get("/calc?data=")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("{\"output\":null}")));
		
		// edge case data.length < 3 
		this.mockMvc.perform(get("/calc?data=1,2")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("{\"output\":null}")));
		
		// non valid url
		this.mockMvc.perform(get("/calc")).andDo(print()).andExpect(status().is4xxClientError());
		
		// non valid url
		this.mockMvc.perform(get("/google")).andDo(print()).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void UnitTest() throws Exception {
		// example case
		Double[] data = {5.0,4.0,6.0,1.0};
		Calc tester = new Calc(data);
		Assertions.assertEquals(tester.getOutput().toString(), "8.77");
		
		// empty list
		Double[] data2 = {};
		tester = new Calc(data2);
		Assertions.assertEquals(tester.getOutput(), null);
		
		// all zeroes
		Double[] data3 = {0.0,0.0,0.0,0.0,0.0};
		tester = new Calc(data3);
		Assertions.assertEquals(tester.getOutput().toString(), "0.00" );
	}
	

}
