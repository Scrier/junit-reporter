package se.scrier.plugin.test.junit;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 	<xs:element name="testcase">
 * 		<xs:complexType>
 * 			<xs:sequence>
 * 				<xs:element ref="skipped" minOccurs="0" maxOccurs="1"/>
 * 				<xs:element ref="error" minOccurs="0" maxOccurs="unbounded"/>
 * 				<xs:element ref="failure" minOccurs="0" maxOccurs="unbounded"/>
 * 				<xs:element ref="system-out" minOccurs="0" maxOccurs="unbounded"/>
 * 				<xs:element ref="system-err" minOccurs="0" maxOccurs="unbounded"/>
 * 			</xs:sequence>
 * 			<xs:attribute name="name" type="xs:string" use="required"/>
 * 			<xs:attribute name="assertions" type="xs:string" use="optional"/>
 * 			<xs:attribute name="time" type="xs:string" use="optional"/>
 * 			<xs:attribute name="classname" type="xs:string" use="optional"/>
 * 			<xs:attribute name="status" type="xs:string" use="optional"/>
 * 		</xs:complexType>
 * 	</xs:element>
 */
public class TestCase extends XmlElement implements Comparable<TestCase> {
	
	private static Logger log = Logger.getLogger(TestCase.class);
	
	// Elements
	private Skipped skipped;
	private List<Error> error;
	private List<Failure> failure;
	private List<SystemOut> system_out;
	private List<SystemErr> system_err;
	
	// Attributes
	private String name;
	private String assertions;
	private double time;
	private String classname;
	private String status;
	
	// Strings
	transient private final String ELEMENT = "testcase";
	transient private final String NAME_ATTRIBUTE = "name";
	transient private final String ASSERTIONS_ATTRIBUTE = "assertions";
	transient private final String TIME_ATTRIBUTE = "time";
	transient private final String CLASSNAME_ATTRIBUTE = "classname";
	transient private final String STATUS_ATTRIBUTE = "status";
	
	// Modified
	transient protected final long NAME_MODIFIED = 0x0000000000000001L;
	transient protected final long ASSERTIONS_MODIFIED = 0x0000000000000002L;
	transient protected final long TIME_MODIFIED = 0x0000000000000004L;
	transient protected final long CLASSNAME_MODIFIED = 0x0000000000000008L;
	transient protected final long STATUS_MODIFIED = 0x0000000000000010L;
	
	/**
	 * Constructor
	 */
	public TestCase() {
		setSkipped(new Skipped());
		setError(new ArrayList<Error>());
		setFailure(new ArrayList<Failure>());
		setSystemOut(new ArrayList<SystemOut>());
		setSystemErr(new ArrayList<SystemErr>());
		setName("");
		setAssertions("");
		setTime(0);
		setClassname("");
		setStatus("");
		resetValuesModified();
	}
	
	/**
	 * Constructor
	 * @param name String with the name of the testcase.
	 */
	public TestCase(String name) {
		this();
		setName(name);
	}

	@Override
	public void write(Document doc, Element parent) throws RequiredAttributeException {
		log.trace("write(" + doc + ", " + parent + ")");
		if( true != isValueModified(NAME_MODIFIED) ) {
			throw new RequiredAttributeException(NAME_ATTRIBUTE, "Required attribute name not set in TestCase.");
		} else if ( null == parent ) {
			throw new NullPointerException("Element is not allowed to be null in class TestCase::write(Document doc, Element parent)");
		} else if ( null == doc ) {
			throw new NullPointerException("Document is not allowed to be null in TestCase::write(Document doc, Element parent) method.");
		} else {
			Element element = doc.createElement(ELEMENT);
			element.setAttribute(NAME_ATTRIBUTE, getName());
			if( true == isValueModified(ASSERTIONS_MODIFIED) ) {
				element.setAttribute(ASSERTIONS_ATTRIBUTE, getAssertions());
			} 
			if( true == isValueModified(TIME_MODIFIED) ) {
				element.setAttribute(TIME_ATTRIBUTE, String.format("%.4f", getTime()));
			}
			if( true == isValueModified(CLASSNAME_MODIFIED) ) {
				element.setAttribute(CLASSNAME_ATTRIBUTE, getClassname());
			}
			if( true == isValueModified(STATUS_MODIFIED) ) {
				element.setAttribute(STATUS_ATTRIBUTE, getStatus());
			}
			getSkipped().write(doc, element);
			for( Error error : getError() ) {
				error.write(doc, element);
			}
			for( Failure failure : getFailure() ) {
				failure.write(doc, element);
			}
			for( SystemErr systemErr : getSystemErr() ) {
				systemErr.write(doc, element);
			}
			for( SystemOut systemOut : getSystemOut() ) {
				systemOut.write(doc, element);
			}
			parent.appendChild(element);
		}
	}
	
	@Override
	public void autoComplete() {
		getSkipped().autoComplete();
		for( Error pError : getError() ) {
			pError.autoComplete();
		}
		for( Failure pFailure : getFailure() ) {
			pFailure.autoComplete();
		}
		for( SystemErr pSystemErr : getSystemErr() ) {
			pSystemErr.autoComplete();
		}
		for( SystemOut pSystemOut : getSystemOut() ) {
			pSystemOut.autoComplete();
		}
	}

	/**
	 * Method to get the Skipped object.
	 * @return Skipped
	 */
	public Skipped getSkipped() {
		return skipped;
	}

	/**
	 * Method to set the Skipped object.
	 * @param skipped Skipped
	 */
	public void setSkipped(Skipped skipped) {
		this.skipped = skipped;
	}

	/**
	 * Method to get a list of errors.
	 * @return List<Error>
	 */
	public List<Error> getError() {
		return error;
	}

	/**
	 * Method to set a list of errors.
	 * @param error List<Error>
	 */
	public void setError(List<Error> error) {
		this.error = error;
	}
	
	/**
	 * Method to add an Error to the list.
	 * @param error Error
	 */
	public void addError(Error error) {
		this.error.add(error);
	}

	/**
	 * Method to get a list of the Failures
	 * @return List<Failure>
	 */
	public List<Failure> getFailure() {
		return failure;
	}

	/**
	 * Method to set a list of the Failures.
	 * @param failure List<Failure>
	 */
	public void setFailure(List<Failure> failure) {
		this.failure = failure;
	}
	
	/**
	 * Method to add a failure to the list.
	 * @param failure
	 */
	public void addFailure(Failure failure) {
		this.failure.add(failure);
	}

	/**
	 * Method to get a list of the System Output.
	 * @return List<SystemOut>
	 */
	public List<SystemOut> getSystemOut() {
		return system_out;
	}

	/**
	 * Method to set a list of the System Output
	 * @param system_out List<SystemOut>
	 */
	public void setSystemOut(List<SystemOut> system_out) {
		this.system_out = system_out;
	}
	
	/**
	 * Method to add a System Output to the list.
	 * @param system_out SystemOut object.
	 */
	public void addSystemOut(SystemOut system_out) {
		this.system_out.add(system_out);
	}

	/**
	 * Method to get a list of the System Errors.
	 * @return List<SystemErr>
	 */
	public List<SystemErr> getSystemErr() {
		return system_err;
	}

	/**
	 * Method to set a list of System Errors
	 * @param system_err List<SystemErr>
	 */
	public void setSystemErr(List<SystemErr> system_err) {
		this.system_err = system_err;
	}
	
	/**
	 * Method to add a SystemErr to the list.
	 * @param system_err SystemErr
	 */
	public void addSystemErr(SystemErr system_err) {
		this.system_err.add(system_err);
	}

	/**
	 * Method to get the name of the testcase.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the name of the testcase.
	 * @param name String
	 */
	public void setName(String name) {
		if( this.name != name ) {
			this.name = name;
			addValuesModified(NAME_MODIFIED);
		}
	}

	/**
	 * Method to get a String with the assertions.
	 * @return String
	 */
	public String getAssertions() {
		return assertions;
	}

	/**
	 * Method to set a String with assertion information.
	 * @param assertions String
	 */
	public void setAssertions(String assertions) {
		if( this.assertions != assertions ) {
			this.assertions = assertions;
			addValuesModified(ASSERTIONS_MODIFIED);
		}
	}

	/**
	 * Method to get the time of the testcase.
	 * @return double
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Method to set the time of the testcase.
	 * @param time double
	 */
	public void setTime(double time) {
		if( this.time != time ) {
			this.time = time;
			addValuesModified(TIME_MODIFIED);
		}
	}

	/**
	 * Method to get a classname.
	 * @return String
	 */
	public String getClassname() {
		return classname;
	}

	/**
	 * Method to set the classname.
	 * @param classname String
	 */
	public void setClassname(String classname) {
		if( this.classname != classname ) {
			this.classname = classname;
			addValuesModified(CLASSNAME_MODIFIED);
		}
	}

	/**
	 * Method to get the status of a testcase.
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Metod to set the status of a testcase.
	 * @param status String
	 */
	public void setStatus(String status) {
		if( this.status != status ) {
			this.status = status;
			addValuesModified(STATUS_MODIFIED);
		}
	}

	@Override
	public int compareTo(TestCase obj2compare) {
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
		String retValue = "TestCase{";
		boolean first = true;
		if( true == isValueModified(NAME_MODIFIED) ) {
			if( first ) {
				first = false;
			}
			retValue += NAME_ATTRIBUTE + ":" + getName();
		} 
		if( true == isValueModified(ASSERTIONS_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += ASSERTIONS_ATTRIBUTE + ":" + getAssertions();
		} 
		if( true == isValueModified(TIME_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += TIME_ATTRIBUTE + ":" + String.format("%.4f", getTime());
		}
		if( true == isValueModified(CLASSNAME_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += CLASSNAME_ATTRIBUTE + ":" + getClassname();
		}
		if( true == isValueModified(STATUS_MODIFIED) ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += STATUS_ATTRIBUTE + ":" + getStatus();
		}
		if( true != getError().isEmpty() ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += "error:" + getError().size();
		}
		if( true != getFailure().isEmpty() ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += "failure:" + getFailure().size();
		}
		if( true != getSystemErr().isEmpty() ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += "system-err:" + getSystemErr().size();
		}
		if( true != getSystemOut().isEmpty() ) {
			if( first ) {
				first = false;
			} else {
				retValue += ",";
			}
			retValue += "system-out:" + getSystemOut().size();
		}
		retValue += "}";
		return retValue;
	}
	
}
