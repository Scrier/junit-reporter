package se.scrier.plugin.test.junit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 	<xs:element name="testsuites">
 * 		<xs:complexType>
 * 			<xs:sequence>
 * 					<xs:element ref="testsuite" minOccurs="0" maxOccurs="unbounded"/>
 * 			</xs:sequence>
 * 			<xs:attribute name="name" type="xs:string" use="optional"/>
 * 			<xs:attribute name="time" type="xs:string" use="optional"/>
 * 			<xs:attribute name="tests" type="xs:string" use="optional"/>
 * 			<xs:attribute name="failures" type="xs:string" use="optional"/>
 * 			<xs:attribute name="disabled" type="xs:string" use="optional"/>
 * 			<xs:attribute name="errors" type="xs:string" use="optional"/>
 *		</xs:complexType>
 *	</xs:element>
 */
public class TestSuites extends XmlElement {
	
	private static Logger log = Logger.getLogger(TestSuites.class);
	
	// Elements
	private List<TestSuite> testsuite;
	
	// Attributes
	private String name;
	private double time;
	private int tests;
	private int failures;
	private int disabled;
	private int errors;
	
	// Strings
	private final String ELEMENT = "testsuites";
	private final String NAME_ATTRIBUTE = "name";
	private final String TIME_ATTRIBUTE = "time";
	private final String TESTS_ATTRIBUTE = "tests";
	private final String FAILURES_ATTRIBUTE = "failures";
	private final String DISABLED_ATTRIBUTE = "disabled";
	private final String ERRORS_ATTRIBUTE = "errors";
	
	// Modified
	protected final long NAME_MODIFIED = 0x0000000000000001L;
	protected final long TIME_MODIFIED = 0x0000000000000002L;
	protected final long TESTS_MODIFIED = 0x0000000000000004L;
	protected final long FAILURES_MODIFIED = 0x0000000000000008L;
	protected final long DISABLED_MODIFIED = 0x0000000000000010L;
	protected final long ERRORS_MODIFIED = 0x0000000000000020L;

	/**
	 * Constructor
	 */
	public TestSuites() {
		log.trace("TestSuites()");
		setTestsuite(new ArrayList<TestSuite>());
		setName("");
		setTime(0);
		setTests(0);
		setFailures(0);
		setDisabled(0);
		setErrors(0);
		resetValuesModified();
	}
	
	@Override
	public void write(Document doc, Element parent) throws RequiredAttributeException {
		if ( null == doc ) {
			throw new NullPointerException("Document is not allowed to be null in TestSuites::write(Document doc, Element parent) method.");
		} else {
			Element element = doc.createElement(ELEMENT);
			if( true == isValueModified(NAME_MODIFIED) ) {
				element.setAttribute(NAME_ATTRIBUTE, getName());
			}
			if( true == isValueModified(TIME_MODIFIED) ) {
				element.setAttribute(TIME_ATTRIBUTE, String.format("%.4f", getTime()));
			}
			if( true == isValueModified(TESTS_MODIFIED) ) {
				element.setAttribute(TESTS_ATTRIBUTE, String.valueOf(getTests()));
			}
			if( true == isValueModified(FAILURES_MODIFIED) ) {
				element.setAttribute(FAILURES_ATTRIBUTE, String.valueOf(getFailures()));
			}
			if( true == isValueModified(DISABLED_MODIFIED) ) {
				element.setAttribute(DISABLED_ATTRIBUTE, String.valueOf(getDisabled()));
			}
			if( true == isValueModified(ERRORS_MODIFIED) ) {
				element.setAttribute(ERRORS_ATTRIBUTE, String.valueOf(getErrors()));
			}
			for( TestSuite suite : getTestsuite() ) {
				suite.write(doc, element);
			}
			if( null == parent ) {
				doc.appendChild(element);
			} else {
				parent.appendChild(element);
			}
		}
	}
	
	@Override
	public void autoComplete() {
		for( TestSuite suite : getTestsuite() ) {
			suite.autoComplete();
		}
		int pFailures = -1;
		int pDisabled = -1;
		int pErrors = -1;
		if( true != isValueModified(FAILURES_MODIFIED) ) {
			pFailures = 0;
		}
		if( true != isValueModified(DISABLED_MODIFIED) ) {
			pDisabled = 0;
		}
		if( true != isValueModified(ERRORS_MODIFIED) ) {
			pErrors = 0;
		}
		if( true != isValueModified(TESTS_MODIFIED) ) {
			setTests(getTestsuite().size());
		}
		for( TestSuite suite : getTestsuite() ) {
			if( suite.isDisabled() ) {
				pDisabled++;
			} else if( -1 != pErrors && 0 < suite.getErrors() ) {
				pErrors++;
			} else if ( -1 != pFailures && 0 < suite.getFailures() ) {
				pFailures++;
			}
		}
		if( -1 != pDisabled ) {
			setDisabled(pDisabled);
		}
		if( -1 != pErrors ) {
			setErrors(pErrors);
		}
		if( -1 != pFailures ) {
			setFailures(pFailures);
		}
	}
	
	/**
	 * Method to sort TestSuite objects.
	 */
	public void sortTestSuite() {
		Collections.sort(getTestsuite());
		for( TestSuite suite : getTestsuite() ) {
			suite.sortTestCase();
		}
	}

	/**
	 * @return the testsuite
	 */
	public List<TestSuite> getTestsuite() {
		return testsuite;
	}

	/**
	 * @param testsuite the testsuite to set
	 */
	public void setTestsuite(List<TestSuite> testsuite) {
		this.testsuite = testsuite;
	}
	
	/**
	 * @param testsuite the testsuite to add
	 */
	public void addTestsuite(TestSuite testsuite) {
		this.testsuite.add(testsuite);
	}
	
	/**
	 * @param testsuite the testsuite to add
	 */
	public boolean containsTestsuite(TestSuite testsuite) {
		boolean retValue = containsTestsuite(testsuite.getName());
		return retValue;
	}
	
	/**
	 * Method to check if the testSuite exists by name.
	 * @param name String
	 */
	public boolean containsTestsuite(String name) {
		boolean retValue = false;
		for( TestSuite suite : getTestsuite() ) {
			if( name.equals(suite.getName()) ) {
				retValue = true;
				break;
			}
		}
		return retValue;
	}
	
	/**
	 * @param name the name of the testsuite.
	 */
	public TestSuite getTestsuite(String name) {
		TestSuite retValue = null;
		for( TestSuite suite : getTestsuite() ) {
			if( name.equals(suite.getName()) ) {
				retValue = suite;
				break;
			}
		}
		return retValue;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if( this.name != name ) {
			this.name = name;
			addValuesModified(NAME_MODIFIED);
		}
	}

	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(double time) {
		if( this.time != time ) {
			this.time = time;
			addValuesModified(TIME_MODIFIED);
		}
	}

	/**
	 * @return the tests
	 */
	public int getTests() {
		return tests;
	}

	/**
	 * @param tests the tests to set
	 */
	public void setTests(int tests) {
		if( this.tests != tests ) {
			this.tests = tests;
			addValuesModified(TESTS_MODIFIED);
		}
	}

	/**
	 * @return the failures
	 */
	public int getFailures() {
		return failures;
	}

	/**
	 * @param failures the failures to set
	 */
	public void setFailures(int failures) {
		if( this.failures != failures ) {
			this.failures = failures;
			addValuesModified(FAILURES_MODIFIED);
		}
	}

	/**
	 * @return the disabled
	 */
	public int getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(int disabled) {
		if( this.disabled != disabled ) {
			this.disabled = disabled;
			addValuesModified(DISABLED_MODIFIED);
		}
	}

	/**
	 * @return the errors
	 */
	public int getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(int errors) {
		if( this.errors != errors ) {
			this.errors = errors;
			addValuesModified(ERRORS_MODIFIED);
		}
	}

}
