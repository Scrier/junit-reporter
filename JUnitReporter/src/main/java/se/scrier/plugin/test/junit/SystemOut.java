package se.scrier.plugin.test.junit;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <xs:element name="system-out" type="xs:string"/>
 */
public class SystemOut extends XmlElement {
	
	private static Logger log = Logger.getLogger(SystemOut.class);
	
	private String system_out;
	
	// Strings
	private final String ELEMENT = "system-out";
	
	// Modified
	protected final long SYSTEM_OUT_MODIFIED = 0x0000000000000001L;
	
	/**
	 * Constructor
	 */
	public SystemOut() {
		setSystemOut("");
		resetValuesModified();
	}
	
	/**
	 * Constructor
	 * @param out String to set system output text.
	 */
	public SystemOut(String out) {
		setSystemOut(out);
	}

	@Override
	public void write(Document doc, Element parent) {
		log.trace("write(" + doc + ", " + parent + ")");
		if ( null == parent ) {
			throw new NullPointerException("Element is not allowed to be null in class SystemOut::write(Document doc, Element parent)");
		} else if ( null == doc ) {
			throw new NullPointerException("Document is not allowed to be null in SystemOut::write(Document doc, Element parent) method.");
		} else if ( true != isValueModified(SYSTEM_OUT_MODIFIED) ) {
			// do nothing
		} else {
			Element element = doc.createElement(ELEMENT);
			element.appendChild(doc.createTextNode(getSystemOut()));
			parent.appendChild(element);
		}
	}
	
	@Override
	public void autoComplete() {
	}

	/**
	 * Method to get the system output.
	 * @return String
	 */
	public String getSystemOut() {
		return system_out;
	}

	/**
	 * Method to set the system output.
	 * @param system_out String
	 */
	public void setSystemOut(String system_out) {
		if( this.system_out != system_out ) {
			this.system_out = system_out;
			addValuesModified(SYSTEM_OUT_MODIFIED);
		}
	}
}