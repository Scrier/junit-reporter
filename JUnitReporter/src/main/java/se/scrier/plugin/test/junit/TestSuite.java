package se.scrier.plugin.test.junit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *  <xs:element name="testsuite">
 *  	<xs:complexType>
 *  		<xs:sequence>
 *  			<xs:element ref="properties" minOccurs="0" maxOccurs="1"/>
 *  			<xs:element ref="testcase" minOccurs="0" maxOccurs="unbounded"/>
 *  			<xs:element ref="system-out" minOccurs="0" maxOccurs="1"/>
 *  			<xs:element ref="system-err" minOccurs="0" maxOccurs="1"/>
 *  		</xs:sequence>
 *  		<xs:attribute name="name" type="xs:string" use="required"/>
 *  		<xs:attribute name="tests" type="xs:string" use="required"/>
 *  		<xs:attribute name="failures" type="xs:string" use="optional"/>
 *  		<xs:attribute name="errors" type="xs:string" use="optional"/>
 *  		<xs:attribute name="time" type="xs:string" use="optional"/>
 *  		<xs:attribute name="disabled" type="xs:string" use="optional"/>
 *  		<xs:attribute name="skipped" type="xs:string" use="optional"/>
 *  		<xs:attribute name="timestamp" type="xs:string" use="optional"/>
 *  		<xs:attribute name="hostname" type="xs:string" use="optional"/>
 *  		<xs:attribute name="id" type="xs:string" use="optional"/>
 *  		<xs:attribute name="package" type="xs:string" use="optional"/>
 *  	</xs:complexType>
 *  </xs:element>
 */
public class TestSuite extends XmlElement implements Comparable<TestSuite> {
	
	private static Logger log = Logger.getLogger(TestSuite.class);
	
	// Elements
	private Properties properties;
	private List<TestCase> testcase;
	private SystemOut system_out;
	private SystemErr system_err;
	
	// Attributes
	private String name;
	private int tests;
	private int failures;
	private int errors;
	private double time;
	private boolean disabled;
	private int skipped;
	private String timestamp;
	private String hostname;
	private String id;
	private String _package;
	
	// Strings
	private final String ELEMENT = "testsuite";
	private final String NAME_ATTRIBUTE = "name";
	private final String TESTS_ATTRIBUTE = "tests";
	private final String FAILURES_ATTRIBUTE = "failures";
	private final String ERRORS_ATTRIBUTE = "errors";
	private final String TIME_ATTRIBUTE = "time";
	private final String DISABLED_ATTRIBUTE = "disabled";
	private final String SKIPPED_ATTRIBUTE = "skipped";
	private final String TIMESTAMP_ATTRIBUTE = "timestamp";
	private final String HOSTNAME_ATTRIBUTE = "hostname";
	private final String ID_ATTRIBUTE = "id";
	private final String PACKAGE_ATTRIBUTE = "package";
	
	// Modified
	private final long NAME_MODIFIED = 0x0000000000000001L;
	private final long TESTS_MODIFIED = 0x0000000000000002L;
	private final long FAILURES_MODIFIED = 0x0000000000000004L;
	private final long ERRORS_MODIFIED = 0x0000000000000008L;
	private final long TIME_MODIFIED = 0x0000000000000010L;
	private final long DISABLED_MODIFIED = 0x0000000000000020L;
	private final long SKIPPED_MODIFIED = 0x0000000000000040L;
	private final long TIMESTAMP_MODIFIED = 0x0000000000000080L;
	private final long HOSTNAME_MODIFIED = 0x0000000000000100L;
	private final long ID_MODIFIED = 0x0000000000000200L;
	private final long PACKAGE_MODIFIED = 0x0000000000000400L;
	
	/**
	 * Constructor
	 */
	public TestSuite() {
		setProperties(new Properties());
		setTestcase(new ArrayList<TestCase>());
		setSystemOut(new SystemOut());
		setSystemErr(new SystemErr());
		setName("");
		setTests(-1);
		setFailures(-1);
		setErrors(-1);
		setTime(0);
		setDisabled(false);
		setSkipped(-1);
		setTimestamp("");
		setHostname("");
		setId("");
		setPackage("");
		resetValuesModified();
	}
	
	/**
	 * Constructor
	 * @param name String with the name of the testsuite.
	 */
	public TestSuite(String name) {
		this();
		setName(name);
	}

	@Override
	public void write(Document doc, Element parent) throws RequiredAttributeException {
		log.trace("write(" + doc + ", " + parent + ")");
		if( true != isValueModified(NAME_MODIFIED) ) {
			throw new RequiredAttributeException(NAME_ATTRIBUTE, "Required attribute name not set in TestSuite.");
		} else if ( true != isValueModified(TESTS_MODIFIED) && true == getTestcase().isEmpty() ) {
			throw new RequiredAttributeException(TESTS_ATTRIBUTE, "Required attribute test not set in TestSuite.");
		} else if ( null == doc ) {
			throw new NullPointerException("Document is not allowed to be null in TestSuite::write(Document doc, Element parent) method.");
		} else {
			Element element = doc.createElement(ELEMENT);
			element.setAttribute(NAME_ATTRIBUTE, getName());
			if( true == isValueModified(TESTS_MODIFIED) ) {
				element.setAttribute(TESTS_ATTRIBUTE, String.valueOf(getTests()));
			} else {
				element.setAttribute(TESTS_ATTRIBUTE, String.valueOf(getTestcase().size()));
			}
			if( true == isValueModified(FAILURES_MODIFIED) ) {
				element.setAttribute(FAILURES_ATTRIBUTE, String.valueOf(getFailures()));
			}
			if( true == isValueModified(ERRORS_MODIFIED) ) {
				element.setAttribute(ERRORS_ATTRIBUTE, String.valueOf(getErrors()));
			}
			if( true == isValueModified(TIME_MODIFIED) ) {
				element.setAttribute(TIME_ATTRIBUTE, String.format("%.4f", getTime()));
			}
			if( true == isValueModified(DISABLED_MODIFIED) ) {
				element.setAttribute(DISABLED_ATTRIBUTE, String.valueOf(isDisabled()));
			}
			if( true == isValueModified(SKIPPED_MODIFIED) ) {
				element.setAttribute(SKIPPED_ATTRIBUTE, String.valueOf(getSkipped()));
			}
			if( true == isValueModified(TIMESTAMP_MODIFIED) ) {
				element.setAttribute(TIMESTAMP_ATTRIBUTE, getTimestamp());
			}
			if( true == isValueModified(HOSTNAME_MODIFIED) ) {
				element.setAttribute(HOSTNAME_ATTRIBUTE, getHostname());
			}
			if( true == isValueModified(ID_MODIFIED) ) {
				element.setAttribute(ID_ATTRIBUTE, getId());
			}
			if( true == isValueModified(PACKAGE_MODIFIED) ) {
				element.setAttribute(PACKAGE_ATTRIBUTE, getPackage());
			}
			getProperties().write(doc, element);
			for( TestCase testcase : getTestcase() ) {
				testcase.write(doc, element);
			}
			getSystemOut().write(doc, element);
			getSystemErr().write(doc, element);
			if( null == parent ) {
				doc.appendChild(element);
			} else {
				parent.appendChild(element);
			}
		}
	}
	
	@Override
	public void autoComplete() {
		getProperties().autoComplete();
		for( TestCase test : getTestcase() ) {
			test.autoComplete();
		}
		getSystemOut().autoComplete();
		getSystemErr().autoComplete();
		setTests(getTestcase().size());
		int pFailures = -1;
		int pErrors = -1;
		int pSkipped = -1;
		if( true != isValueModified(FAILURES_MODIFIED) ) {
			pFailures = 0;
		}
		if( true != isValueModified(ERRORS_MODIFIED) ) {
			pErrors = 0;
		}
		if( true != isValueModified(SKIPPED_MODIFIED) ) {
			pSkipped = 0;
		}
		for( TestCase test: getTestcase() ) {
			if( -1 != pSkipped && true != test.getSkipped().getSkipped().isEmpty() ) {
				pSkipped++;
			} else if ( -1 != pErrors && 0 < test.getError().size() ) {
				pErrors++;
			} else if ( -1 != pFailures && 0 < test.getFailure().size() ) {
				pFailures++;
			}
		}
		if( -1 != pFailures ) {
			setFailures(pFailures);
		}
		if( -1 != pErrors ) {
			setErrors(pErrors);
		}
		if( -1 != pSkipped ) {
			setSkipped(pSkipped);
		}
	}
	
	/**
	 * Method to sort the testcases according to user.
	 */
	public void sortTestCase() {
		Collections.sort(getTestcase());
	}

	/**
	 * Method to get the Properties object.
	 * @return Properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Method to set the Properties.
	 * @param properties Properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * Method to get a list of TestCase
	 * @return List<TestCase>
	 */
	public List<TestCase> getTestcase() {
		return testcase;
	}

	/**
	 * Method to set a list of TestCase
	 * @param testcase List<TestCase>
	 */
	public void setTestcase(List<TestCase> testcase) {
		this.testcase = testcase;
	}
	
	/**
	 * Method to add a testcase to the list.
	 * @param testcase TestCase
	 */
	public void addTestcase(TestCase testcase) {
		this.testcase.add(testcase);
	}

	/**
	 * Method to get the SystemOut object.
	 * @return SystemOut
	 */
	public SystemOut getSystemOut() {
		return system_out;
	}

	/**
	 * Method to set the System Out object.
	 * @param system_out SystemOut
	 */
	public void setSystemOut(SystemOut system_out) {
		this.system_out = system_out;
	}

	/**
	 * Method to get the SystemErr object.
	 * @return SystemErr
	 */
	public SystemErr getSystemErr() {
		return system_err;
	}

	/**
	 * Method to set the SystemErr object.
	 * @param system_err SystemErr
	 */
	public void setSystemErr(SystemErr system_err) {
		this.system_err = system_err;
	}

	/**
	 * Method to get the name of the TestSuite
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the name of the TestSuite
	 * @param name String
	 */
	public void setName(String name) {
		if( this.name != name ) {
			this.name = name;
			addValuesModified(NAME_MODIFIED);
		}
	}

	/**
	 * Method to get the number of tests.
	 * @return int
	 */
	public int getTests() {
		return tests;
	}

	/**
	 * Method to set the number of tests.
	 * @param tests int
	 */
	public void setTests(int tests) {
		if( this.tests != tests ) {
			this.tests = tests;
			addValuesModified(TESTS_MODIFIED);
		}
	}

	/**
	 * Method to get the number of failures.
	 * @return int
	 */
	public int getFailures() {
		return failures;
	}

	/**
	 * Method to set the number of failures.
	 * @param failures int
	 */
	public void setFailures(int failures) {
		if( this.failures != failures ) {
			this.failures = failures;
			addValuesModified(FAILURES_MODIFIED);
		}
	}

	/**
	 * Method to get the number of errors.
	 * @return int
	 */
	public int getErrors() {
		return errors;
	}

	/**
	 * Method to set the number of errors.
	 * @param errors int
	 */
	public void setErrors(int errors) {
		if( this.errors != errors ) {
			this.errors = errors;
			addValuesModified(ERRORS_MODIFIED);
		}
	}

	/**
	 * Method to get the time for the suite.
	 * @return double
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Method to set the time for the testsuite.
	 * @param time double.
	 */
	public void setTime(double time) {
		if( this.time != time ) {
			this.time = time;
			addValuesModified(TIME_MODIFIED);
		}
	}

	/**
	 * Method to get if a suite is disabled.
	 * @return boolean
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * Method to set if a suite is disabled.
	 * @param disabled boolean
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		addValuesModified(DISABLED_MODIFIED);
	}

	/**
	 * Method to get number of skipped test.
	 * @return int
	 */
	public int getSkipped() {
		return skipped;
	}

	/**
	 * Method to set number of skipped tests.
	 * @param skipped int
	 */
	public void setSkipped(int skipped) {
		if( this.skipped != skipped ) {
			this.skipped = skipped;
			addValuesModified(SKIPPED_MODIFIED);
		}
	}

	/**
	 * Method to get a timestamp.
	 * @return String
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Method to set the timestamp.
	 * @param timestamp String
	 */
	public void setTimestamp(String timestamp) {
		if( this.timestamp != timestamp ) {
			this.timestamp = timestamp;
			addValuesModified(TIMESTAMP_MODIFIED);
		}
	}

	/**
	 * Method to get the host name.
	 * @return String
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * Method to set the host name.
	 * @param hostname String
	 */
	public void setHostname(String hostname) {
		if( this.hostname != hostname ) {
			this.hostname = hostname;
			addValuesModified(HOSTNAME_MODIFIED);
		}
	}

	/**
	 * Method to get the id of the testsuite.
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method to set the id of the testsuite
	 * @param id String
	 */
	public void setId(String id) {
		if( this.id != id ) {
			this.id = id;
			addValuesModified(ID_MODIFIED);
		}
	}
	
	/**
	 * Method to get the package of the testsuite.
	 * @return String
	 */
	public String getPackage() {
		return _package;
	}

	/**
	 * Method to set package for the testsuite.
	 * @param _package String
	 */
	public void setPackage(String _package) {
		if( this._package != _package ) {
			this._package = _package;
			addValuesModified(PACKAGE_MODIFIED);
		}
	}

	@Override
	public int compareTo(TestSuite obj2compare) {
		if( getTime() < obj2compare.getTime() ) {
			return -1;
		} else if( getTime() > obj2compare.getTime() ) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		String retValue = "TestSuite{";
		boolean first = true;
		if( true == isValueModified(NAME_MODIFIED) ) {
			if( first ) {
				first = false;
			}
			retValue += NAME_ATTRIBUTE + ":" + getName();
		}
		if( true == isValueModified(TESTS_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += TESTS_ATTRIBUTE + ":" + getTests();
		}
		if( true == isValueModified(FAILURES_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += FAILURES_ATTRIBUTE + ":" + getFailures();
		}
		if( true == isValueModified(ERRORS_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += ERRORS_ATTRIBUTE + ":" + getErrors();
		}
		if( true == isValueModified(TIME_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += TIME_ATTRIBUTE + ":" + String.format("%.4f", getTime());
		}
		if( true == isValueModified(DISABLED_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += DISABLED_ATTRIBUTE + ":" + isDisabled();
		}
		if( true == isValueModified(SKIPPED_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += SKIPPED_ATTRIBUTE + ":" + getSkipped();
		}
		if( true == isValueModified(TIMESTAMP_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += TIMESTAMP_ATTRIBUTE + ":" + getTimestamp();
		}
		if( true == isValueModified(HOSTNAME_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += HOSTNAME_ATTRIBUTE + ":" + getHostname();
		}
		if( true == isValueModified(ID_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += ID_ATTRIBUTE + ":" + getId();
		}
		if( true == isValueModified(PACKAGE_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += PACKAGE_ATTRIBUTE + ":" + getPackage();
		}
		if( true != getTestcase().isEmpty() ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += "testcase:" + getTestcase().size();
		}
		return retValue + "}";
	}
	
}
