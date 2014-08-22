package se.scrier.plugin.test.junit;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 	<xs:element name="properties">
 * 		<xs:complexType>
 * 			<xs:sequence>
 * 				<xs:element ref="property" maxOccurs="unbounded"/>
 * 			</xs:sequence>
 * 		</xs:complexType>
 * 	</xs:element>
 */
public class Properties extends XmlElement {
	
	private static Logger log = Logger.getLogger(Properties.class);
	
	private List<Property> property;
	
	// Strings
	transient protected final String ELEMENT = "properties";
	
	/**
	 * Constructor
	 */
	public Properties() {
		setProperty(new ArrayList<Property>());
	}
	
	@Override
	public void write(Document doc, Element parent) throws RequiredAttributeException {
		log.trace("write(" + doc + ", " + parent + ")");
		if ( null == parent ) {
			throw new NullPointerException("Element is not allowed to be null in class Properties::write(Document doc, Element parent)");
		} else if ( null == doc ) {
			throw new NullPointerException("Document is not allowed to be null in Properties::write(Document doc, Element parent) method.");
		} else if ( true == getProperty().isEmpty() ) {
			// do nothing
		} else {
			Element element = doc.createElement(ELEMENT);
			for( Property property : getProperty() ) {
				property.write(doc, element);
			}
			parent.appendChild(element);
		}
	}
	
	@Override
	public void autoComplete() {
		for( Property prop : getProperty() ) {
			prop.autoComplete();
		}
	}

	/**
	 * Method to get the properties.
	 * @return List<Property>
	 */
	public List<Property> getProperty() {
		return property;
	}

	/**
	 * Method to set the list of properties.
	 * @param property List<Property>
	 */
	public void setProperty(List<Property> property) {
		this.property = property;
	}
	
	/**
	 * Method to add a property to the list.
	 * @param property Property
	 */
	public void addProperty(Property property) {
		this.property.add(property);
	}
	
}
