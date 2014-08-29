package se.scrier.plugin.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.scrier.plugin.test.stdd.Customer;
import se.scrier.plugin.test.stdd.Project;
import se.scrier.plugin.test.stdd.Run;

public class StddToJsonTest {
	
	private static Logger log = Logger.getLogger(StddToJsonTest.class);

	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}
	
	@Before
	public void setup() throws IOException {
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testCustomerEmpty() {
		log.info("testCustomerEmpty");
		Customer input = new Customer();
		String json = StddToJson.INSTANCE.getCustomer(input);
		log.info("String: " + json);
		Customer output = StddToJson.INSTANCE.getCustomer(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testCustomerID() {
		log.info("testCustomerID");
		Customer input = new Customer();
		input.setID("this is an id");
		String json = StddToJson.INSTANCE.getCustomer(input);
		log.info("String: " + json);
		Customer output = StddToJson.INSTANCE.getCustomer(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testCustomerName() {
		log.info("testCustomerName");
		Customer input = new Customer();
		input.setName("this is a name");
		String json = StddToJson.INSTANCE.getCustomer(input);
		log.info("String: " + json);
		Customer output = StddToJson.INSTANCE.getCustomer(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testCustomer() {
		log.info("testCustomer");
		Customer input = new Customer();
		input.setID("id");
		input.setName("name");
		String json = StddToJson.INSTANCE.getCustomer(input);
		log.info("String: " + json);
		Customer output = StddToJson.INSTANCE.getCustomer(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testProjectEmpty() {
		log.info("testProjectEmpty");
		Project input = new Project();
		String json = StddToJson.INSTANCE.getProject(input);
		log.info("String: " + json);
		Project output = StddToJson.INSTANCE.getProject(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testProjectID() {
		log.info("testProjectID");
		Project input = new Project();
		input.setID("ID");
		String json = StddToJson.INSTANCE.getProject(input);
		log.info("String: " + json);
		Project output = StddToJson.INSTANCE.getProject(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testProjectName() {
		log.info("testProjectName");
		Project input = new Project();
		input.setName("Name");
		String json = StddToJson.INSTANCE.getProject(input);
		log.info("String: " + json);
		Project output = StddToJson.INSTANCE.getProject(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testProject() {
		log.info("testProject");
		Project input = new Project();
		input.setID("ID");
		input.setName("Name");
		input.setCustomer(new Customer("name"));
		String json = StddToJson.INSTANCE.getProject(input);
		log.info("String: " + json);
		Project output = StddToJson.INSTANCE.getProject(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testRunEmpty() {
		log.info("testRunEmpty");
		Run input = new Run();
		String json = StddToJson.INSTANCE.getRun(input);
		log.info("String: " + json);
		Run output = StddToJson.INSTANCE.getRun(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testRunID() {
		log.info("testRunID");
		Run input = new Run();
		input.setID("id");
		String json = StddToJson.INSTANCE.getRun(input);
		log.info("String: " + json);
		Run output = StddToJson.INSTANCE.getRun(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testRunName() {
		log.info("testRunName");
		Run input = new Run();
		input.setName("name");
		String json = StddToJson.INSTANCE.getRun(input);
		log.info("String: " + json);
		Run output = StddToJson.INSTANCE.getRun(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testRunRevision() {
		log.info("testRunRevision");
		Run input = new Run();
		input.setRevision("revision");
		String json = StddToJson.INSTANCE.getRun(input);
		log.info("String: " + json);
		Run output = StddToJson.INSTANCE.getRun(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testRunSource() {
		log.info("testRunSource");
		Run input = new Run();
		input.setSource("source");
		String json = StddToJson.INSTANCE.getRun(input);
		log.info("String: " + json);
		Run output = StddToJson.INSTANCE.getRun(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
	@Test
	public void testRun() {
		log.info("testRun");
		Run input = new Run();
		input.setID("id");
		input.setName("name");
		input.setProject(new Project("project", new Customer("customer")));
		input.setRevision("revision");
		input.setSource("source");
		String json = StddToJson.INSTANCE.getRun(input);
		log.info("String: " + json);
		Run output = StddToJson.INSTANCE.getRun(json);
		assertEquals(input.getID(), output.getID());
		assertEquals(input.getName(), output.getName());
	}
	
}
