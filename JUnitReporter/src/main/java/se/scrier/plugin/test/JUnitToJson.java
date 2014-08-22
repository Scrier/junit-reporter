package se.scrier.plugin.test;

import com.google.gson.Gson;

import se.scrier.plugin.test.junit.Error;
import se.scrier.plugin.test.junit.Failure;
import se.scrier.plugin.test.junit.Properties;
import se.scrier.plugin.test.junit.Property;
import se.scrier.plugin.test.junit.Skipped;
import se.scrier.plugin.test.junit.SystemErr;
import se.scrier.plugin.test.junit.SystemOut;
import se.scrier.plugin.test.junit.TestCase;
import se.scrier.plugin.test.junit.TestSuite;
import se.scrier.plugin.test.junit.TestSuites;

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
	
	public String getSystemErr(SystemErr systemErr) {
		return gson.toJson(systemErr, SystemErr.class);
	}
	
	public SystemErr getSystemErr(String systemErr) {
		return gson.fromJson(systemErr, SystemErr.class);
	}
	
	public String getSystemOut(SystemOut systemOut) {
		return gson.toJson(systemOut, SystemOut.class);
	}
	
	public SystemOut getSystemOut(String systemOut) {
		return gson.fromJson(systemOut, SystemOut.class);
	}
	
	public String getTestCase(TestCase testCase) {
		return gson.toJson(testCase, TestCase.class);
	}
	
	public TestCase getTestCase(String testCase) {
		return gson.fromJson(testCase, TestCase.class);
	}
	
	public String getTestSuite(TestSuite testSuite) {
		return gson.toJson(testSuite, TestSuite.class);
	}
	
	public TestSuite getTestSuite(String testSuite) {
		return gson.fromJson(testSuite, TestSuite.class);
	}
	
	public String getTestSuites(TestSuites testSuites) {
		return gson.toJson(testSuites, TestSuites.class);
	}
	
	public TestSuites getTestSuites(String testSuites) {
		return gson.fromJson(testSuites, TestSuites.class);
	}
	
}
