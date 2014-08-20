package se.scrier.plugin.test.junit;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
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

public class TestCaseTest {
	
	private static Logger log = Logger.getLogger(TestCaseTest.class);
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}
	
	private Error error;
	private Failure failure;
	private SystemErr systemErr;
	private SystemOut systemOut;
	private Document doc;
	private Element element;
	private Element elementReturned;
	
	@Before
	public void setup() {
		error = Mockito.mock(Error.class);
		failure = Mockito.mock(Failure.class);
		systemErr = Mockito.mock(SystemErr.class);
		systemOut = Mockito.mock(SystemOut.class);
		doc = Mockito.mock(Document.class);
		element = Mockito.mock(Element.class);
		elementReturned = Mockito.mock(Element.class);
		Mockito.when(doc.createElement(anyString())).thenReturn(elementReturned);
	}
	
	@After
	public void tearDown() {
		error = null;
		failure = null;
		systemErr = null;
		systemOut = null;
		doc = null;
		element = null;
		elementReturned = null;
	}
	
	@Test
	public void testConstructor() {
		TestCase testObject = new TestCase();
		assertNotNull(testObject.getSkipped());
		assertTrue(testObject.getError().isEmpty());
		assertTrue(testObject.getFailure().isEmpty());
		assertTrue(testObject.getSystemErr().isEmpty());
		assertTrue(testObject.getSystemOut().isEmpty());
		assertTrue(testObject.getName().isEmpty());
		assertTrue(testObject.getAssertions().isEmpty());
		assertEquals(0.0, testObject.getTime(), 0.0);
		assertTrue(testObject.getClassname().isEmpty());
		assertTrue(testObject.getStatus().isEmpty());
		assertFalse(testObject.isValuesModified());
	}
	
	@Test
	public void testConstructor2() {
		TestCase testObject = new TestCase("Name");
		assertNotNull(testObject.getSkipped());
		assertTrue(testObject.getError().isEmpty());
		assertTrue(testObject.getFailure().isEmpty());
		assertTrue(testObject.getSystemErr().isEmpty());
		assertTrue(testObject.getSystemOut().isEmpty());
		assertFalse(testObject.getName().isEmpty());
		assertTrue(testObject.getAssertions().isEmpty());
		assertEquals(0.0, testObject.getTime(), 0.0);
		assertTrue(testObject.getClassname().isEmpty());
		assertTrue(testObject.getStatus().isEmpty());
		assertTrue(testObject.isValuesModified());
	}
	
	@Test(expected=RequiredAttributeException.class)
	public void testWriteNameNotModified() throws RequiredAttributeException {
		TestCase testObject = new TestCase();
		testObject.write(doc, element);
		fail("Should now come here");
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteParentNull() throws RequiredAttributeException {
		TestCase testObject = new TestCase("name");
		testObject.write(doc, null);
		fail("Should now come here");
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteDocNull() throws RequiredAttributeException {
		TestCase testObject = new TestCase("name");
		testObject.write(null, element);
		fail("Should now come here");
	}
	
	@Test
	public void testWriteEmpty() throws RequiredAttributeException {
		TestCase testObject = new TestCase("name");
		testObject.autoComplete();
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(1)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteFull() throws RequiredAttributeException {
		TestCase testObject = new TestCase("name");
		testObject.setAssertions("asertion");
		testObject.setClassname("classname");
		testObject.setStatus("status");
		testObject.setTime(1234.5678);
		testObject.addError(error);
		testObject.addFailure(failure);
		testObject.addSystemErr(systemErr);
		testObject.addSystemOut(systemOut);
		testObject.autoComplete();
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(5)).setAttribute(anyString(), anyString());
		testObject.setAssertions("asertion");
		testObject.setClassname("classname");
		testObject.setStatus("status");
		testObject.setTime(1234.5678);
	}
	
	@Test
	public void testWriteToString() throws RequiredAttributeException {
		TestCase testObject = new TestCase("name");
		testObject.setAssertions("asertion");
		testObject.setClassname("classname");
		testObject.setStatus("status");
		testObject.setTime(1234.5678);
		testObject.addError(error);
		testObject.addFailure(failure);
		testObject.addSystemErr(systemErr);
		testObject.addSystemOut(systemOut);
		testObject.autoComplete();
		String check = testObject.toString();
		assertTrue(check.contains("name"));
		assertTrue(check.contains("asertion"));
		assertTrue(check.contains("classname"));
		assertTrue(check.contains("status"));
	}
	
	@Test
	public void testWriteToString2() throws RequiredAttributeException {
		TestCase testObject = new TestCase();
		String check = testObject.toString();
		testObject.addSystemOut(systemOut);
		check = testObject.toString();
		assertTrue(check.contains("system-out"));
		testObject.addSystemErr(systemErr);
		check = testObject.toString();
		assertTrue(check.contains("system-err"));
		testObject.addFailure(failure);
		check = testObject.toString();
		assertTrue(check.contains("failure"));
		testObject.addError(error);
		check = testObject.toString();
		assertTrue(check.contains("error"));
		testObject.setStatus("status");
		check = testObject.toString();
		assertTrue(check.contains("status"));
		testObject.setClassname("classname");
		check = testObject.toString();
		assertTrue(check.contains("classname"));
		testObject.setTime(1234.5667);
		check = testObject.toString();
		assertTrue(check.contains("time"));
		testObject.setAssertions("assertions");
		check = testObject.toString();
		assertTrue(check.contains("assertions"));
		testObject.setName("name");
		check = testObject.toString();
		assertTrue(check.contains("name"));
	}

	@Test
	public void testSortMethod() {
		ArrayList<TestCase> list = new ArrayList<TestCase>();
		for( int i = 0; i < 10; i++ ) {
			list.add(new TestCase(String.valueOf(i)));
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
	
	public void randomizeTimeNumbers(ArrayList<TestCase> list, long numbers) {
		Random rand = new Random(numbers);
		for( TestCase test : list ) {
			test.setTime(rand.nextDouble() * 20);
		}
	}
	
	public void printArray(ArrayList<TestCase> list) {
		for( TestCase test : list ) {
			log.info(test);
		}
	}

}
