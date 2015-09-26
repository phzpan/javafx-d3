package com.github.javafxd3.api.scales;

import static org.junit.Assert.assertEquals;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;


@SuppressWarnings("javadoc")
public class QuantizeScaleTest extends AbstractTestCase {
	
	private static final double DELTA = 0.001d;

	@Override
	public void doTest() {
		
		D3 d3 = new D3(webEngine);

		QuantizeScale quantize = d3.scale().quantize();

		// default
		assertEquals(0.0, quantize.apply(0.49).asDouble(),DELTA);
		assertEquals(1.0, quantize.apply(0.51).asDouble(),DELTA);
		assertEquals(1.0, quantize.apply(2.5).asDouble(),DELTA);
		assertEquals(0.0, quantize.apply(-100).asDouble(),DELTA);

		// range
		quantize.range(0.0, 1.0, 100.0);
		assertEquals(0.0, quantize.apply(-10.0).asDouble(),DELTA);
		assertEquals(0.0, quantize.apply(0.0).asDouble(),DELTA);
		// assertEquals(1.0, quantize.apply(0.26).asDouble());
		// assertEquals(1.0, quantize.apply(0.5).asDouble());
		assertEquals(100.0, quantize.apply(1.0).asDouble(),DELTA);
		assertEquals(100.0, quantize.apply(261).asDouble(),DELTA);

		// domain: only 1st and last number are taken
		quantize.domain(0.0, 1.0, 30.0);
		// it takes only the first and last
		assertEquals(0.0, quantize.domain()[0]);
		assertEquals(30.0, quantize.domain()[1]);

		assertEquals(0.0, quantize.apply(-10.0).asDouble(),DELTA);
		assertEquals(0.0, quantize.apply(0.0).asDouble(),DELTA);
		assertEquals(0.0, quantize.apply(4.9).asDouble(),DELTA);
		assertEquals(0.0, quantize.apply(5.9).asDouble(),DELTA);
		assertEquals(1.0, quantize.apply(10.1).asDouble(),DELTA);
		assertEquals(1.0, quantize.apply(18.0).asDouble(),DELTA);
		assertEquals(100.0, quantize.apply(20.1).asDouble(),DELTA);
		assertEquals(100.0, quantize.apply(25.0).asDouble(),DELTA);
		assertEquals(100.0, quantize.apply(261).asDouble(),DELTA);

		//invertextent
		assertEquals(0.0, quantize.invertExtent(0.0)[0],DELTA);
		assertEquals(10.0, quantize.invertExtent(0.0)[1],DELTA);

		// getters
		assertEquals(0.0, quantize.range()[0]);
		assertEquals(1.0, quantize.range()[1]);
		assertEquals(100.0, quantize.range()[2]);

		// copy
		quantize.copy().range(5, 6, 7);
		assertEquals(0.0, quantize.range()[0]);
		assertEquals(1.0, quantize.range()[1]);
		assertEquals(100.0, quantize.range()[2]);

	}
}