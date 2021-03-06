package org.treez.javafxd3.d3.selection.datumfunction;

import static org.junit.Assert.assertEquals;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;



/**
 * A datum function that asserts that the datum equals an increasing
 * counting index, starting with 1.
 *
 */
public class AssertStringDatumFunction implements DatumFunction<Void> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;	
	
	private String expectedValue;
	
	//#end region
	
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AssertStringDatumFunction(WebEngine webEngine, String expectedValue){
		this.webEngine=webEngine;
		this.expectedValue=expectedValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);
		String stringValue = value.asString();
		assertEquals(expectedValue, stringValue);
		return null;
	}
	
	//#end region

}
