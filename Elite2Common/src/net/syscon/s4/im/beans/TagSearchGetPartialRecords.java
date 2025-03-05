package net.syscon.s4.im.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagSearchGetPartialRecords extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("hits")
	private String hits;
	
	@JsonProperty("secondMiddleName")
	private String secondMiddleName;
	
	public TagSearchGetPartialRecords()
	{
		// TagSearchGetPartialRecords
	}

	/**
	 *
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 *
	 * @param lastName
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 *
	 * @return
	 */
	public String getHits() {
		return hits;
	}

	/**
	 *
	 * @param hits
	 */
	public void setHits(final String hits) {
		this.hits = hits;
	}
	
	/**
	 *
	 * @return
	 */
	public String getsecondMiddleName() {
		return secondMiddleName;
	}

	/**
	 *
	 * @param lastName
	 */
	public void setsecondMiddleName(final String secondMiddleName) {
		this.secondMiddleName = secondMiddleName;
	}


}