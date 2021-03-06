package com.github.javafxd3.d3.svg;

import com.github.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A line generator where each points may be expressed as radial coordinates.
 * <p>
 * 
 * 
 * 
 */
public class RadialLine extends Line {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public RadialLine(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Set the radius coordinates of points generated by this generator.
	 * 
	 * @param d
	 *            the radius
	 * @return the current line
	 */
	public RadialLine radius(double d) {
		JSObject result = call("radius", d);
		return new RadialLine(webEngine, result);
	}

	/**
	 * Set the function used to compute radius coordinates of points generated
	 * by this line generator. The function is invoked for each element in the
	 * data array passed to the line generator.
	 * <p>
	 * The default accessor assumes that each input element is a two-element
	 * array of numbers.
	 * 
	 * @param datumFunction
	 * @return the current line
	 */
	public RadialLine radius(final DatumFunction<Double> callback) {

		assertObjectIsNotAnonymous(callback);

		String callbackName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(callbackName, callback);

		String command = "this.radius(function(d, i) { " //
				+ "return d3." + callbackName + ".apply(this,{datum:d},i);" //
				+ " });";
		JSObject result = evalForJsObject(command);
		return new RadialLine(webEngine, result);

	}

	/**
	 * Set the angle coordinates of points generated by this generator.
	 * 
	 * @param d
	 *            the angle
	 * @return the current line
	 */
	public RadialLine angle(double d) {
		JSObject result = call("angle", d);
		return new RadialLine(webEngine, result);
	}

	/**
	 * Set the function used to compute angle coordinates of points generated by
	 * this line generator. The function is invoked for each element in the data
	 * array passed to the line generator.
	 * <p>
	 * The default accessor assumes that each input element is a two-element
	 * array of numbers.
	 * 
	 * @param datumFunction
	 * @return the current line
	 */
	public RadialLine angle(final DatumFunction<Double> callback) {

		assertObjectIsNotAnonymous(callback);

		String callbackName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(callbackName, callback);

		String command = "this.angle(function(d, i) { " //
				+ "return d3." + callbackName + ".apply(this,{datum:d},i);" //
				+ " });";
		JSObject result = evalForJsObject(command);
		return new RadialLine(webEngine, result);
	}

}
