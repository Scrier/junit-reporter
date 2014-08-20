package se.scrier.plugin.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.scrier.plugin.test.JUnitToXml;
import se.scrier.plugin.test.junit.RequiredAttributeException;
import se.scrier.plugin.test.junit.TestCase;
import se.scrier.plugin.test.junit.TestSuite;
import se.scrier.plugin.test.junit.TestSuites;


public class JUnitToXmlTest {
	
	private static Logger log = Logger.getLogger(JUnitToXmlTest.class);
	private JUnitToXml testObject;
	private File file;
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}
	
	@Before
	public void setup() throws IOException {
		testObject = new JUnitToXml();
		if( testObject.getJUnitFile().exists() ) {
			testObject.getJUnitFile().delete();
		}
		file = File.createTempFile("hah", "hohoh");
	}
	
	@After
	public void tearDown() {
		testObject = null;
		if( file.exists() ) {
			file.delete();
		}
	}
	
	@Test
	public void testDefaultConstructor() throws IOException {
		log.info("testDefaultConstructor");
		assertFalse(testObject.getJUnitFile().exists());
	}

	@Test
	public void testInit() throws IOException {
		log.info("testInit");
		testObject.init();
		assertTrue(testObject.getPathOnly(testObject.getJUnitFile()).exists());
	}
	
	@Test
	public void testCreateFile() throws IOException, TransformerException, ParserConfigurationException, RequiredAttributeException {
		log.info("testCreateFile");
		testObject.init();
		TestSuite suite = new TestSuite();
		suite.setName("TestRun Haha");
		for( int i = 0; i < 20; i++ ) {
			TestCase t = new TestCase();
			t.setName(String.valueOf(i));
			t.setTime((int)(new Date().getTime()));
			suite.addTestcase(t);
		}
		testObject.writeToFile(suite);
		String strippedPath = testObject.getJUnitFile().getAbsolutePath();
		strippedPath = strippedPath.substring(0, strippedPath.lastIndexOf(File.separator));
		assertTrue(new File(testObject.getPathOnly(testObject.getJUnitFile()) + "/" + suite.getName() + ".xml").exists());
	}
	
	@Test
	public void testCreateFileFromSuites() throws IOException, TransformerException, ParserConfigurationException, RequiredAttributeException {
		log.info("testCreateFile");
		testObject = null;
		testObject = new JUnitToXml(file);
		if( file.exists() ) {
			file.delete();
		}
		testObject.init();
		TestSuites suites = new TestSuites();
		TestSuite suite = new TestSuite();
		suite.setName("TestRun Haha");
		for( int i = 0; i < 20; i++ ) {
			TestCase t = new TestCase();
			t.setName(String.valueOf(i));
			t.setTime((int)(new Date().getTime()));
			suite.addTestcase(t);
		}
		suites.addTestsuite(suite);
		testObject.writeToFile(suites);
		assertTrue(file.exists());
	}
	
	@Test
	public void pathDoesNotExist() throws IOException {
		JUnitToXml test = new JUnitToXml(new File("/unknownpath/deluxe"));
		test.init();
		assertFalse(test.getJUnitFile().exists());
	}

}
