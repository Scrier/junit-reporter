package se.scrier.plugin.test.junit;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *	<xs:element name="failure">
 *		<xs:complexType mixed="true">
 *			<xs:attribute name="type" type="xs:string" use="optional"/>
 *			<xs:attribute name="message" type="xs:string" use="optional"/>
 *		</xs:complexType>
 *	</xs:element>
 */
public class Failure extends XmlElement {
	
	private static Logger log = Logger.getLogger(Failure.class);
	
	private String type;
	private String message;
	
	// Strings
	transient private final String ELEMENT = "failure";
	transient private final String TYPE_ATTRIBUTE = "type";
	transient private final String MESSAGE_ATTRIBUTE = "message";
	
	// Modified
	transient protected final long TYPE_MODIFIED = 0x0000000000000001L;
	transient protected final long MESSAGE_MODIFIED = 0x0000000000000002L;
	
	/**
	 * Constructor
	 */
	public Failure() {
		log.trace("Failure()");
		setType("");
		setMessage("");
		resetValuesModified();
	}
	
	/**
	 * Constructor
	 * @param type String
	 * @param message String
	 */
	public Failure(String type, String message) {
		log.trace("Failure(" + type + ", " + message+ ")");
		setType(type);
		setMessage(message);
	}
	
	@Override
	public void write(Document doc, Element parent) {
		log.trace("write(" + doc + ", " + parent + ")");
		if ( null == parent ) {
			throw new NullPointerException("Element is not allowed to be null in class Failure::write(Document doc, Element parent)");
		} else if ( null == doc ) {
			throw new NullPointerException("Document is not allowed to be null in Failure::write(Document doc, Element parent) method.");
		} else if ( true != isValueModified(TYPE_MODIFIED) && 
				true != isValueModified(MESSAGE_MODIFIED) ) {
			// do nothing
		} else {
			Element element = doc.createElement(ELEMENT);
			if( true == isValueModified(TYPE_MODIFIED) ) {
				element.setAttribute(TYPE_ATTRIBUTE, getType());
			}
			if( true == isValueModified(MESSAGE_MODIFIED) ) {
				element.setAttribute(MESSAGE_ATTRIBUTE, getMessage());
			}
			parent.appendChild(element);
		}
	}
	
	@Override
	public void autoComplete() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metho to get the Type.
	 * @return String
	 */
	public String getType() {
		log.trace("getType()");
		return type;
	}

	/**
	 * Method to set the Type.
	 * @param type String
	 */
	public void setType(String type) {
		if( this.type != type ) {
			this.type = type;
			addValuesModified(TYPE_MODIFIED);
		}
	}

	/**
	 * Method to get the message.
	 * @return String
	 */
	public String getMessage() {
		log.trace("getMessage()");
		return message;
	}

	/**
	 * Method to set the Message.
	 * @param message String
	 */
	public void setMessage(String message) {
		if( this.message != message ) {
			this.message = message;
			addValuesModified(MESSAGE_MODIFIED);
		}
	}
	
}
