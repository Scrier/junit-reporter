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
import se.scrier.plugin.test.junit.SystemErr;
import se.scrier.plugin.test.junit.SystemOut;
import se.scrier.plugin.test.junit.TestCase;
import se.scrier.plugin.test.junit.TestSuite;
import se.scrier.plugin.test.junit.TestSuites;

public class JUnitToJsonTest {
	
	private static Logger log = Logger.getLogger(JUnitToJsonTest.class);
	private static double TOLERANCE = 0.0000001;
	
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
	
	@Test
	public void testSystemErrEmpty() {
		log.info("testSystemErrEmpty");
		SystemErr input = new SystemErr();
		String json = JUnitToJson.INSTANCE.getSystemErr(input);
		log.info("String: " + json);
		SystemErr output = JUnitToJson.INSTANCE.getSystemErr(json);
		assertEquals(input.getSystemErr(), output.getSystemErr());
	}
	
	@Test
	public void testSystemErrText() {
		log.info("testSystemErrText");
		SystemErr input = new SystemErr("SystemErr");
		String json = JUnitToJson.INSTANCE.getSystemErr(input);
		log.info("String: " + json);
		SystemErr output = JUnitToJson.INSTANCE.getSystemErr(json);
		assertEquals(input.getSystemErr(), output.getSystemErr());
	}
	
	@Test
	public void testSystemOutEmpty() {
		log.info("testSystemOutEmpty");
		SystemOut input = new SystemOut();
		String json = JUnitToJson.INSTANCE.getSystemOut(input);
		log.info("String: " + json);
		SystemOut output = JUnitToJson.INSTANCE.getSystemOut(json);
		assertEquals(input.getSystemOut(), output.getSystemOut());
	}
	
	@Test
	public void testSystemOutText() {
		log.info("testSystemOutText");
		SystemOut input = new SystemOut("SystemOut");
		String json = JUnitToJson.INSTANCE.getSystemOut(input);
		log.info("String: " + json);
		SystemOut output = JUnitToJson.INSTANCE.getSystemOut(json);
		assertEquals(input.getSystemOut(), output.getSystemOut());
	}
	
	@Test
	public void testTestCaseEmpty() {
		log.info("testTestCaseEmpty");
		TestCase input = new TestCase();
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseAssertions() {
		log.info("testTestCaseAssertions");
		TestCase input = new TestCase();
		input.setAssertions("assertions");
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseClassname() {
		log.info("testTestCaseClassname");
		TestCase input = new TestCase();
		input.setClassname("Classname");
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseName() {
		log.info("testTestCaseName");
		TestCase input = new TestCase();
		input.setName("Name");
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseStatus() {
		log.info("testTestCaseStatus");
		TestCase input = new TestCase();
		input.setStatus("Status");
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseTime() {
		log.info("testTestCaseTime");
		TestCase input = new TestCase();
		input.setTime(1234.1234);
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseSkipped() {
		log.info("testTestCaseSkipped");
		TestCase input = new TestCase();
		input.getSkipped().setSkipped("skipped");
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseError() {
		log.info("testTestCaseError");
		TestCase input = new TestCase();
		input.addError(new Error("type1", "message1"));
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getError().get(0).getType(), output.getError().get(0).getType());
		assertEquals(input.getError().get(0).getMessage(), output.getError().get(0).getMessage());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseFailure() {
		log.info("testTestCaseFailure");
		TestCase input = new TestCase();
		input.addFailure(new Failure("type1", "message1"));
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getFailure().get(0).getType(), output.getFailure().get(0).getType());
		assertEquals(input.getFailure().get(0).getMessage(), output.getFailure().get(0).getMessage());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseSystemErr() {
		log.info("testTestCaseSystemErr");
		TestCase input = new TestCase();
		input.addSystemErr(new SystemErr("systemErr"));
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemErr().get(0).getSystemErr(), output.getSystemErr().get(0).getSystemErr());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCaseSystemOut() {
		log.info("testTestCaseSystemOut");
		TestCase input = new TestCase();
		input.addSystemOut(new SystemOut("SystemOut"));
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getSystemOut().get(0).getSystemOut(), output.getSystemOut().get(0).getSystemOut());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestCase() {
		log.info("testTestCase");
		TestCase input = new TestCase();
		input.setAssertions("assertions");
		input.setClassname("Classname");
		input.setName("Name");
		input.setStatus("Status");
		input.setTime(1234.1234);
		input.getSkipped().setSkipped("skipped");
		input.addError(new Error("type1", "message1"));
		input.addError(new Error("type2", "message2"));
		input.addFailure(new Failure("type1", "message1"));
		input.addFailure(new Failure("type2", "message2"));
		input.addSystemErr(new SystemErr("systemErr1"));
		input.addSystemErr(new SystemErr("systemErr2"));
		input.addSystemOut(new SystemOut("SystemOut1"));
		input.addSystemOut(new SystemOut("SystemOut2"));
		String json = JUnitToJson.INSTANCE.getTestCase(input);
		log.info("String: " + json);
		TestCase output = JUnitToJson.INSTANCE.getTestCase(json);
		assertEquals(input.getAssertions(), output.getAssertions());
		assertEquals(input.getClassname(), output.getClassname());
		assertEquals(input.getError().size(), output.getError().size());
		assertEquals(input.getError().get(0).getType(), output.getError().get(0).getType());
		assertEquals(input.getError().get(0).getMessage(), output.getError().get(0).getMessage());
		assertEquals(input.getError().get(1).getType(), output.getError().get(1).getType());
		assertEquals(input.getError().get(1).getMessage(), output.getError().get(1).getMessage());
		assertEquals(input.getFailure().size(), output.getFailure().size());
		assertEquals(input.getFailure().get(0).getType(), output.getFailure().get(0).getType());
		assertEquals(input.getFailure().get(0).getMessage(), output.getFailure().get(0).getMessage());
		assertEquals(input.getFailure().get(1).getType(), output.getFailure().get(1).getType());
		assertEquals(input.getFailure().get(1).getMessage(), output.getFailure().get(1).getMessage());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getSkipped().getSkipped(), output.getSkipped().getSkipped());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getSystemErr().size(), output.getSystemErr().size());
		assertEquals(input.getSystemErr().get(0).getSystemErr(), output.getSystemErr().get(0).getSystemErr());
		assertEquals(input.getSystemErr().get(1).getSystemErr(), output.getSystemErr().get(1).getSystemErr());
		assertEquals(input.getSystemOut().size(), output.getSystemOut().size());
		assertEquals(input.getSystemOut().get(0).getSystemOut(), output.getSystemOut().get(0).getSystemOut());
		assertEquals(input.getSystemOut().get(1).getSystemOut(), output.getSystemOut().get(1).getSystemOut());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestSuiteEmpty() {
		log.info("testTestSuiteEmpty");
		TestSuite input = new TestSuite();
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteErrors() {
		log.info("testTestSuiteErrors");
		TestSuite input = new TestSuite();
		input.setErrors(12345);
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteFailures() {
		log.info("testTestSuiteFailures");
		TestSuite input = new TestSuite();
		input.setFailures(12345);
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteHostName() {
		log.info("testTestSuiteHostName");
		TestSuite input = new TestSuite();
		input.setHostname("HostName");
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteDisabled() {
		log.info("testTestSuiteDisabled");
		TestSuite input = new TestSuite();
		input.setDisabled( ! input.isDisabled() );
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteId() {
		log.info("testTestSuiteId");
		TestSuite input = new TestSuite();
		input.setId("Id");
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteName() {
		log.info("testTestSuiteName");
		TestSuite input = new TestSuite();
		input.setName("Name");
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuitePackage() {
		log.info("testTestSuitePackage");
		TestSuite input = new TestSuite();
		input.setPackage("Package");
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteSkipped() {
		log.info("testTestSuiteSkipped");
		TestSuite input = new TestSuite();
		input.setSkipped(12345);
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteTests() {
		log.info("testTestSuiteTests");
		TestSuite input = new TestSuite();
		input.setTests(12345);
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteTime() {
		log.info("testTestSuiteTime");
		TestSuite input = new TestSuite();
		input.setTime(12345.12345);
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteTimeStamp() {
		log.info("testTestSuiteTimeStamp");
		TestSuite input = new TestSuite();
		input.setTimestamp("TimeStamp");
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteProperty() {
		log.info("testTestSuiteProperty");
		TestSuite input = new TestSuite();
		input.getProperties().addProperty(new Property("Name 1", "Value 1"));
		input.getProperties().addProperty(new Property("Name 2", "Value 2"));
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getProperties().getProperty().get(0).getName(), output.getProperties().getProperty().get(0).getName());
		assertEquals(input.getProperties().getProperty().get(0).getValue(), output.getProperties().getProperty().get(0).getValue());
		assertEquals(input.getProperties().getProperty().get(1).getName(), output.getProperties().getProperty().get(1).getName());
		assertEquals(input.getProperties().getProperty().get(1).getValue(), output.getProperties().getProperty().get(1).getValue());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteSystemErr() {
		log.info("testTestSuiteSystemErr");
		TestSuite input = new TestSuite();
		input.getSystemErr().setSystemErr("SystemErr");
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteSystemOut() {
		log.info("testTestSuiteSystemOut");
		TestSuite input = new TestSuite();
		input.getSystemOut().setSystemOut("SystemOut");
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
	}
	
	@Test
	public void testTestSuiteTestCase() {
		log.info("testTestSuiteTestCase");
		TestSuite input = new TestSuite();
		TestCase testcase = new TestCase();
		testcase.setAssertions("assertions");
		testcase.setClassname("Classname");
		testcase.setName("Name");
		testcase.setStatus("Status");
		testcase.setTime(1234.1234);
		testcase.getSkipped().setSkipped("skipped");
		testcase.addError(new Error("type1", "message1"));
		testcase.addError(new Error("type2", "message2"));
		testcase.addFailure(new Failure("type1", "message1"));
		testcase.addFailure(new Failure("type2", "message2"));
		testcase.addSystemErr(new SystemErr("systemErr1"));
		testcase.addSystemErr(new SystemErr("systemErr2"));
		testcase.addSystemOut(new SystemOut("SystemOut1"));
		testcase.addSystemOut(new SystemOut("SystemOut2"));
		input.addTestcase(testcase);
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
		assertEquals(input.getTestcase().get(0).getAssertions(), output.getTestcase().get(0).getAssertions());
		assertEquals(input.getTestcase().get(0).getClassname(), output.getTestcase().get(0).getClassname());
		assertEquals(input.getTestcase().get(0).getError().size(), output.getTestcase().get(0).getError().size());
		assertEquals(input.getTestcase().get(0).getError().get(0).getType(), output.getTestcase().get(0).getError().get(0).getType());
		assertEquals(input.getTestcase().get(0).getError().get(0).getMessage(), output.getTestcase().get(0).getError().get(0).getMessage());
		assertEquals(input.getTestcase().get(0).getError().get(1).getType(), output.getTestcase().get(0).getError().get(1).getType());
		assertEquals(input.getTestcase().get(0).getError().get(1).getMessage(), output.getTestcase().get(0).getError().get(1).getMessage());
		assertEquals(input.getTestcase().get(0).getFailure().size(), output.getTestcase().get(0).getFailure().size());
		assertEquals(input.getTestcase().get(0).getFailure().get(0).getType(), output.getTestcase().get(0).getFailure().get(0).getType());
		assertEquals(input.getTestcase().get(0).getFailure().get(0).getMessage(), output.getTestcase().get(0).getFailure().get(0).getMessage());
		assertEquals(input.getTestcase().get(0).getFailure().get(1).getType(), output.getTestcase().get(0).getFailure().get(1).getType());
		assertEquals(input.getTestcase().get(0).getFailure().get(1).getMessage(), output.getTestcase().get(0).getFailure().get(1).getMessage());
		assertEquals(input.getTestcase().get(0).getName(), output.getTestcase().get(0).getName());
		assertEquals(input.getTestcase().get(0).getSkipped().getSkipped(), output.getTestcase().get(0).getSkipped().getSkipped());
		assertEquals(input.getTestcase().get(0).getStatus(), output.getTestcase().get(0).getStatus());
		assertEquals(input.getTestcase().get(0).getSystemErr().size(), output.getTestcase().get(0).getSystemErr().size());
		assertEquals(input.getTestcase().get(0).getSystemErr().get(0).getSystemErr(), output.getTestcase().get(0).getSystemErr().get(0).getSystemErr());
		assertEquals(input.getTestcase().get(0).getSystemErr().get(1).getSystemErr(), output.getTestcase().get(0).getSystemErr().get(1).getSystemErr());
		assertEquals(input.getTestcase().get(0).getSystemOut().size(), output.getTestcase().get(0).getSystemOut().size());
		assertEquals(input.getTestcase().get(0).getSystemOut().get(0).getSystemOut(), output.getTestcase().get(0).getSystemOut().get(0).getSystemOut());
		assertEquals(input.getTestcase().get(0).getSystemOut().get(1).getSystemOut(), output.getTestcase().get(0).getSystemOut().get(1).getSystemOut());
		assertEquals(input.getTestcase().get(0).getTime(), output.getTestcase().get(0).getTime(), TOLERANCE);
	}
	
	@Test
	public void testTestSuiteFull() {
		log.info("testTestSuiteFull");
		TestSuite input = new TestSuite();
		input.setErrors(12345);
		input.setFailures(12345);
		input.setHostname("HostName");
		input.setDisabled( ! input.isDisabled() );
		input.setId("Id");
		input.setName("Name");
		input.setPackage("Package");
		input.setSkipped(12345);
		input.setTests(12345);
		input.setTime(12345.12345);
		input.setTimestamp("TimeStamp");
		input.getProperties().addProperty(new Property("Name 1", "Value 1"));
		input.getProperties().addProperty(new Property("Name 2", "Value 2"));
		input.getSystemErr().setSystemErr("SystemErr");
		input.getSystemOut().setSystemOut("SystemOut");
		TestCase testcase1 = new TestCase();
		testcase1.setAssertions("assertions");
		testcase1.setClassname("Classname");
		testcase1.setName("Name");
		testcase1.setStatus("Status");
		testcase1.setTime(1234.1234);
		testcase1.getSkipped().setSkipped("skipped");
		testcase1.addError(new Error("type1", "message1"));
		testcase1.addError(new Error("type2", "message2"));
		testcase1.addFailure(new Failure("type1", "message1"));
		testcase1.addFailure(new Failure("type2", "message2"));
		testcase1.addSystemErr(new SystemErr("systemErr1"));
		testcase1.addSystemErr(new SystemErr("systemErr2"));
		testcase1.addSystemOut(new SystemOut("SystemOut1"));
		testcase1.addSystemOut(new SystemOut("SystemOut2"));
		input.addTestcase(testcase1);
		TestCase testcase2 = new TestCase();
		testcase2.setAssertions("2assertions");
		testcase2.setClassname("2Classname");
		testcase2.setName("2Name");
		testcase2.setStatus("2Status");
		testcase2.setTime(12345.12345);
		testcase2.getSkipped().setSkipped("2skipped");
		testcase2.addError(new Error("2type1", "message1"));
		testcase2.addError(new Error("2type2", "message2"));
		testcase2.addFailure(new Failure("2type1", "message1"));
		testcase2.addFailure(new Failure("2type2", "message2"));
		testcase2.addSystemErr(new SystemErr("2systemErr1"));
		testcase2.addSystemErr(new SystemErr("2systemErr2"));
		testcase2.addSystemOut(new SystemOut("2SystemOut1"));
		testcase2.addSystemOut(new SystemOut("2SystemOut2"));
		input.addTestcase(testcase2);
		String json = JUnitToJson.INSTANCE.getTestSuite(input);
		log.info("String: " + json);
		TestSuite output = JUnitToJson.INSTANCE.getTestSuite(json);
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getHostname(), output.getHostname());
		assertEquals(input.isDisabled(), output.isDisabled());
		assertEquals(input.getId(), output.getId());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getPackage(), output.getPackage());
		assertEquals(input.getSkipped(), output.getSkipped());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTimestamp(), output.getTimestamp());
		assertEquals(input.getProperties().getProperty().size(), output.getProperties().getProperty().size());
		assertEquals(input.getProperties().getProperty().get(0).getName(), output.getProperties().getProperty().get(0).getName());
		assertEquals(input.getProperties().getProperty().get(0).getValue(), output.getProperties().getProperty().get(0).getValue());
		assertEquals(input.getProperties().getProperty().get(1).getName(), output.getProperties().getProperty().get(1).getName());
		assertEquals(input.getProperties().getProperty().get(1).getValue(), output.getProperties().getProperty().get(1).getValue());
		assertEquals(input.getSystemErr().getSystemErr(), output.getSystemErr().getSystemErr());
		assertEquals(input.getSystemOut().getSystemOut(), output.getSystemOut().getSystemOut());
		assertEquals(input.getTestcase().size(), output.getTestcase().size());
		assertEquals(input.getTestcase().get(0).getAssertions(), output.getTestcase().get(0).getAssertions());
		assertEquals(input.getTestcase().get(0).getClassname(), output.getTestcase().get(0).getClassname());
		assertEquals(input.getTestcase().get(0).getError().size(), output.getTestcase().get(0).getError().size());
		assertEquals(input.getTestcase().get(0).getError().get(0).getType(), output.getTestcase().get(0).getError().get(0).getType());
		assertEquals(input.getTestcase().get(0).getError().get(0).getMessage(), output.getTestcase().get(0).getError().get(0).getMessage());
		assertEquals(input.getTestcase().get(0).getError().get(1).getType(), output.getTestcase().get(0).getError().get(1).getType());
		assertEquals(input.getTestcase().get(0).getError().get(1).getMessage(), output.getTestcase().get(0).getError().get(1).getMessage());
		assertEquals(input.getTestcase().get(0).getFailure().size(), output.getTestcase().get(0).getFailure().size());
		assertEquals(input.getTestcase().get(0).getFailure().get(0).getType(), output.getTestcase().get(0).getFailure().get(0).getType());
		assertEquals(input.getTestcase().get(0).getFailure().get(0).getMessage(), output.getTestcase().get(0).getFailure().get(0).getMessage());
		assertEquals(input.getTestcase().get(0).getFailure().get(1).getType(), output.getTestcase().get(0).getFailure().get(1).getType());
		assertEquals(input.getTestcase().get(0).getFailure().get(1).getMessage(), output.getTestcase().get(0).getFailure().get(1).getMessage());
		assertEquals(input.getTestcase().get(0).getName(), output.getTestcase().get(0).getName());
		assertEquals(input.getTestcase().get(0).getSkipped().getSkipped(), output.getTestcase().get(0).getSkipped().getSkipped());
		assertEquals(input.getTestcase().get(0).getStatus(), output.getTestcase().get(0).getStatus());
		assertEquals(input.getTestcase().get(0).getSystemErr().size(), output.getTestcase().get(0).getSystemErr().size());
		assertEquals(input.getTestcase().get(0).getSystemErr().get(0).getSystemErr(), output.getTestcase().get(0).getSystemErr().get(0).getSystemErr());
		assertEquals(input.getTestcase().get(0).getSystemErr().get(1).getSystemErr(), output.getTestcase().get(0).getSystemErr().get(1).getSystemErr());
		assertEquals(input.getTestcase().get(0).getSystemOut().size(), output.getTestcase().get(0).getSystemOut().size());
		assertEquals(input.getTestcase().get(0).getSystemOut().get(0).getSystemOut(), output.getTestcase().get(0).getSystemOut().get(0).getSystemOut());
		assertEquals(input.getTestcase().get(0).getSystemOut().get(1).getSystemOut(), output.getTestcase().get(0).getSystemOut().get(1).getSystemOut());
		assertEquals(input.getTestcase().get(0).getTime(), output.getTestcase().get(0).getTime(), TOLERANCE);

		assertEquals(input.getTestcase().get(1).getAssertions(), output.getTestcase().get(1).getAssertions());
		assertEquals(input.getTestcase().get(1).getClassname(), output.getTestcase().get(1).getClassname());
		assertEquals(input.getTestcase().get(1).getError().size(), output.getTestcase().get(1).getError().size());
		assertEquals(input.getTestcase().get(1).getError().get(0).getType(), output.getTestcase().get(1).getError().get(0).getType());
		assertEquals(input.getTestcase().get(1).getError().get(0).getMessage(), output.getTestcase().get(1).getError().get(0).getMessage());
		assertEquals(input.getTestcase().get(1).getError().get(1).getType(), output.getTestcase().get(1).getError().get(1).getType());
		assertEquals(input.getTestcase().get(1).getError().get(1).getMessage(), output.getTestcase().get(1).getError().get(1).getMessage());
		assertEquals(input.getTestcase().get(1).getFailure().size(), output.getTestcase().get(1).getFailure().size());
		assertEquals(input.getTestcase().get(1).getFailure().get(0).getType(), output.getTestcase().get(1).getFailure().get(0).getType());
		assertEquals(input.getTestcase().get(1).getFailure().get(0).getMessage(), output.getTestcase().get(1).getFailure().get(0).getMessage());
		assertEquals(input.getTestcase().get(1).getFailure().get(1).getType(), output.getTestcase().get(1).getFailure().get(1).getType());
		assertEquals(input.getTestcase().get(1).getFailure().get(1).getMessage(), output.getTestcase().get(1).getFailure().get(1).getMessage());
		assertEquals(input.getTestcase().get(1).getName(), output.getTestcase().get(1).getName());
		assertEquals(input.getTestcase().get(1).getSkipped().getSkipped(), output.getTestcase().get(1).getSkipped().getSkipped());
		assertEquals(input.getTestcase().get(1).getStatus(), output.getTestcase().get(1).getStatus());
		assertEquals(input.getTestcase().get(1).getSystemErr().size(), output.getTestcase().get(1).getSystemErr().size());
		assertEquals(input.getTestcase().get(1).getSystemErr().get(0).getSystemErr(), output.getTestcase().get(1).getSystemErr().get(0).getSystemErr());
		assertEquals(input.getTestcase().get(1).getSystemErr().get(1).getSystemErr(), output.getTestcase().get(1).getSystemErr().get(1).getSystemErr());
		assertEquals(input.getTestcase().get(1).getSystemOut().size(), output.getTestcase().get(1).getSystemOut().size());
		assertEquals(input.getTestcase().get(1).getSystemOut().get(0).getSystemOut(), output.getTestcase().get(1).getSystemOut().get(0).getSystemOut());
		assertEquals(input.getTestcase().get(1).getSystemOut().get(1).getSystemOut(), output.getTestcase().get(1).getSystemOut().get(1).getSystemOut());
		assertEquals(input.getTestcase().get(1).getTime(), output.getTestcase().get(1).getTime(), TOLERANCE);
	}

	@Test
	public void testTestSuitseEmpty() {
		log.info("testTestSuitesEmpty");
		TestSuites input = new TestSuites();
		String json = JUnitToJson.INSTANCE.getTestSuites(input);
		log.info("String: " + json);
		TestSuites output = JUnitToJson.INSTANCE.getTestSuites(json);
		assertEquals(input.getDisabled(), output.getDisabled());
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTestsuite().size(), output.getTestsuite().size());
	}
	
	@Test
	public void testTestSuitseDisabled() {
		log.info("testTestSuitesDisabled");
		TestSuites input = new TestSuites();
		input.setDisabled(12345);
		String json = JUnitToJson.INSTANCE.getTestSuites(input);
		log.info("String: " + json);
		TestSuites output = JUnitToJson.INSTANCE.getTestSuites(json);
		assertEquals(input.getDisabled(), output.getDisabled());
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTestsuite().size(), output.getTestsuite().size());
	}
	
	@Test
	public void testTestSuitseErrors() {
		log.info("testTestSuitesErrors");
		TestSuites input = new TestSuites();
		input.setErrors(12345);
		String json = JUnitToJson.INSTANCE.getTestSuites(input);
		log.info("String: " + json);
		TestSuites output = JUnitToJson.INSTANCE.getTestSuites(json);
		assertEquals(input.getDisabled(), output.getDisabled());
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTestsuite().size(), output.getTestsuite().size());
	}
	
	@Test
	public void testTestSuitseFailures() {
		log.info("testTestSuitesFailures");
		TestSuites input = new TestSuites();
		input.setFailures(12345);
		String json = JUnitToJson.INSTANCE.getTestSuites(input);
		log.info("String: " + json);
		TestSuites output = JUnitToJson.INSTANCE.getTestSuites(json);
		assertEquals(input.getDisabled(), output.getDisabled());
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTestsuite().size(), output.getTestsuite().size());
	}
	
	@Test
	public void testTestSuitseName() {
		log.info("testTestSuitesName");
		TestSuites input = new TestSuites();
		input.setName("Name");
		String json = JUnitToJson.INSTANCE.getTestSuites(input);
		log.info("String: " + json);
		TestSuites output = JUnitToJson.INSTANCE.getTestSuites(json);
		assertEquals(input.getDisabled(), output.getDisabled());
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTestsuite().size(), output.getTestsuite().size());
	}
	
	@Test
	public void testTestSuitseTests() {
		log.info("testTestSuitesTests");
		TestSuites input = new TestSuites();
		input.setTests(12345);
		String json = JUnitToJson.INSTANCE.getTestSuites(input);
		log.info("String: " + json);
		TestSuites output = JUnitToJson.INSTANCE.getTestSuites(json);
		assertEquals(input.getDisabled(), output.getDisabled());
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTestsuite().size(), output.getTestsuite().size());
	}
	
	@Test
	public void testTestSuitseTime() {
		log.info("testTestSuitesTime");
		TestSuites input = new TestSuites();
		input.setTime(12345.12345);
		String json = JUnitToJson.INSTANCE.getTestSuites(input);
		log.info("String: " + json);
		TestSuites output = JUnitToJson.INSTANCE.getTestSuites(json);
		assertEquals(input.getDisabled(), output.getDisabled());
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTestsuite().size(), output.getTestsuite().size());
	}
	
	@Test
	public void testTestSuitseTestSuite() {
		log.info("testTestSuitesTestSuite");
		TestSuites input = new TestSuites();
		
		TestSuite testsuite = new TestSuite();
		testsuite.setErrors(12345);
		testsuite.setFailures(12345);
		testsuite.setHostname("HostName");
		testsuite.setDisabled( ! testsuite.isDisabled() );
		testsuite.setId("Id");
		testsuite.setName("Name");
		testsuite.setPackage("Package");
		testsuite.setSkipped(12345);
		testsuite.setTests(12345);
		testsuite.setTime(12345.12345);
		testsuite.setTimestamp("TimeStamp");
		testsuite.getProperties().addProperty(new Property("Name 1", "Value 1"));
		testsuite.getProperties().addProperty(new Property("Name 2", "Value 2"));
		testsuite.getSystemErr().setSystemErr("SystemErr");
		testsuite.getSystemOut().setSystemOut("SystemOut");
		TestCase testcase1 = new TestCase();
		testcase1.setAssertions("assertions");
		testcase1.setClassname("Classname");
		testcase1.setName("Name");
		testcase1.setStatus("Status");
		testcase1.setTime(1234.1234);
		testcase1.getSkipped().setSkipped("skipped");
		testcase1.addError(new Error("type1", "message1"));
		testcase1.addError(new Error("type2", "message2"));
		testcase1.addFailure(new Failure("type1", "message1"));
		testcase1.addFailure(new Failure("type2", "message2"));
		testcase1.addSystemErr(new SystemErr("systemErr1"));
		testcase1.addSystemErr(new SystemErr("systemErr2"));
		testcase1.addSystemOut(new SystemOut("SystemOut1"));
		testcase1.addSystemOut(new SystemOut("SystemOut2"));
		testsuite.addTestcase(testcase1);
		TestCase testcase2 = new TestCase();
		testcase2.setAssertions("2assertions");
		testcase2.setClassname("2Classname");
		testcase2.setName("2Name");
		testcase2.setStatus("2Status");
		testcase2.setTime(12345.12345);
		testcase2.getSkipped().setSkipped("2skipped");
		testcase2.addError(new Error("2type1", "message1"));
		testcase2.addError(new Error("2type2", "message2"));
		testcase2.addFailure(new Failure("2type1", "message1"));
		testcase2.addFailure(new Failure("2type2", "message2"));
		testcase2.addSystemErr(new SystemErr("2systemErr1"));
		testcase2.addSystemErr(new SystemErr("2systemErr2"));
		testcase2.addSystemOut(new SystemOut("2SystemOut1"));
		testcase2.addSystemOut(new SystemOut("2SystemOut2"));
		testsuite.addTestcase(testcase2);
		
		input.addTestsuite(testsuite);
		String json = JUnitToJson.INSTANCE.getTestSuites(input);
		log.info("String: " + json);
		TestSuites output = JUnitToJson.INSTANCE.getTestSuites(json);
		assertEquals(input.getDisabled(), output.getDisabled());
		assertEquals(input.getErrors(), output.getErrors());
		assertEquals(input.getFailures(), output.getFailures());
		assertEquals(input.getName(), output.getName());
		assertEquals(input.getTests(), output.getTests());
		assertEquals(input.getTime(), output.getTime(), TOLERANCE);
		assertEquals(input.getTestsuite().size(), output.getTestsuite().size());
		
		assertEquals(input.getTestsuite().get(0).getErrors(), output.getTestsuite().get(0).getErrors());
		assertEquals(input.getTestsuite().get(0).getFailures(), output.getTestsuite().get(0).getFailures());
		assertEquals(input.getTestsuite().get(0).getHostname(), output.getTestsuite().get(0).getHostname());
		assertEquals(input.getTestsuite().get(0).isDisabled(), output.getTestsuite().get(0).isDisabled());
		assertEquals(input.getTestsuite().get(0).getId(), output.getTestsuite().get(0).getId());
		assertEquals(input.getTestsuite().get(0).getName(), output.getTestsuite().get(0).getName());
		assertEquals(input.getTestsuite().get(0).getPackage(), output.getTestsuite().get(0).getPackage());
		assertEquals(input.getTestsuite().get(0).getSkipped(), output.getTestsuite().get(0).getSkipped());
		assertEquals(input.getTestsuite().get(0).getTests(), output.getTestsuite().get(0).getTests());
		assertEquals(input.getTestsuite().get(0).getTime(), output.getTestsuite().get(0).getTime(), TOLERANCE);
		assertEquals(input.getTestsuite().get(0).getTimestamp(), output.getTestsuite().get(0).getTimestamp());
		assertEquals(input.getTestsuite().get(0).getProperties().getProperty().size(), output.getTestsuite().get(0).getProperties().getProperty().size());
		assertEquals(input.getTestsuite().get(0).getProperties().getProperty().get(0).getName(), output.getTestsuite().get(0).getProperties().getProperty().get(0).getName());
		assertEquals(input.getTestsuite().get(0).getProperties().getProperty().get(0).getValue(), output.getTestsuite().get(0).getProperties().getProperty().get(0).getValue());
		assertEquals(input.getTestsuite().get(0).getProperties().getProperty().get(1).getName(), output.getTestsuite().get(0).getProperties().getProperty().get(1).getName());
		assertEquals(input.getTestsuite().get(0).getProperties().getProperty().get(1).getValue(), output.getTestsuite().get(0).getProperties().getProperty().get(1).getValue());
		assertEquals(input.getTestsuite().get(0).getSystemErr().getSystemErr(), output.getTestsuite().get(0).getSystemErr().getSystemErr());
		assertEquals(input.getTestsuite().get(0).getSystemOut().getSystemOut(), output.getTestsuite().get(0).getSystemOut().getSystemOut());
		assertEquals(input.getTestsuite().get(0).getTestcase().size(), output.getTestsuite().get(0).getTestcase().size());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getAssertions(), output.getTestsuite().get(0).getTestcase().get(0).getAssertions());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getClassname(), output.getTestsuite().get(0).getTestcase().get(0).getClassname());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getError().size(), output.getTestsuite().get(0).getTestcase().get(0).getError().size());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getError().get(0).getType(), output.getTestsuite().get(0).getTestcase().get(0).getError().get(0).getType());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getError().get(0).getMessage(), output.getTestsuite().get(0).getTestcase().get(0).getError().get(0).getMessage());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getError().get(1).getType(), output.getTestsuite().get(0).getTestcase().get(0).getError().get(1).getType());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getError().get(1).getMessage(), output.getTestsuite().get(0).getTestcase().get(0).getError().get(1).getMessage());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getFailure().size(), output.getTestsuite().get(0).getTestcase().get(0).getFailure().size());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getFailure().get(0).getType(), output.getTestsuite().get(0).getTestcase().get(0).getFailure().get(0).getType());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getFailure().get(0).getMessage(), output.getTestsuite().get(0).getTestcase().get(0).getFailure().get(0).getMessage());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getFailure().get(1).getType(), output.getTestsuite().get(0).getTestcase().get(0).getFailure().get(1).getType());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getFailure().get(1).getMessage(), output.getTestsuite().get(0).getTestcase().get(0).getFailure().get(1).getMessage());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getName(), output.getTestsuite().get(0).getTestcase().get(0).getName());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getSkipped().getSkipped(), output.getTestsuite().get(0).getTestcase().get(0).getSkipped().getSkipped());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getStatus(), output.getTestsuite().get(0).getTestcase().get(0).getStatus());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getSystemErr().size(), output.getTestsuite().get(0).getTestcase().get(0).getSystemErr().size());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getSystemErr().get(0).getSystemErr(), output.getTestsuite().get(0).getTestcase().get(0).getSystemErr().get(0).getSystemErr());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getSystemErr().get(1).getSystemErr(), output.getTestsuite().get(0).getTestcase().get(0).getSystemErr().get(1).getSystemErr());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getSystemOut().size(), output.getTestsuite().get(0).getTestcase().get(0).getSystemOut().size());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getSystemOut().get(0).getSystemOut(), output.getTestsuite().get(0).getTestcase().get(0).getSystemOut().get(0).getSystemOut());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getSystemOut().get(1).getSystemOut(), output.getTestsuite().get(0).getTestcase().get(0).getSystemOut().get(1).getSystemOut());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(0).getTime(), output.getTestsuite().get(0).getTestcase().get(0).getTime(), TOLERANCE);

		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getAssertions(), output.getTestsuite().get(0).getTestcase().get(1).getAssertions());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getClassname(), output.getTestsuite().get(0).getTestcase().get(1).getClassname());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getError().size(), output.getTestsuite().get(0).getTestcase().get(1).getError().size());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getError().get(0).getType(), output.getTestsuite().get(0).getTestcase().get(1).getError().get(0).getType());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getError().get(0).getMessage(), output.getTestsuite().get(0).getTestcase().get(1).getError().get(0).getMessage());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getError().get(1).getType(), output.getTestsuite().get(0).getTestcase().get(1).getError().get(1).getType());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getError().get(1).getMessage(), output.getTestsuite().get(0).getTestcase().get(1).getError().get(1).getMessage());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getFailure().size(), output.getTestsuite().get(0).getTestcase().get(1).getFailure().size());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getFailure().get(0).getType(), output.getTestsuite().get(0).getTestcase().get(1).getFailure().get(0).getType());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getFailure().get(0).getMessage(), output.getTestsuite().get(0).getTestcase().get(1).getFailure().get(0).getMessage());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getFailure().get(1).getType(), output.getTestsuite().get(0).getTestcase().get(1).getFailure().get(1).getType());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getFailure().get(1).getMessage(), output.getTestsuite().get(0).getTestcase().get(1).getFailure().get(1).getMessage());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getName(), output.getTestsuite().get(0).getTestcase().get(1).getName());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getSkipped().getSkipped(), output.getTestsuite().get(0).getTestcase().get(1).getSkipped().getSkipped());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getStatus(), output.getTestsuite().get(0).getTestcase().get(1).getStatus());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getSystemErr().size(), output.getTestsuite().get(0).getTestcase().get(1).getSystemErr().size());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getSystemErr().get(0).getSystemErr(), output.getTestsuite().get(0).getTestcase().get(1).getSystemErr().get(0).getSystemErr());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getSystemErr().get(1).getSystemErr(), output.getTestsuite().get(0).getTestcase().get(1).getSystemErr().get(1).getSystemErr());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getSystemOut().size(), output.getTestsuite().get(0).getTestcase().get(1).getSystemOut().size());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getSystemOut().get(0).getSystemOut(), output.getTestsuite().get(0).getTestcase().get(1).getSystemOut().get(0).getSystemOut());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getSystemOut().get(1).getSystemOut(), output.getTestsuite().get(0).getTestcase().get(1).getSystemOut().get(1).getSystemOut());
		assertEquals(input.getTestsuite().get(0).getTestcase().get(1).getTime(), output.getTestsuite().get(0).getTestcase().get(1).getTime(), TOLERANCE);
	}
	
}
