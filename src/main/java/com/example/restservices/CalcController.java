package com.example.restservices;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {

	@GetMapping("/calc")
	public Calc calc(@RequestParam Double[] data) {
		return new Calc(data);
	}
}