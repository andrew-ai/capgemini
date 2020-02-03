package com.example.restservices;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.lang.Math;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Calc {

	private BigDecimal output;

	public Calc(Double[] data) {
		if (data.length < 3) {
			this.output = null;
			return;
		}
		
		Optional<Double> temp = Arrays.stream(data)
			.sorted(Comparator.reverseOrder())
			.limit(3)
			.map(x -> x * x)
			.reduce((x,y) -> x+y)
			.map(x -> Math.sqrt(x));
		
		if (temp.isPresent()) {
			BigDecimal res = new BigDecimal(temp.get()).setScale(2, BigDecimal.ROUND_HALF_UP);
			this.output = res;
		} else {
			this.output = null;
		}
	}

	public BigDecimal getOutput() {
		return output;
	}
}