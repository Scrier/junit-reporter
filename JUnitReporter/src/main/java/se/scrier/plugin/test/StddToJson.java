package se.scrier.plugin.test;

import se.scrier.plugin.test.stdd.Customer;
import se.scrier.plugin.test.stdd.Project;
import se.scrier.plugin.test.stdd.Run;

import com.google.gson.Gson;

public enum StddToJson {
	INSTANCE;
	
	private Gson gson;
	
	/**
	 * Constructor
	 */
	private StddToJson() {
		gson = new Gson();
	}
	
	/**
	 * Method to convert an Customer class to json string.
	 * @param customer Customer instance.
	 * @return String in format {"name":"name","_id":"id"}
	 */
	public String getCustomer(Customer customer) {
		return gson.toJson(customer, Customer.class);
	}
	
	/**
	 * Method to convert a json String to a Customer object.
	 * @param customer String in format {"name":"name","_id":"id"}
	 * @return Customer instance 
	 */
	public Customer getCustomer(String customer) {
		return gson.fromJson(customer, Customer.class);
	}
	
	/**
	 * Method to convert an Project class to json string.
	 * @param project Project instance.
	 * @return String in format {"name":"Name","_id":"ID"}
	 */
	public String getProject(Project project) {
		return gson.toJson(project, Project.class);
	}
	
	/**
	 * Method to convert a json String to a Project object.
	 * @param project String in format {"name":"Name","_id":"ID"}
	 * @return Project instance
	 */
	public Project getProject(String project) {
		return gson.fromJson(project, Project.class);
	}
	
	/**
	 * Method to convert an Run class to json string.
	 * @param run Run instance.
	 * @return String in format {"name":"name","source":"source","revision":"revision","_id":"id"}
	 */
	public String getRun(Run run) {
		return gson.toJson(run, Run.class);
	}
	
	/**
	 * Method to convert a json String to a Run object.
	 * @param run String in format {"name":"name","source":"source","revision":"revision","_id":"id"}
	 * @return Run instance
	 */
	public Run getRun(String run) {
		return gson.fromJson(run, Run.class);
	}

}
