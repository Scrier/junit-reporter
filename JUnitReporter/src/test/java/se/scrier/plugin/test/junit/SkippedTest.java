package se.scrier.plugin.test.junit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyObject;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SkippedTest {
	
	public static Logger log = Logger.getLogger(SkippedTest.class);
	
	private Document doc;
	private Element element;
	private Element elementReturned;
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}
	
	@Before
	public void setup() {
		doc = Mockito.mock(Document.class);
		element = Mockito.mock(Element.class);
		elementReturned = Mockito.mock(Element.class);
		Mockito.when(doc.createElement(anyString())).thenReturn(elementReturned);
	}
	
	@After
	public void tearDown() {
		doc = null;
		element = null;
	}

	@Test
	public void testConstructor() {
		Skipped testObject = new Skipped();
		assertTrue(testObject.getSkipped().isEmpty());
		assertFalse(testObject.isValuesModified());
		assertFalse(testObject.isValueModified(testObject.SKIPPED_MODIFIED));
	}
	
	@Test
	public void testSetterGetter() {
		Skipped testObject = new Skipped();
		testObject.setSkipped("haha");
		assertEquals("haha", testObject.getSkipped());
		assertTrue(testObject.isValuesModified());
		assertTrue(testObject.isValueModified(testObject.SKIPPED_MODIFIED));
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullParent() {
		Skipped testObject = new Skipped();
		testObject.write(doc, null);
		fail("should not come here");
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullElement() {
		Skipped testObject = new Skipped();
		testObject.write(null, element);
		fail("should not come here");
	}
	
	@Test
	public void testWriteNothingModified() {
		Skipped testObject = new Skipped();
		testObject.write(doc, element);
		verify(doc, times(0)).appendChild((Node) anyObject());
		verify(element, times(0)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteOneModified() {
		Skipped testObject = new Skipped();
		testObject.setSkipped("hoho");
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(1)).appendChild((Element) anyObject());
		testObject.setSkipped("hoho");
	}
	
}
