package se.scrier.plugin.test.junit;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <xs:element name="system-err" type="xs:string"/>
 */
public class SystemErr extends XmlElement {
	
	private static Logger log = Logger.getLogger(SystemErr.class);
	
	// Text
	private String system_err;
	
	// Strings
	private final String ELEMENT = "system-err";
	
	// Modified
	protected final long SYSTEM_ERR_MODIFIED = 0x0000000000000001L;
	
	/**
	 * Constructor
	 */
	public SystemErr() {
		setSystemErr("");
		resetValuesModified();
	}
	
	/**
	 * Constructor
	 * @param error String with the error message.
 	 */
	public SystemErr(String error) {
		setSystemErr(error);
	}

	@Override
	public void write(Document doc, Element parent) {
		log.trace("write(" + doc + ", " + parent + ")");
		if ( null == parent ) {
			throw new NullPointerException("Element is not allowed to be null in class SystemErr::write(Document doc, Element parent)");
		} else if ( null == doc ) {
			throw new NullPointerException("Document is not allowed to be null in SystemErr::write(Document doc, Element parent) method.");
		} else if ( true != isValueModified(SYSTEM_ERR_MODIFIED) ) {
			// do nothing
		} else {
			Element element = doc.createElement(ELEMENT);
			element.appendChild(doc.createTextNode(getSystemErr()));
			parent.appendChild(element);
		}
	}
	
	@Override
	public void autoComplete() {
	}

	/**
	 * Method to get the System Error text.
	 * @return String
	 */
	public String getSystemErr() {
		return system_err;
	}

	/**
	 * Method to set the System Error text.
	 * @param system_err String
	 */
	public void setSystemErr(String system_err) {
		if( this.system_err != system_err ) {
			this.system_err = system_err;
			addValuesModified(SYSTEM_ERR_MODIFIED);
		}
	}
}
