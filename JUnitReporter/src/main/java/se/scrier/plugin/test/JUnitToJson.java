package se.scrier.plugin.test;

import com.google.gson.Gson;

import se.scrier.plugin.test.junit.Error;
import se.scrier.plugin.test.junit.Failure;
import se.scrier.plugin.test.junit.Properties;
import se.scrier.plugin.test.junit.Property;
import se.scrier.plugin.test.junit.Skipped;

public enum JUnitToJson {
	INSTANCE;
	
	private Gson gson;
	
	private JUnitToJson() {
		gson = new Gson();
	}
	
	public String getError(Error error) {
		return gson.toJson(error, Error.class);
	}
	
	public Error getError(String error) {
		return gson.fromJson(error, Error.class);
	}
	
	public String getFailure(Failure failure) {
		return gson.toJson(failure, Failure.class);
	}
	
	public Failure getFailure(String failure) {
		return gson.fromJson(failure, Failure.class);
	}
	
	public String getProperty(Property property) {
		return gson.toJson(property, Property.class);
	}
	
	public Property getProperty(String property) {
		return gson.fromJson(property, Property.class);
	}
	
	public String getProperties(Properties properties) {
		return gson.toJson(properties, Properties.class);
	}
	
	public Properties getProperties(String properties) {
		return gson.fromJson(properties, Properties.class);
	}
	
	public String getSkipped(Skipped skipped) {
		return gson.toJson(skipped, Skipped.class);
	}
	
	public Skipped getSkipped(String skipped) {
		return gson.fromJson(skipped, Skipped.class);
	}
	
}
