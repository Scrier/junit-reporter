package se.scrier.plugin.test.stdd;

import org.apache.log4j.Logger;


public class Customer {
	
	private static Logger log = Logger.getLogger(Customer.class);
	
	private String name;
	private String _id;
	
	/**
	 * Constructor
	 */
	public Customer() {
		setName(null);
	}
	
	/**
	 * Constructor
	 * @param name String with the name of the customer.
	 */
	public Customer(String name) {
		setName(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		log.trace("getName()");
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		log.trace("setName(" + name + ")");
		this.name = name;
	}

	/**
	 * @return the _id
	 */
	public String getID() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void setID(String _id) {
		this._id = _id;
	}
	
	@Override
	public String toString() {
		return "Customer{name:" + getName() + ",_id:" + getID() + "}";
	}
	
}
