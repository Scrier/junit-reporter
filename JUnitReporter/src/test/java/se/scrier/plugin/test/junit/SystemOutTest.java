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

public class SystemOutTest {
	
	public static Logger log = Logger.getLogger(SystemOutTest.class);
	
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
		SystemOut testObject = new SystemOut();
		assertTrue(testObject.getSystemOut().isEmpty());
		assertFalse(testObject.isValuesModified());
		assertFalse(testObject.isValueModified(testObject.SYSTEM_OUT_MODIFIED));
	}
	
	@Test
	public void testConstructor2() {
		SystemOut testObject = new SystemOut("haha");
		testObject.setSystemOut("haha");
		assertEquals("haha", testObject.getSystemOut());
		assertTrue(testObject.isValuesModified());
		assertTrue(testObject.isValueModified(testObject.SYSTEM_OUT_MODIFIED));
		testObject.setSystemOut("haha");
	}
	
	@Test
	public void testSetterGetter() {
		SystemOut testObject = new SystemOut();
		testObject.setSystemOut("haha");
		assertEquals("haha", testObject.getSystemOut());
		assertTrue(testObject.isValuesModified());
		assertTrue(testObject.isValueModified(testObject.SYSTEM_OUT_MODIFIED));
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullParent() {
		SystemOut testObject = new SystemOut();
		testObject.write(doc, null);
		fail("should not come here");
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullElement() {
		SystemOut testObject = new SystemOut();
		testObject.write(null, element);
		fail("should not come here");
	}
	
	@Test
	public void testWriteNothingModified() {
		SystemOut testObject = new SystemOut();
		testObject.write(doc, element);
		verify(doc, times(0)).appendChild((Node) anyObject());
		verify(element, times(0)).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void testWriteOneModified() {
		SystemOut testObject = new SystemOut();
		testObject.setSystemOut("hoho");
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(1)).appendChild((Element) anyObject());
	}
	
}
