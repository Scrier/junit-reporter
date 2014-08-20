package se.scrier.plugin.test.junit;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TestSuiteTest {
	
	private static Logger log = Logger.getLogger(TestSuiteTest.class);
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}
	
	private TestCase testCase;
	private Document doc;
	private Element element;
	private Element elementReturned;
	private Properties properties;
	private SystemErr system_err;
	private SystemOut system_out;
	
	@Before
	public void setup() {
		testCase = Mockito.mock(TestCase.class);
		doc = Mockito.mock(Document.class);
		element = Mockito.mock(Element.class);
		elementReturned = Mockito.mock(Element.class);
		properties = Mockito.mock(Properties.class);
		system_err = Mockito.mock(SystemErr.class);
		system_out = Mockito.mock(SystemOut.class);
		Mockito.when(doc.createElement(anyString())).thenReturn(elementReturned);
	}
	
	@After
	public void tearDown() {
		testCase = null;
		doc = null;
		element = null;
		elementReturned = null;
		properties = null;
		system_err = null;
		system_out = null;
	}
	
	@Test
	public void testConstructor() {
		TestSuite testObject = new TestSuite();
		assertEquals(-1, testObject.getErrors());
		assertEquals(-1, testObject.getFailures());
		assertTrue(testObject.getHostname().isEmpty());
		assertTrue(testObject.getId().isEmpty());
		assertTrue(testObject.getName().isEmpty());
		assertTrue(testObject.getPackage().isEmpty());
		assertNotNull(testObject.getProperties());
		assertEquals(-1, testObject.getSkipped());
		assertNotNull(testObject.getSystemErr());
		assertNotNull(testObject.getSystemOut());
		assertTrue(testObject.getTestcase().isEmpty());
		assertEquals(-1, testObject.getTests());
		assertEquals(0.0, testObject.getTime(), 0.0);
		assertTrue(testObject.getTimestamp().isEmpty());
		assertFalse(testObject.isValuesModified());
	}
	
	@Test
	public void testConstructor2() {
		TestSuite testObject = new TestSuite("suite name");
		assertEquals(-1, testObject.getErrors());
		assertEquals(-1, testObject.getFailures());
		assertTrue(testObject.getHostname().isEmpty());
		assertTrue(testObject.getId().isEmpty());
		assertFalse(testObject.getName().isEmpty());
		assertTrue(testObject.getPackage().isEmpty());
		assertNotNull(testObject.getProperties());
		assertEquals(-1, testObject.getSkipped());
		assertNotNull(testObject.getSystemErr());
		assertNotNull(testObject.getSystemOut());
		assertTrue(testObject.getTestcase().isEmpty());
		assertEquals(-1, testObject.getTests());
		assertEquals(0.0, testObject.getTime(), 0.0);
		assertTrue(testObject.getTimestamp().isEmpty());
		assertTrue(testObject.isValuesModified());
	}
	
	@Test(expected=RequiredAttributeException.class)
	public void testWriteNameNotModified() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.write(doc, element);
		fail("Should now come here");
	}
	
	@Test(expected=RequiredAttributeException.class)
	public void testWriteNameTestNotModified() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite("name");
		testObject.write(doc, element);
		fail("Should now come here");
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteDocNull() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite("name");
		testObject.addTestcase(testCase);
		testObject.write(null, element);
		fail("Should now come here");
	}
	
	@Test
	public void testWriteEmpty() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite("name");
		testObject.autoComplete();
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(5)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteEmptyDoc() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite("name");
		testObject.autoComplete();
		testObject.write(doc, null);
		verify(element, times(0)).appendChild((Element) anyObject());
		verify(elementReturned, times(5)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteFull() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite("name");
		testObject.setDisabled(true);
		testObject.setErrors(123);
		testObject.setFailures(321);
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setName("name");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTests(234);
		testObject.setTime(1234.1234);
		testObject.setTimestamp("timestamp");
		
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(11)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteFullDoc() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite("name");
		testObject.setDisabled(true);
		testObject.setErrors(123);
		testObject.setFailures(321);
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setName("name");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTests(234);
		testObject.setTime(1234.1234);
		testObject.setTimestamp("timestamp");
		
		testObject.write(doc, null);
		verify(element, times(0)).appendChild((Element) anyObject());
		verify(elementReturned, times(11)).setAttribute(anyString(), anyString());
		testObject.setDisabled(true);
		testObject.setErrors(123);
		testObject.setFailures(321);
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setName("name");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTests(234);
		testObject.setTime(1234.1234);
		testObject.setTimestamp("timestamp");
	}
	
	@Test
	public void testAutoCompleteTests() {
		TestSuite testObject = new TestSuite("name");
		Skipped skipped = Mockito.mock(Skipped.class);
		Mockito.when(skipped.getSkipped()).thenReturn("");
		Mockito.when(testCase.getSkipped()).thenReturn(skipped);
		testObject.addTestcase(testCase);
		testObject.autoComplete();
		verify(testCase, times(1)).autoComplete();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAutoCompleteSkipped() {
		TestSuite testObject = new TestSuite("name");
		Skipped skipped = Mockito.mock(Skipped.class);
		List<Error> errorList = Mockito.mock(List.class);
		Mockito.when(errorList.size()).thenReturn(2);
		List<Failure> failureList = Mockito.mock(List.class);
		Mockito.when(failureList.size()).thenReturn(3);
		Mockito.when(testCase.getError()).thenReturn(errorList);
		Mockito.when(testCase.getFailure()).thenReturn(failureList);
		Mockito.when(skipped.getSkipped()).thenReturn("something");
		Mockito.when(testCase.getSkipped()).thenReturn(skipped);
		testObject.addTestcase(testCase);
		testObject.autoComplete();
		verify(testCase, times(1)).autoComplete();
	}
	
	@Test
	public void testToString() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite("name");
		testObject.setDisabled(true);
		testObject.setErrors(123);
		testObject.setFailures(321);
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTests(234);
		testObject.setTime(1234.1234);
		testObject.setTimestamp("timestamp");
		
		String check = testObject.toString();
		assertTrue(check.contains("timestamp"));
		assertTrue(check.contains("1234.1234") || check.contains("1234,1234"));
		assertTrue(check.contains("234"));
		assertTrue(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("id"));
		assertTrue(check.contains("hostname"));
		assertTrue(check.contains("321"));
		assertTrue(check.contains("123"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("true"));
	}
	
	@Test
	public void testToStringName() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setDisabled(true);
		testObject.setErrors(123);
		testObject.setFailures(321);
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTests(234);
		testObject.setTime(1234.1234);
		testObject.setTimestamp("timestamp");
		
		String check = testObject.toString();
		assertTrue(check.contains("timestamp"));
		assertTrue(check.contains("1234.1234") || check.contains("1234,1234"));
		assertTrue(check.contains("234"));
		assertTrue(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("id"));
		assertTrue(check.contains("hostname"));
		assertTrue(check.contains("321"));
		assertTrue(check.contains("123"));
		assertTrue(check.contains("true"));
	}
	
	@Test
	public void testToStringTests() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setDisabled(true);
		testObject.setErrors(123);
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTime(1234.1234);
		testObject.setTimestamp("timestamp");
		
		String check = testObject.toString();
		assertTrue(check.contains("timestamp"));
		assertTrue(check.contains("1234.1234") || check.contains("1234,1234"));
		assertTrue(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("id"));
		assertTrue(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertTrue(check.contains("123"));
		assertTrue(check.contains("true"));
	}
	
	@Test
	public void testToStringFailures() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setDisabled(true);
		testObject.setErrors(123);
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setFailures(222);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTime(1234.1234);
		testObject.setTimestamp("timestamp");
		String check = testObject.toString();
		assertTrue(check.contains("timestamp"));
		assertTrue(check.contains("1234.1234") || check.contains("1234,1234"));
		assertTrue(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("id"));
		assertTrue(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertTrue(check.contains("123"));
		assertTrue(check.contains("true"));
	}
	
	@Test
	public void testToStringErrors() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setDisabled(true);
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTime(1234.1234);
		testObject.setTimestamp("timestamp");
		
		String check = testObject.toString();
		assertTrue(check.contains("timestamp"));
		assertTrue(check.contains("1234.1234") || check.contains("1234,1234"));
		assertTrue(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("id"));
		assertTrue(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertTrue(check.contains("true"));
	}
	
	@Test
	public void testToStringTime() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setDisabled(true);
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTimestamp("timestamp");
		
		String check = testObject.toString();
		assertTrue(check.contains("timestamp"));
		assertFalse(check.contains("1234.1234") || check.contains("1234,1234"));
		assertTrue(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("id"));
		assertTrue(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertFalse(check.contains("123"));
		assertTrue(check.contains("true"));
	}
	
	@Test
	public void testToStringDisabled() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSkipped(432);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTimestamp("timestamp");
		
		String check = testObject.toString();
		assertTrue(check.contains("timestamp"));
		assertFalse(check.contains("1234.1234") || check.contains("1234,1234"));
		assertTrue(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("id"));
		assertTrue(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertFalse(check.contains("123"));
		assertFalse(check.contains("true"));
	}
	
	@Test
	public void testToStringSkipped() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		testObject.setTimestamp("timestamp");
		
		String check = testObject.toString();
		assertTrue(check.contains("timestamp"));
		assertFalse(check.contains("1234.1234") || check.contains("1234,1234"));
		assertFalse(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("id"));
		assertTrue(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertFalse(check.contains("123"));
		assertFalse(check.contains("true"));
	}
	
	@Test
	public void testToStringTimeStamp() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setHostname("hostname");
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		
		String check = testObject.toString();
		assertFalse(check.contains("timestamp"));
		assertFalse(check.contains("1234.1234") || check.contains("1234,1234"));
		assertFalse(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("name"));
		assertTrue(check.contains("id"));
		assertTrue(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertFalse(check.contains("123"));
		assertFalse(check.contains("true"));
	}
	
	@Test
	public void testToStringHostName() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setId("id");
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		
		String check = testObject.toString();
		assertFalse(check.contains("timestamp"));
		assertFalse(check.contains("1234.1234") || check.contains("1234,1234"));
		assertFalse(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertTrue(check.contains("id"));
		assertFalse(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertFalse(check.contains("123"));
		assertFalse(check.contains("true"));
	}
	
	@Test
	public void testToStringID() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setPackage("_package");
		testObject.setProperties(properties);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		
		String check = testObject.toString();
		assertFalse(check.contains("timestamp"));
		assertFalse(check.contains("1234.1234") || check.contains("1234,1234"));
		assertFalse(check.contains("432"));
		assertTrue(check.contains("_package"));
		assertFalse(check.contains("id"));
		assertFalse(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertFalse(check.contains("123"));
		assertFalse(check.contains("true"));
	}
	
	@Test
	public void testToStringPackage() throws RequiredAttributeException {
		TestSuite testObject = new TestSuite();
		testObject.setProperties(properties);
		testObject.setSystemErr(system_err);
		testObject.setSystemOut(system_out);
		
		String check = testObject.toString();
		assertFalse(check.contains("timestamp"));
		assertFalse(check.contains("1234.1234") || check.contains("1234,1234"));
		assertFalse(check.contains("432"));
		assertFalse(check.contains("_package"));
		assertFalse(check.contains("id"));
		assertFalse(check.contains("hostname"));
		assertFalse(check.contains("321"));
		assertFalse(check.contains("123"));
		assertFalse(check.contains("true"));
	}

	@Test
	public void testSortMethod() {
		ArrayList<TestSuite> list = new ArrayList<TestSuite>();
		for( int i = 0; i < 10; i++ ) {
			list.add(new TestSuite(String.valueOf(i)));
		}
		randomizeTimeNumbers(list, 20);
		printArray(list);
		Collections.sort(list);
		for ( int i = 1; i < list.size(); i++ ) {
			log.info("Checking that " + list.get(i - 1).getTime() + " <= " + list.get(i).getTime());
			assertTrue(list.get(i - 1).getTime() <= list.get(i).getTime());
		}
		printArray(list);
	}
	
	@Test
	public void testPrintout() {
		double check = new Random(1245).nextDouble() * 1000;
		log.info("Test format 1 " + check + " to " + String.format("%04.4f", check));
		log.info("Test format 2 " + check + " to " + String.format("%09.4f", check));
		log.info("Test format 3 " + check + " to " + String.format("%9.4f", check));
		log.info("Test format 4 " + check + " to " + String.format("%.4f", check));
	}
	
	public void randomizeTimeNumbers(ArrayList<TestSuite> list, long numbers) {
		Random rand = new Random(numbers);
		for( TestSuite test : list ) {
			test.setTime(rand.nextDouble() * 20);
		}
	}
	
	public void printArray(ArrayList<TestSuite> list) {
		for( TestSuite test : list ) {
			log.info(test);
		}
	}

}
