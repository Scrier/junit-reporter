package se.scrier.plugin.test.stdd;

import org.apache.log4j.Logger;

public class Run {

	private static Logger log = Logger.getLogger(Run.class);
	
	private String name;
	private String source;
	private String revision;
	private String _id;
	transient private Project project;
	
	/**
	 * Constructor
	 */
	public Run() {
		setName(null);
		setSource(null);
		setRevision(null);
		setID(null);
		setProject(null);
	}
	
	/**
	 * Constructor
	 * @param name String with the name of the Run.
	 */ 
	public Run(String name) {
		setName(name);
		setSource(null);
		setRevision(null);
		setID(null);
		setProject(null);
	}
	
	/**
	 * Constructor
	 * @param name String with the name of the Run.
	 * @param source String with the name of the source (i.e. junit).
	 */
	public Run(String name, String source) {
		setName(name);
		setSource(source);
		setRevision(null);
		setID(null);
		setProject(null);
	}
	
	/**
	 * Constructor
	 * @param name String with the name of the Run.
	 * @param source String with the name of the source (i.e. junit).
	 * @param project Instance of the Project that we are currently reporting to.
	 */
	public Run(String name, String source, Project project) {
		setName(name);
		setSource(source);
		setRevision(null);
		setID(null);
		setProject(project);
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
	 * @return the source
	 */
	public String getSource() {
		log.trace("getSource()");
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		log.trace("setSource(" + source + ")");
		this.source = source;
	}

	/**
	 * @return the revision
	 */
	public String getRevision() {
		log.trace("getRevision()");
		return revision;
	}

	/**
	 * @param revision the revision to set
	 */
	public void setRevision(String revision) {
		log.trace("setRevision(" + revision + ")");
		this.revision = revision;
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
	 * @return the project
	 */
	public Project getProject() {
		log.trace("getProject()");
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		log.trace("setProject(" + project + ")");
		this.project = project;
	}
	
	@Override
	public String toString() {
		return "Run{name:" + getName() + ",source:" + getSource() + ",revision:" + getRevision() + ",_id:" + getID() + "}"; 
	}
}
