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

public class JUnitToJsonTest {
	
	private static Logger log = Logger.getLogger(JUnitToJsonTest.class);
	private JUnitToJson testObject;
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

}
