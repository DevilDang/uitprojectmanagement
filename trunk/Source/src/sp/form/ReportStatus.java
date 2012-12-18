package sp.form;

import java.io.Serializable;

public class ReportStatus implements  Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String satus;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the satus
	 */
	public String getSatus() {
		return satus;
	}

	/**
	 * @param satus the satus to set
	 */
	public void setSatus(String satus) {
		this.satus = satus;
	}

}
