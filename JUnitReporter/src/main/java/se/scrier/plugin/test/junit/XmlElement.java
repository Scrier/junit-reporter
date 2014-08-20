package se.scrier.plugin.test.junit;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Abstract class for use with xml writing and reading.
 * @author Andreas Joelsson
 */
public abstract class XmlElement {
	
	private long valuesModified;
	
	/**
	 * Constructor
	 */
	public XmlElement() {
		resetValuesModified();
	}
	
	/**
	 * Method to implement to write an element to xml file.
	 * @param doc Document 
	 * @param parent Element
	 * @throws Exception
	 */
	public abstract void write(Document doc, Element parent) throws Exception;
	
	/**
	 * Method to implement to autocomplete any information an element holds before writing to file.
	 */
	public abstract void autoComplete();
	
	/**
	 * Method to check if values of a element has been modified.
	 * @return boolean
	 */
	public boolean isValuesModified() {
		return (valuesModified != 0);
	}
	
	/**
	 * Method to check if a specified value has been modified.
	 * @param value long with the value to check.
	 * @return boolean
	 */
	public boolean isValueModified(long value) {
		return (valuesModified & value) > 0;
	}

	/**
	 * Method to get all values modified.
	 * @return long
	 */
	public long getValuesModified() {
		return valuesModified;
	}
	
	/**
	 * Method to reset all values modified.
	 */
	public void resetValuesModified() {
		this.valuesModified = 0L;
	}

	/**
	 * Method to set the values modified.
	 * @param valuesModified long
	 */
	public void setValuesModified(long valuesModified) {
		this.valuesModified = valuesModified;
	}
	
	/**
	 * Method to add a values modified to the ones existing.
	 * @param valuesModified long
	 */
	public void addValuesModified(long valuesModified) {
		this.valuesModified |= valuesModified;
	}
}
