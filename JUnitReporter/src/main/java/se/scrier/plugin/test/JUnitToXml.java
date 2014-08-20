package se.scrier.plugin.test;

import java.io.File;
import java.io.IOException;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import se.scrier.plugin.test.junit.RequiredAttributeException;
import se.scrier.plugin.test.junit.TestSuite;
import se.scrier.plugin.test.junit.TestSuites;

/**
 * Class to convert JUnit objects to xml.
 * @author Andreas Joelsson
 */
public class JUnitToXml {
	
	private static Logger log = Logger.getLogger(JUnitToXml.class);
	
	private File jUnitFile;

	/**
	 * Constructor
	 */
	public JUnitToXml() {
		log.trace("JUnitToXml()");
		setJUnitFile(new File("/tmp/multilog/multigurkan.xml"));
	}
	
	/**
	 * Constructor
	 * @param file File
	 */
	public JUnitToXml(File file) {
		log.trace("JUnitToXml(" + file + ")");
		setJUnitFile(file);
	}
	
	/**
	 * init method for the JUnitToXml class.
	 * @throws IOException
	 */
	public void init() throws IOException {
		log.trace("init()");
		File stripped = getPathOnly(getJUnitFile());
		if( stripped.exists() ) {
			String[]entries = stripped.list();
			log.info("Number of entries: " + entries.length);
			for(String s: entries){
			    File currentFile = new File(stripped.getPath(), s);
			    log.info("Deleting file: " + currentFile);
			    currentFile.delete();
			}
		} else {
			log.info("Creating file for first time returned : " + stripped.mkdirs());
		}
	}
	
	/**
	 * Method to write a TestSuite object to file.
	 * @param suite TestSuite to write
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 * @throws XMLParseException
	 */
	public void writeToFile(TestSuite suite) throws TransformerException, ParserConfigurationException, RequiredAttributeException {
		log.trace("writeToFile(" + suite + ")");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		suite.write(doc, null);
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		String strippedPath = getJUnitFile().getAbsolutePath();
		strippedPath = strippedPath.substring(0, strippedPath.lastIndexOf(File.separator));
		File currentFile = new File(strippedPath + "/" + suite.getName() + ".xml");
		log.info("Current file is: " + currentFile + " where i connnected path: " + getJUnitFile().getAbsolutePath().substring(0, getJUnitFile().getAbsolutePath().lastIndexOf(File.separator)) + ", name: " + suite.getName() + ", extension: .xml");
		StreamResult result = new StreamResult(currentFile);
		
		transformer.transform(source, result);
	}
	
	/**
	 * Method to write a TestSuites object to file.
	 * @param suites TestSuites to write
	 * @throws Exception
	 */
	public void writeToFile(TestSuites suites) throws TransformerException, ParserConfigurationException, RequiredAttributeException {
		log.trace("writeToFile(" + suites + ")");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		suites.write(doc, null);
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(getJUnitFile());
		
		transformer.transform(source, result);
	}
	
	/**
	 * @return the jUnitFile
	 */
	public File getJUnitFile() {
		return jUnitFile;
	}

	/**
	 * @param jUnitFile the jUnitFile to set
	 */
	public void setJUnitFile(File jUnitFile) {
		this.jUnitFile = jUnitFile;
	}
	
	/**
	 * Method to extract path from a filename.
	 * @param filename File to extract path from.
	 * @return File with the path only.
	 */
	public File getPathOnly(File filename) {
		String strippedPath = filename.getAbsolutePath();
		strippedPath = strippedPath.substring(0, strippedPath.lastIndexOf(File.separator));
		return new File(strippedPath);
	}

}
