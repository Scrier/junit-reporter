package se.scrier.plugin.test.stdd;

import org.apache.log4j.Logger;

public class Project {
	
	private static Logger log = Logger.getLogger(Project.class);

	private String name;
	private String _id;
	transient private Customer customer;
	
	/**
	 * Constructor
	 */
	public Project() {
		setName(null);
		setID(null);
		setCustomer(null);
	}
	
	/**
	 * Constructor
	 * @param name String with the name of the Project
	 */
	public Project(String name) {
		setName(name);
		setID(null);
		setCustomer(null);
	}
	
	/**
	 * Constructor
	 * @param name String with the name of the Project
	 * @param customer Customer instance of the customer we currently work with.
	 */
	public Project(String name, Customer customer) {
		setName(name);
		setID(null);
		setCustomer(customer);
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
		log.trace("getID()");
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void setID(String _id) {
		log.trace("setID(" + _id + ")");
		this._id = _id;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		log.trace("getCustomer()");
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		log.trace("setCustomer(" + customer + ")"); 
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return "Project{name:" + getName() + ",_id" + getID() + "}";
	}
	
}
