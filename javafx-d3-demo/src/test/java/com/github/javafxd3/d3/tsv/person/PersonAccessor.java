package com.github.javafxd3.d3.tsv.person;

import com.github.javafxd3.d3.dsv.DsvObjectAccessor;
import com.github.javafxd3.d3.dsv.DsvRow;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class PersonAccessor implements DsvObjectAccessor<Person> {
	
	private WebEngine webEngine;

	public PersonAccessor(WebEngine webEngine) {
		this.webEngine = webEngine;
	}
	
	
	@Override
	public Person apply(final Object row, final int index) {
		
		JSObject jsRow = (JSObject) row;
		DsvRow dsvRow = new DsvRow(webEngine, jsRow);
		
		return new Person(dsvRow.get("Name").asString(), dsvRow.get("Age").asInt());
	}
}
