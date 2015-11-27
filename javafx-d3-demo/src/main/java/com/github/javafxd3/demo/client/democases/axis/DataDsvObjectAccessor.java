package com.github.javafxd3.demo.client.democases.axis;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.dsv.DsvObjectAccessor;
import com.github.javafxd3.api.dsv.DsvRow;
import com.github.javafxd3.api.time.JsDate;
import com.github.javafxd3.api.time.TimeFormat;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class DataDsvObjectAccessor implements DsvObjectAccessor<DsvData> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	private TimeFormat format;
	
	//#end region

	
	//#region CONSTRUCTORS
	
	public DataDsvObjectAccessor(WebEngine webEngine, TimeFormat format){
		this.webEngine = webEngine;
		this.format = format;
	}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public DsvData apply(Object row, int index) {
			
		JSObject jsRow = (JSObject) row;
		DsvRow dsvRow = new DsvRow(webEngine, jsRow);
	
		Value value = dsvRow.get("symbol");
		
		if ("S&P 500".equals(value.asString())) {
			String symbol = dsvRow.get("symbol").asString();
			JsDate date = format.parse(dsvRow.get("date").asString());
			double price = dsvRow.get("price").asDouble();
			return new DsvData(webEngine, symbol, date, price);
		} else {
			return null;
		}
	}
		
	//#end region

}