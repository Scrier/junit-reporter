package se.scrier.plugin.test.junit;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 	<xs:element name="property">
 * 		<xs:complexType>
 * 			<xs:attribute name="name" type="xs:string" use="required"/>
 * 			<xs:attribute name="value" type="xs:string" use="required"/>
 * 		</xs:complexType>
 * 	</xs:element>
 */
public class Property extends XmlElement {
	
	private static Logger log = Logger.getLogger(Property.class);
	
	private String name;
	private String value;
	
	// Strings
	private final String ELEMENT = "property";
	private final String NAME_ATTRIBUTE = "name";
	private final String VALUE_ATTRIBUTE = "value";
	
	/**
	 * Constructor
	 */
	public Property() {
		setName("");
		setValue("");
		resetValuesModified();
	}
	
	// Modified
	protected final long NAME_MODIFIED = 0x0000000000000001L;
	protected final long VALUE_MODIFIED = 0x0000000000000002L;
	
	@Override
	public void write(Document doc, Element parent) throws RequiredAttributeException {
		log.trace("write(" + doc + ", " + parent + ")");
		if ( null == parent ) {
			throw new NullPointerException("Element is not allowed to be null in class Property::write(Document doc, Element parent)");
		} else if ( null == doc ) {
			throw new NullPointerException("Document is not allowed to be null in Property::write(Document doc, Element parent) method.");
		} else if ( true != isValueModified(NAME_MODIFIED) ) {
			throw new RequiredAttributeException(NAME_ATTRIBUTE, "Required attribute name not set in Property.");
		} else if ( true != isValueModified(VALUE_MODIFIED) ) {
			throw new RequiredAttributeException(VALUE_ATTRIBUTE, "Required attribute value not set in Property.");
		} else {
			Element element = doc.createElement(ELEMENT);
			element.setAttribute(NAME_ATTRIBUTE, getName());
			element.setAttribute(VALUE_ATTRIBUTE, getValue());
			parent.appendChild(element);
		}
	}
	
	@Override
	public void autoComplete() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method to get the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the name.
	 * @param name String
	 */
	public void setName(String name) {
		if( this.name != name ) {
			this.name = name;
			addValuesModified(NAME_MODIFIED);
		}
	}

	/**
	 * Method to get the value.
	 * @return String
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Method to set the value.
	 * @param value String
	 */
	public void setValue(String value) {
		if( this.value != value ) {
			this.value = value;
			addValuesModified(VALUE_MODIFIED);
		}
	}
	
}
