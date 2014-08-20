package se.scrier.plugin.test.junit;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <xs:element name="skipped" type="xs:string"/>
 */
public class Skipped extends XmlElement {
	
	private static Logger log = Logger.getLogger(Skipped.class);
	
	// Text
	private String skipped;
	
	// Strings
	private final String ELEMENT = "skipped";
	
	// Modified
	protected final long SKIPPED_MODIFIED = 0x0000000000000001L;
	
	/**
	 * Constructor
	 */
	public Skipped() {
		log.trace("Skipped()");
		setSkipped("");
		resetValuesModified();
	}
	
	/**
	 * Constructor
	 */
	public Skipped(String skipped) {
		log.trace("Skipped(" + skipped + ")");
		resetValuesModified();
		setSkipped(skipped);
	}

	@Override
	public void write(Document doc, Element parent) {
		log.trace("write(" + doc + ", " + parent + ")");
		if ( null == parent ) {
			throw new NullPointerException("Element is not allowed to be null in class Skipped::write(Document doc, Element parent)");
		} else if ( null == doc ) {
			throw new NullPointerException("Document is not allowed to be null in Skipped::write(Document doc, Element parent) method.");
		} else if ( true != isValueModified(SKIPPED_MODIFIED) ) {
			// do nothing
		} else {
			Element element = doc.createElement(ELEMENT);
			element.appendChild(doc.createTextNode(getSkipped()));
			parent.appendChild(element);
		}
	}
	
	@Override
	public void autoComplete() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method to get the skipped text.
	 * @return String
	 */
	public String getSkipped() {
		log.trace("getSkipped()");
		return skipped;
	}

	/**
	 * Method to set the skipped text.
	 * @param skipped String
	 */
	public void setSkipped(String skipped) {
		if( this.skipped != skipped ) {
			this.skipped = skipped;
			addValuesModified(SKIPPED_MODIFIED);
		}
	}
}
