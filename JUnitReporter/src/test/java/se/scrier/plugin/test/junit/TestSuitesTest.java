package se.scrier.plugin.test.junit;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TestSuitesTest {
	
	private static Logger log = Logger.getLogger(TestSuitesTest.class);
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}
	
	private Document doc;
	private Element element;
	private Element elementReturned;
	private TestSuite suite;
	
	@Before
	public void setup() {
		doc = Mockito.mock(Document.class);
		element = Mockito.mock(Element.class);
		elementReturned = Mockito.mock(Element.class);
		suite = Mockito.mock(TestSuite.class);
		Mockito.when(doc.createElement(anyString())).thenReturn(elementReturned);
	}
	
	@After
	public void tearDown() {
		doc = null;
		element = null;
		elementReturned = null;
		suite = null;
	}
	
	@Test
	public void testConstrcuctor() {
		log.info("testConstrcuctor");
		TestSuites testObject = new TestSuites();
		assertTrue(testObject.getTestsuite().isEmpty());
		assertTrue(testObject.getName().isEmpty());
		assertEquals(0.0, testObject.getTime(), 0.0);
		assertEquals(0, testObject.getTests());
		assertEquals(0, testObject.getFailures());
		assertEquals(0, testObject.getDisabled());
		assertEquals(0, testObject.getErrors());
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteDocNull() throws RequiredAttributeException {
		TestSuites testObject = new TestSuites();
		testObject.write(null, element);
		fail("Should now come here");
	}
	
	@Test
	public void testWriteEmpty() throws RequiredAttributeException {
		TestSuites testObject = new TestSuites();
		testObject.autoComplete();
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(0)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteEmptyDoc() throws RequiredAttributeException {
		TestSuites testObject = new TestSuites();
		testObject.autoComplete();
		testObject.write(doc, null);
		verify(element, times(0)).appendChild((Element) anyObject());
		verify(elementReturned, times(0)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteFull() throws RequiredAttributeException {
		TestSuites testObject = new TestSuites();
		testObject.addTestsuite(suite);
		testObject.setDisabled(2);
		testObject.setErrors(3);
		testObject.setFailures(4);
		testObject.setName("5");
		testObject.setTests(6);
		testObject.setTime(7.7);
		testObject.autoComplete();
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(6)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteFullDoc() throws RequiredAttributeException {
		TestSuites testObject = new TestSuites();
		testObject.addTestsuite(suite);
		testObject.setDisabled(2);
		testObject.setErrors(3);
		testObject.setFailures(4);
		testObject.setName("5");
		testObject.setTests(6);
		testObject.setTime(7.7);
		testObject.autoComplete();
		testObject.write(doc, null);
		verify(element, times(0)).appendChild((Element) anyObject());
		verify(elementReturned, times(6)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testContainsTestSuite() {
		TestSuites testObject = new TestSuites();
		TestSuite a = new TestSuite("a");
		TestSuite b = new TestSuite("b");
		testObject.addTestsuite(a);
		testObject.addTestsuite(b);
		assertTrue(testObject.containsTestsuite("a"));
		assertTrue(testObject.containsTestsuite("b"));
		assertFalse(testObject.containsTestsuite("c"));
	}
	
	@Test
	public void testContainsTestSuiteObj() {
		TestSuites testObject = new TestSuites();
		TestSuite a = new TestSuite("a");
		TestSuite b = new TestSuite("b");
		TestSuite c = new TestSuite("c");
		testObject.addTestsuite(a);
		testObject.addTestsuite(b);
		assertTrue(testObject.containsTestsuite(a));
		assertTrue(testObject.containsTestsuite(b));
		assertFalse(testObject.containsTestsuite(c));
	}
	
	@Test
	public void testCompareTestSuite() {
		TestSuites testObject = new TestSuites();
		TestSuite a = new TestSuite("a");
		testObject.addTestsuite(a);
		TestSuite aRef = testObject.getTestsuite("a");
		assertEquals(a, aRef);
	}
	
	@Test
	public void testCompareTestSuiteNull() {
		TestSuites testObject = new TestSuites();
		TestSuite a = new TestSuite("a");
		testObject.addTestsuite(a);
		TestSuite b = testObject.getTestsuite("b");
		assertNull(b);
	}
	
	@Test
	public void testXmlElement() {
		TestSuites testObject = new TestSuites();
		testObject.setValuesModified(testObject.NAME_MODIFIED);
		assertTrue(testObject.NAME_MODIFIED == testObject.getValuesModified());
	}
	
}
