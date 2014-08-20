package se.scrier.plugin.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.scrier.plugin.test.junit.Error;
import se.scrier.plugin.test.junit.Failure;
import se.scrier.plugin.test.junit.Properties;
import se.scrier.plugin.test.junit.Property;
import se.scrier.plugin.test.junit.Skipped;

public class JUnitToJsonTest {
	
	private static Logger log = Logger.getLogger(JUnitToJsonTest.class);
	private JUnitToJson testObject;
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}
	
	@Before
	public void setup() throws IOException {
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testErrorEmpty() {
		log.info("testErrorEmpty");
		Error input = new Error();
		String json = JUnitToJson.INSTANCE.getError(input);
		log.info("String: " + json);
		Error output = JUnitToJson.INSTANCE.getError(json);
		assertEquals(input.getType(), output.getType());
		assertEquals(input.getMessage(), output.getMessage());
	}
	
	@Test
	public void testErrorType() {
		log.info("testErrorType");
		Error input = new Error("type", "");
		String json = JUnitToJson.INSTANCE.getError(input);
		log.info("String: " + json);
		Error output = JUnitToJson.INSTANCE.getError(json);
		assertEquals(input.getType(), output.getType());
		assertEquals(input.getMessage(), output.getMessage());
	}
	
	@Test
	public void testErrorMessage() {
		log.info("testErrorMessage");
		Error input = new Error("", "message");
		String json = JUnitToJson.INSTANCE.getError(input);
		log.info("String: " + json);
		Error output = JUnitToJson.INSTANCE.getError(json);
		assertEquals(input.getType(), output.getType());
		assertEquals(input.getMessage(), output.getMessage());
	}
	
	@Test
	public void testErrorTypeMessage() {
		log.info("testErrorMessage");
		Error input = new Error("type", "message");
		String json = JUnitToJson.INSTANCE.getError(input);
		log.info("String: " + json);
		Error output = JUnitToJson.INSTANCE.getError(json);
		assertEquals(input.getType(), output.getType());
		assertEquals(input.getMessage(), output.getMessage());
	}
	
	@Test
	public void testFailureEmpty() {
		log.info("testFailureEmpty");
		Failure input = new Failure();
		String json = JUnitToJson.INSTANCE.getFailure(input);
		log.info("String: " + json);
		Failure output = JUnitToJson.INSTANCE.getFailure(json);
		assertEquals(input.getType(), output.getType());
		assertEquals(input.getMessage(), output.getMessage());
	}
	
	@Test
	public void testFailureType() {
		log.info("testFailureType");
		Failure input = new Failure("type", "");
		String json = JUnitToJson.INSTANCE.getFailure(input);
		log.info("String: " + json);
		Failure output = JUnitToJson.INSTANCE.getFailure(json);
		assertEquals(input.getType(), output.getType());
		assertEquals(input.getMessage(), output.getMessage());
	}
	
	@Test
	public void testFailureMessage() {
		log.info("testFailureMessage");
		Failure input = new Failure("", "message");
		String json = JUnitToJson.INSTANCE.getFailure(input);
		log.info("String: " + json);
		Failure output = JUnitToJson.INSTANCE.getFailure(json);
		assertEquals(input.getType(), output.getType());
		assertEquals(input.getMessage(), output.getMessage());
	}
	
	@Test
	public void testFailureTypeMessage() {
		log.info("testFailureMessage");
		Failure input = new Failure("type", "message");
		String json = JUnitToJson.INSTANCE.getFailure(input);
		log.info("String: " + json);
		Failure output = JUnitToJson.INSTANCE.getFailure(json);
		assertEquals(input.getType(), output.getType());
		assertEquals(input.getMessage(), output.getMessage());
	}
	
	@Test
	public void testPropertyEmpty() {
		log.info("testPropertyEmpty");
		Property input = new Property();
		String json = JUnitToJson.INSTANCE.getProperty(input);
		log.info("String: " + json);
		Property output = JUnitToJson.INSTANCE.getProperty(json);
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getValue(), output.getValue());
	}
	
	@Test
	public void testPropertyType() {
		log.info("testPropertyType");
		Property input = new Property("name", "");
		String json = JUnitToJson.INSTANCE.getProperty(input);
		log.info("String: " + json);
		Property output = JUnitToJson.INSTANCE.getProperty(json);
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getValue(), output.getValue());
	}
	
	@Test
	public void testPropertyMessage() {
		log.info("testPropertyMessage");
		Property input = new Property("", "value");
		String json = JUnitToJson.INSTANCE.getProperty(input);
		log.info("String: " + json);
		Property output = JUnitToJson.INSTANCE.getProperty(json);
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getValue(), output.getValue());
	}
	
	@Test
	public void testPropertyTypeMessage() {
		log.info("testPropertyMessage");
		Property input = new Property("name", "value");
		String json = JUnitToJson.INSTANCE.getProperty(input);
		log.info("String: " + json);
		Property output = JUnitToJson.INSTANCE.getProperty(json);
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getValue(), output.getValue());
	}
	
	@Test
	public void testPropertiesEmpty() {
		log.info("testPropertiesEmpty");
		Properties input = new Properties();
		String json = JUnitToJson.INSTANCE.getProperties(input);
		log.info("String: " + json);
		Properties output = JUnitToJson.INSTANCE.getProperties(json);
		assertEquals(input.getProperty().size(), output.getProperty().size());
	}
	
	@Test
	public void testPropertiesOneProperty() {
		log.info("testPropertiesOneProperty");
		Properties input = new Properties();
		input.addProperty(new Property("name1", "value1"));
		String json = JUnitToJson.INSTANCE.getProperties(input);
		log.info("String: " + json);
		Properties output = JUnitToJson.INSTANCE.getProperties(json);
		assertEquals(input.getProperty().size(), output.getProperty().size());
		assertEquals(input.getProperty().get(0).getName(), input.getProperty().get(0).getName());
		assertEquals(input.getProperty().get(0).getValue(), input.getProperty().get(0).getValue());
	}
	
	@Test
	public void testPropertiesThreeProperty() {
		log.info("testPropertiesThreeProperty");
		Properties input = new Properties();
		input.addProperty(new Property("name1", "value1"));
		input.addProperty(new Property("name2", "value2"));
		input.addProperty(new Property("name3", "value3"));
		String json = JUnitToJson.INSTANCE.getProperties(input);
		log.info("String: " + json);
		Properties output = JUnitToJson.INSTANCE.getProperties(json);
		assertEquals(input.getProperty().size(), output.getProperty().size());
		assertEquals(input.getProperty().get(0).getName(), input.getProperty().get(0).getName());
		assertEquals(input.getProperty().get(0).getValue(), input.getProperty().get(0).getValue());
		assertEquals(input.getProperty().get(1).getName(), input.getProperty().get(1).getName());
		assertEquals(input.getProperty().get(1).getValue(), input.getProperty().get(1).getValue());
		assertEquals(input.getProperty().get(2).getName(), input.getProperty().get(2).getName());
		assertEquals(input.getProperty().get(2).getValue(), input.getProperty().get(2).getValue());
	}
	
	@Test
	public void testSkippedEmpty() {
		log.info("testSkippedEmpty");
		Skipped input = new Skipped();
		String json = JUnitToJson.INSTANCE.getSkipped(input);
		log.info("String: " + json);
		Skipped output = JUnitToJson.INSTANCE.getSkipped(json);
		assertEquals(input.getSkipped(), output.getSkipped());
	}
	
	@Test
	public void testSkippedText() {
		log.info("testSkippedText");
		Skipped input = new Skipped("skipped");
		String json = JUnitToJson.INSTANCE.getSkipped(input);
		log.info("String: " + json);
		Skipped output = JUnitToJson.INSTANCE.getSkipped(json);
		assertEquals(input.getSkipped(), output.getSkipped());
	}

}
