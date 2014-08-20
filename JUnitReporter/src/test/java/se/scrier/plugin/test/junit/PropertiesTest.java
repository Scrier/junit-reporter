package se.scrier.plugin.test.junit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyObject;

import java.util.ArrayList;

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

public class PropertiesTest {
	
	public static Logger log = Logger.getLogger(PropertiesTest.class);
	
	private Document doc;
	private Element element;
	private Element elementReturned;
	private Property property;
	
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
		property = new Property();
		property.setName("asdasd");
		property.setValue("asdfsdg");
	}
	
	@After
	public void tearDown() {
		doc = null;
		element = null;
	}

	@Test
	public void testConstructor() {
		Properties testObject = new Properties();
		assertTrue(testObject.getProperty().isEmpty());
		assertFalse(testObject.isValuesModified());
	}
	
	@Test
	public void testSetterGetter() {
		Properties testObject = new Properties();
		testObject.setProperty(new ArrayList<Property>());
		testObject.addProperty(property);
		assertFalse(testObject.getProperty().isEmpty());
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullParent() throws RequiredAttributeException {
		Properties testObject = new Properties();
		testObject.write(doc, null);
		fail("should not come here");
	}
	
	@Test(expected=NullPointerException.class)
	public void testWriteNullElement() throws RequiredAttributeException {
		Properties testObject = new Properties();
		testObject.write(null, element);
		fail("should not come here");
	}
	
	@Test
	public void testWriteNothingModified() throws RequiredAttributeException {
		Properties testObject = new Properties();
		testObject.write(doc, element);
		verify(doc, times(0)).appendChild((Node) anyObject());
		verify(element, times(0)).setAttribute(anyString(), anyString());
		testObject.autoComplete();
	}
	
	@Test
	public void testWriteOneModified() throws RequiredAttributeException {
		Properties testObject = new Properties();
		testObject.addProperty(property);
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(2)).setAttribute(anyString(), anyString());
		testObject.autoComplete();
	}
	
	@Test
	public void testWriteTwoModified() throws RequiredAttributeException {
		Properties testObject = new Properties();
		testObject.addProperty(property);
		testObject.addProperty(property);
		testObject.write(doc, element);
		verify(element, times(1)).appendChild((Element) anyObject());
		verify(elementReturned, times(4)).setAttribute(anyString(), anyString());
	}

}
