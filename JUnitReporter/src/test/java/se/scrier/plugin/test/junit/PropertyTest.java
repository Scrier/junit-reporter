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

public class PropertyTest {
	
	public static Logger log = Logger.getLogger(PropertyTest.class);
	
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
		Property testObject = new Property();
		assertTrue(testObject.getValue().isEmpty());
		assertTrue(testObject.getName().isEmpty());
		assertFalse(testObject.isValuesModified());
		assertFalse(testObject.isValueModified(testObject.VALUE_MODIFIED));
		assertFalse(testObject.isValueModified(testObject.NAME_MODIFIED));
	}
	
	@Test
	public void testSetterGetter() {
		Property testObject = new Property();
		testObject.setValue("haha");
		testObject.setName("hoho");
		assertEquals("haha", testObject.getValue());
		assertEquals("hoho", testObject.getName());
		assertTrue(testObject.isValuesModified());
		assertTrue(testObject.isValueModified(testObject.VALUE_MODIFIED));
		assertTrue(testObject.isValueModified(testObject.NAME_MODIFIED));
		testObject.setValue("haha");
		testObject.setName("hoho");
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullParent() throws RequiredAttributeException {
		Property testObject = new Property();
		testObject.write(doc, null);
		fail("should not come here");
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullElement() throws RequiredAttributeException {
		Property testObject = new Property();
		testObject.write(null, element);
		fail("should not come here");
	}
	
	@Test
	public void testWriteOneModified() throws RequiredAttributeException {
		Property testObject = new Property();
		testObject.setValue("hoho");
		testObject.setName("hehe");
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(2)).setAttribute(anyString(), anyString());
	}
	
	@Test(expected=RequiredAttributeException.class)
	public void testWriteNameNotSet() throws RequiredAttributeException {
		Property testObject = new Property();
		testObject.setValue("hoho");
		testObject.write(doc, element);
		fail("Should not come here");
	}
	
	@Test(expected=RequiredAttributeException.class)
	public void testWriteValueNotSet() throws RequiredAttributeException {
		Property testObject = new Property();
		testObject.setName("hoho");
		testObject.write(doc, element);
		fail("Should not come here");
	}

}
