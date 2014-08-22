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

/**
 * Singleton class to convert to and from json text.
 * @author Andreas Joelsson
 */
public enum JUnitToJson {
	INSTANCE;
	
	private Gson gson;	///< Gson instance for converting classes to json objects.
	
	/**
	 * Constructor
	 */
	private JUnitToJson() {
		gson = new Gson();
	}
	
	/**
	 * Method to convert an Error class to json string.
	 * @param error Error instance.
	 * @return String in format {"type":"","message":""}
	 */
	public String getError(Error error) {
		return gson.toJson(error, Error.class);
	}
	
	/**
	 * Method to convert a json String to a Error object.
	 * @param error String in format {"type":"","message":""}
	 * @return Error instance
	 */
	public Error getError(String error) {
		return gson.fromJson(error, Error.class);
	}
	
	/**
	 * Method to convert an Failure class to json string.
	 * @param failure Failure instance.
	 * @return String in format {"type":"","message":""}
	 */
	public String getFailure(Failure failure) {
		return gson.toJson(failure, Failure.class);
	}
	
	/**
	 * Method to convert a json String to a Failure object.
	 * @param failure String in format {"type":"","message":""}
	 * @return Failure instance
	 */
	public Failure getFailure(String failure) {
		return gson.fromJson(failure, Failure.class);
	}
	
	/**
	 * Method to convert an Property class to json string.
	 * @param property Property instance.
	 * @return String in format {"name":"","value":""}
	 */
	public String getProperty(Property property) {
		return gson.toJson(property, Property.class);
	}
	
	/**
	 * Method to convert a json String to a Property object.
	 * @param property String in format {"name":"","value":""}
	 * @return Property instance
	 */
	public Property getProperty(String property) {
		return gson.fromJson(property, Property.class);
	}
	
	/**
	 * Method to convert an Properties class to json string.
	 * @param properties Properties instance.
	 * @return String in format {"property":[]}
	 */
	public String getProperties(Properties properties) {
		return gson.toJson(properties, Properties.class);
	}
	
	/**
	 * Method to convert a json String to a Properties object.
	 * @param properties String in format {"property":[]}
	 * @return Properties instance
	 */
	public Properties getProperties(String properties) {
		return gson.fromJson(properties, Properties.class);
	}
	
	/**
	 * Method to convert an Skipped class to json string.
	 * @param skipped Skipped instance.
	 * @return String in format {"skipped":""}
	 */
	public String getSkipped(Skipped skipped) {
		return gson.toJson(skipped, Skipped.class);
	}
	
	/**
	 * Method to convert a json String to a Skipped object.
	 * @param skipped String in format {"skipped":""}
	 * @return Skipped instance
	 */
	public Skipped getSkipped(String skipped) {
		return gson.fromJson(skipped, Skipped.class);
	}
	
	/**
	 * Method to convert an SystemErr class to json string.
	 * @param systemErr SystemErr instance.
	 * @return String in format {"system_err":""}
	 */
	public String getSystemErr(SystemErr systemErr) {
		return gson.toJson(systemErr, SystemErr.class);
	}
	
	/**
	 * Method to convert a json String to a SystemErr object.
	 * @param systemErr String in format {"system_err":""}
	 * @return SystemErr instance
	 */
	public SystemErr getSystemErr(String systemErr) {
		return gson.fromJson(systemErr, SystemErr.class);
	}
	
	/**
	 * Method to convert an SystemOut class to json string.
	 * @param systemOut SystemOut instance.
	 * @return String in format {"system_out":""}
	 */
	public String getSystemOut(SystemOut systemOut) {
		return gson.toJson(systemOut, SystemOut.class);
	}
	
	/**
	 * Method to convert a json String to a SystemOut object.
	 * @param systemOut String in format {"system_out":""}
	 * @return SystemOut instance
	 */
	public SystemOut getSystemOut(String systemOut) {
		return gson.fromJson(systemOut, SystemOut.class);
	}
	
	/**
	 * Method to convert an TestCase class to json string.
	 * @param testCase TestCase instance.
	 * @return String in format {"skipped":{"skipped":""},"error":[],"failure":[],"system_out":[],"system_err":[],"name":"","assertions":"","time":0.0,"classname":"","status":""}
	 */
	public String getTestCase(TestCase testCase) {
		return gson.toJson(testCase, TestCase.class);
	}
	
	/**
	 * Method to convert a json String to a TestCase object.
	 * @param testCase String in format {"skipped":{"skipped":""},"error":[],"failure":[],"system_out":[],"system_err":[],"name":"","assertions":"","time":0.0,"classname":"","status":""}
	 * @return TestCase instance
	 */
	public TestCase getTestCase(String testCase) {
		return gson.fromJson(testCase, TestCase.class);
	}
	
	/**
	 * Method to convert an TestSuite class to json string.
	 * @param testSuite TestSuite instance.
	 * @return String in format {"properties":{"property":[]},"testcase":[],"system_out":{"system_out":""},"system_err":{"system_err":""},"name":"","tests":-1,"failures":-1,"errors":-1,"time":0.0,"disabled":false,"skipped":-1,"timestamp":"","hostname":"","id":"","_package":""}
	 */
	public String getTestSuite(TestSuite testSuite) {
		return gson.toJson(testSuite, TestSuite.class);
	}
	
	/**
	 * Method to convert a json String to a TestSuite object.
	 * @param testSuite String in format {"properties":{"property":[]},"testcase":[],"system_out":{"system_out":""},"system_err":{"system_err":""},"name":"","tests":-1,"failures":-1,"errors":-1,"time":0.0,"disabled":false,"skipped":-1,"timestamp":"","hostname":"","id":"","_package":""}
	 * @return TestSuite instance
	 */
	public TestSuite getTestSuite(String testSuite) {
		return gson.fromJson(testSuite, TestSuite.class);
	}
	
	/**
	 * Method to convert an TestSuites class to json string.
	 * @param testSuites TestSuites instance.
	 * @return String in format {"testsuite":[],"name":"","time":0.0,"tests":0,"failures":0,"disabled":0,"errors":0}
	 */
	public String getTestSuites(TestSuites testSuites) {
		return gson.toJson(testSuites, TestSuites.class);
	}
	
	/**
	 * Method to convert a json String to a TestSuites object.
	 * @param testSuites String in format {"testsuite":[],"name":"","time":0.0,"tests":0,"failures":0,"disabled":0,"errors":0}
	 * @return TestSuites instance
	 */
	public TestSuites getTestSuites(String testSuites) {
		return gson.fromJson(testSuites, TestSuites.class);
	}
	
}
