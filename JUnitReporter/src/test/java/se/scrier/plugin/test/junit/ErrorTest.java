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

public class ErrorTest {
	
	public static Logger log = Logger.getLogger(ErrorTest.class);
	
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
		Error testObject = new Error();
		assertTrue(testObject.getMessage().isEmpty());
		assertTrue(testObject.getType().isEmpty());
		assertFalse(testObject.isValuesModified());
		assertFalse(testObject.isValueModified(testObject.MESSAGE_MODIFIED));
		assertFalse(testObject.isValueModified(testObject.TYPE_MODIFIED));
	}
	
	@Test
	public void testConstructor2() {
		Error testObject = new Error("haha", "hoho");
		testObject.setMessage("haha");
		testObject.setType("hoho");
		assertEquals("haha", testObject.getMessage());
		assertEquals("hoho", testObject.getType());
		assertTrue(testObject.isValuesModified());
		assertTrue(testObject.isValueModified(testObject.MESSAGE_MODIFIED));
		assertTrue(testObject.isValueModified(testObject.TYPE_MODIFIED));
		testObject.setMessage("haha");
		testObject.setType("hoho");
	}
	
	@Test
	public void testSetterGetter() {
		Error testObject = new Error();
		testObject.setMessage("haha");
		testObject.setType("hoho");
		assertEquals("haha", testObject.getMessage());
		assertEquals("hoho", testObject.getType());
		assertTrue(testObject.isValuesModified());
		assertTrue(testObject.isValueModified(testObject.MESSAGE_MODIFIED));
		assertTrue(testObject.isValueModified(testObject.TYPE_MODIFIED));
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullParent() {
		Error testObject = new Error();
		testObject.write(doc, null);
		fail("should not come here");
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullElement() {
		Error testObject = new Error();
		testObject.write(null, element);
		fail("should not come here");
	}
	
	@Test
	public void testWriteNothingModified() {
		Error testObject = new Error();
		testObject.write(doc, element);
		verify(doc, times(0)).appendChild((Node) anyObject());
		verify(element, times(0)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteOneModified() {
		Error testObject = new Error();
		testObject.setMessage("hoho");
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(1)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteTwoModified() {
		Error testObject = new Error();
		testObject.setMessage("hoho");
		testObject.setType("haha");
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(2)).setAttribute(anyString(), anyString());
	}

}
