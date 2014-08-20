package se.scrier.plugin.test.junit;

/**
 * Custm exception class.
 * @author Andreas Joelsson
 */
public class RequiredAttributeException extends Exception {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 1215251590353844690L;
	
	/**
	 * Attribute expected.
	 */
	private String attribute;

	/**
	 * Constructor
	 * @param message String with the error text.
	 */
	public RequiredAttributeException(String message) {
		super(message);
		setAttribute("");
	}
	
	/**
	 * Constructor
	 * @param attribute String with the attribute that is conflicting.
	 * @param message String with the error text.
	 */
	public RequiredAttributeException(String attribute, String message) {
		super(message);
		setAttribute(attribute);
	}

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
}
